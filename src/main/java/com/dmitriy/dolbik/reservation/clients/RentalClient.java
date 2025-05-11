package com.dmitriy.dolbik.reservation.clients;

import com.dmitriy.dolbik.reservation.models.Rental;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestPath;

@RegisterRestClient(configKey = "rental-api")//аннотация для создания HTTP клиента.
                                             //configKey указывает ключ базового пути (см. application.yaml)
@Path("/rental") //относительный путь, куда будем отправлять запросы. Например http://localhost:8082/rental
public interface RentalClient {

    @POST //отправляем HTTP POST-запрос
    @Path("/start/{userId}/{reservationId}") //на адрес вида http://localhost:8082/rental/start/userId/1
    Rental start(@RestPath String userId, //@RestPath позволяет поместить параметры метода в HTTP путь
                 @RestPath Long reservationId);
}
