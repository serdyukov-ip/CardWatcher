package ru.serdyukov.ilya.CardWatcher.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "paymentstatus")
public class PaymentStatus {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "status_name")
    private String currencyName;

    public PaymentStatus() {}

}
