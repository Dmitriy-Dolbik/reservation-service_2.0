package com.dmitriy.dolbik.reservation.models;

import java.time.LocalDate;

public record Reservation(
        /**
         * Идентификатор брони
         */
        Long id,
        /**
         * Идентификатор пользователя, сделавшего бронь
         */
        String userId,
        /**
         * Идентификатор забронированного автомобиля
         */
        Long carId,
        /**
         * Дата начала бронирования
         */
        LocalDate startDay,
        /**
         * Дата окончания бронирования
         */
        LocalDate endDay) {
}
