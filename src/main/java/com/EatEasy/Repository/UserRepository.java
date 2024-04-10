package com.EatEasy.Repository;

import com.EatEasy.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUuid(UUID uuid);
    User findByEmail(String email);

    User findBynameAndPassword(String name, String password);

}
