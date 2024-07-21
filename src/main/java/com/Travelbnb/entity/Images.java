package com.Travelbnb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "images")
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "url", nullable = false, length = 2000)
    private String url;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

}