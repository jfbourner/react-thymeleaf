package com.jackbourner.reactthymeleaf.repository;

import com.jackbourner.reactthymeleaf.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByEmail(String email);
    Optional<UserAccount> findByUsernameOrEmail(String username, String email);
    Optional<UserAccount> findByUsername(String username);
    //List<UserAccount> findAll();
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    @Override
    void delete(UserAccount userAccount);

}
