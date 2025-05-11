package com.dmitriy.dolbik.reservation.endpoints;

import com.dmitriy.dolbik.reservation.models.Car;
import com.dmitriy.dolbik.reservation.models.Reservation;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.RestQuery;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("reservation")
public class ReservationEndpoint {

    @GET
    @Path("available/cars")
    public Collection<Car> getCarsAvailableForReservations(@RestQuery LocalDate startDate, @RestQuery LocalDate endDate) {
        List<Car> availableCars = List.of(
                new Car(1L, "licensePlateNumber_1", "manufacturer_1", "model_1"),
                new Car(2L, "licensePlateNumber_2", "manufacturer_2", "model_2")
        );
        Map<Long, Car> carsByIdMap = new HashMap<>();
        for (Car car : availableCars) {
            carsByIdMap.put(car.id(), car);
        }
        List<Reservation> reservations = Reservation.listAll();
        for (Reservation reservation : reservations) {
            if (reservation.isIntersect(startDate, endDate)) {
                carsByIdMap.remove(reservation.carId);
            }
        }
        return carsByIdMap.values();
    }

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

    @DELETE
    @Path("cancel")
    @Transactional
    public boolean cancel(@RestQuery Long reservationId) {
        return Reservation.deleteById(reservationId);
    }
}
