package com.example.jabakallah.Controllers;

import com.example.jabakallah.models.Livraison;
import com.example.jabakallah.request.LivraisonRequest;
import com.example.jabakallah.service.LivraisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/virement")
public class LivraisonController {

    @Autowired
    private LivraisonService livraisonService;

    @PostMapping("/effectuerVirement")

    public void effectuerVirement(@Valid @RequestBody LivraisonRequest livraisonRequest) throws IOException {

        livraisonService.createVirement( livraisonRequest.getMontant(), livraisonRequest.getRibSrc(), livraisonRequest.getRibDest(), livraisonRequest.getState(), livraisonRequest.getDate(), livraisonRequest.getOwnerphone(), livraisonRequest.getCin() );
    }

    @GetMapping("/listVirement/{ownerphone}")
    public List<Livraison> getVirement(@Valid @PathVariable String ownerphone){
        return  livraisonService.getVirement(ownerphone);
    }

    @GetMapping("/VirementsEnvoyer")
    public List<Livraison> getVirementEnv(@Valid @RequestBody LivraisonRequest livraisonRequest) {

        return livraisonService.getListVirementsEnvoyer(livraisonRequest.getRibSrc());
    }

    @GetMapping("/viremetRecevoir")
    public List<Livraison> getVirementRcv(@Valid @RequestBody LivraisonRequest livraisonRequest){
        return livraisonService.getListVirementsRecevoir(livraisonRequest.getRibDest());
    }

    @GetMapping("/listviremetAgent/{adresse}")
    public List<Livraison> getVirementRcv(@Valid @PathVariable String adresse){
        return livraisonService.getVirementByCity(adresse);
    }

    @PostMapping("/updateState/{ref}/{state}")
    public void updateState(@Valid @PathVariable String ref,@Valid @PathVariable String state) throws IOException {
        livraisonService.updateSatate( ref,state);
    }

    @DeleteMapping("/deleteVirment/{ref}")
    public void deletVirment(@Valid @PathVariable String ref) throws IOException {
        livraisonService.deleteVirment(ref);
    }

}