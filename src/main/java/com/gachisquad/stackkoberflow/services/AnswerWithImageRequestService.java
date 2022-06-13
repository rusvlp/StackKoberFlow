package com.gachisquad.stackkoberflow.services;

import com.gachisquad.stackkoberflow.request.AnswerWithImageRequest;
import com.gachisquad.stackkoberflow.request.QuestionWithImageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerWithImageRequestService {
    public List<AnswerWithImageRequest> pool = new ArrayList<>();

    public AnswerWithImageRequest getByAnswerId(int id){
        return this.pool.get(id);
    }
}
