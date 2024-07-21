package com.Travelbnb.controller;

import com.Travelbnb.entity.AppUser;
import com.Travelbnb.payload.AppUserDto;
import com.Travelbnb.payload.JwtTokenDto;
import com.Travelbnb.payload.LoginDto;
import com.Travelbnb.repository.AppUserRepository;
import com.Travelbnb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private UserService uservice;
    private AppUserRepository arepo;

    public UserController(AppUserRepository arepo, UserService uservice) {
        this.arepo = arepo;
        this.uservice = uservice;
    }

    @PostMapping("/adduser")
    public ResponseEntity<?> create(
            @RequestBody AppUserDto auser
    ){

        if(arepo.existsByUsername(auser.getUsername())){
            return new ResponseEntity<>("Username Already exists!!", HttpStatus.BAD_REQUEST);
        }
        if(arepo.existsByEmail(auser.getEmail())){
            return new ResponseEntity<>("Email Already exists!!", HttpStatus.BAD_REQUEST);
        }

        AppUserDto adto = uservice.addData(auser);

        return new ResponseEntity<>(adto, HttpStatus.CREATED);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyLogin(
            @RequestBody LoginDto ldto
    ){

        String resultToken= uservice.verify(ldto);

        if(resultToken!=null){

            JwtTokenDto tokendto = new JwtTokenDto();
            tokendto.setType("JWT token");
            tokendto.setName(resultToken);

            return new ResponseEntity<>(tokendto ,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("invalid token", HttpStatus.BAD_REQUEST);
        }
    }


}