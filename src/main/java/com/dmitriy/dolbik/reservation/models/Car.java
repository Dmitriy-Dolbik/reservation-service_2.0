package com.dmitriy.dolbik.reservation.models;

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
}
