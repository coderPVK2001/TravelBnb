package com.Travelbnb.configurations;

import com.Travelbnb.entity.AppUser;
import com.Travelbnb.repository.AppUserRepository;
import com.Travelbnb.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class JwtFilterRequest extends OncePerRequestFilter {
    
    private JWTService jservice;

    private AppUserRepository arepo;

    public JwtFilterRequest(JWTService jservice, AppUserRepository arepo) {
        this.jservice = jservice;
        this.arepo = arepo;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String auth = request.getHeader("Authorization");

        if(auth!=null && auth.startsWith("Bearer")){
            String sstring = auth.substring(7);
            String username = jservice.getUsername(sstring);

            Optional<AppUser> opt = arepo.findByUsername(username);
            if(opt.isPresent()){
                AppUser userentity = opt.get();

                UsernamePasswordAuthenticationToken upat=new UsernamePasswordAuthenticationToken
                        (userentity, null , Collections.singleton( new SimpleGrantedAuthority(userentity.getRole()))); //singleton means it will take only one userobject/request at a time

                upat.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(upat);
            }

        }

        filterChain.doFilter(request,response);
    }
}
