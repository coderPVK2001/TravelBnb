package com.Travelbnb.configurations;

import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioSmsConfiguration {

    @Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;

    @Value("${twilio.fromPhoneNumber}")
    private String fromPhoneNumber;

    @PostConstruct
    public void init() {
        Twilio.init(accountSid, authToken);
    }
}
