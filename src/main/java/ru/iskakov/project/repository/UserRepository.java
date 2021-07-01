package ru.iskakov.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.iskakov.project.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    //@Query("SELECT f FROM Footballer f WHERE f.name=?1 and f.age=?2")
    List<User> findByName(String name);
}
