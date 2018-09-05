package ru.job4j.carssale.controller;

import lombok.extern.log4j.Log4j;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.carssale.domain.Car;
import ru.job4j.carssale.domain.Image;
import ru.job4j.carssale.domain.Owner;
import ru.job4j.carssale.service.CarService;
import ru.job4j.carssale.service.ImageService;
import ru.job4j.carssale.service.OwnerService;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Log4j
@Controller
public class CarController {

    private final CarService carService;

    private final OwnerService ownerService;

    private final ImageService imageService;

    private ServletContext context;

    @Value("${upload.path}")
    private String uploadDirPath;

    @Autowired
    public CarController(CarService carService, OwnerService ownerService, ImageService imageService) {
        this.carService = carService;
        this.ownerService = ownerService;
        this.imageService = imageService;
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
    public String showCarCardById(@ModelAttribute("carId") Long id, ModelMap modelMap) {
        String resultPage = "carCard";
        Car car = carService.findById(id);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            Owner owner = ownerService.findByLogin(auth.getName());
            if (car.getOwner() != null && owner.getId() == car.getOwner().getId()) {
                resultPage = "carCardOwner";
            }
        }
        modelMap.addAttribute("car", car);
        return resultPage;
    }

    @PostMapping("/carcard")
    public String updateCarCard(@ModelAttribute Car car) {
        car.setOwner(ownerService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()));
        carService.save(car);
        return "redirect:/cars";
    }

    @GetMapping("/add")
    public String getCarAddPage(Model model) {
        model.addAttribute("car", new Car());
        return "carAdd";
    }

    @PostMapping("/add")
    public String addCar(@ModelAttribute Car car, @RequestParam MultipartFile[] files) {
        List<Image> images = new ArrayList<>();
        try {
            if (files != null && files.length > 0) {
                for (MultipartFile multipartFile : files) {
                    String imageName = String.format("%3.0f%s", Math.random() * 1000, multipartFile.getOriginalFilename());
                    String imagePath = uploadDirPath + imageName;
                    String thumbnailPath = uploadDirPath + "thumb_" + imageName;
                    Files.copy(multipartFile.getInputStream(), Paths.get(imagePath));
                    BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
                    BufferedImage thumbnail = Scalr.resize(bufferedImage,
                            Scalr.Method.QUALITY,
                            Scalr.Mode.AUTOMATIC,
                            100,
                            Scalr.OP_ANTIALIAS);

                    String fileExtension = imageName.trim().toLowerCase().substring(imageName.lastIndexOf(".") + 1);
                    ImageIO.write(thumbnail, fileExtension, new File(thumbnailPath));
                    Image image = new Image();
                    image.setName(imageName);
                    image.setPath(imagePath);
                    image.setCar(car);
                    images.add(image);
                    imageService.save(image);
                }
            }
            car.setImages(images);
            car.setOwner(ownerService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()));
            carService.save(car);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return "redirect:/cars";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
}
