package com.depromeet.warmup1.repository;

import com.depromeet.warmup1.entity.Category;
import com.depromeet.warmup1.entity.Connect;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);

    List<Category> findAllByConnect(Connect connect);
}
