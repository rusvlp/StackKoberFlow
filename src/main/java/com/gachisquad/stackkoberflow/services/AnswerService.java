package com.gachisquad.stackkoberflow.services;

import com.gachisquad.stackkoberflow.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

}
