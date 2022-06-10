package com.gachisquad.stackkoberflow.controller;

import com.gachisquad.stackkoberflow.entity.Image;
import com.gachisquad.stackkoberflow.entity.Question;
import com.gachisquad.stackkoberflow.services.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    private final QuestionService questionService;               //final - при создании контроллера сразу же инжектится поле

    public MainController(QuestionService questionService) {     // конструктор с полем для сервиса. Спринг сам заинжектит класс, помеченый @Component
        this.questionService = questionService;
    }

    @GetMapping("/question/ask")
    public String ask(){
        return "ask";
    }

    @GetMapping("/")
    public String mainPage(@RequestParam(name = "title", required = false) String title, Principal p, Model model){                        // Model необходимо для передачи данных в шаблонизатор (у нас не обычный HTML, а FreeMarker)
        if (title == null){
            model.addAttribute("questions", questionService.getQuestions());                       // "questions" - ключ атрибута, qS.gQ() - значение
        } else {
            model.addAttribute("questions", questionService.getQuestionsByTitle(title));
        }
        model.addAttribute("user", questionService.getUserByPrincipal(p));
        System.out.println(questionService.getUserByPrincipal(p));
        return "main";
    }


    @GetMapping("/question/{id}")
    public String questionInfo(@PathVariable long id, Model model){
        Question q = questionService.getQuestionById(id);
        model.addAttribute("question", q);
        model.addAttribute("images", q.getImages());

        return "question_info";
    }






}
