package com.carParts.repository;

import com.carParts.model.entity.Offer;
import com.carParts.model.entity.Part;
import com.carParts.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OfferRepo extends JpaRepository<Offer, Long> {
    List<Offer> findBySeller(User user);
}
