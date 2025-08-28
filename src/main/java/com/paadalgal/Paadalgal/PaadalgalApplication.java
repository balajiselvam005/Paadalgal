package com.paadalgal.Paadalgal;

import com.cloudinary.Cloudinary;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.ArrayList;

@SpringBootApplication
public class PaadalgalApplication {
	public static void main(String[] args) {
		SpringApplication.run(PaadalgalApplication.class, args);
	}
}
