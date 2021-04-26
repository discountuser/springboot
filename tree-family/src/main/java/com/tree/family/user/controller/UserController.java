package com.tree.family.user.controller;

import com.tree.family.firebase.service.FirebaseInitializer;
import com.tree.family.spring.data.es.repository.ArticleRepository;
import com.tree.family.user.dao.User_;
import com.tree.family.user.dto.UserDTO;
import com.tree.family.user.mapper.UserDtoUserDao;
import com.tree.family.user.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/ws/users")
public class UserController {

    public final ArticleRepository articleRepository;
    public final UserService userService;
    public final FirebaseInitializer firebaseInitializer;

    public UserController(ArticleRepository articleRepository, UserService userService, FirebaseInitializer firebaseInitializer) {
        this.articleRepository = articleRepository;
        this.userService = userService;
        this.firebaseInitializer = firebaseInitializer;
    }

    @GetMapping(value = "/")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<?> getByUserId(@PathVariable String userId) {
        if (null != userId) {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getByUserId(Long.parseLong(userId)));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }
    }

    /**
     * @param emailAddress emailAddress
     * @return ResponseEntity<User_>
     */
    @GetMapping(value = "/{emailAddress}")
    public ResponseEntity<?> getByEmailAddress(@PathVariable String emailAddress) {
        if (null != emailAddress) {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getByEmailAddress(emailAddress));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> add(@RequestBody UserDTO userDTO) {
        try {
            UserDtoUserDao userDaoMapper = new UserDtoUserDao();
            User_ user = userDaoMapper.apply(userDTO);
            return ResponseEntity.status(HttpStatus.OK).body(userService.addUser(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }

    }

    @PutMapping(value = "/")
    public ResponseEntity<?> update(@RequestBody UserDTO userDTO) {
        UserDtoUserDao userDaoMapper = new UserDtoUserDao();
        User_ user = userDaoMapper.apply(userDTO);
        try {
            Optional.of(userService.getByUserId(user.getUserId())).orElseThrow(() -> new Exception("User Not Found"));
            return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }
    }


    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<?> delete(@PathVariable String userId) {
        try {
            Optional<User_> user = Optional.of(userService.getByUserId(Long.parseLong(userId))).orElseThrow(() -> new Exception(""));
            userService.deleteUser(user.get());
            return ResponseEntity.status(HttpStatus.OK).body("");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }
    }

}
