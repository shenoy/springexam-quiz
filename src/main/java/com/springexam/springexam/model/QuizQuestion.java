package com.springexam.springexam.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="quiz_questions_tbl")
public class QuizQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "question", length = 512)
    private String question;

    @Lob
    @Column(name = "choiceA", length = 512)
    private String choiceA;

    @Lob
    @Column(name = "choiceB", length = 512)
    private String choiceB;

    @Lob
    @Column(name = "choiceC", length = 512)
    private String choiceC;

    @Lob
    @Column(name = "choiceD", length = 512)
    private String choiceD;

    @Lob
    @Column(name = "answer", length = 512)
    private String answer;

    private String chapterName;


}
