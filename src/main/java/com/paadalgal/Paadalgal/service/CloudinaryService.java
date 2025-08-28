package com.paadalgal.Paadalgal.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;
    public Map uploadFile(MultipartFile file) throws IOException {
        return cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap("resource_type", "auto"));
    }
    public Map getFileDetails(String publicId) throws Exception {
        return cloudinary.api().resource(publicId, ObjectUtils.emptyMap());
    }
}
