package lk.ijse.dinemore.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Meal")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mealId;
    private String mealName;
    private String mealCategory;
    private double mealUnitPrice;

    @OneToMany(mappedBy = "meal",cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public Meal() {
    }

    public Meal(String mealName, String mealCategory, double mealUnitPrice, List<OrderDetail> orderDetails) {
        this.mealName = mealName;
        this.mealCategory = mealCategory;
        this.mealUnitPrice = mealUnitPrice;
        this.orderDetails = orderDetails;
    }

    public Meal(String mealName, String mealCategory, double mealUnitPrice) {
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

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "mealId=" + mealId +
                ", mealName='" + mealName + '\'' +
                ", mealCategory='" + mealCategory + '\'' +
                ", mealUnitPrice=" + mealUnitPrice +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
