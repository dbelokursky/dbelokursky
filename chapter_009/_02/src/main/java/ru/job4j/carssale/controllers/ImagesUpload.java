package ru.job4j.carssale.controllers;

import lombok.extern.log4j.Log4j;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@Log4j
public class ImagesUpload extends HttpServlet {

    private final static String IMG_DIR = "/opt/tomcat/apache-tomcat-9.0.10/img/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/Upload.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
//        Map<String, String> formFields = new HashMap<>();
//        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
//        diskFileItemFactory.setSizeThreshold(1024 * 1024);
//        File tempDir = (File) getServletContext().getAttribute("javax.servlet.context.tempdir");
//        diskFileItemFactory.setRepository(tempDir);
//        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
//        servletFileUpload.setFileSizeMax(1024 * 1024 * 10);
//        System.out.println("START");
//        try {
//            System.out.println("try");
//            List<FileItem> items = servletFileUpload.parseRequest(req);
//            System.out.println(items.size());
//            for (FileItem item : items) {
//                if (item.isFormField()) {
//                    processFormField(item, formFields);
//                } else {
//                    processFileField(item);
//                }
//            }
//
//            Boolean soldStatus = Boolean.parseBoolean(formFields.get("sold"));
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
//            Car car = new Car();
//            car.setBrand(formFields.get("brand"));
//            car.setModel(formFields.get("model"));
//            car.setSuspension(suspension);
//            car.setTransmission(transmission);
//            car.setEngine(engine);
//            car.setSold(soldStatus);
//
//            carsStore.add(car);
//            resp.sendRedirect(String.format("%s/cars", req.getContextPath()));
//
//        } catch (Exception e) {
//            log(e.getMessage(), e);
//        }
    }

    private void processFileField(FileItem item) throws Exception {
        item.write(new File(IMG_DIR + item.getName() + System.currentTimeMillis()));
    }

    private void processFormField(FileItem item, Map<String, String> formFields) {
        formFields.put(item.getName(), item.getString());
    }
}
