package ru.iskakov.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.iskakov.project.dto.UserDTO;
import ru.iskakov.project.model.User;
import ru.iskakov.project.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @PostMapping
    public User addUser(@RequestBody UserDTO footballer) {
        return userRepository.save(new User(null, footballer.getName(),
                footballer.getAge(), footballer.getEmail(), footballer.getPassword(), null));
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") Integer id,
                                 @RequestBody UserDTO footballer) {
        User userEnyity = userRepository.findById(id).orElse(null);
        if (userEnyity == null) {
            return addUser(footballer);
        }
        else {
            userEnyity.setAge(footballer.getAge());
            userEnyity.setName(footballer.getName());
            userEnyity.setEmail(footballer.getEmail());
            userEnyity.setPassword(footballer.getPassword());
            return userRepository.save(userEnyity);
        }
    }

    @DeleteMapping("/{id}")
    public boolean deleteFootballer(@PathVariable("id")Integer id) {
        User userEntity = userRepository.findById(id).orElse(null);
        if (userEntity == null) {
            return false;
        }
        else {
            userRepository.delete(userEntity);
            return true;
        }
    }
}
