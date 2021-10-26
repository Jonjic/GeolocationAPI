package com.example.demo.bus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusRepository extends JpaRepository <Bus, Long> {
    Optional<Bus> findByRegistration(String registration);
    List<Bus> findByNumber(Long number);
}
