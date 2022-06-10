package com.gachisquad.stackkoberflow.request;

import com.gachisquad.stackkoberflow.entity.Image;
import com.gachisquad.stackkoberflow.entity.Question;
import com.gachisquad.stackkoberflow.exception.NotFoundException;
import com.gachisquad.stackkoberflow.services.QuestionService;
import com.gachisquad.stackkoberflow.services.QuestionWithImageRequestService;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class QuestionWithImageRequest {
    private final QuestionWithImageRequestService qws;
    private Question question;
    private int numberOfImages;
    private final QuestionService qs;
    private final Principal principal;

    public QuestionWithImageRequest(Question q, int noi, QuestionService qs, QuestionWithImageRequestService qws, Principal principal){
        this.question = q;
        this.numberOfImages = noi;
        this.qs = qs;
        this.qws = qws;
        this.principal = principal;
        qws.pool.add(this);
    }

    public int getId(){
        for (int i = 0; i<qws.pool.size(); i++){
            if (qws.pool.get(i) == this)
                return i;
        }
        throw new NotFoundException();
    }



    public Question getQuestion() {
        return question;
    }


    public void addImage(Image i){
        if (this.question.getImages().size() > this.numberOfImages){
            throw new RuntimeException("Unable to save image, because maximum amount of images is reached");
        }
        System.out.println("added image");
        this.question.addImageToQuestion(i);
        if (this.question.getImages().size() == this.numberOfImages){
            System.out.println("question added");
            qs.addQuestion(principal, this.question);
            return;
        }
    }
}
