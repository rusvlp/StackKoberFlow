package com.gachisquad.stackkoberflow.controller;

import com.gachisquad.stackkoberflow.entity.Answer;
import com.gachisquad.stackkoberflow.entity.Image;
import com.gachisquad.stackkoberflow.entity.Question;
import com.gachisquad.stackkoberflow.entity.User;
import com.gachisquad.stackkoberflow.request.AnswerWithImageRequest;
import com.gachisquad.stackkoberflow.request.QuestionWithImageRequest;
import com.gachisquad.stackkoberflow.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;
    private final AnswerWithImageRequestService answerWithImageRequestService;
    private final ImageService imageService;
    private final QuestionService questionService;
    private final UserService userService;

    @PostMapping("/question/{id}/addAnswer")
    public Long addAnswer(@PathVariable Long id, Answer a, Principal principal){
        System.out.println(a);
        a.setQuestion(questionService.getQuestionById(id));
        questionService.getQuestionById(id).addAnswer(a);
        int numberOfImages = a.noi;
        if (numberOfImages > 0){
            AnswerWithImageRequest ai = new AnswerWithImageRequest(a, numberOfImages, this.answerWithImageRequestService, this.answerService, principal);
            return (long)ai.getId();
        } else{
            answerService.addAnswer(a, principal);
        }
        System.out.println(a.getId());
        return a.getId();
    }

    @PostMapping("/answer/{id}/addImage")
    public void addImage(@RequestParam(name = "file") MultipartFile file, @PathVariable Integer id){

        /*List<Image> li = files
                .stream()
                .map((f) -> toImage(f))
                .collect(Collectors.toList()); */
        Image i = imageService.toImage(file);
        AnswerWithImageRequest qi = answerWithImageRequestService.getByAnswerId(id);
        qi.addImage(i);

        //qi.getQuestion().getId();
    }

    @PostMapping("/answer/{id}/increaseRating")
    public ModelAndView increase(@PathVariable Long id, Principal p){
        User user = userService.getUserByPrincipal(p);
        Answer answer = answerService.getAnswerById(id);
        if (answer.getIncreased().contains(user)){
            ModelAndView mav = new ModelAndView("unsuccess");
            mav.addObject("text", "повысить рейтинг ответа, так как вы его уже повышали!");
            mav.addObject("user", user);
            return mav;
        }

        if (answer.getDecreased().contains(user)){
            answer.removeDecreased(user);
        } else {
            answer.addIncreased(user);
        }

        answer.setRating(answer.getRating()+1);

        answerService.saveAnswer(answer);
        return new ModelAndView("redirect:/question/" + answer.getQuestion().getId());
    }

    @PostMapping("/answer/{id}/delete")
    public ModelAndView deleteAnswer(@PathVariable Long id, Principal p){
        if(answerService.getAnswerById(id).getAuthor() != userService.getUserByPrincipal(p)){
            ModelAndView mav = new ModelAndView("unsuccessDelete");
            mav.addObject("user", userService.getUserByPrincipal(p));
            return mav;
        }
        ModelAndView mav = new ModelAndView("sda");
        mav.addObject("user", userService.getUserByPrincipal(p));
        answerService.deleteAnswer(id);
        return mav;
    }

    @PostMapping("/answer/{id}/decreaseRating")
    public ModelAndView decrease(@PathVariable Long id, Principal p){
        User user = userService.getUserByPrincipal(p);
        Answer answer = answerService.getAnswerById(id);

        if (answer.getDecreased().contains(user)){
            ModelAndView mav = new ModelAndView("unsuccess");
            mav.addObject("text", "понизить рейтинг ответа, так как вы его уже понижали!");
            mav.addObject("user", user);
            return mav;
        }

        if (answer.getIncreased().contains(user)){
            answer.removeIncreased(user);
        } else {
            answer.addDecreased(user);
        }

        answer.setRating(answer.getRating()-1);


        answerService.saveAnswer(answer);
        return new ModelAndView("redirect:/question/" + answer.getQuestion().getId());
    }

    @PostMapping("/answer/{id}/markAsSolution")
    public ModelAndView markAsSolution(@PathVariable Long id, Principal p){
        Answer a = answerService.getAnswerById(id);
        Question q = a.getQuestion();
        Answer marked = q.getAnswers()
                        .stream()
                        .filter(answer -> answer.getIsSolution() == true)
                        .findFirst()
                        .orElse(null);

        if (marked != null){
            ModelAndView mav = new ModelAndView("unsuccess");
            mav.addObject("user", userService.getUserByPrincipal(p));
            mav.addObject("text", "пометить ответ как решение, так как уже помечен другой ответ");
            return mav;
        }
        a.setIsSolution(true);
        answerService.saveAnswer(a);
        ModelAndView mav = new ModelAndView("redirect:/question/" + q.getId());
        return mav;
    }

    @PostMapping("/answer/{id}/demark")
    public ModelAndView demark(@PathVariable Long id){
        Answer a = answerService.getAnswerById(id);
        a.setIsSolution(false);
        answerService.saveAnswer(a);
        return new ModelAndView("redirect:/question/" + a.getQuestion().getId());
    }

}



