package com.springexam.springexam.service;

import com.springexam.springexam.model.Chapter;
import com.springexam.springexam.model.Question;
import com.springexam.springexam.repository.ChapterRepository;
import com.springexam.springexam.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    ChapterRepository chapterRepository;

    @Autowired
    QuestionRepository questionRepository;
    public List<Question> getQuestionsByChapterId(Long id) {
        Optional<Chapter> chapterOptional = chapterRepository.findById(id);

        if(chapterOptional.isPresent()){
            String chapterName = chapterOptional.get().getChapterName();
            List<Question> questionsByChapterName = questionRepository.findByChapterName(chapterName);
            return questionsByChapterName;
        }
        return new ArrayList<>();
    }

    public Question saveQuestion(Question question) {
        System.out.println("saving question");
        return questionRepository.save(question);
    }
}
