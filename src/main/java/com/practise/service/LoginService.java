package com.practise.service;

import com.practise.entity.UserEntity;
import com.practise.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    HttpSession httpSession;
    public boolean validateUser(String userEmail, String userPass) {

        UserEntity user = (UserEntity) userRepository.findById(userEmail).orElse(null);
        if (user != null) {
            if (userRepository.findById(userEmail).get().getUserPass().equals(userPass)) {
                httpSession.setAttribute("user", user);
                return true;
            } else {
                return false;
            }
        }

        else {
            return false;
        }
    }


    public boolean saveUser(String userName, String userEmail, String userPassword, int age, String mobNo) {
        Optional<UserEntity> userRepositoryById = userRepository.findById(userEmail);

            if (userRepositoryById.isEmpty()) {
                UserEntity userEntity = new UserEntity();
                userEntity.setUserName(userName);
                userEntity.setUserEmail(userEmail);
                userEntity.setUserPass(userPassword);
                userEntity.setAge(age);
                userEntity.setMobNo(mobNo);
                userRepository.save(userEntity);
                return true;
            } else {
                return false;
            }
    }

    public String setNewPass(String currentPass, String newPass){
        UserEntity user = (UserEntity) httpSession.getAttribute("user");
        if(user.getUserPass().matches(currentPass)){
            if(!currentPass.matches(newPass)) {
                user.setUserPass(newPass);
                userRepository.save(user);
                return "changed";
            }
            else {
                return "sameOldNew";
            }
        }
        else {
            return "passNotMatched";
        }

    }

}
