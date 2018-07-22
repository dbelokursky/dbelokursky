package ru.job4j.carssale.controllers;

import lombok.extern.log4j.Log4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import ru.job4j.carssale.CarsStore;
import ru.job4j.carssale.models.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Log4j
public class CarAdd extends HttpServlet {

    private final CarsStore carsStore = new CarsStore();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/CarAdd.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, String> formFields = new HashMap<>();
        Set<Image> images = new HashSet<>();
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setSizeThreshold(1024 * 1024);
        File tempDir = (File) getServletContext().getAttribute("javax.servlet.context.tempdir");
        diskFileItemFactory.setRepository(tempDir);
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        servletFileUpload.setFileSizeMax(1024 * 1024 * 10);
        servletFileUpload.setHeaderEncoding("UTF-8");
        try {
            List<FileItem> items = servletFileUpload.parseRequest(req);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    processFormField(item, formFields);
                } else {
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
            carsStore.add(car, images);
            resp.sendRedirect(String.format("%s/cars", req.getContextPath()));
        } catch (Exception e) {
            log(e.getMessage(), e);
        }
    }

    private void processFileField(FileItem item, Set<Image> images) throws Exception {
        String fileName = System.currentTimeMillis() + item.getName();
        String uploadDirPath = new File(getServletContext().getRealPath("/")) + "/../uploads/";
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
}
