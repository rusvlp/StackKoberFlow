package com.gachisquad.stackkoberflow.repository;

import com.gachisquad.stackkoberflow.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
