package com.EatEasy.Repository;


import com.EatEasy.Models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

//Se extiende de JPARepository como siempre y importar del models User
public interface UserDetailsRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

}
