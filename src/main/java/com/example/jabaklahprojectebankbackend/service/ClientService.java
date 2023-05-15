package com.example.jabaklahprojectebankbackend.service;

import com.example.jabaklahprojectebankbackend.entity.Client;
import com.example.jabaklahprojectebankbackend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClientService {

    @Autowired
    private ClientRepository Repo;

    public List<Client> listAll() {
        return (List<Client>) Repo.findAll();
    }
}
