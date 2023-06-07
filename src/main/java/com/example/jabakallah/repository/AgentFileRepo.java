package com.example.jabakallah.repository;


import com.example.jabakallah.models.AgentFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentFileRepo extends JpaRepository<AgentFile,Long> {
    AgentFile findByName(String name);

}
