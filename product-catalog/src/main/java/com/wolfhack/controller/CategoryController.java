package com.wolfhack.controller;

import com.wolfhack.mapper.CategoryMapper;
import com.wolfhack.model.domain.Category;
import com.wolfhack.model.dto.CategoryCreationDTO;
import com.wolfhack.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;
	private final CategoryMapper categoryMapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public long create(@RequestBody @Valid CategoryCreationDTO categoryCreationDTO) {
		Category model = categoryMapper.toModel(categoryCreationDTO);
		return categoryService.addCategory(model);
	}

	@DeleteMapping("/{id}")
	public void create(@PathVariable Long id) {
		categoryService.removeCategory(id);
	}

}
