package com.springexam.springexam.repository;
import com.springexam.springexam.model.SessionInformation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SessionRepository extends CrudRepository<SessionInformation,Long> {
    SessionInformation save(SessionInformation sessionInformation);
    List<SessionInformation> findAll();
}
