package ru.job4j.carssale.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import ru.job4j.carssale.dao.CarDao;
import ru.job4j.carssale.dao.SqlCarDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CarController extends AbstractController {

    CarDao sqlCarDao = new SqlCarDao();

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        ModelAndView model = new ModelAndView("CarsList");
        model.addObject("cars", sqlCarDao.getAll());
        return model;
    }
}
