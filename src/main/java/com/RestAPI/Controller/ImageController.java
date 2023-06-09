package com.RestAPI.Controller;

import com.RestAPI.Service.EmployeeImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class ImageController {

    @Autowired
    private EmployeeImageService imageService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(name = "/upload")
    public void uploadImage(@RequestParam ("employeeImage")MultipartFile file) throws IOException{
        imageService.uploadImage(file);
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName){
        byte[] image=imageService.downloadImage(fileName);
        return  ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
    }
}
