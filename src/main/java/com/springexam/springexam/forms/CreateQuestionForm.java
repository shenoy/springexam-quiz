package com.springexam.springexam.forms;
import com.springexam.springexam.model.Chapter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateQuestionForm {
    private String question;
    private String answer;
    private String chapterName;
}
