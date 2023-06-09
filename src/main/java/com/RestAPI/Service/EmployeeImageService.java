package com.RestAPI.Service;

import com.RestAPI.Entity.EmployeeImage;
import com.RestAPI.Repository.EmployeeImageRepository;
import com.RestAPI.Util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class EmployeeImageService {


    @Autowired
    private EmployeeImageRepository employeeImageRepository;

    public EmployeeImage uploadImage(MultipartFile file) throws IOException{
        EmployeeImage employeeImage=new EmployeeImage();
        employeeImage.setName(file.getOriginalFilename());
        employeeImage.setType(file.getContentType());
        employeeImage.setImageData(ImageUtil.compressImage(file.getBytes()));
        return employeeImageRepository.save(employeeImage);
    }

    public byte[] downloadImage(String fileName){
        Optional<EmployeeImage> imageData = employeeImageRepository.findByName(fileName);
        return ImageUtil.decompressImage(imageData.get().getImageData());
    }
}
