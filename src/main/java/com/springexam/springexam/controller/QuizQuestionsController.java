package com.springexam.springexam.controller;
import com.springexam.springexam.forms.CreateQuestionForm;
import com.springexam.springexam.forms.CreateQuizQuestionForm;
import com.springexam.springexam.model.Chapter;
import com.springexam.springexam.model.Question;
import com.springexam.springexam.model.QuizQuestion;
import com.springexam.springexam.repository.ChapterRepository;
import com.springexam.springexam.repository.QuestionRepository;
import com.springexam.springexam.repository.QuizQuestionRepository;
import com.springexam.springexam.service.ChapterService;
import com.springexam.springexam.service.QuizQuestionService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class QuizQuestionsController {



    @Autowired
    SessionService sessionService;

    @Autowired
    ChapterService chapterService;

    @Autowired
    QuizQuestionService quizQuestionService;

    @Autowired
    QuizQuestionRepository quizQuestionRepository;

    @Autowired
    ChapterRepository chapterRepository;

    @GetMapping("/quiz-question/create")
    public String createQuestion(Model model, HttpSession httpSession, HttpServletRequest request){
        model.addAttribute("form", new CreateQuizQuestionForm());
        model.addAttribute("chapters", chapterService.getAll());
        sessionService.saveHttpSessionInfo(httpSession,request);
        return "create-quiz-question";
    }

    @PostMapping("/quiz-question/create")
    public String createQuestion(CreateQuizQuestionForm questionForm,HttpSession httpSession,HttpServletRequest request){
        sessionService.saveHttpSessionInfo(httpSession,request);
        QuizQuestion question = new QuizQuestion();
        question.setQuestion(questionForm.getQuestion());
        question.setChoiceA(questionForm.getChoiceA());
        question.setChoiceB(questionForm.getChoiceB());
        question.setChoiceC(questionForm.getChoiceC());
        question.setChoiceD(questionForm.getChoiceD());
        question.setAnswer(questionForm.getAnswer());
        question.setChapterName(questionForm.getChapterName());
        QuizQuestion savedQuestion = quizQuestionService.saveQuestion(question);
        Chapter chapter = chapterRepository.findByChapterName(questionForm.getChapterName());
        return "redirect:/quiz-question/chapter/" + chapter.getId();
    }


    @GetMapping("/quiz-question/chapters")
    public String getQuizQuestionIndex(Model model, HttpSession httpSession, HttpServletRequest request){
        sessionService.saveHttpSessionInfo(httpSession,request);
        model.addAttribute("chapters", chapterService.getAll());
        return "quiz-questions-chapters";
    }

    @GetMapping("/quiz-question/chapter/{id}")
    public String getQuizQuestionsByChapter(Model model, @PathVariable("id") Long id, HttpSession httpSession, HttpServletRequest request){
        sessionService.saveHttpSessionInfo(httpSession,request);
        List<QuizQuestion> questions = quizQuestionService.getQuestionsByChapterId(id);
        Optional<Chapter> chaop = chapterRepository.findById(id);
        if(chaop.isPresent()){
            model.addAttribute("chapter",chaop.get());
        }
        model.addAttribute("questions",questions);
        return "quiz-questions-chapterId";
    }

    @GetMapping("/quiz-question/edit/{id}")
    public String getEditQuestion(@PathVariable("id")Long id, Model model,HttpSession httpSession,HttpServletRequest request ){
        sessionService.saveHttpSessionInfo(httpSession,request);
        Optional<QuizQuestion> qop = quizQuestionRepository.findById(id);
        if(qop.isPresent()){
            QuizQuestion question = qop.get();
            model.addAttribute("question",question);
            model.addAttribute("form",
                    new CreateQuizQuestionForm(question.getQuestion(),
                    question.getChoiceA(),
                    question.getChoiceB(),
                    question.getChoiceC(),
                    question.getChoiceD(),
                    question.getAnswer(),
                    question.getChapterName()));
            model.addAttribute("chapters", chapterService.getAll());
        }
        return "edit-quiz-question";
    }

    @PostMapping("/quiz-question/edit/{id}")
    public String getPostEditQuestion(@PathVariable("id")Long id, CreateQuizQuestionForm questionForm,
                                      HttpSession httpSession,HttpServletRequest request ){
        sessionService.saveHttpSessionInfo(httpSession,request);
        QuizQuestion question = new QuizQuestion();
        question.setId(id);
        question.setQuestion(questionForm.getQuestion());
        question.setChoiceA(questionForm.getChoiceA());
        question.setChoiceB(questionForm.getChoiceB());
        question.setChoiceC(questionForm.getChoiceC());
        question.setChoiceD(questionForm.getChoiceD());
        question.setAnswer(questionForm.getAnswer());
        question.setChapterName(questionForm.getChapterName());
        quizQuestionService.saveQuestion(question);
        Chapter chapter = chapterRepository.findByChapterName(question.getChapterName());
        return "redirect:/quiz-question/chapter/" + chapter.getId();
    }

    @GetMapping("/quiz-question/{id}")
    public String getQuestion(@PathVariable("id")Long id, Model model,HttpSession httpSession,HttpServletRequest request ){
        sessionService.saveHttpSessionInfo(httpSession,request);
        Optional<QuizQuestion> qop = quizQuestionRepository.findById(id);
        if(qop.isPresent()) {
            QuizQuestion question = qop.get();
            model.addAttribute("question",question);
            model.addAttribute("chapterId", chapterRepository.findByChapterName(question.getChapterName()).getId());
        }
        return "quiz-question";
    }

}
