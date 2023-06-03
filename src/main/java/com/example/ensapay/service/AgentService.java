package com.example.ensapay.service;

import com.example.ensapay.models.Agent;
import com.example.ensapay.models.Compte;
import com.example.ensapay.models.UserApp;
import com.example.ensapay.repository.AgentRepo;
import com.example.ensapay.repository.CompteRepo;
import com.example.ensapay.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional @Slf4j
public class AgentService {
    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private final AgentRepo agentRepo;

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



    public Boolean createUser(String username,String nom, String prenom, String email, String numTel) throws IOException, MessagingException {

        UserApp user=new UserApp();

        user.setNom(nom);
        user.setUsername(username);
        user.setPrenom(prenom);
        user.setEmail(email);
        user.setNumTel(numTel);

        String pass=this.genererPassword();
        System.out.println(pass);
        log.info("Password for Client generated: "+pass);


        user.setFirstAuth(true);
        user.setPassword(passwordEncoder.encode(pass));
        userRepo.save(user);



        String content = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "  <title>Bienvenue chez ABC Bank</title>\n" +
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
                "      background-color: #34495e;\n" +
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
                "      background-color: #34495e;\n" +
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
                "    <h2>Cher <span id=\"customerName\">"+ nom +"</span>,</h2>\n" +
                "    <p>Merci d'avoir choisi JabkLah pour vos besoins bancaires. Nous sommes ravis de vous accueillir en tant que nouveau client.</p>\n" +
                "    <p>Voici vos informations de connexion :</p>\n" +
                "    <ul>\n" +
                "      <li><strong>Nom d'utilisateur :</strong> <span id=\"customerEmail\">"+ email +"</span></li>\n" +
                "      <li><strong>Mot de passe :</strong> <span id=\"customerPassword\">"+ pass +"</span></li>\n" +
                "    </ul>\n" +
                "    <p>Nous vous encourageons à vous connecter dès maintenant à votre compte en utilisant les informations ci-dessus. Une fois connecté, vous pourrez accéder à toutes les fonctionnalités de notre banque en ligne.</p>\n" +
                "    <p>Si vous avez des questions ou avez besoin d'aide, n'hésitez pas à nous contacter. Notre équipe est là pour vous aider.</p>\n" +
                "    <p>Merci encore de votre confiance. Nous sommes impatients de vous offrir une expérience bancaire exceptionnelle.</p>\n" +
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

    public UserApp getUser(String numTel) {
        log.info("Fetching user by the agent {}", numTel);
        return userRepo.findByNumTel(numTel);
    }

    public List<UserApp> getUsers() {
        log.info("Fetching all users BY agent");
        return userRepo.findAll();
    }

    public void changePassword(String uid, String newPassword) {
        Agent agent = agentRepo.findByUsername(uid);
        String encodedPassword = passwordEncoder.encode(newPassword);
        if(agent.getFirstAuth() == true){ agent.setFirstAuth(false);}
        agent.setPassword(encodedPassword);
        agentRepo.save(agent);
    }

    public Boolean agentHaschangedPassword(String username){
        Agent agent = agentRepo.findByUsername(username);

        if (agent.getFirstAuth()) return false;
        else return true;
    }
}
