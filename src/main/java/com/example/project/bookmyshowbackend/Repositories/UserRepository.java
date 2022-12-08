package com.example.project.bookmyshowbackend.Repositories;

import com.example.project.bookmyshowbackend.Models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {

}
