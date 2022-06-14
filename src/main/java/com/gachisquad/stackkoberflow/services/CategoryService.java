package com.gachisquad.stackkoberflow.services;

import com.gachisquad.stackkoberflow.entity.Category;
import com.gachisquad.stackkoberflow.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id){
        return categoryRepository.getById(id);
    }
}
