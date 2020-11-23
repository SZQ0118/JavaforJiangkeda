package com.neusoft.dao;

import com.neusoft.domain.Food;

import java.util.List;

public interface FoodDao {
    public List<Food> listFoodByBuinessId(Integer BusinessId);
    public int saveFood(Food food);
    public int updateFood(Food food);
    public int removeFood(Integer foodId);
    public Food getFoodById(Integer foodId);
}
