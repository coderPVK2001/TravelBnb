package com.Travelbnb.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    private JwtFilterRequest jfr;

    public SecurityConfiguration(JwtFilterRequest jfr) {
        this.jfr = jfr;
    }

    @Bean
    public SecurityFilterChain sfc(HttpSecurity http) throws Exception{

    http.csrf().disable().cors().disable();

    http.authorizeHttpRequests()
              .requestMatchers("/api/v1/user/adduser","/api/v1/user/verify").permitAll()
              .requestMatchers("/apicountry/**","/api/v1/s3/upload/property").hasRole("ADMIN")
              .requestMatchers("/api/v1/reviews/addreview","/api/v1/reviews/getreviews","/api/v1/reviews/updatereview","/api/v1/reviews/deletereview",
                              "/api/v1/favourites/status","/api/v1/favourites/status/pdfreport","/api/v1/booking/book").hasAnyRole("ADMIN","USER")
              .anyRequest().authenticated();

    http.addFilterBefore(jfr, AuthorizationFilter.class); //for urls withh token we should call this method first
    return http.build();
    }
}
