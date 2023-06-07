package com.example.jabakallah;

import com.example.jabakallah.service.AdminService;
import com.example.jabakallah.service.AgentService;
import com.example.jabakallah.service.FactureService;
import com.example.jabakallah.service.LivraisonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;


@SpringBootApplication
public class JabakallahApplication {

    public static void main(String[] args) {
        SpringApplication.run(JabakallahApplication.class, args);
    }


    @Bean
    CommandLineRunner run(FactureService factureService, AgentService agentService, AdminService adminService, LivraisonService livraisonService) {
        return args -> {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

            Date date = new Date();

           livraisonService.createVirement("Dar","Marrakech","hok", "En attente" ,date,"+212123456789","123456");
            livraisonService.createVirement("Dar","Marrakech","hok1", "En attente" ,date,"+212123456789","123456");
            livraisonService.createVirement("Dar","Marrakech","hok2", "En attente" ,date,"+212123456789","123456");
            adminService.createAgent("Admin","agent1","agent1","CIN",date,"marrakech","ag1ent@mail.com","0612452258","az1212","az1212");

            factureService.createFacture("unpaied", 20.0, "paiment facture electricite", date, "IAMRECHARGE", "+212123456789");
            factureService.createFacture("unpaied", 47.0, "paiment facture electricite", date, "INWIRECHARGE", "+212123456789");
            factureService.createFacture("unpaied", 30.0, "paiment facture eau", date, "ORANGERECHARGE", "+212123456789");
            factureService.createFacture("unpaied", 120.0, "paiment facture eau", date, "LYDEC", "212123456789");
            factureService.createFacture("unpaied", 03.5, "paiment facture eau", date, "AMANDISTANGER", "+212123456789");
            /*factureService.createFacture("unpaied", 40.0, "paiment facture electricite", date, "AMANDISTANGER", "0613085665");
            factureService.createFacture("unpaied", 57.0, "paiment facture abonement IAM", date, "IAMFACTURES", "0613085665");
            factureService.createFacture("unpaied", 43.0, "paiment facture abonement IAM", date, "IAMFACTURES", "0765432345");
            factureService.createFacture("unpaied", 99.0, "paiment facture wifi", date, "IAMFACTURES", "0613085665");
            factureService.createFacture("unpaied", 99.0, "paiment facture wifi", date, "IAMFACTURES", "0765432345");
            factureService.createFacture("unpaied", 99.0, "paiment facture electricite", date, "REDAL", "0613085665");
            factureService.createFacture("unpaied", 20.0, "paiment facture abonement telephone fix", date, "IAMRECHARGE", "0613085665");
            factureService.createFacture("unpaied", 45.0, "paiment facture abonement telephone fix", date, "IAMRECHARGE", "0765432345");
            factureService.createFacture("unpaied", 25.0, "paiment facture abonement telephone fix", date, "IAMRECHARGE", "0613085665");
            factureService.createFacture("unpaied", 27.0, "paiment facture d'eau penalite ", date, "LYDEC", "0613085665");
            factureService.createFacture("unpaied", 200.0, "paiment facture d'eau penalite", date, "REDAL", "0613085665");
*/        };
    }
}

/*           agentService.createUser("agent123","abdelmounim","elmoussaddar","client@mail.com","06465465465");
           adminService.saveAdmin(new Admin("Admin4", "admin4", "admin4@gmail.com"));
    adminService.saveAdmin(new Admin("Admin1234", "admin1234", "admin1234@gmail.com"));
            adminService.createAgent("agent41", "agent4", "CIN", "A123"
                    , new Date(), "marrakech", "agent@mail.com", "0612454558", "az1212",
                    "az1212");
            //adminService.createAgent("Admin","agent1","agent1","CIN","A123"
            //,date,"marrakech","agent@mail.com","0612454558","az1212","az1212");

            //agentService.createUser("D0951653789656527","clienuid","client","client1","client@gmail.com","0646546");

        };
*/

   /* @Bean
    CommandLineRunner run(AgentService agentService) {
        return args -> {

            agentService.createUser("agent2", "abdelmounim", "elmoussaddar", "mounik.moussa@gmail.com", "06465465465");

        };


    }
    */




