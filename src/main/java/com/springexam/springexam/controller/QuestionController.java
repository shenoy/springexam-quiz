package com.springexam.springexam.controller;


import com.springexam.springexam.forms.CreateQuestionForm;
import com.springexam.springexam.model.Chapter;
import com.springexam.springexam.model.Question;
import com.springexam.springexam.repository.ChapterRepository;
import com.springexam.springexam.repository.QuestionRepository;
import com.springexam.springexam.service.ChapterService;
import com.springexam.springexam.service.QuestionService;
import com.springexam.springexam.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class QuestionController {

    @Autowired
    ChapterService chapterService;

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionRepository repository;

    @Autowired
    ChapterRepository chapterRepository;

    @Autowired
    SessionService sessionService;

    @GetMapping({"/","","/home"})
    public String getOverview(Model model, HttpSession httpSession,HttpServletRequest request){
        model.addAttribute("chapters", chapterService.getAll());
        sessionService.saveHttpSessionInfo(httpSession,request);
        return "index";
    }

    @GetMapping("/about")
    public String getAboutPage(HttpSession httpSession,HttpServletRequest request){
        sessionService.saveHttpSessionInfo(httpSession,request);
        return "about";
    }

    @GetMapping("/question/create")
    public String createQuestion(Model model,HttpSession httpSession,HttpServletRequest request){
        model.addAttribute("form", new CreateQuestionForm());
        model.addAttribute("chapters", chapterService.getAll());
        sessionService.saveHttpSessionInfo(httpSession,request);
        return "createquestion";
    }

    @PostMapping("/question/create")
    public String createQuestion(CreateQuestionForm questionForm,HttpSession httpSession,HttpServletRequest request){
        sessionService.saveHttpSessionInfo(httpSession,request);
        Question question = new Question();
        question.setQuestion(questionForm.getQuestion());
        question.setAnswer(questionForm.getAnswer());
        question.setChapterName(questionForm.getChapterName());
        Question savedQuestion = questionService.saveQuestion(question);
        Optional qop = repository.findById(savedQuestion.getId());
        Chapter chapter = chapterRepository.findByChapterName(questionForm.getChapterName());
        return "redirect:/question/chapter/" + chapter.getId();
    }

    @GetMapping("/question/chapter/{id}")
    public String getQuestionsByChapterId(@PathVariable("id") Long id, Model model, HttpSession httpSession,HttpServletRequest request){
        sessionService.saveHttpSessionInfo(httpSession,request);
        List<Question> questions = questionService.getQuestionsByChapterId(id);
        Optional<Chapter> chaop = chapterRepository.findById(id);
        if(chaop.isPresent()){
            model.addAttribute("chapter",chaop.get());

        }
        model.addAttribute("questions",questions);
        return "questionsbychapterid";
    }

    @GetMapping("/question/edit/{id}")
    public String getEditQuestion(@PathVariable("id")Long id, Model model,HttpSession httpSession,HttpServletRequest request ){
        sessionService.saveHttpSessionInfo(httpSession,request);
        Optional<Question> qop = repository.findById(id);
       if(qop.isPresent()){
           Question question = qop.get();
           model.addAttribute("question",question);
           model.addAttribute("form", new CreateQuestionForm(question.getQuestion(), question.getAnswer(),question.getChapterName()));
           model.addAttribute("chapters", chapterService.getAll());
       }
        return "editquestion";
    }

    @PostMapping("/question/edit/{id}")
    public String getPostEditQuestion(@PathVariable("id")Long id, CreateQuestionForm questionForm,HttpSession httpSession,HttpServletRequest request ){
        sessionService.saveHttpSessionInfo(httpSession,request);
        Question question = new Question();
        question.setId(id);
        question.setQuestion(questionForm.getQuestion());
        question.setAnswer(questionForm.getAnswer());
        question.setChapterName(questionForm.getChapterName());
        questionService.saveQuestion(question);
        Chapter chapter = chapterRepository.findByChapterName(question.getChapterName());
        return "redirect:/question/chapter/" + chapter.getId();
    }

}
