package com.springexam.springexam.controller;


import com.springexam.springexam.forms.CreateChapterForm;
import com.springexam.springexam.model.Chapter;
import com.springexam.springexam.service.ChapterService;
import com.springexam.springexam.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
public class ChapterController {

    @Autowired
    ChapterService chapterService;

    @Autowired
    SessionService sessionService;

    @GetMapping("/chapter/create")
    public String createChapter(Model model, HttpSession httpSession, HttpServletRequest request){
        sessionService.saveHttpSessionInfo(httpSession,request);
        model.addAttribute("form", new CreateChapterForm());
        return "createchapter";
    }

    @PostMapping("/chapter/create")
    public String postCreateChapter(CreateChapterForm form,HttpSession httpSession,HttpServletRequest request){
        sessionService.saveHttpSessionInfo(httpSession,request);
        Chapter chapter = new Chapter();
        chapter.setChapterName(form.getChapterName());
        chapterService.saveChapter(chapter);
        return "redirect:/home";
    }
}
