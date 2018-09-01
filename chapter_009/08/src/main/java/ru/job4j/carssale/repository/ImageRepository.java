package ru.job4j.carssale.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.carssale.domain.Image;

public interface ImageRepository extends CrudRepository<Image, Long> {
}
