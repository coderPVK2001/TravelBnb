package com.Travelbnb.service;

import com.Travelbnb.entity.AppUser;
import com.Travelbnb.payload.AppUserDto;
import com.Travelbnb.payload.LoginDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    AppUserDto addData(AppUserDto auser);

    String verify(LoginDto ldto);
}
