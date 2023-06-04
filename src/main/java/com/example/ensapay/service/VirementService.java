package com.example.ensapay.service;

import com.example.ensapay.models.Agent;
import com.example.ensapay.models.Compte;
import com.example.ensapay.models.Facture;
import com.example.ensapay.models.Virement;
import com.example.ensapay.repository.AgentRepo;
import com.example.ensapay.repository.CompteRepo;
import com.example.ensapay.repository.VirementRepo;
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
public class VirementService {
    @Autowired
    VirementService virementService;

    @Autowired
    VirementRepo virementRepo;

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
    public List<Virement> getListVirementsEnvoyer(String rib){
        List<Virement> virements = new ArrayList<>();
        for (Virement vrm : virementRepo.findAll()){
            if (vrm.getRibsource().equals(rib)){
                virements.add(vrm);
            }
        }
        return virements;

    }
    public List<Virement> getListVirementsRecevoir(String rib){
        List<Virement> virements = new ArrayList<>();
        for (Virement vrm : virementRepo.findAll()){
            if (vrm.getRibdest().equals(rib)){
                virements.add(vrm);
            }
        }
        return virements;

    }

    public List<Virement> getVirement(String ownerphone){
        List<Virement> virements = new ArrayList<>();
        for (Virement fct : virementRepo.findAll()){
            if (fct.getOwnerphone().equals(ownerphone)){

                virements.add(fct);


            }
        }
        return virements;
    }

    public List<Virement> getVirementByCity(String username){
        Agent agent = agentRepo.findByUsername(username);
        String city = agent.getAdresse();
        return virementRepo.findAllByRibdestOrRibsource(city,city);
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
        Virement virement = new Virement( montant, ribSrc,ribDest,  state, date_virement,ownerphone,cin);
        virement.setRef(genererRef());
        virementRepo.save(virement);
    }

    public void updateSatate (String ref,String state){
        Virement virement = virementRepo.findByRef(ref);
        virement.setState(state);
        virementRepo.save(virement);
    }

    public void deleteVirment (String ref){
        Virement virement = virementRepo.findByRef(ref);
        virementRepo.delete(virement);
    }

}
