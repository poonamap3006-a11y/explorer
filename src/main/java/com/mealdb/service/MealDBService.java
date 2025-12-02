package com.mealdb.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mealdb.model.CategoryList;
import com.mealdb.model.Meal;
import com.mealdb.model.MealList;
import com.mealdb.model.MealResponse;

import exception.ResourceNotFoundException;

@Service
public class MealDBService {

	@Autowired
	private RestTemplate restTemplate;

	private static final String API_URL = "https://www.themealdb.com/api/json/v1/1";

	public MealList searchMeals(String name) {
		String url = API_URL + "/search.php?s=" + name;

		MealList response = restTemplate.getForObject(url, MealList.class);

		if (response == null || response.getMeals() == null) {
			MealList emptyListWrapper = new MealList();
			emptyListWrapper.setMeals(new ArrayList<>());
			return emptyListWrapper;
		}
		return response;
	}

	public CategoryList getCategories() {
		String url = API_URL + "/categories.php";
		return restTemplate.getForObject(url, CategoryList.class);

	}

	public MealResponse getCategoryItem(String name) {
		String url = API_URL + "/filter.php?c=" + name;
//		return restTemplate.getForObject(url, CategoryList.class);

		return restTemplate.getForObject(url, MealResponse.class);

	}

	public Meal getMealById(String id) {
		String url = API_URL + "/lookup.php?i=";
		MealResponse response = restTemplate.getForObject(url + id, MealResponse.class);
		if (response == null) {
			throw new ResourceNotFoundException("Meal with ID " + id + " not found.");
		}

		if (response != null && response.getMeals() != null && !response.getMeals().isEmpty()) {
			return response.getMeals().get(0); // return first meal
		}

		return null;
	}

	public MealResponse searchMealRandom() {
		String url = API_URL + "/random.php";
		return restTemplate.getForObject(url, MealResponse.class);

	}
}
