package ru.job4j.carssale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.carssale.domain.Image;
import ru.job4j.carssale.repository.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public void save(Image image) {
        imageRepository.save(image);
    }
}
