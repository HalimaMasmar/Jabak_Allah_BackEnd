package com.example.jabaklahprojectebankbackend.client;

import com.example.jabaklahprojectebankbackend.entity.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {

}
