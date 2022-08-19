package com.springexam.springexam.forms;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizForm {

    public List<QuizAnswerForm> list = new ArrayList<>();


}
