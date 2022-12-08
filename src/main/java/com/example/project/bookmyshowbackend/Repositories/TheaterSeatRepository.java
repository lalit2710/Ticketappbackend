package com.example.project.bookmyshowbackend.Repositories;

import com.example.project.bookmyshowbackend.Models.TheaterSeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterSeatRepository extends JpaRepository<TheaterSeatEntity,Integer> {
}
