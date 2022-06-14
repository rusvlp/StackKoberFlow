package com.gachisquad.stackkoberflow.controller;

import com.gachisquad.stackkoberflow.entity.Answer;
import com.gachisquad.stackkoberflow.entity.Image;
import com.gachisquad.stackkoberflow.entity.Question;
import com.gachisquad.stackkoberflow.services.CategoryService;
import com.gachisquad.stackkoberflow.services.QuestionService;
import com.gachisquad.stackkoberflow.services.UserService;
import org.springframework.boot.Banner;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {
    private final QuestionService questionService;               //final - при создании контроллера сразу же инжектится поле
    private final UserService userService;
    private final CategoryService categoryService;

    public MainController(QuestionService questionService, UserService us, CategoryService cs) {     // конструктор с полем для сервиса. Спринг сам заинжектит класс, помеченый @Component
        this.questionService = questionService;
        this.userService = us;
        this.categoryService = cs;
    }

    @GetMapping("/question/ask")
    public String ask(Model model, Principal p){
        model.addAttribute("user", userService.getUserByPrincipal(p));
        model.addAttribute("categories", categoryService.getAllCategories());
        return "ask";
    }

    @GetMapping("/")
    public String mainPage(@RequestParam(name = "category", required = false) Long categoryId,  @RequestParam(name = "sort", required = false) String sort, @RequestParam(name = "title", required = false) String title, Principal p, Model model){
        model.addAttribute("user", userService.getUserByPrincipal(p));                                                                                          // Model необходимо для передачи данных в шаблонизатор (у нас не обычный HTML, а FreeMarker)
        model.addAttribute("categories", categoryService.getAllCategories());

        List<Question> questions = questionService.getQuestions();

        if (categoryId != null){
            questions = questions.stream()
                    .filter(q -> {
                        if (q.getCategory() == null)
                            return false;
                        return q.getCategory().getId() == categoryId;
                    })
                    .collect(Collectors.toList());
        }

        if (sort!=null && sort.equals("datenew")){
            List<Question> tmpQuestion = new ArrayList<>();
            for (int i = questions.size()-1; i>=0; i--){
                tmpQuestion.add(questions.get(i));
            }
            questions = tmpQuestion;
        }

        if (sort!=null && sort.equals("best")){
            questions.sort((a, b) -> {
                if (a.getRating() > b.getRating())
                    return -1;
                if(a.getRating() < b.getRating())
                    return 1;
                return 0;
            });
        }

        if (sort!=null && sort.equals("worst")){
            questions.sort((a, b) -> {
                if (a.getRating() > b.getRating())
                    return 1;
                if(a.getRating() < b.getRating())
                    return -1;
                return 0;
            });
        }

        if (title == null){
            model.addAttribute("questions", questions);                       // "questions" - ключ атрибута, qS.gQ() - значение
        } else {
            model.addAttribute("questions", questionService.getQuestionsByTitle(title));
        }

        System.out.println(userService.getUserByPrincipal(p));
        return "main";
    }


    @GetMapping("/question/{id}")
    public String questionInfo(@PathVariable long id, Model model, Principal p){
        Question q = questionService.getQuestionById(id);
        model.addAttribute("question", q);
        model.addAttribute("images", q.getImages());
        model.addAttribute("user", userService.getUserByPrincipal(p));
        Answer solution = q.getAnswers()
                .stream()
                .filter((a) -> a.getIsSolution() == true)
                .findFirst()
                .orElse(null);
        model.addAttribute("solution", solution);
        List<Answer> answers = q.getAnswers();
        if (solution != null){
            answers.remove(solution);
        }

        answers.sort((a, b) -> {
            if (a.getRating() > b.getRating())
                return -1;
            if(a.getRating() < b.getRating())
                return 1;
            return 0;
        });

        model.addAttribute("category", questionService.getQuestionById(id).getCategory());
        model.addAttribute("answers", answers);
        return "question_info";
    }






}
