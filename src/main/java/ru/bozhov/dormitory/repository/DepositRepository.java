package ru.bozhov.dormitory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.bozhov.dormitory.model.Deposit;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
}
