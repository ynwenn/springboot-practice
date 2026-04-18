package com.example.demo.service;

import com.example.demo.entity.Name;
import com.example.demo.repository.NameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelloService {

    private final NameRepository nameRepository;

    public HelloService(NameRepository nameRepository) {
        this.nameRepository = nameRepository;
    }

    public void addName(String name) {
        Name nameEntity = new Name(name);
        nameRepository.save(nameEntity);
    }

    public List<Name> getNames() {
        return nameRepository.findAll();
    }

    public void deleteName(Long id) {
        nameRepository.deleteById(id);
    }

    public Name findById(Long id) {
        return nameRepository.findById(id).orElse(null);
    }

    public void updateName(Long id, String name) {
        Name nameEntity = nameRepository.findById(id).orElse(null);
        if (nameEntity != null) {
            nameEntity.setName(name);
            nameRepository.save(nameEntity);
        }
    }
}
