package com.gachisquad.stackkoberflow.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.naming.Name;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "answers")
@Data
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "answerItself")
    private String answerItself;

    @Column(name = "rating")
    private Integer rating;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "answer")
    private List<Image> image = new ArrayList<>();

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn
    private Question question;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn
    private User author;

    public void addImageToAnswer(Image image){
        image.setAnswer(this);
        this.image.add(image);
    }

    @ManyToMany
    private List<User> increased = new ArrayList<>();

    @ManyToMany
    private List<User> decreased = new ArrayList<>();

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

    public int noi;
}
