package ru.job4j.carssale.controllers;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.carssale.domain.*;
import ru.job4j.carssale.service.CarService;
import ru.job4j.carssale.service.OwnerService;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Log4j
@Controller
public class CarController {

    private final CarService carService;

    private final OwnerService ownerService;

    private ServletContext context;

    @Value("${upload.path}")
    private String uploadDirPath;

    @Autowired
    public CarController(CarService carService, OwnerService ownerService) {
        this.carService = carService;
        this.ownerService = ownerService;
    }

    @Autowired
    public void setContext(ServletContext context) {
        this.context = context;
    }

    @GetMapping("/cars")
    public String showCars(ModelMap model) {
        model.addAttribute("cars", carService.findAll());
        return "carsList";
    }

    @GetMapping("/carcard")
    public String showCarCardById(@ModelAttribute("carId") Long id, ModelMap model, HttpServletRequest req) {
        String resultPage = "carCard";
        Car car = carService.findById(id);
        req.setAttribute("car", car);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Owner owner = ownerService.findByLogin(user.getUsername());
            if (car.getOwner() != null && owner.getId() == car.getOwner().getId()) {
                resultPage = "carCardOwner";
            }
        }
        model.addAttribute("car", car);
        return resultPage;
    }

    @PostMapping("/carcard")
    public String updateCarCard(HttpServletRequest req, ModelMap modelMap) {
        Long id = Long.parseLong(req.getParameter("id"));
        Car car = carService.findById(id);
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
        carService.save(car);
        modelMap.addAttribute("cars", carService.findAll());
        return "carsList";
    }

    @GetMapping("/add")
    public String getCarAddPage(Model model) {
        model.addAttribute("car", new Car());
        return "carAdd";
    }

    @PostMapping("/add")
    public String addCar(@ModelAttribute Car car, @RequestParam MultipartFile file, ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        Owner owner = ownerService.findByLogin(authentication.getName());
        System.out.println(owner.getLogin());
        System.out.println(owner.getPassword());
        List<Image> images = new ArrayList<>();
        if (!file.isEmpty()) {
            String fileName = String.format("%3.0f%s", Math.random() * 1000, file.getOriginalFilename());
            try {
                InputStream inputStream = file.getInputStream();
                Files.copy(inputStream, Paths.get(uploadDirPath + fileName));
                Image image = new Image();
                image.setName(fileName);
                image.setCar(car);
                images.add(image);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
        car.setOwner(owner);
        carService.save(car);
//        Map<String, String> formFields = new HashMap<>();
//        List<Image> images = new ArrayList<>();
//        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
//        diskFileItemFactory.setSizeThreshold(1024 * 1024);
//        File tempDir = (File) context.getAttribute("javax.servlet.context.tempdir");
//        diskFileItemFactory.setRepository(tempDir);
//        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
//        servletFileUpload.setFileSizeMax(1024 * 1024 * 10);
//        servletFileUpload.setHeaderEncoding("UTF-8");
//        Car car = new Car();
//        try {
//            List<FileItem> items = servletFileUpload.parseRequest(req);
//            for (FileItem item : items) {
//                if (item.isFormField()) {
//                    processFormField(item, formFields);
//                } else if (item.getName() != "") {
//                    processFileField(item, images);
//                }
//            }
//
//            Owner owner = (Owner) req.getSession(false).getAttribute("owner");
//
//            Transmission transmission = new Transmission();
//            transmission.setName(formFields.get("transmission"));
//
//            Suspension suspension = new Suspension();
//            suspension.setName(formFields.get("suspension"));
//
//            Engine engine = new Engine();
//            engine.setName(formFields.get("engine"));
//
//
//            car.setBrand(formFields.get("brand"));
//            car.setModel(formFields.get("model"));
//            car.setSuspension(suspension);
//            car.setTransmission(transmission);
//            car.setEngine(engine);
//            car.setSold(Boolean.parseBoolean(formFields.get("sold")));
//            car.setOwner(owner);
//            setCar(images, car);
//            car.setImages(images);
//            carService.save(car);
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }
        modelMap.addAttribute("cars", carService.findAll());
        return "carsList";
    }

    private void setCar(List<Image> images, Car car) {
        for (int i = 0; i < images.size(); i++) {
            images.get(i).setCar(car);
        }
    }

    private void makeThumbnail(String uploadDirPath, String fileName) throws IOException {
        BufferedImage thumbnail = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        thumbnail.createGraphics().drawImage(ImageIO.read(new File(uploadDirPath + fileName)).getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
        ImageIO.write(thumbnail, "jpg", new File(uploadDirPath + "thumb_" + fileName));
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("owner") Owner owner, HttpServletRequest req, ModelMap model) {
        String resultPage = "login";
        Owner carOwner = ownerService.findAllByLoginAndPassword(owner.getLogin(), owner.getPassword());
        if (carOwner != null) {
            req.getSession().setAttribute("owner", carOwner);
            model.addAttribute("cars", carService.findAll());
            resultPage = "carsList";
        }
        return resultPage;
    }
}
