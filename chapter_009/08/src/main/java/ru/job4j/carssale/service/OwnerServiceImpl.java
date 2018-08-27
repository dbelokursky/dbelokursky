package ru.job4j.carssale.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.carssale.domain.Owner;
import ru.job4j.carssale.repository.OwnerRepository;

import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public List<Owner> findAll() {
        return Lists.newArrayList(ownerRepository.findAll());
    }

    @Override
    public Owner findAllByLoginAndPassword(String login, String password) {
        return ownerRepository.findAllByLoginAndPassword(login, password);
    }

    @Override
    public Owner findByLogin(String login) {
        return ownerRepository.findByLogin(login);
    }
}
