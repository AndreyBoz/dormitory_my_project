package ru.bozhov.dormitory.model;

import jakarta.persistence.*;
import lombok.Cleanup;
import lombok.Data;
import ru.bozhov.dormitory.botAPI.state.DepositState;

import java.sql.Date;

@Entity
@Table(name = "deposit")
@Data
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long depositId;
    @ManyToOne
    @JoinColumn(name = "usr")
    private User user;
    @Column(name = "date_ deposit")
    private java.sql.Date depositDate;
    @Column(name = "deposit_state")
    @Enumerated(value = EnumType.STRING)
    private DepositState depositState;
    @Column(name = "file_path")
    String filePath;
}
