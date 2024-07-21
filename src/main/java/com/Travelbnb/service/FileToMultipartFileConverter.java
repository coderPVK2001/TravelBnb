package com.Travelbnb.service;
import org.springframework.mock.web.MockMultipartFile;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class FileToMultipartFileConverter {

    public MultipartFile convertFileToMultipartFile(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream input = new FileInputStream(file);
        return new MockMultipartFile("file",
                file.getName(), "application/pdf", input);
    }
}
