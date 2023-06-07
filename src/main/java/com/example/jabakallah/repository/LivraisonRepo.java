package com.example.jabakallah.repository;


import com.example.jabakallah.models.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivraisonRepo extends JpaRepository<Livraison, Long> {
    Livraison findByRibdest(String rib_dest);
    Livraison findByRibsource(String rib_source);
    Livraison findByRef(String ref);
    List<Livraison> findAllByRibdestOrRibsource(String city, String city2);

}
