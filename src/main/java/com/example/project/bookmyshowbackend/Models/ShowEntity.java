package com.example.project.bookmyshowbackend.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.jfr.Timestamp;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "shows")
@Entity
@EntityListeners(value = {AuditingEntityListener.class})
public class ShowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "show_Date", columnDefinition = "DATE", nullable = false)
    private LocalDate showDate;

    @Column(name = "show_Time", columnDefinition = "TIME", nullable = false)
    private LocalTime showTime;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne
    @JsonIgnore
    private MovieEntity movie;

    @ManyToOne
    @JsonIgnore
    private TheaterEntity theater;

    @OneToMany(mappedBy = "show",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TicketEntity> tickets;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ShowSeatEntity>seats;
}
