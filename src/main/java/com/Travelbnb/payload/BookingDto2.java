package com.Travelbnb.payload;

import com.Travelbnb.entity.AppUser;
import com.Travelbnb.entity.Property;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class BookingDto2 {

    private String propertyName;
    private String locationName;
    private String countryName;
    private String guestName;
    private String email;
    private String mobile;
    private int totalNights;
    private int perNightPrice;
    private double gst;
    private double totalPrice;

//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Integer id;
//
//    @Column(name = "guest_name", nullable = false, length = 200)
//    private String guestName;
//
//    @Column(name = "email", nullable = false, length = 200,unique = true)
//    private String email;
//
//    @Column(name = "mobile", nullable = false, unique = true, length = 15)
//    private String mobile;
//
//    @Column(name = "total_price")
//    private Integer totalPrice;
//
//    @Column(name = "total_nights", nullable = false)
//    private Integer totalNights;
//
//    @Column(name = "gst")
//    private Integer gst;
//
//    @ManyToOne
//    @JoinColumn(name = "property_id")
//    private Property property;
//
//    @ManyToOne
//    @JoinColumn(name = "app_user_id")
//    private AppUser appUser;

}
