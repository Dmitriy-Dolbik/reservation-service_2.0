package com.dmitriy.dolbik.reservation.clients;

import com.dmitriy.dolbik.reservation.models.Car;
import io.smallrye.graphql.client.GraphQLClient;
import io.smallrye.graphql.client.Response;
import io.smallrye.graphql.client.core.Document;
import io.smallrye.graphql.client.dynamic.api.DynamicGraphQLClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static io.smallrye.graphql.client.core.Document.document;
import static io.smallrye.graphql.client.core.Field.field;
import static io.smallrye.graphql.client.core.Operation.operation;

@ApplicationScoped
public class GraphQLInventoryService {

    @Inject
    @GraphQLClient("inventory")
    DynamicGraphQLClient graphQLClient;

    public List<Car> getAllCars() throws ExecutionException, InterruptedException {
        Document cars = document(
                operation(
                        field("allCars",
                                field("id"),
                                field("licensePlateNumber"),
                                field("manufacturer"),
                                field("model")
                        )
                )
        );
        Response response = graphQLClient.executeSync(cars);
        return response.getList(Car.class, "allCars");
    }
}

