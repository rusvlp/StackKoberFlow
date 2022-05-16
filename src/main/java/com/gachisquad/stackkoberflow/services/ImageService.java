package com.gachisquad.stackkoberflow.services;

import com.gachisquad.stackkoberflow.entity.Image;
import com.gachisquad.stackkoberflow.exception.NotFoundException;
import com.gachisquad.stackkoberflow.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
}
