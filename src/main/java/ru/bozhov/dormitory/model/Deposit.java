package ru.bozhov.dormitory.model;

import jakarta.persistence.*;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.sql.Date;

@Entity
@Table(name = "deposit")
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long depositId;
    @Column(name= "deposit_amount")
    private int amount;
    @Column(name = "usr")
    @ManyToOne
    private User user;
    @Column(name = "date_ deposit")
    private Date depositDate;
}
