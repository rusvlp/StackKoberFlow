package com.gachisquad.stackkoberflow.request;

import com.gachisquad.stackkoberflow.entity.Answer;
import com.gachisquad.stackkoberflow.entity.Image;
import com.gachisquad.stackkoberflow.entity.Question;
import com.gachisquad.stackkoberflow.exception.NotFoundException;
import com.gachisquad.stackkoberflow.services.AnswerService;
import com.gachisquad.stackkoberflow.services.AnswerWithImageRequestService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.security.Principal;


public class AnswerWithImageRequest {
    private final AnswerWithImageRequestService aws;
    private Answer answer;
    private int numberOfImages;
    private final AnswerService as;
    private final Principal principal;

    public AnswerWithImageRequest(Answer a, int noi, AnswerWithImageRequestService aws, AnswerService as, Principal p){
        this.answer = a;
        this.numberOfImages = noi;
        this.aws = aws;
        this.as = as;
        this.principal = p;
        aws.pool.add(this);
    }

    public int getId(){
        for (int i = 0; i<aws.pool.size(); i++){
            if (aws.pool.get(i) == this)
                return i;
        }
        throw new NotFoundException();
    }

    public Answer getAnswer() {
        return answer;
    }

    public void addImage(Image i){
        if (this.answer.getImage().size() > this.numberOfImages){
            throw new RuntimeException("Unable to save image, because maximum amount of images is reached");
        }
        System.out.println("added image");
        this.answer.addImageToAnswer(i);
        if (this.answer.getImage().size() == this.numberOfImages){
            System.out.println("question added");
            as.addAnswer(this.answer, principal);
            return;
        }
    }

}
