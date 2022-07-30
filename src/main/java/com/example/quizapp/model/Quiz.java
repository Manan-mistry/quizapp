package com.example.quizapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quiz")
public class Quiz {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank
        private String question;

        @ElementCollection
        @NotNull
        private List<String> answers = new ArrayList<>();

        @NotNull
        @Min(0)
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private Integer answer;

        public Quiz() {
        }

        public Quiz(String question, List<String> answers, Integer answer) {
                this.question = question;
                this.answers = answers;
                this.answer = answer;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getQuestion() {
                return question;
        }

        public void setQuestion(String question) {
                this.question = question;
        }

        public List<String> getAnswers() {
                return answers;
        }

        public void setAnswers(List<String> answers) {
                this.answers = answers;
        }

        public Integer getAnswer() {
                return answer;
        }

        public void setAnswer(Integer answer) {
                this.answer = answer;
        }
}
