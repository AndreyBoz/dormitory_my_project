package ru.bozhov.dormitory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bozhov.dormitory.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
}
