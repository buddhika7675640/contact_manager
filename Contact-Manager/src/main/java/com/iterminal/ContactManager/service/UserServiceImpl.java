package com.iterminal.ContactManager.service;

import com.iterminal.ContactManager.dto.UserDto;
import com.iterminal.ContactManager.exception.UserException;
import com.iterminal.ContactManager.model.User;
import com.iterminal.ContactManager.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService{



    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;



    public List<UserDto> getAllUser() {

        List<User> userList = userRepository.findAll();
        try {
            return modelMapper.map(userList ,new TypeToken<List<UserDto>>(){}.getType());

        }catch (Exception e){
            throw new UserException("404","This list completely empty, we have nothing to return "+e.getMessage());
        }

    }


    public UserDto getOneUser(long id){

        Optional<User> optionalUser = userRepository.findById(id);
        // User getoneuser = userRepository.findById(id).get();
        return modelMapper.map(optionalUser.get(), UserDto.class);
        /*
        try {

        }catch(java.util.NoSuchElementException e){
            throw new UserException("404","Given user id does not exist in DB. " + e.getMessage());
        }catch(Exception e){
            throw new UserException("500","Something went wrong in UserService. "+e.getMessage());
        }

         */

    }

    public UserDto saveUser(UserDto userDto){

        userRepository.save(modelMapper.map(userDto, User.class)); //we need to map user and
        // userdto entity so we need to maven model mapper
        try {
            return userDto;
        }catch(Exception e){
            throw new UserException("500","Something went wrong in UserService. "+e.getMessage());
        }

    }


    public UserDto updateUser(UserDto userDto , long id){

        Optional<User> updatedUser = userRepository.findById(id);
        //User updatedUser = userRepository.findById(id).get();
        try {
            updatedUser.get().setName(userDto.getName());
            updatedUser.get().setEmail(userDto.getEmail());
            updatedUser.get().setMobile(userDto.getMobile());
            updatedUser.get().setCompany(userDto.getCompany());
            updatedUser.get().setTitle(userDto.getTitle());
            updatedUser.get().setGroup(userDto.getGroup());
            userRepository.save(modelMapper.map(updatedUser.get() , User.class));
            return userDto;
        }catch (java.util.NoSuchElementException e){
            throw new UserException("404","Given user id does not exist in DB. " + e.getMessage());
        }catch(Exception e){
            throw new UserException("500","Something went wrong in UserService. "+e.getMessage());
        }
    }

    public UserDto deleteUser(long id){

        try {
            User deleteUser = userRepository.findById(id).get();
            userRepository.delete(deleteUser);
            return modelMapper.map(deleteUser, UserDto.class);
        }catch (java.util.NoSuchElementException e){
            throw new UserException("404","Given user id does not exist in DB. " + e.getMessage());
        } catch(Exception e){
            throw new UserException("500","Something went wrong in UserService. "+e.getMessage());
        }
    }
}

