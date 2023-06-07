package com.example.jabakallah.repository;

import com.example.jabakallah.models.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactureRepo extends JpaRepository<Facture,Long> {
    Facture findByCreancier(String creancier);
    Facture findByRef(String ref);


    List<Facture> findAllByStatus(String status);
}
