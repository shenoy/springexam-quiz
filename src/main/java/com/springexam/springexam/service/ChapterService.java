package com.springexam.springexam.service;

import com.springexam.springexam.model.Chapter;
import com.springexam.springexam.repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterService {

    @Autowired
    ChapterRepository repository;

    public List<Chapter> getAll(){
        return repository.findAll();
    }

    public void saveChapter(Chapter chapter){
//        Chapter chapterToSave = chapter;
//        chapter.setId((long)(getAll().size() + 1));
        repository.save(chapter);
    }
}
