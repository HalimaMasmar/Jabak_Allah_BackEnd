package com.example.jabakallah.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_facture;
    @Column(name = "ref")
    private String ref;
    private String status;
    private double montant_fac;
    private String description;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date_emission;
    @DateTimeFormat(pattern = "dd-MM-yyyy")

    private Date date_payement;
    private String creancier;
    private String ownerphone;

    public Facture(String status, double montant_fac, String description, Date date_emission, String creancier, String ownerphone) {

        this.status = status;
        this.montant_fac = montant_fac;
        this.description = description;
        this.date_emission = date_emission;

        this.creancier = creancier;
        this.ownerphone = ownerphone;
    }
}
