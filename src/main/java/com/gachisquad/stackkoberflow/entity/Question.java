package com.gachisquad.stackkoberflow.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "question")
@Data                    // аннотация lombok, добавляющая геттеры и сеттеры на все поля
@AllArgsConstructor
@NoArgsConstructor
//@ToString
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

    private Long cid;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "question")   //при загрузке вопроса (к примеру при отображении в списке), все фотографии не загружаются (а зачем нам впустую тратить ресурсы?)
                                                                                            //при удалении вопроса удаляются и все связанные с ним фотографии, при сохранении вопрос сохраняются все связанные с ним фотографии
    private List<Image> images = new ArrayList<>();

    private Long previewImageId;

    private LocalDateTime dateOfCreate;

    @PrePersist                             //аннотация инициализации в спринге
    private void init(){
        dateOfCreate = LocalDateTime.now();
    }

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User author;

    @OneToOne
    private Category category;

    public void addImageToQuestion(Image image){
        image.setQuestion(this);
        this.images.add(image);
    }

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();

    public void addAnswer(Answer a){
        this.answers.add(a);
    }

    public int numberOfImages(){
        return images.size();
    }

    @ManyToMany
    private List<User> increased = new ArrayList<>();

    @ManyToMany
    private List<User> decreased = new ArrayList<>();
    public int noi;

    public void addIncreased(User user){
        this.increased.add(user);
    }

    public void addDecreased(User user){
        this.decreased.add(user);
    }

    public void removeIncreased(User user) {
        this.increased.remove(user);
    }

    public void removeDecreased(User user){
        this.decreased.remove(user);
    }

}
