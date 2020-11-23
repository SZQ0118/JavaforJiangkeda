package com.neusoft.View;

import com.neusoft.domain.Food;

import java.util.List;

public interface FoodView {
    public List<Food> showFoodList(Integer businessId);
    public void saveFood(Integer businessId);
    public void updateFood(Integer businessId);
    public void removeeFood(Integer businessId);

}
