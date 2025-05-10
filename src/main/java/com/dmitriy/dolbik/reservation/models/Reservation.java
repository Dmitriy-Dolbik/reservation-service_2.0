package com.dmitriy.dolbik.reservation.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "Reservations", schema = "my_schema")
public class Reservation extends PanacheEntity {
    public String userId;
    public Long carId;
    public LocalDate startDay;
    public LocalDate endDay;
}
