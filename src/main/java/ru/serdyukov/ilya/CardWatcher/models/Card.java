package ru.serdyukov.ilya.CardWatcher.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "creditcards")
public class Card {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_user")
    private int idUser;

    @Column(name = "id_bank")
    private int idBank;

    @Column(name = "card_name")
    private String cardName;

    @Column(name = "last_four_digit")
    private String lastFourDigit;

    @Column(name = "id_currency")
    private int idCurrency;

    @Column(name = "limit_amount")
    private double limitAmount;

    @Column(name = "grace_period")
    private int gracePeriod;

    @Column(name = "interest_rate")
    private float interestRate;

    @Column(name = "statement_day")
    private int statementDay;

    @Column(name = "total_debit")
    private double totalDebit;

    @Transient
    private Payment nextPayment;

    public Card() {
    }
}
