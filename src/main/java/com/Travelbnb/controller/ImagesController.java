package com.Travelbnb.controller;

import com.Travelbnb.entity.Images;
import com.Travelbnb.entity.Property;
import com.Travelbnb.repository.ImagesRepository;
import com.Travelbnb.repository.PropertyRepository;
import com.Travelbnb.service.BucketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/s3")
public class ImagesController {

    private BucketService bucketService;
    private PropertyRepository propertyRepository;
    private ImagesRepository imagesRepository;

    public ImagesController(BucketService bucketService, PropertyRepository propertyRepository, ImagesRepository imagesRepository) {
        this.bucketService = bucketService;
        this.propertyRepository = propertyRepository;
        this.imagesRepository = imagesRepository;
    }

    @PostMapping("/upload/property")
    public ResponseEntity<?> uploadfile(@RequestParam int propertyId,
                                        @RequestParam MultipartFile file){

        Images images=new Images();
        Property property = propertyRepository.findById(propertyId).get();
        String url = bucketService.fileUpload("propertyimages2001", file,property);

        images.setProperty(property);
        images.setUrl(url);
        imagesRepository.save(images);

        return new ResponseEntity<>(url, HttpStatus.CREATED);
    }
}
