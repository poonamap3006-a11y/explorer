package com.mealdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mealdb.model.CategoryList;
import com.mealdb.model.Meal;
import com.mealdb.model.MealList;
import com.mealdb.model.MealResponse;
import com.mealdb.service.MealDBService;

@RestController
@RequestMapping("/api/meal")
@CrossOrigin(origins = "http://localhost:4200")
public class MealsController {

	@Autowired
	private MealDBService service;

	@GetMapping("/search/{name}")
	private ResponseEntity<?> searchMeal(@PathVariable("name") String name) {

		if (name == null || name.trim().isEmpty()) {
			return new ResponseEntity<>("Search name cannot be empty.", HttpStatus.BAD_REQUEST);
		}

		MealList meals = service.searchMeals(name);

		if (meals == null || meals.getMeals() == null || meals.getMeals().isEmpty()) {
			return new ResponseEntity<>(meals, HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(meals, HttpStatus.OK);
	}

	@GetMapping("/allCategories")
	private ResponseEntity<?> getAllCategories() {

		CategoryList meals = service.getCategories();
		return new ResponseEntity<>(meals, HttpStatus.OK);

	}

	@GetMapping("/getCategorieItem/{name}")
	private ResponseEntity<?> getCategorieItem(@PathVariable("name") String name) {

		if (name == null || name.trim().isEmpty()) {
			return new ResponseEntity<>("Category name cannot be empty.", HttpStatus.BAD_REQUEST);
		}

		MealResponse meals = service.getCategoryItem(name);
		return new ResponseEntity<>(meals, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getMeal(@PathVariable String id) {
		if (id == null || id.trim().isEmpty()) {
			return new ResponseEntity<>("Meal ID cannot be empty.", HttpStatus.BAD_REQUEST);
		}
		Meal meal = service.getMealById(id);
		return new ResponseEntity<>(meal, HttpStatus.OK);
	}

	@GetMapping("/random")
	public ResponseEntity<?> searchMealRandom() {
		MealResponse response = service.searchMealRandom();
		return ResponseEntity.ok(response);
	}

}
