package com.Travelbnb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "guest_name", nullable = false, length = 200)
    private String guestName;

    @Column(name = "email", nullable = false, length = 200,unique = true)
    private String email;

    @Column(name = "mobile", nullable = false, unique = true, length = 15)
    private String mobile;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "total_nights", nullable = false)
    private Integer totalNights;

    @Column(name = "gst")
    private Double gst;

    @Column(name = "per_night_price")
    private Integer perNightPrice;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;



}