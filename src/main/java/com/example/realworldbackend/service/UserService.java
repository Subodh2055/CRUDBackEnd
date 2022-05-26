package com.example.realworldbackend.service;

import com.example.realworldbackend.exception.UserNotFoundException;
import com.example.realworldbackend.model.User;
import com.example.realworldbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public User addUser(User user){
        return userRepository.save(user);
    }

  public List<User> findAllUsers(){
      return userRepository.findAll();
  }

  public User findUserById(Long id) throws UserNotFoundException {
      return userRepository.findUserById(id)
              .orElseThrow(()-> new UserNotFoundException("User by id" + id + "Was not Found"));
  }

  public void deleteUser(Long id){
      userRepository.deleteUserById(id);
  }

}
