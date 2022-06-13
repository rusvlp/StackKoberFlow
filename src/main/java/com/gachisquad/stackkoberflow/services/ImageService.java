package com.gachisquad.stackkoberflow.services;

import com.gachisquad.stackkoberflow.entity.Image;
import com.gachisquad.stackkoberflow.exception.NotFoundException;
import com.gachisquad.stackkoberflow.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service

public class ImageService {
    public ImageRepository imageRepository;

    public ImageService(ImageRepository ir){
        this.imageRepository = ir;
    }

    public Image getImageById(Long id){
        return imageRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }

    public void addImage(Image i){
        imageRepository.save(i);
    }

    public void addImageToQuestion(MultipartFile file){

    }
    public Image toImage(MultipartFile file){
        try {
            Image image = new Image();
            image.setName(file.getName());
            image.setOriginalFileName(file.getOriginalFilename());
            image.setContentType(file.getContentType());
            image.setSize(file.getSize());
            image.setBytes(file.getBytes());
            return image;
        } catch (IOException e){
            throw new RuntimeException(e);
        }

    }
}
