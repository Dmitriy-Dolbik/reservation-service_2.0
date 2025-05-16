package com.dmitriy.dolbik.reservation.endpoints;

import com.dmitriy.dolbik.reservation.clients.GraphQLInventoryService;
import com.dmitriy.dolbik.reservation.clients.RentalClient;
import com.dmitriy.dolbik.reservation.models.Car;
import com.dmitriy.dolbik.reservation.models.Reservation;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestQuery;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Path("reservation")
public class ReservationEndpoint {

    private final RentalClient rentalClient;
    private final GraphQLInventoryService inventoryClient;

    public ReservationEndpoint(@RestClient RentalClient rentalClient,
                               GraphQLInventoryService inventoryClient) {
        this.rentalClient = rentalClient;
        this.inventoryClient = inventoryClient;
    }

    @GET
    @Path("available/cars")
    public Collection<Car> getCarsAvailableForReservations(@RestQuery LocalDate startDate, @RestQuery LocalDate endDate) throws ExecutionException, InterruptedException {
        List<Car> availableCars = inventoryClient.getAllCars();
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
        if (reservation.startDay.equals(LocalDate.now())) {
            rentalClient.start("userId", reservation.id);
        }
        return reservation;
    }

    @DELETE
    @Path("cancel")
    @Transactional
    public boolean cancel(@RestQuery Long reservationId) {
        return Reservation.deleteById(reservationId);
    }
}
