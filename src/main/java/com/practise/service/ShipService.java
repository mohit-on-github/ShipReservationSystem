package com.practise.service;

import com.practise.dto.BookingDto;
import com.practise.entity.ShipEntity;
import com.practise.entity.UserEntity;
import com.practise.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;



@Service
public class ShipService {

    @Autowired
    ShipRepository shipRepository;

    public HashSet<String> getAllSources() {
        List<ShipEntity> ships = shipRepository.findAll();
        HashSet<String> set = new HashSet<>();
        for (ShipEntity ship : ships) {
            set.add(ship.getSource());
        }
        return set;
    }

    public HashSet<String> getAllDestinations() {
        List<ShipEntity> ships = shipRepository.findAll();
        HashSet<String> set = new HashSet<>();
        for (ShipEntity ship : ships) {
            set.add(ship.getDestination());
        }
        return set;
    }

    public List<ShipEntity> getShipBySourceAndDestination(String source, String destination){
        List<ShipEntity> allShips = shipRepository.findAllBySourceAndAndDestination(source, destination);
        return allShips;
    }

    public BookingDto getShipDetails(int shipId){
        ShipEntity shipEntity = shipRepository.findAllByShipId(shipId);


        UserEntity userEntity = new UserEntity();
        BookingDto bookingDto = new BookingDto();
        bookingDto.setShipId(shipEntity.getShipId());
        bookingDto.setShipName(shipEntity.getShipName());
        bookingDto.setShipModel(shipEntity.getShipModel());
        bookingDto.setSource(shipEntity.getSource());
        bookingDto.setDestination(shipEntity.getDestination());
        bookingDto.setPrice(shipEntity.getPrice());
        bookingDto.setDateOfJourney(shipEntity.getDateOfJourney());
        bookingDto.setUserEmail(userEntity.getUserEmail());
        bookingDto.setUserName(userEntity.getUserName());
        bookingDto.setAge(userEntity.getAge());
        bookingDto.setMobNo(userEntity.getMobNo());
        return bookingDto;
    }

}
