package com.springexam.springexam.controller;


import com.springexam.springexam.forms.QuizAnswerForm;
import com.springexam.springexam.forms.QuizCreationDTO;
import com.springexam.springexam.forms.QuizForm;
import com.springexam.springexam.model.Quiz;
import com.springexam.springexam.model.QuizQuestion;
import com.springexam.springexam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class QuizController {

    @Autowired
    QuizService quizService;

    @GetMapping("/quiz")
    public String getQuiz(Model model){
        QuizCreationDTO quizForm = new QuizCreationDTO();
        List<QuizAnswerForm> qlist = quizService.getQuizAnswerForms();
        System.out.println(qlist);
        for (int i = 1; i < qlist.size(); i++) {
            quizForm.addQuestion(qlist.get(i));
        }
        model.addAttribute("form", quizForm);
        return "quiz";
    }

    @PostMapping("/post-answer/")
    public String postQuizAnser(@ModelAttribute QuizCreationDTO form){
        System.out.println(form);
        System.out.println("posted");
        return "redirect:/";
    }

}
