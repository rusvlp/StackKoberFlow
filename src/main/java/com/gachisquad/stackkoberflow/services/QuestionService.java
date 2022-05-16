package com.gachisquad.stackkoberflow.services;

import com.gachisquad.stackkoberflow.entity.Image;
import com.gachisquad.stackkoberflow.entity.Question;
import com.gachisquad.stackkoberflow.exception.NotFoundException;
import com.gachisquad.stackkoberflow.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionService{
    private final QuestionRepository questionRepository;


    public List<Question> getQuestions(){
        return questionRepository.findAll();
    }

    public List<Question> getQuestionsByTitle(String title){
        return questionRepository.findByTitle(title);
    }

    public void addQuestion(Question q){
        Integer rate = Integer.valueOf(0);
        q.setRating(rate);
        log.info("Saved question. Title: " + q.getTitle() + ", author: " + q.getAuthor());
        this.questionRepository.save(q);


    }



    public void deleteQuestion(Long ... ids){
        List<Long> idList = Arrays.asList(ids);
        this.questionRepository.deleteAllById(idList);
    }

    public Question getQuestionById(long id){
        return this.questionRepository.findById(id).orElseThrow(() -> new NotFoundException());  //дефолтная реализация findById(T id) возвращает Optional
    }
}
