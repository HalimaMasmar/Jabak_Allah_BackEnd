package com.example.jabakallah.Controllers;


import com.example.jabakallah.models.Facture;
import com.example.jabakallah.request.CreateFactureRequest;
import com.example.jabakallah.request.FactureRequest;
import com.example.jabakallah.request.RechargeRequest;
import com.example.jabakallah.request.SignupRequestClient;
import com.example.jabakallah.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/facture")
public class FactureController {
    @Autowired
    private FactureService factureService;

    @PostMapping("/payerFacture")

    public Boolean payerFacture(@Valid @RequestBody FactureRequest factureRequest) throws IOException {
        factureService.PayerFacture(factureRequest.getRef(),factureRequest.getOwnerphone());
        return true;
    }

    @PostMapping("/payerRecharge")

    public Boolean payerRecharge(@Valid @RequestBody RechargeRequest rechargeRequest) throws IOException {
        factureService.PayerRecharge(rechargeRequest.getValue(),rechargeRequest.getOwnerphone());
        return true;
    }

    @PostMapping("/listFacturecreancier")

    public ResponseEntity<List<Facture>>  getFacturesCreancier(@Valid @RequestBody FactureRequest factureRequest) {

        return ResponseEntity.ok().body(factureService.getListFacturesOfCreancier(factureRequest.getCreancier(),factureRequest.getOwnerphone()));

    }

    @GetMapping("/listFacturecreancier/{creancier}/{ownerphone}")

    public ResponseEntity<List<Facture>>  getFacturesCreancier2(@Valid @PathVariable String creancier,@PathVariable String ownerphone) {

        return ResponseEntity.ok().body(factureService.getListFacturesOfCreancier(creancier, ownerphone));

    }
    @GetMapping("/listFacture")
    @PreAuthorize("hasRole('CLIENT') ")
    public List<Facture> getFactureList(@Valid @RequestBody FactureRequest factureRequest){
        return factureService.getListFactures(factureRequest.getOwnerphone());
    }

    @GetMapping("/listFacturePaid/{ownerphone}")
    public List<Facture> getFactureListPaid(@Valid @PathVariable String ownerphone){
        return factureService.getListFacturePaid(ownerphone);
    }

    @PostMapping("/addFacture")
    public ResponseEntity<?> registerFacture(@Valid @RequestBody CreateFactureRequest createFactureRequest) throws IOException {

        factureService.createFacture(createFactureRequest.getStatus(),createFactureRequest.getMontant_fac(),createFactureRequest.getDescription(),
                createFactureRequest.getDate_emission(),createFactureRequest.getCreancier(),createFactureRequest.getOwnerphone());

        return ResponseEntity.ok("User registered successfully!");
    }


}
