package com.springexam.springexam.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import java.util.Timer;
import java.util.TimerTask;

@Service
@Getter
@Setter
public class TimerService {

    public int secondsRemaining=5*60;
    public String minutes;
    public String minuteStoppedAt;

    Timer timer = new Timer();


    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            secondsRemaining--;
            minutes=getTimeString(secondsRemaining);
        }
    };

    public void  start(){
        timer.scheduleAtFixedRate(timerTask,1000,1000);
    }

    public void  cancel(){
        timerTask.cancel();
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
