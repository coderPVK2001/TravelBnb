package com.Travelbnb.service;

import com.Travelbnb.entity.Property;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.List;

public interface BucketService {

    public List<String> getBucketList();

    public String fileUpload(String bucketName, MultipartFile file, Property property);

    public String fileUploadbooking(String bucketName, MultipartFile file);
}
