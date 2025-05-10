package com.dmitriy.dolbik.reservation.endpoints;

import com.dmitriy.dolbik.reservation.models.Reservation;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Path("reservation")
public class ReservationEndpoint {

    @GET
    @Path("all")
    public List<Reservation> getAllReservations() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return List.of(
                new Reservation(
                        1L,
                        "userId_1",
                        1L,
                        LocalDate.parse("10.05.2025", formatter),
                        LocalDate.parse("15.05.2025", formatter)),
                new Reservation(
                        2L,
                        "userId_2",
                        2L,
                        LocalDate.parse("15.05.2025", formatter),
                        LocalDate.parse("20.05.2025", formatter))
        );
    }
}
