package com.paadalgal.Paadalgal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.cloudinary.utils.ObjectUtils;

import io.github.cdimascio.dotenv.Dotenv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cloudinary.Cloudinary;

@SpringBootApplication
public class PaadalgalApplication {

	public static void main(String[] args) throws IOException {
				Dotenv dotenv=Dotenv.load();
				dotenv.get("CLOUDINARY_CLOUD_NAME");
		        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name",dotenv.get("CLOUDINARY_CLOUD_NAME"),
                "api_key", dotenv.get("CLOUDINARY_API_KEY"),
                "api_secret", dotenv.get("CLOUDINARY_API_SECRET")));
				File folder = new File("M:/Paadalgal/src/assets/audio/");
        for (File file : folder.listFiles()) {
            if (file.isFile() && (file.getName().endsWith(".mp3") || file.getName().endsWith(".wav"))) {
                Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.asMap(
                        "resource_type", "auto"
                ));
            }
        }
		SpringApplication.run(PaadalgalApplication.class, args);
	}
}
