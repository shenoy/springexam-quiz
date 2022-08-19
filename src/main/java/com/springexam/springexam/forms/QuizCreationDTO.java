package com.springexam.springexam.forms;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class QuizCreationDTO {

    public List<QuizAnswerForm> questions = new ArrayList<>();

    // default and parameterized constructor

    public void addQuestion(QuizAnswerForm question) {
        this.questions.add(question);
    }

    // getter and setter
}