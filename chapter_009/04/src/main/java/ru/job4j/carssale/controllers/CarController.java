package ru.job4j.carssale.controllers;

import lombok.extern.log4j.Log4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.job4j.carssale.models.*;
import ru.job4j.carssale.repositories.CarDao;
import ru.job4j.carssale.repositories.OwnerDao;
import ru.job4j.carssale.repositories.SqlCarDao;
import ru.job4j.carssale.repositories.SqlOwnerDao;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Log4j
@Controller
public class CarController {

    @Autowired
    ServletContext context;

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public String showCars(ModelMap model) {
        CarDao carDao = new SqlCarDao();
        model.addAttribute("cars", carDao.getAll());
        return "CarsList";
    }

    @RequestMapping(value = "/carcard", method = RequestMethod.GET, params = "carId")
    public String showCarCardById(@ModelAttribute("carId") Integer id, ModelMap model, HttpServletRequest req) {
        String resultPage = "CarCard";
        CarDao carDao = new SqlCarDao();
        Car car = carDao.getCar(id);
        req.setAttribute("car", car);
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("owner") != null) {
            Owner owner = (Owner) session.getAttribute("owner");
            if (car.getOwner() != null && owner.getId() == car.getOwner().getId()) {
                resultPage = "CarCardOwner";
            }
        }
        model.addAttribute("car", carDao.getCar(id));
        return resultPage;
    }

    @RequestMapping(value = "/carcard", method = RequestMethod.POST)
    public String updateCarCard(HttpServletRequest req, ModelMap modelMap) {
        int id = Integer.parseInt(req.getParameter("id"));
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        Transmission transmission = new Transmission();
        transmission.setName(req.getParameter("transmission"));
        Suspension suspension = new Suspension();
        suspension.setName(req.getParameter("suspension"));
        Engine engine = new Engine();
        engine.setName(req.getParameter("engine"));
        boolean status = Boolean.parseBoolean(req.getParameter("sold"));
        Owner owner = (Owner) req.getSession().getAttribute("owner");
        Car car = new Car();
        car.setId(id);
        car.setBrand(brand);
        car.setModel(model);
        car.setTransmission(transmission);
        car.setSuspension(suspension);
        car.setEngine(engine);
        car.setSold(status);
        car.setOwner(owner);
        CarDao carDao = new SqlCarDao();
        carDao.update(car);
        modelMap.addAttribute("cars", carDao.getAll());
        return "CarsList";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getCarAddPage() {
        return "CarAdd";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCar(HttpServletRequest req, ModelMap modelMap) {
        Map<String, String> formFields = new HashMap<>();
        Set<Image> images = new HashSet<>();
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setSizeThreshold(1024 * 1024);
        File tempDir = (File) context.getAttribute("javax.servlet.context.tempdir");
        diskFileItemFactory.setRepository(tempDir);
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        servletFileUpload.setFileSizeMax(1024 * 1024 * 10);
        servletFileUpload.setHeaderEncoding("UTF-8");
        CarDao carDao = new SqlCarDao();
        try {
            List<FileItem> items = servletFileUpload.parseRequest(req);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    processFormField(item, formFields);
                } else if (item.getName() != "") {
                    processFileField(item, images);
                }
            }

            Transmission transmission = new Transmission();
            transmission.setName(formFields.get("transmission"));

            Suspension suspension = new Suspension();
            suspension.setName(formFields.get("suspension"));

            Engine engine = new Engine();
            engine.setName(formFields.get("engine"));

            Car car = new Car();
            car.setBrand(formFields.get("brand"));
            car.setModel(formFields.get("model"));
            car.setSuspension(suspension);
            car.setTransmission(transmission);
            car.setEngine(engine);
            car.setSold(Boolean.parseBoolean(formFields.get("sold")));
            car.setOwner((Owner) req.getSession(false).getAttribute("owner"));
            carDao.add(car, images);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        modelMap.addAttribute("cars", carDao.getAll());
        return "CarsList";
    }

    private void processFileField(FileItem item, Set<Image> images) throws Exception {
        String fileName = System.currentTimeMillis() + item.getName();
        String uploadDirPath = System.getProperty("catalina.home") + "/webapps/uploads/";
        String filePath = uploadDirPath + fileName;
        Image img = new Image();
        img.setName(fileName);
        img.setPath(filePath);
        images.add(img);
        item.write(new File(filePath));
        makeThumbnail(uploadDirPath, fileName);
    }

    private void makeThumbnail(String uploadDirPath, String fileName) throws IOException {
        BufferedImage thumbnail = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        thumbnail.createGraphics().drawImage(ImageIO.read(new File(uploadDirPath + fileName)).getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
        ImageIO.write(thumbnail, "jpg", new File(uploadDirPath + "thumb_" + fileName));
    }

    private void processFormField(FileItem item, Map<String, String> formFields) {
        try {
            InputStream stream = item.getInputStream();
            formFields.put(item.getFieldName(), Streams.asString(stream, "UTF-8"));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "Login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("owner") Owner owner, HttpServletRequest request, ModelMap model) {
        String resultPage = "Login";
        OwnerDao ownerDao = new SqlOwnerDao();
        Owner carOwner = ownerDao.isExist(owner.getLogin(), owner.getPassword());
        if (carOwner != null) {
            request.getSession().setAttribute("owner", carOwner);
            CarDao carDao = new SqlCarDao();
            model.addAttribute("cars", carDao.getAll());
            resultPage = "CarsList";
        }
        return resultPage;
    }
}
