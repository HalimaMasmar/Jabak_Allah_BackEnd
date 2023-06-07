package com.example.jabakallah.request;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class CreateFactureRequest {


    private String creancier;

    private String ownerphone;

    private String status;

    private double montant_fac;

    private String description;

    private Date date_emission;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getMontant_fac() {
        return montant_fac;
    }

    public void setMontant_fac(double montant_fac) {
        this.montant_fac = montant_fac;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_emission() {
        return date_emission;
    }

    public void setDate_emission(Date date_emission) {
        this.date_emission = date_emission;
    }

    public String getCreancier() {
        return creancier;
    }

    public void setCreancier(String creancier) {
        this.creancier = creancier;
    }

    public String getOwnerphone() {
        return ownerphone;
    }

    public void setOwnerphone(String ownerphone) {
        this.ownerphone = ownerphone;
    }
}
