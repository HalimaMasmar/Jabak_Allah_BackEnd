package com.example.jabakallah.repository;

import com.example.jabakallah.models.Compte;
import com.example.jabakallah.models.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompteRepo extends JpaRepository<Compte, Long> {
    Compte findByRib(String rib);
}