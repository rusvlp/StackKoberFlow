package com.gachisquad.stackkoberflow.services;

import com.gachisquad.stackkoberflow.request.QuestionWithImageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionWithImageRequestService {
    public List<QuestionWithImageRequest> pool = new ArrayList<>();

    public QuestionWithImageRequest getByQuestionId(int id){
        return this.pool.get(id);
    }
}
