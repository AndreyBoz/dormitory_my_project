package ru.bozhov.dormitory.model;

import jakarta.persistence.*;
import lombok.Data;
import ru.bozhov.dormitory.botAPI.state.BotState;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="usr")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatId;
    @Column(name="user_name")
    private String userName;
    @Column(name = "number_room")
    private int numberRoom;
    @Column(name = "billing_period_start")
    private Date billingPeriodStart;
    @Column(name = "billing_period_end")
    private Date billingPeriodEnd;
    @Column(name = "user_type")
    @Enumerated(value = EnumType.STRING)
    private ClientType type;
    @Column(name = "deposit_data")
    @OneToMany(mappedBy = "user")
    private List<Deposit> depositData = new ArrayList<>();

    @Column(name = "bot_state")
    @Enumerated(value = EnumType.STRING)
    private BotState botState;
}
