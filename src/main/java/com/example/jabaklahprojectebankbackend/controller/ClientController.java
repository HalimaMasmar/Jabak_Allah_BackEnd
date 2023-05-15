package com.example.jabaklahprojectebankbackend.controller;

import com.example.jabaklahprojectebankbackend.service.ClientService;
import com.example.jabaklahprojectebankbackend.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping("/clients")
    public List<Client> listFirstPAge() {

        return service.listAll();
    }
}
