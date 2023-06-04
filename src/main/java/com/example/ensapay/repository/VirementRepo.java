package com.example.ensapay.repository;


import com.example.ensapay.models.Virement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VirementRepo extends JpaRepository<Virement, Long> {
    Virement findByRibdest(String rib_dest);
    Virement findByRibsource(String rib_source);
    Virement findByRef(String ref);
    List<Virement> findAllByRibdestOrRibsource(String city,String city2);

}
