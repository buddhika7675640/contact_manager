package com.iterminal.ContactManager.controller;


import com.iterminal.ContactManager.dto.UserDto;
import com.iterminal.ContactManager.exception.ControllerException;
import com.iterminal.ContactManager.exception.UserException;
import com.iterminal.ContactManager.service.UserService;
import com.iterminal.ContactManager.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @GetMapping(value = "/user")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        try {

            List<UserDto> list = userService.getAllUser();
            LOGGER.info("Get User list Successfully");
            return new ResponseEntity<List<UserDto>>(list, HttpStatus.OK);
        } catch (Exception e) {
            //ControllerException ce = new ControllerException("600", "Something went wrong in controller");
            LOGGER.error("Unable to Get User list , message: " + e.getMessage(), e);
            // return new ResponseEntity<>(ce, HttpStatus.BAD_REQUEST);
            throw new RuntimeException(e);
        }

    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") long id) {


        try {
            UserDto userDto = userService.getOneUser(id);
            LOGGER.info("Specimen with ID " + id + " was  Get Successfully.");
            return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

            /*
            try {
        } catch (UserException e) {
            ControllerException ce = new ControllerException(e.getCode(), e.getMessage());
            LOGGER.error("Unable to Get specimen with ID: " + id + ", message: " + e.getMessage(), e);
            return new ResponseEntity<>(ce, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            ControllerException ce = new ControllerException("600", "Something went wrong in controller");
            LOGGER.error("Unable to Get specimen with ID: " + id + ", message: " + e.getMessage(), e);
            return new ResponseEntity<>(ce, HttpStatus.INTERNAL_SERVER_ERROR);
        }

             */


    @PostMapping(value = "/user")
    public ResponseEntity<UserDto> saveUsers(@RequestBody UserDto userDto) {
        try {

            UserDto userDto1 = userService.saveUser(userDto);
            LOGGER.info("User was saved Successfully.");
            // UserException ue = new UserException("200","Created");
            return new ResponseEntity<UserDto>(userDto1, HttpStatus.OK);

        } catch (Exception e) {
            //ControllerException ce = new ControllerException("600", "Something went wrong in controller");
            LOGGER.error("Unable to save User , message: " + e.getMessage(), e);
            //return new ResponseEntity<>(ce, HttpStatus.BAD_REQUEST);
            throw new RuntimeException(e);
        }

    }

    @PutMapping(value = "/user/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("id") long id) {
        try {

            UserDto userDto1 = userService.updateUser(userDto, id);
            LOGGER.info("Specimen with ID " + userDto1.getId() + " was updated.");
            return new ResponseEntity<UserDto>(userDto1, HttpStatus.OK);


        } catch (Exception e) {
            //ControllerException ce = new ControllerException("600", "Something went wrong in controller");
            LOGGER.error("Unable to update specimen with ID: " + id + ", message: " + e.getMessage(), e);
            throw new RuntimeException(e);
        }

    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable("id") long id) {

        try {

            UserDto userDto1 = userService.deleteUser(id);
            LOGGER.info("Specimen with ID " + id + " was deleted.");
            return new ResponseEntity<UserDto>(userDto1, HttpStatus.OK);
        } catch (Exception e) {
            //ControllerException ce = new ControllerException(e.getCode(), e.getMessage());
            LOGGER.error("Unable to delete specimen with ID: " + id + ", message: " + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
