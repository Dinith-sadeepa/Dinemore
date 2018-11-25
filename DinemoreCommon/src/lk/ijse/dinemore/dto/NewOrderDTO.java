package lk.ijse.dinemore.dto;

public class NewOrderDTO {
    private int mealId;
    private String mealName;
    private String mealCategory;
    private double mealUnitPrice;
    private int mealQty;
    private double amount;

    public NewOrderDTO() {
    }

    public NewOrderDTO(int mealId, String mealName, String mealCategory, double mealUnitPrice, int mealQty, double amount) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.mealCategory = mealCategory;
        this.mealUnitPrice = mealUnitPrice;
        this.mealQty = mealQty;
        this.amount = amount;
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

    public int getMealQty() {
        return mealQty;
    }

    public void setMealQty(int mealQty) {
        this.mealQty = mealQty;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
