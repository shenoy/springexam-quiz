package com.springexam.springexam.controller;


import com.springexam.springexam.repository.SessionRepository;
import com.springexam.springexam.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SessionController {

    @Autowired
    SessionService sessionService;

    @Autowired
    SessionRepository sessionRepository;

    @GetMapping("/sessions")
    public String getAllSessions(Model model){
        Long count = sessionRepository.findAll()
                        .stream().map(x->x.getSessionId())
                        .distinct().count();
        model.addAttribute("count", count);
        model.addAttribute("sessions", sessionRepository.findAll());
        return "sessions";
    }
}
