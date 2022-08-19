package com.springexam.springexam.service;
import com.springexam.springexam.model.SessionInformation;
import com.springexam.springexam.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class SessionService {

    @Autowired
    SessionRepository sessionRepository;


    public void saveHttpSessionInfo(HttpSession httpSession, HttpServletRequest request){

        System.out.println(httpSession.getId());
        System.out.println(convertMillis(httpSession.getCreationTime()));
        System.out.println(request.getRequestURI());
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
            System.out.println(ipAddress);
        }
        SessionInformation sessionInformation = new SessionInformation();
        sessionInformation.setSessionId( httpSession.getId());
        sessionInformation.setCreationTime(convertMillis(httpSession.getCreationTime()));
        sessionInformation.setIpAddress(ipAddress);
        sessionInformation.setUrl(request.getRequestURI());
        sessionRepository.save(sessionInformation);
    }

    public Timestamp convertMillis(Long millis){
        var instance = java.time.Instant.ofEpochMilli(millis);
        var localDateTime = java.time.LocalDateTime
                .ofInstant(instance, java.time.ZoneId.of("Europe/London"));
       return Timestamp.valueOf(localDateTime);
    }


}
