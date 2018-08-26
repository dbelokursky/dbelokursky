package ru.job4j.carssale.controllers;

import lombok.extern.log4j.Log4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.job4j.carssale.models.*;
import ru.job4j.carssale.repositories.CarRepository;
import ru.job4j.carssale.repositories.OwnerRepository;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j
@Controller
public class CarController {

    private CarRepository carRepository;

    private OwnerRepository ownerRepository;

    private ServletContext context;

    @Autowired
    public void setContext(ServletContext context) {
        this.context = context;
    }

    @Autowired
    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Autowired
    public void setOwnerRepository(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public String showCars(ModelMap model) {
        model.addAttribute("cars", carRepository.findAll());
        return "CarsList";
    }

    @RequestMapping(value = "/carcard", method = RequestMethod.GET, params = "carId")
    public String showCarCardById(@ModelAttribute("carId") Long id, ModelMap model, HttpServletRequest req) {
        String resultPage = "CarCard";
        Car car = carRepository.findById(id).get();
        req.setAttribute("car", car);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Owner owner = ownerRepository.findByLogin(user.getUsername());
            if (car.getOwner() != null && owner.getId() == car.getOwner().getId()) {
                resultPage = "CarCardOwner";
            }
        }
        model.addAttribute("car", car);
        return resultPage;
    }

    @RequestMapping(value = "/carcard", method = RequestMethod.POST)
    public String updateCarCard(HttpServletRequest req, ModelMap modelMap) {
        Long id = java.lang.Long.parseLong(req.getParameter("id"));
        Car car = carRepository.findById(id).get();
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        Transmission transmission = new Transmission();
        transmission.setName(req.getParameter("transmission"));
        Suspension suspension = new Suspension();
        suspension.setName(req.getParameter("suspension"));
        Engine engine = new Engine();
        engine.setName(req.getParameter("engine"));
        boolean status = Boolean.parseBoolean(req.getParameter("sold"));
        car.setBrand(brand);
        car.setModel(model);
        car.setTransmission(transmission);
        car.setSuspension(suspension);
        car.setEngine(engine);
        car.setSold(status);
        carRepository.save(car);
        modelMap.addAttribute("cars", carRepository.findAll());
        return "CarsList";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getCarAddPage() {
        return "CarAdd";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCar(HttpServletRequest req, ModelMap modelMap) {
        Map<String, String> formFields = new HashMap<>();
        List<Image> images = new ArrayList<>();
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setSizeThreshold(1024 * 1024);
        File tempDir = (File) context.getAttribute("javax.servlet.context.tempdir");
        diskFileItemFactory.setRepository(tempDir);
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        servletFileUpload.setFileSizeMax(1024 * 1024 * 10);
        servletFileUpload.setHeaderEncoding("UTF-8");
        Car car = new Car();
        try {
            List<FileItem> items = servletFileUpload.parseRequest(req);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    processFormField(item, formFields);
                } else if (item.getName() != "") {
                    processFileField(item, images);
                }
            }

            Owner owner = (Owner) req.getSession(false).getAttribute("owner");

            Transmission transmission = new Transmission();
            transmission.setName(formFields.get("transmission"));

            Suspension suspension = new Suspension();
            suspension.setName(formFields.get("suspension"));

            Engine engine = new Engine();
            engine.setName(formFields.get("engine"));


            car.setBrand(formFields.get("brand"));
            car.setModel(formFields.get("model"));
            car.setSuspension(suspension);
            car.setTransmission(transmission);
            car.setEngine(engine);
            car.setSold(Boolean.parseBoolean(formFields.get("sold")));
            car.setOwner(owner);
            setCar(images, car);
            car.setImages(images);
            carRepository.save(car);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        modelMap.addAttribute("cars", carRepository.findAll());
        return "CarsList";
    }

    private void setCar(List<Image> images, Car car) {
        for (int i = 0; i < images.size(); i++) {
            images.get(i).setCar(car);
        }
    }

    private void processFileField(FileItem item, List<Image> images) throws Exception {
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
        Owner carOwner = ownerRepository.findAllByLoginAndPassword(owner.getLogin(), owner.getPassword());
        if (carOwner != null) {
            request.getSession().setAttribute("owner", carOwner);
            model.addAttribute("cars", carRepository.findAll());
            resultPage = "CarsList";
        }
        return resultPage;
    }
}
