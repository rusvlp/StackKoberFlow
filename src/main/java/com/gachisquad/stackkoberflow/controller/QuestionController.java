package com.gachisquad.stackkoberflow.controller;

import com.gachisquad.stackkoberflow.entity.Image;
import com.gachisquad.stackkoberflow.entity.Question;
import com.gachisquad.stackkoberflow.request.QuestionWithImageRequest;
import com.gachisquad.stackkoberflow.services.ImageService;
import com.gachisquad.stackkoberflow.services.QuestionService;
import com.gachisquad.stackkoberflow.services.QuestionWithImageRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController

public class QuestionController {
    public final QuestionService questionService;
    public final ImageService imageService;
    public final QuestionWithImageRequestService qws;

    public QuestionController(QuestionService qs, ImageService is, QuestionWithImageRequestService qws){
        this.questionService = qs;
        this.imageService = is;
        this.qws = qws;
    }

   /* @GetMapping("/question/ask")
    public ModelAndView askQuestion(){
        return new ModelAndView("ask");
    } */

    @PostMapping("/question/add")
    public Long ask(@RequestBody Map<String, String> question){
        Question q = new Question();
        q.setTitle(question.get("title"));
        q.setQuestionItself(question.get("questionItself"));
        q.setAuthor(question.get("author"));
        int numberOfImages = Integer.parseInt(question.get("noi"));
        if (numberOfImages > 0){
            QuestionWithImageRequest qi = new QuestionWithImageRequest(q, numberOfImages, this.questionService, this.qws);
            return (long)qi.getId();
        } else{
            questionService.addQuestion(q);
        }
        return q.getId();
    }

    @PostMapping("/question/{id}/addImage")
    public void addImage(@RequestParam(name = "file") MultipartFile file, @PathVariable Integer id){
        System.out.println(file);
        /*List<Image> li = files
                .stream()
                .map((f) -> toImage(f))
                .collect(Collectors.toList()); */
        Image i = toImage(file);
        QuestionWithImageRequest qi = qws.getByQuestionId(id);
        qi.addImage(i);
    }



    private Image toImage(MultipartFile file){
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
