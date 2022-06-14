package com.gachisquad.stackkoberflow.services;

import com.gachisquad.stackkoberflow.entity.Answer;
import com.gachisquad.stackkoberflow.exception.NotFoundException;
import com.gachisquad.stackkoberflow.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final UserService userService;
    public void addAnswer(Answer answer, Principal principal){

        answer.setAuthor(userService.getUserByPrincipal(principal));
        userService.getUserByPrincipal(principal).addAnswer(answer);
        System.out.println("added answer: " + answer.toString());
        answer.setId(null);
        answer.setIsSolution(false);
        answer.setRating(0);
        answerRepository.save(answer);
    }

    public void saveAnswer(Answer a){
        answerRepository.save(a);
    }

    public Answer getAnswerById(Long id){
        return this.answerRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }

    @Transactional
    public void deleteAnswer(Long id){
        System.out.println("deleted answer" + id);
        this.answerRepository.delete(this.answerRepository.getById(id));
    }
}
