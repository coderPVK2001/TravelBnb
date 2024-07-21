package com.Travelbnb.service;

import com.Travelbnb.entity.AppUser;
import com.Travelbnb.payload.AppUserDto;
import com.Travelbnb.payload.LoginDto;
import com.Travelbnb.repository.AppUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private AppUserRepository appUserRepo;
    private JWTService jwtService;

    public UserServiceImpl(AppUserRepository arepo, JWTService jservice) {
        this.appUserRepo = arepo;
        this.jwtService = jservice;
    }


    private AppUser DtoToEntity(AppUserDto adto) {
        AppUser entity= new AppUser();
        entity.setName(adto.getName());
        entity.setUsername(adto.getUsername());
        entity.setEmail(adto.getEmail());
        entity.setPassword(BCrypt.hashpw(adto.getPassword(),BCrypt.gensalt(10)));  //encrypting password  //10 rounds of encryption
        entity.setRole(adto.getRole());
        AppUser savedEntity = appUserRepo.save(entity);
        return savedEntity;
    }

    public AppUserDto EntityToDto(AppUser aentity){
        AppUserDto adto = new AppUserDto();
        adto.setId(aentity.getId());
        adto.setName(aentity.getName());
        adto.setUsername(aentity.getUsername());
        adto.setEmail(aentity.getEmail());
        adto.setRole(aentity.getRole());
        return adto;
    }

    @Override
    public AppUserDto addData(AppUserDto adto) {
        AppUser savedEntity = DtoToEntity(adto);
        AppUserDto savedDto = EntityToDto(savedEntity);
        return savedDto;
    }


    @Override
    public String verify(LoginDto loginDto) {
        Optional<AppUser> opt =appUserRepo.findByUsername(loginDto.getUsername());
        if(opt.isPresent()){
            AppUser appUser = opt.get();
            //boolean val = BCrypt.checkpw(ldto.getPassword(), auser.getPassword()); //will verify password
            if(BCrypt.checkpw(loginDto.getPassword(),appUser.getPassword())) {
                String generatedToken = jwtService.generateToken(loginDto);
                return generatedToken;
            }
        }
       return null;
    }

}
