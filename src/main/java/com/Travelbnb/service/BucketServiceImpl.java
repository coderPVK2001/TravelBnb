package com.Travelbnb.service;

import com.Travelbnb.entity.Property;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import com.amazonaws.services.s3.model.Bucket;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BucketServiceImpl implements BucketService {

    @Autowired
    private AmazonS3 amazonS3;

    @Override
    public List<String> getBucketList() {
        return amazonS3.listBuckets().stream().map(Bucket::getName).collect(Collectors.toList());
    }

    @Override
    public String fileUpload(String bucketName, MultipartFile file, Property property) {
        String fileName = "";
        try {
            if (!amazonS3.doesBucketExistV2(bucketName)) {
                return "Bucket Not Exist";
            }
            else {
                fileName = property.getName()+"-"+file.getOriginalFilename();
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentLength(file.getSize());
                amazonS3.putObject(bucketName, fileName, file.getInputStream(), metadata);
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return amazonS3.getUrl(bucketName, fileName).toString();
    }

    @Override
    public String fileUploadbooking(String bucketName, MultipartFile file) {
        String fileName = "";
        try {
            if (!amazonS3.doesBucketExistV2(bucketName)) {
                return "Bucket Not Exist";
            }
            else {
                fileName = file.getOriginalFilename();
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentLength(file.getSize());
                amazonS3.putObject(bucketName, fileName, file.getInputStream(), metadata);
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return amazonS3.getUrl(bucketName, fileName).toString();
    }
}
