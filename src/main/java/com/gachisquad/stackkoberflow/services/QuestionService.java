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

    public void addQuestion(Question q, List<MultipartFile> files){
        if (files != null && files.size() != 0) {
            List<Image> li = files
                    .stream()
                    .filter(f -> f.getSize() != 0)
                    .map(f -> toImage(f))
                    .collect(Collectors.toList());

            q.addImageToQuestion(li);
            System.out.println(files);
        }
        Integer rate = Integer.valueOf(0);
        q.setRating(rate);
        log.info("Saved question. Title: " + q.getTitle() + ", author: " + q.getAuthor());
        this.questionRepository.save(q);
        System.out.println(q);

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

    public void deleteQuestion(Long ... ids){
        List<Long> idList = Arrays.asList(ids);
        this.questionRepository.deleteAllById(idList);
    }

    public Question getQuestionById(long id){
        return this.questionRepository.findById(id).orElseThrow(() -> new NotFoundException());  //дефолтная реализация findById(T id) возвращает Optional
    }
}
