package com.tree.family.user.repository;

import com.tree.family.user.dao.User_;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User_, String> {

    /**
     * @param userId userId
     * @return User_
     */
    Optional<User_> getByUserId(long userId);

    /**
     * @param emailAddress emailAddress
     * @return User_
     */
    Optional<User_> getByEmailAddress(String emailAddress);

}
