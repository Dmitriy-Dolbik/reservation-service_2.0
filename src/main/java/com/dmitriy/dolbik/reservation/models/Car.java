package com.dmitriy.dolbik.reservation.models;

import com.fasterxml.jackson.annotation.JsonCreator;

public record Car(/**
                   * Уникальный идентификатор
                   */
                  Long id,
                  /**
                   * Номерной знак
                   */
                  String licensePlateNumber,
                  /**
                   * Производитель
                   */
                  String manufacturer,
                  /**
                   * Модель
                   */
                  String model) {

    @JsonCreator
    public Car {
    }
}
