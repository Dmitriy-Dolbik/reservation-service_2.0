package com.dmitriy.dolbik.reservation.models;

import java.time.LocalDate;

public record Rental(Long id,
                     String userId,
                     Long reservationId,
                     LocalDate startDate) {
}
