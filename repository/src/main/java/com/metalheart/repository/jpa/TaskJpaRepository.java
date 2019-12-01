package com.metalheart.repository.jpa;

import com.metalheart.model.jpa.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskJpaRepository extends JpaRepository<Task, Integer> {

    List<Task> findAllByOrderByPriorityAsc();

    @Query("SELECT DISTINCT t from Task t "
        + "INNER JOIN FETCH t.tags tag where tag.id in :tagIds "
        + "ORDER BY t.priority")
    List<Task> findAllByTags(@Param("tagIds") List<Integer> tagIds);
}