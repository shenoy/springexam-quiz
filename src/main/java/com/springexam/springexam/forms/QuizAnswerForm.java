package com.springexam.springexam.forms;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuizAnswerForm {
     String question;
     String choiceA;
     String choiceB;
     String choiceC;
     String choiceD;
     String answer;
}
