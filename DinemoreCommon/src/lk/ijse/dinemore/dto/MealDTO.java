package lk.ijse.dinemore.dto;

import java.io.Serializable;

public class MealDTO implements Serializable {
    private int mealId;
    private String mealName;
    private String mealCategory;
    private double mealUnitPrice;

    public MealDTO() {
    }

    public MealDTO(int mealId, String mealName, String mealCategory, double mealUnitPrice) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.mealCategory = mealCategory;
        this.mealUnitPrice = mealUnitPrice;
    }

    public MealDTO(String mealName, String mealCategory, double mealUnitPrice) {
        this.mealName = mealName;
        this.mealCategory = mealCategory;
        this.mealUnitPrice = mealUnitPrice;
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

    public double getMealUnitPrice() {
        return mealUnitPrice;
    }

    public void setMealUnitPrice(double mealUnitPrice) {
        this.mealUnitPrice = mealUnitPrice;
    }

    @Override
    public String toString() {
        return "MealDTO{" +
                "mealId=" + mealId +
                ", mealName='" + mealName + '\'' +
                ", mealCategory='" + mealCategory + '\'' +
                ", mealUnitPrice=" + mealUnitPrice +
                '}';
    }
}
