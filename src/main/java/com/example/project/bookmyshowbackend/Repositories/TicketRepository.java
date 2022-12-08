package com.example.project.bookmyshowbackend.Repositories;

import com.example.project.bookmyshowbackend.Models.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity,Integer> {
}
