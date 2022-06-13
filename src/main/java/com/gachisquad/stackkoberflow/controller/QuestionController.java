package com.gachisquad.stackkoberflow.controller;

import com.gachisquad.stackkoberflow.entity.Image;
import com.gachisquad.stackkoberflow.entity.Question;
import com.gachisquad.stackkoberflow.entity.User;
import com.gachisquad.stackkoberflow.request.QuestionWithImageRequest;
import com.gachisquad.stackkoberflow.respond.StringRespond;
import com.gachisquad.stackkoberflow.services.ImageService;
import com.gachisquad.stackkoberflow.services.QuestionService;
import com.gachisquad.stackkoberflow.services.QuestionWithImageRequestService;
import com.gachisquad.stackkoberflow.services.UserService;
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
    private final QuestionService questionService;
    private final ImageService imageService;
    private final QuestionWithImageRequestService qws;
    private final UserService userService;

    public QuestionController(QuestionService qs, ImageService is, QuestionWithImageRequestService qws, UserService us){
        this.questionService = qs;
        this.imageService = is;
        this.qws = qws;
        this.userService = us;
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
    public ModelAndView successCreated(Principal p){
        ModelAndView mav = new ModelAndView("successCreated");
        mav.addObject("user", userService.getUserByPrincipal(p));
        return mav;
    }

    @PostMapping("/question/{id}/addImage")
    public void addImage(@RequestParam(name = "file") MultipartFile file, @PathVariable Integer id){

        /*List<Image> li = files
                .stream()
                .map((f) -> toImage(f))
                .collect(Collectors.toList()); */
        Image i = imageService.toImage(file);
        QuestionWithImageRequest qi = qws.getByQuestionId(id);
        qi.addImage(i);

        //qi.getQuestion().getId();
    }



    @PostMapping("/question/delete/{id}")
    public ModelAndView deleteQuestion(@PathVariable Long id, Principal p){
        if (userService.getUserByPrincipal(p) != questionService.getQuestionById(id).getAuthor()){
            ModelAndView mav = new ModelAndView("unsuccessDelete");
        }
        System.out.println("deleted question " + id);
        ModelAndView mav = new ModelAndView("successDeleted");
        mav.addObject("questionTitle", questionService.getQuestionById(id).getTitle());
        System.out.println(p);
        questionService.deleteQuestion(id);

        return mav;
    }

    @PostMapping("/question/{id}/increaseRating")
    public ModelAndView increase(@PathVariable Long id, Principal p){
        User user = userService.getUserByPrincipal(p);
        Question question = questionService.getQuestionById(id);
        if (question.getIncreased().contains(user)){
            ModelAndView mav = new ModelAndView("unsuccess");
            mav.addObject("text", "повысить рейтинг вопроса, так как вы его уже повышали!");
            mav.addObject("user", user);
            return mav;
        }

        if (question.getDecreased().contains(user)){
            question.removeDecreased(user);
        } else {
            question.addIncreased(user);
        }

        question.setRating(question.getRating()+1);

        questionService.saveQuestion(question);
        return new ModelAndView("redirect:/question/" + id);
    }

    @PostMapping("/question/{id}/decreaseRating")
    public ModelAndView decrease(@PathVariable Long id, Principal p){
        User user = userService.getUserByPrincipal(p);
        Question question = questionService.getQuestionById(id);

        if (question.getDecreased().contains(user)){
            ModelAndView mav = new ModelAndView("unsuccess");
            mav.addObject("text", "понизить рейтинг вопроса, так как вы его уже понижали!");
            mav.addObject("user", user);
            return mav;
        }

        if (question.getIncreased().contains(user)){
            question.removeIncreased(user);
        } else {
            question.addDecreased(user);
        }

        question.setRating(question.getRating()-1);


        questionService.saveQuestion(question);
        return new ModelAndView("redirect:/question/" + id);
    }
}
