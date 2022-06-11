package com.gachisquad.stackkoberflow;

import com.gachisquad.stackkoberflow.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StackKoberFlowApplication {
    @Autowired
    static QuestionService qs;

    public static void main(String[] args) {
        SpringApplication.run(StackKoberFlowApplication.class, args);
        /*for (int i = 1000; i>6 ; i-=7){
            System.out.println(Integer.toString(i) + " - 7 = " + Integer.toString(i - 7));
        }*/


    }

}
