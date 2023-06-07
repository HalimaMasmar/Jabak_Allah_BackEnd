package com.example.jabakallah.request;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class LivraisonRequest {
    @NotBlank
    private String ribSrc;

    @NotBlank
    private String ribDest;
    @NotBlank
    private String montant;

    private Date date;

    private String ownerphone;
    private String cin;

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getOwnerphone() {
        return ownerphone;
    }

    public void setOwnerphone(String ownerphone) {
        this.ownerphone = ownerphone;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRibSrc() {
        return ribSrc;
    }

    public void setRibSrc(String ribSrc) {
        this.ribSrc = ribSrc;
    }

    public String getRibDest() {
        return ribDest;
    }

    public void setRibDest(String ribDest) {
        this.ribDest = ribDest;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }
}

