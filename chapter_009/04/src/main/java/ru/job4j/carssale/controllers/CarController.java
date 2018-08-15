package ru.job4j.carssale.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.carssale.CarsStore;

@Controller
public class CarController {

    @RequestMapping("/cars")
    public String carsList(Model model) {
        CarsStore carsStore = new CarsStore();
        model.addAttribute("cars", carsStore.getAll());
        return "CarsList";
    }
}
