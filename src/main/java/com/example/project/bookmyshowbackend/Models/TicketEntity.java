package com.example.project.bookmyshowbackend.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tickets")
@Entity
@Builder
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "alloted_seats",nullable = false)
    private String alloted_seats;

    @Column(name = "amount",nullable = false)
    private int amount;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdOn",nullable = false)
    private Date createdOn;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    UserEntity user;

    @ManyToOne
    @JsonIgnore
    private ShowEntity show;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ShowSeatEntity> seats;



}
