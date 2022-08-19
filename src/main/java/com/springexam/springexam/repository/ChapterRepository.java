package com.springexam.springexam.repository;

import com.springexam.springexam.model.Chapter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterRepository extends CrudRepository<Chapter,Long> {

    List<Chapter> findAll();

    Chapter findByChapterName(String chapterName);
}
