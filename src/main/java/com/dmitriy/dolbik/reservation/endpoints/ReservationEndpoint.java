package com.dmitriy.dolbik.reservation.endpoints;

import com.dmitriy.dolbik.reservation.models.Reservation;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("reservation")
public class ReservationEndpoint {

    @GET
    @Path("all")
    public List<Reservation> getAllReservations() {
        return Reservation.listAll();
    }

    @POST
    @Transactional
    public Reservation makeReservation(Reservation reservation) {
        reservation.persist();
        return reservation;
    }
}
