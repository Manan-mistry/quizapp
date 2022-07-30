package com.example.quizapp.model;


import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "master_quiz")

public class MasterQuiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @ElementCollection
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @NotNull
    private List<Quiz>quizzes;

    public MasterQuiz() {
    }

    public MasterQuiz(String title, String description, List<Quiz> quizzes) {
        this.title = title;
        this.description = description;
        this.quizzes = quizzes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }
}
