package com.metalheart.repository.jpa;

import com.metalheart.model.jpa.TagJpa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagJpaRepository extends JpaRepository<TagJpa, Integer> {

    boolean existsByUserIdAndTitle(Integer userId, String title);

    TagJpa findTagByUserIdAndTitle(Integer userId, String title);

    List<TagJpa> findAllByUserId(Integer userId);
}