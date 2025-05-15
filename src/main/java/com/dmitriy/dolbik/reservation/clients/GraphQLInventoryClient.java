package com.dmitriy.dolbik.reservation.clients;

import com.dmitriy.dolbik.reservation.models.Car;
import io.smallrye.graphql.client.typesafe.api.GraphQLClientApi;
import org.eclipse.microprofile.graphql.Query;

import java.util.List;

@GraphQLClientApi(configKey = "inventory")
public interface GraphQLInventoryClient {

    @Query("allCars")
    List<Car> getAllCars();
}

