package com.example.project.bookmyshowbackend.Models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "mobileNo",nullable = false)
    private String mobileNo;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL) //bidirectional mapping
    private List<TicketEntity> listOfTickets;

}
