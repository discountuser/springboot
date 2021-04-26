package com.tree.family.user.mapper;

import com.tree.family.user.dao.User_;
import com.tree.family.user.dto.UserDTO;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.Random;
import java.util.UUID;
import java.util.function.Function;

public class UserDtoUserDao implements Function<UserDTO, User_> {
    /**
     * Applies this function to the given argument.
     *
     * @param userDTO the function argument
     * @return the function result
     */
    @Override
    public User_ apply(UserDTO userDTO) {

        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String password = new String(array, StandardCharsets.UTF_8);

        long millis = System.currentTimeMillis();
        UUID uuid = UUID.randomUUID();


        User_ user_ = new User_();

        user_.setUserId(new Random().nextInt(4));
        user_.setUuid_(uuid.toString());
        user_.setFirstName(userDTO.getFirstName());
        user_.setLastName(userDTO.getLastName());
        user_.setEmailAddress(userDTO.getEmailAddress());
        user_.setPassword(password);
        user_.setCreatedBy(1802);
        user_.setModifiedDate(new Date(millis));
        user_.setCreateDate(new Date(millis));

        return user_;
    }
}
