package ru.job4j.carssale.controllers;

import lombok.extern.log4j.Log4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Log4j
public class ImagesUpload extends HttpServlet {

    private final static String IMG_DIR = "/opt/tomcat/apache-tomcat-9.0.10/img/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/Upload.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setSizeThreshold(1024 * 1024);
        File tempDir = (File) getServletContext().getAttribute("javax.servlet.context.tempdir");
        diskFileItemFactory.setRepository(tempDir);
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        servletFileUpload.setFileSizeMax(1024 * 1024 * 10);
        System.out.println("START");
        try {
            System.out.println("try");
            List<FileItem> images = servletFileUpload.parseRequest(req);
            System.out.println(images.size());
            for (FileItem img : images) {
                if (!img.isFormField()) {
                    img.write(new File(IMG_DIR + img.getName() + System.currentTimeMillis()));
                }
            }
        } catch (Exception e) {
            log(e.getMessage(), e);
        }
    }
}
