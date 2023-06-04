package com.example.ensapay.Controllers;

import com.example.ensapay.models.Facture;
import com.example.ensapay.models.Virement;
import com.example.ensapay.request.VirementRequest;
import com.example.ensapay.service.VirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/virement")
public class VirementController {

    @Autowired
    private VirementService virementService;

    @PostMapping("/effectuerVirement")

    public void effectuerVirement(@Valid @RequestBody VirementRequest virementRequest) throws IOException {

        virementService.createVirement( virementRequest.getMontant(),virementRequest.getRibSrc(),virementRequest.getRibDest(),virementRequest.getState(), virementRequest.getDate(),virementRequest.getOwnerphone(),virementRequest.getCin() );
    }

    @GetMapping("/listVirement/{ownerphone}")
    public List<Virement> getVirement(@Valid @PathVariable String ownerphone){
        return  virementService.getVirement(ownerphone);
    }

    @GetMapping("/VirementsEnvoyer")
    public List<Virement> getVirementEnv(@Valid @RequestBody VirementRequest virementRequest) {

        return virementService.getListVirementsEnvoyer(virementRequest.getRibSrc());
    }

    @GetMapping("/viremetRecevoir")
    public List<Virement> getVirementRcv(@Valid @RequestBody VirementRequest virementRequest){
        return virementService.getListVirementsRecevoir(virementRequest.getRibDest());
    }

    @GetMapping("/listviremetAgent/{adresse}")
    public List<Virement> getVirementRcv(@Valid @PathVariable String adresse){
        return virementService.getVirementByCity(adresse);
    }

    @PostMapping("/updateState/{ref}/{state}")
    public void updateState(@Valid @PathVariable String ref,@Valid @PathVariable String state) throws IOException {
        virementService.updateSatate( ref,state);
    }

    @DeleteMapping("/deleteVirment/{ref}")
    public void deletVirment(@Valid @PathVariable String ref) throws IOException {
        virementService.deleteVirment(ref);
    }

}