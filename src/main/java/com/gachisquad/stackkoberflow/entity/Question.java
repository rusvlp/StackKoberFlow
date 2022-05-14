package com.gachisquad.stackkoberflow.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "question")
@Data                    // аннотация lombok, добавляющая геттеры и сеттеры на все поля
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "question", columnDefinition = "text")
    private String questionItself;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "author")
    public String author;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "question")   //при загрузке вопроса (к примеру при отображении в списке), все фотографии не загружаются (а зачем нам впустую тратить ресурсы?)
                                                                                            //при удалении вопроса удаляются и все связанные с ним фотографии, при сохранении вопрос сохраняются все связанные с ним фотографии
    private List<Image> images = new ArrayList<>();

    private Long previewImageId;

    private LocalDateTime dateOfCreate;

    @PrePersist                             //аннотация инициализации в спринге
    private void init(){
        dateOfCreate = LocalDateTime.now();
    }

    public void addImageToQuestion(List<Image> images){
        for (Image i: images){
            i.setQuestion(this);
            this.images.add(i);
        }
    }

    public int numberOfImages(){
        return images.size();
    }
}
