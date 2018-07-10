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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
        System.out.println(getServletContext().getRealPath("img"));
        try {
            List<FileItem> images = servletFileUpload.parseRequest(req);
            for (FileItem img : images) {
                System.out.println("-----------file-----------");
                System.out.println(getServletContext().getRealPath("/") + img.getName() + System.currentTimeMillis());
                img.write(new File(getServletContext().getRealPath("img") + "/" + img.getName()));
            }
        } catch (Exception e) {
            log(e.getMessage(), e);
        }
    }
}
