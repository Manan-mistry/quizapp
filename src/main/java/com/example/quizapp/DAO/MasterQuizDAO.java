package com.example.quizapp.DAO;

import com.example.quizapp.model.MasterQuiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterQuizDAO extends JpaRepository<MasterQuiz,Long> {
}
