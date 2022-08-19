package com.springexam.springexam.repository;
import com.springexam.springexam.model.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuestionRepository extends CrudRepository<Question,Long> {

    List<Question> findByChapterName(String chapterName);

}
