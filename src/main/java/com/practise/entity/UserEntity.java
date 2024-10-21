package com.practise.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Represents a user entity in the system.
 * This class is a JPA entity class; hence it is automatically scanned and
 * used to create the table 'UserEntity' in the database.
 */
@Entity
@Getter
@Setter
public class UserEntity {

    /**
     * Email of the user.
     * It is the unique identifier for a user in the system.
     */
    @Id
    @Column(nullable = false)
    private String userEmail;

    /**
     * Password of the user.
     * It is stored as a string and is required for user authentication.
     */
    @Column(nullable = false)
    private String userPass;

    /**
     * Name of the user.
     */
    private String userName;

    /**
     * Age of the user.
     */
    private int age;

    /**
     * Mobile number of the user.
     */
    private String mobNo;

    /**
     * List of bookings made by the user.
     * It is a one-to-many relationship with the BookingEntity.
     */
    @OneToMany(mappedBy = "user")
    List<BookingEntity> bookingEntities;
}