package com.example.jabakallah.service;

import com.example.jabakallah.models.Admin;
import com.example.jabakallah.models.Agent;
import com.example.jabakallah.models.AgentFile;
import com.example.jabakallah.models.UserApp;
import com.example.jabakallah.repository.AdminRepo;
import com.example.jabakallah.repository.AgentFileRepo;
import com.example.jabakallah.repository.AgentRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AdminService {

    @Autowired
    private final AgentRepo agentRepo;
    @Autowired
    private final AdminRepo adminRepo;
    @Autowired
    private final AgentFileRepo agentFileRepo;

    @Autowired
    private final MailService emailService;
    private final PasswordEncoder passwordEncoder;

    final String letterLower = "abcdefghijklmnopqrstuvwxyz";
    final String letterUpper= letterLower.toUpperCase();
    final String number = "0123456789";
    final String caractereSpeciaux = "!@#$%&*_?':,;~°^";
    final String passworwdCombinaison= letterLower+ letterUpper + number + caractereSpeciaux;
    public String genererPassword() {


        SecureRandom random = new SecureRandom();
        String password="";

        password+=letterLower.charAt(random.nextInt(letterLower.length()));
        password+=letterUpper.charAt(random.nextInt(letterUpper.length()));
        password+=number.charAt(random.nextInt(number.length()));
        password+=caractereSpeciaux.charAt(random.nextInt(caractereSpeciaux.length()));

        for(int i=0;i<5;i++) {
            password+=passworwdCombinaison.charAt(random.nextInt(passworwdCombinaison.length()));
        }

        return password;
    }
    public String genererUid(String email) {
        Long dateoftoday =  System.currentTimeMillis();
        String dateoftodayinms = dateoftoday.toString();

        SecureRandom random = new SecureRandom();
        String uid="";

        uid+=letterUpper.charAt(random.nextInt(letterUpper.length()));

        for(int i=0;i<3;i++) {
            uid+=number.charAt(random.nextInt(number.length()));
        }
        uid+=dateoftodayinms;

        log.info("UID of agent genarated: "+uid);
        return uid;
    }
    public Boolean createAgent(String nom, String prenom, String pieceIdentite,
                               String numPieceIdentite, Date dateNaissance, String adresse, String email,
                               String numTel, String numMatriculation,
                               String numPattente) throws IOException, MessagingException {

        Agent agent=new Agent();

        agent.setNom(nom);
        agent.setPrenom(prenom);
        agent.setPieceIdentite(pieceIdentite);
        agent.setNumPieceIdentite(numPieceIdentite);
        agent.setDateNaissance(dateNaissance);
        agent.setAdresse(adresse);
        agent.setEmail(email);
        agent.setNumTel(numTel);
        agent.setNumMatriculation(numMatriculation);
        agent.setNumPattente(numPattente);
        String uid = this.genererUid(email);
        agent.setUsername(uid);
        agent.setUsersApp(new ArrayList<>());


        String pass=this.genererPassword();
        System.out.println("Password of agent "+pass);
        log.info("Password of agent "+pass);
        //String body = "Bonjour Monsieur / Madame . \n  \n Votre mot de passe est " + pass + ". \n Bienvenue chez nous";
        //this.smtpMailSender.sendMail(agent.getUsername(), "Your Password", body);

        agent.setFirstAuth(true);
        //  smtpMailSender.sendMail(agent.getUsername(), "Your Password", body);
        agent.setPassword(passwordEncoder.encode(pass));
        agentRepo.save(agent);


        String content  = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "  <title>Bienvenue chez Jabakallah E-Bank</title>\n" +
                "  <style>\n" +
                "    /* Styles pour le corps de l'e-mail */\n" +
                "    body {\n" +
                "      font-family: Arial, sans-serif;\n" +
                "      background-color: #f9f9f9;\n" +
                "      margin: 0;\n" +
                "      padding: 0;\n" +
                "    }\n" +
                "    \n" +
                "    /* Styles pour l'en-tête */\n" +
                "    .header {\n" +
                "      background-color: #f36806;\n" +
                "      color: #ffffff;\n" +
                "      padding: 20px;\n" +
                "      text-align: center;\n" +
                "    }\n" +
                "    \n" +
                "    .header h1 {\n" +
                "      margin: 0;\n" +
                "      font-size: 24px;\n" +
                "    }\n" +
                "    \n" +
                "    /* Styles pour le contenu */\n" +
                "    .content {\n" +
                "      padding: 20px;\n" +
                "    }\n" +
                "    \n" +
                "    .content h2 {\n" +
                "      font-size: 20px;\n" +
                "      margin-top: 0;\n" +
                "    }\n" +
                "    \n" +
                "    .content p {\n" +
                "      margin-bottom: 20px;\n" +
                "    }\n" +
                "    \n" +
                "    .content .highlight {\n" +
                "      background-color: #3498db;\n" +
                "      color: #ffffff;\n" +
                "      padding: 5px 10px;\n" +
                "      border-radius: 5px;\n" +
                "      display: inline-block;\n" +
                "    }\n" +
                "    \n" +
                "    /* Styles pour le pied de page */\n" +
                "    .footer {\n" +
                "      background-color:  #f36806;\n" +
                "      color: #ffffff;\n" +
                "      padding: 20px;\n" +
                "      text-align: center;\n" +
                "    }\n" +
                "    \n" +
                "    .footer p {\n" +
                "      margin: 0;\n" +
                "      font-size: 14px;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <div class=\"header\">\n" +
                "    <h1>Bienvenue chez JabkLah</h1>\n" +
                "  </div>\n" +
                "  <div class=\"content\">\n" +
                "    <h2>Cher <span id=\"customerName\">"+ nom +" "+prenom+"</span>,</h2>\n" +
                "    <p>Nous sommes ravis de t'accueillir dans notre équipe ! </p>\n" +
                "    <p>Voici vos informations de connexion :</p>\n" +
                "    <ul>\n" +
                "      <li><strong>Nom d'utilisateur :</strong> <span id=\"customerEmail\">" +uid+"</span></li>\n" +
                "      <li><strong>Mot de passe :</strong> <span id=\"customerPassword\">"+ pass +"</span></li>\n" +
                "    </ul>\n" +
                "    <p>" +
                " Ton expertise et tes compétences vont certainement contribuer à notre succès. Nous croyons fermement que tu vas apporter une valeur ajoutée significative à notre Agence Jabakallah</p>\n" +
                "    <p>Si vous avez des questions ou avez besoin d'aide, n'hésitez pas à nous contacter. Notre équipe est là pour vous aider.</p>\n" +
                "    <p>Cordialement,<br>L'équipe JabkLah</p>\n" +
                "  </div>\n" +
                "  <div class=\"footer\">\n" +
                "    <p>Ce message a été envoyé automatiquement. Veuillez ne pas y répondre.</p>\n" +
                "  </div>\n" +
                "</body>\n" +
                "</html>\n";
        emailService.sendEmail(email,content);
        return  true;
    }


    public Boolean addFileToAgent(String agentUid,String fileName) throws IOException{
        Agent agent = agentRepo.findByUsername(agentUid);
        AgentFile file = agentFileRepo.findByName(fileName);
        agent.getAgentFiles().add(file);
        agentRepo.save(agent);

        return true;
    }


    public Agent getAgent(String uid) {
        log.info("Fetching agent by the admin {}", uid);
        return agentRepo.findByUsername(uid);
    }

    public Admin getAdmin(String uid) {
        log.info("Fetching admin by uid {}", uid);
        return adminRepo.findByUsername(uid);
    }

    public List<Agent> getAgents() {
        log.info("Fetching all agents by admin");
        return  agentRepo.findAll();
    }

    public List<Admin> getAdmins() {
        log.info("Fetching all Admins by admin");

        return adminRepo.findAll();
    }

    public Admin saveAdmin(Admin admin) {
        log.info("Saving new admin {} to the database", admin.getUsername());
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepo.save(admin);
    }

    public Admin checkAdminExists(String uid){
        Admin admin1 = adminRepo.findByUsername(uid);

        if (admin1 != null) return admin1;
        else return null;
    }
}
