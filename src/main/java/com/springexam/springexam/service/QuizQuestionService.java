package com.springexam.springexam.service;


import com.springexam.springexam.model.Chapter;
import com.springexam.springexam.model.Question;
import com.springexam.springexam.model.QuizQuestion;
import com.springexam.springexam.repository.ChapterRepository;
import com.springexam.springexam.repository.QuizQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizQuestionService {

    @Autowired
    ChapterRepository chapterRepository;

    @Autowired
    QuizQuestionRepository quizQuestionRepository;

    public QuizQuestion saveQuestion(QuizQuestion question) {
        System.out.println("saving question");
        return quizQuestionRepository.save(question);
    }

    public List<QuizQuestion> getQuizQuestionsByChapterId(Long id) {
        Optional<Chapter> chapterOptional = chapterRepository.findById(id);

        if(chapterOptional.isPresent()){
            String chapterName = chapterOptional.get().getChapterName();
            List<QuizQuestion> questionsByChapterName = quizQuestionRepository.findByChapterName(chapterName);
            return questionsByChapterName;
        }
        return new ArrayList<>();
    }

    public List<QuizQuestion> getQuestionsByChapterId(Long id) {
        Optional<Chapter> chapterOptional = chapterRepository.findById(id);
        if(chapterOptional.isPresent()){
            String chapterName = chapterOptional.get().getChapterName();
            List<QuizQuestion> questionsByChapterName = quizQuestionRepository.findByChapterName(chapterName);
            return questionsByChapterName;
        }
        return new ArrayList<>();
    }
}
