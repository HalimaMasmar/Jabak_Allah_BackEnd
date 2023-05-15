package com.example.jabaklahprojectebankbackend;


import com.example.jabaklahprojectebankbackend.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@ComponentScan("com.example.jabaklahprojectebankbackend.entity")
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository repo;

    @Test
    public void testCreateClient(){

    }


}
