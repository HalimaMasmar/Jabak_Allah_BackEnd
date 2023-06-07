package com.example.jabakallah.service;

import com.example.jabakallah.models.Agent;
import com.example.jabakallah.models.Compte;
import com.example.jabakallah.models.Livraison;
import com.example.jabakallah.repository.AgentRepo;
import com.example.jabakallah.repository.CompteRepo;
import com.example.jabakallah.repository.LivraisonRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LivraisonService {
    @Autowired
    LivraisonService livraisonService;

    @Autowired
    LivraisonRepo livraisonRepo;

    @Autowired
    AgentRepo agentRepo;
    @Autowired
    CompteRepo compteRepo;
    final String letterLower = "abcdefghijklmnopqrstuvwxyz";
    final String letterUpper= letterLower.toUpperCase();
    final String number = "0123456789";
    public boolean effectuerVirement(String ribDest,String ribSrc,Double montant){
        Compte compteDest = compteRepo.findByRib(ribDest);
        Compte compteSrc = compteRepo.findByRib(ribSrc);
        Double soldeDest = compteDest.getSolde();
        Double soldeSrc = compteSrc.getSolde();
        if (montant <= soldeSrc){
            compteSrc.setSolde(soldeSrc-montant);
            compteDest.setSolde(soldeDest+montant);
            compteRepo.save(compteDest);
            compteRepo.save(compteSrc);
            return true;
        }
        else return false;

    }
    public List<Livraison> getListVirementsEnvoyer(String rib){
        List<Livraison> livraisons = new ArrayList<>();
        for (Livraison vrm : livraisonRepo.findAll()){
            if (vrm.getRibsource().equals(rib)){
                livraisons.add(vrm);
            }
        }
        return livraisons;

    }
    public List<Livraison> getListVirementsRecevoir(String rib){
        List<Livraison> livraisons = new ArrayList<>();
        for (Livraison vrm : livraisonRepo.findAll()){
            if (vrm.getRibdest().equals(rib)){
                livraisons.add(vrm);
            }
        }
        return livraisons;

    }

    public List<Livraison> getVirement(String ownerphone){
        List<Livraison> livraisons = new ArrayList<>();
        for (Livraison fct : livraisonRepo.findAll()){
            if (fct.getOwnerphone().equals(ownerphone)){

                livraisons.add(fct);


            }
        }
        return livraisons;
    }

    public List<Livraison> getVirementByCity(String username){
        Agent agent = agentRepo.findByUsername(username);
        String city = agent.getAdresse();
        return livraisonRepo.findAllByRibdestOrRibsource(city,city);
    }

    public String genererRef() {
        Long dateoftoday =  System.currentTimeMillis();
        String dateoftodayinms = dateoftoday.toString();

        SecureRandom random = new SecureRandom();
        String ref="";

        ref+=letterUpper.charAt(random.nextInt(letterUpper.length()));


        for(int i=0;i<3;i++) {
            ref+=number.charAt(random.nextInt(number.length()));
        }
        ref+=dateoftodayinms;

        log.info("ref of facture: "+ref);
        return ref;
    }

    public void createVirement(String montant,String ribSrc, String ribDest, String state, Date date_virement,String ownerphone,String cin) throws IOException {
        Livraison livraison = new Livraison( montant, ribSrc,ribDest,  state, date_virement,ownerphone,cin);
        livraison.setRef(genererRef());
        livraisonRepo.save(livraison);
    }

    public void updateSatate (String ref,String state){
        Livraison livraison = livraisonRepo.findByRef(ref);
        livraison.setState(state);
        livraisonRepo.save(livraison);
    }

    public void deleteVirment (String ref){
        Livraison livraison = livraisonRepo.findByRef(ref);
        livraisonRepo.delete(livraison);
    }

}
