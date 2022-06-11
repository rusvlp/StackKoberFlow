package com.gachisquad.stackkoberflow.services;

import com.gachisquad.stackkoberflow.entity.Image;
import com.gachisquad.stackkoberflow.entity.Question;
import com.gachisquad.stackkoberflow.entity.User;
import com.gachisquad.stackkoberflow.exception.NotFoundException;
import com.gachisquad.stackkoberflow.repository.QuestionRepository;
import com.gachisquad.stackkoberflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionService{
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public List<Question> getQuestions(){
        return questionRepository.findAll();
    }

    public List<Question> getQuestionsByTitle(String title){
        return questionRepository.findByTitle(title);
    }

    public void addQuestion(Principal principal, Question q){
        q.setAuthor(getUserByPrincipal(principal));
        Integer rate = Integer.valueOf(0);
        q.setRating(rate);
        //log.info("Saved question. Title: " + q.getTitle() + ", author: " + q.getAuthor());
        this.questionRepository.save(q);


    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    @Transactional
    public void deleteQuestion(Long id){

        this.questionRepository.deleteById(id);
    }

    public Question getQuestionById(long id){
        return this.questionRepository.findById(id).orElseThrow(() -> new NotFoundException());  //дефолтная реализация findById(T id) возвращает Optional
    }
}
