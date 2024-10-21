package com.practise.repository;

import com.practise.entity.BookingEntity;
import com.practise.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {
    public List<BookingEntity> findAllByUser(UserEntity user);

    public BookingEntity findByBookingId(int bookingId);
}
