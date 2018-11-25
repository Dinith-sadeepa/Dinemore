package lk.ijse.dinemore.dto;

import java.util.Date;

public class MealChefDTO {
    private int mealId;
    private String mealName;
    private String mealCategory;
    private int qty;

    public MealChefDTO() {
    }

    public MealChefDTO(int mealId, String mealName, String mealCategory, int qty) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.mealCategory = mealCategory;
        this.qty = qty;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealCategory() {
        return mealCategory;
    }

    public void setMealCategory(String mealCategory) {
        this.mealCategory = mealCategory;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
