package ru.bozhov.dormitory.model;

import jakarta.persistence.*;
import ru.bozhov.dormitory.botAPI.state.DepositState;

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
    @Column(name = "deposit_state")
    @Enumerated(value = EnumType.STRING)
    private DepositState depositState;
}
