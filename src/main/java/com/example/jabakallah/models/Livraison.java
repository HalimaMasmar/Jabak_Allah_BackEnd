package com.example.jabakallah.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Livraison {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_virement;
    @Column(name = "montant")
    private String montant;

    private String ribsource;

    private String ownerphone;

    private String ribdest;

    private String state;
    private String CIN;
    private String ref;

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Livraison(String montant, String ribsource, String ribdest, String state, Date date_virement, String ownerphone, String CIN) {
        this.montant = montant;
        this.ribsource = ribsource;
        this.ribdest = ribdest;
        this.state = state;
        this.date_virement = date_virement;
        this.ownerphone= ownerphone;
        this.CIN=CIN;
    }

    @Column(name = "date_virement")
    private Date date_virement;
}
