package com.example.jabaklahprojectebankbackend;

import com.example.jabaklahprojectebankbackend.client.ClientRepository;
import com.example.jabaklahprojectebankbackend.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JabakLahProjectEBankBackEndApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JabakLahProjectEBankBackEndApplication.class, args);
    }

    @Autowired
    private ClientRepository repo ;

    @Override
    public void run(String... args) throws Exception {

    }
}
