package com.example.project.bookmyshowbackend.Repositories;

import com.example.project.bookmyshowbackend.Models.TheaterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends JpaRepository<TheaterEntity,Integer> {
}
