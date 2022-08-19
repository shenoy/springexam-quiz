package com.springexam.springexam.service;

import com.springexam.springexam.forms.QuizAnswerForm;
import com.springexam.springexam.model.Quiz;
import com.springexam.springexam.model.QuizQuestion;
import com.springexam.springexam.repository.QuizQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    QuizQuestionRepository quizQuestionRepository;

    @Autowired
    TimerService timerService;

    public List<QuizQuestion> getQuestions(){
        return quizQuestionRepository.findAll();
    }

    public List<QuizAnswerForm> getQuizAnswerForms(){
        List<QuizAnswerForm> quizForms = getQuestions().stream()
                .map(x->{
                   QuizAnswerForm temp = new QuizAnswerForm();
                   temp.setQuestion(x.getQuestion());
                   temp.setChoiceA(x.getChoiceA());
                   temp.setChoiceB(x.getChoiceB());
                   temp.setChoiceC(x.getChoiceC());
                   temp.setChoiceD(x.getChoiceD());
                   temp.setAnswer(x.getAnswer());
                   return temp;
                }).collect(Collectors.toList());
        return quizForms;
    }

}
