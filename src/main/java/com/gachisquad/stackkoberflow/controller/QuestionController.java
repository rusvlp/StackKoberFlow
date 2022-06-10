package com.gachisquad.stackkoberflow.controller;

import com.gachisquad.stackkoberflow.entity.Image;
import com.gachisquad.stackkoberflow.entity.Question;
import com.gachisquad.stackkoberflow.request.QuestionWithImageRequest;
import com.gachisquad.stackkoberflow.respond.StringRespond;
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
import java.security.Principal;
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
    public Long ask(Question q, Principal principal){

        int numberOfImages = q.noi;
        if (numberOfImages > 0){
            QuestionWithImageRequest qi = new QuestionWithImageRequest(q, numberOfImages, this.questionService, this.qws, principal);
            return (long)qi.getId();
        } else{
            questionService.addQuestion(principal, q);
        }
        return q.getId();
    }

    @GetMapping("/successCreated")
    public ModelAndView successCreated(Map<String, String> body){
        return new ModelAndView("successCreated");
    }

    @PostMapping("/question/{id}/addImage")
    public void addImage(@RequestParam(name = "file") MultipartFile file, @PathVariable Integer id){

        /*List<Image> li = files
                .stream()
                .map((f) -> toImage(f))
                .collect(Collectors.toList()); */
        Image i = toImage(file);
        QuestionWithImageRequest qi = qws.getByQuestionId(id);
        qi.addImage(i);

        //qi.getQuestion().getId();
    }



    @PostMapping("/question/delete/{id}")
    public ModelAndView deleteQuestion(@PathVariable Long id, Principal p){
        System.out.println("deleted question " + id);
        ModelAndView mav = new ModelAndView("successDeleted");
        mav.addObject("questionTitle", questionService.getQuestionById(id).getTitle());
        System.out.println(p);
        questionService.deleteQuestion(id);

        return mav;
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
