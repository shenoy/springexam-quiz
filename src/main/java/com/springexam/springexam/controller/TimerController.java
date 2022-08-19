package com.springexam.springexam.controller;


import com.springexam.springexam.service.TimerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Timer;
import java.util.TimerTask;

@Controller
public class TimerController {

  static Timer timer;

  int minutesRemaining = 5*60;

    @GetMapping("/timer")
    @ResponseBody
    public String getTimer(Model model){
        timer = new Timer();
        model.addAttribute("timer", timer);
        return "hello world";

    }

    @GetMapping("/start")
    @ResponseBody
    public String startTimer(){
        timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                minutesRemaining--;
            }
        };
        timer.scheduleAtFixedRate(task, 1000, 1000);
        return "Started Timer";
    }

    @GetMapping("/stop")
    @ResponseBody
    public String stopTimer(){
        timer.cancel();
        timer.purge();
        minutesRemaining = 5*60;
        return "Stopped timer";
    }

    @GetMapping("/minutes")
    @ResponseBody
    public ResponseEntity<?> getMinutes(){
        return new ResponseEntity<>(getTimeString(minutesRemaining), HttpStatus.OK);
    }

    public String getTimeString(int s){

        int sec = s % 60;
        int min = (s / 60)%60;
        int hours = (s/60)/60;

        String strSec=(sec<10)?"0"+Integer.toString(sec):Integer.toString(sec);
        String strmin=(min<10)?"0"+Integer.toString(min):Integer.toString(min);
        String strHours=(hours<10)?"0"+Integer.toString(hours):Integer.toString(hours);

        return strHours + ":" + strmin + ":" + strSec;

    }
}
