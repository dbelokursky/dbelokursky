package ru.job4j.carssale.controllers;

import lombok.extern.log4j.Log4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

@Log4j
public class ImagesUpload extends HttpServlet {

    private final String filePath = "/tmp/";

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
                    System.out.println("-----------file-----------");
                    img.write(new File("/tmp/" + System.currentTimeMillis()));
                }
            }
        } catch (Exception e) {
            log(e.getMessage(), e);
        }
    }
}
