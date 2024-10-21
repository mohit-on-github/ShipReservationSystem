package com.practise.repository;

import com.practise.entity.ShipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipRepository extends JpaRepository<ShipEntity,Integer> {
 List<ShipEntity> findAllBySourceAndAndDestination(String source, String destination);
 ShipEntity findAllByShipId(int shipId);
}
