package com.example.project.bookmyshowbackend.Repositories;

import com.example.project.bookmyshowbackend.Models.ShowSeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeatEntity,Integer> {
}
