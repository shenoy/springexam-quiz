package com.springexam.springexam.repository;
import com.springexam.springexam.model.Question;
import com.springexam.springexam.model.QuizQuestion;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface QuizQuestionRepository extends CrudRepository<QuizQuestion,Long> {
    QuizQuestion save(QuizQuestion question);
    List<QuizQuestion> findAll();
    List<QuizQuestion> findByChapterName(String chapterName);
}
