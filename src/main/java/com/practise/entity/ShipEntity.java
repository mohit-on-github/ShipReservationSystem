package com.practise.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents a ship entity in the system.
 * This class is a JPA entity class; hence it is automatically scanned and
 * used to create the table 'ShipEntity' in the database.
 */
@Entity
@Data
public class ShipEntity {

    /**
     * Unique ID of the ship.
     */
    @Id
    private int shipId;

    /**
     * Name of the ship.
     */
    private String shipName;

    /**
     * Model of the ship.
     */
    private String shipModel;

    /**
     * Source location of the ship's journey.
     */
    private String source;

    /**
     * Destination location of the ship's journey.
     */
    private String destination;

    /**
     * Date of the ship's journey.
     */
    private LocalDate dateOfJourney;

    /**
     * Price for a seat on the ship.
     */
    private int price;

    /**
     * Number of seats available on the ship.
     */
    private int seatAvailability;

    /**
     * List of bookings associated with the ship.
     * It is a one-to-many relationship with the BookingEntity.
     */
    @OneToMany(mappedBy = "ship")
    private List<BookingEntity> shipEntitiesList;
}