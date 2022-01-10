package by.epam.finalproject.model.entity;

import java.math.BigDecimal;
import java.time.LocalTime;

public class Menu extends CustomEntity {

    private long foodId;
    private String nameFood;
    private String picturePath;
    private String composition;
    private double weight;
    private double calories;
    private LocalTime cookingTime;
    private BigDecimal discount;
    private BigDecimal price;
    private long sectionId;

    public Menu(){}

    public Menu(long foodId, String nameFood, String picturePath, String composition,
                double weight, double calories, LocalTime cookingTime, BigDecimal discount,
                BigDecimal price, long sectionId) {
        this.foodId = foodId;
        this.nameFood = nameFood;
        this.picturePath = picturePath;
        this.composition = composition;
        this.weight = weight;
        this.calories = calories;
        this.cookingTime = cookingTime;
        this.discount = discount;
        this.price = price;
        this.sectionId = sectionId;
    }

    public Menu(String nameFood, String picturePath, String composition,
                double weight, double calories, LocalTime cookingTime,
                BigDecimal discount, BigDecimal price, long sectionId) {
        this.nameFood = nameFood;
        this.picturePath = picturePath;
        this.composition = composition;
        this.weight = weight;
        this.calories = calories;
        this.cookingTime = cookingTime;
        this.discount = discount;
        this.price = price;
        this.sectionId = sectionId;
    }

    public long getFoodId() {
        return foodId;
    }

    public void setFoodId(long foodId) {
        this.foodId = foodId;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public LocalTime getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(LocalTime cookingTime) {
        this.cookingTime = cookingTime;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getSectionId() {
        return sectionId;
    }

    public void setSectionId(long sectionId) {
        this.sectionId = sectionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Menu menu = (Menu) o;

        if (foodId != menu.foodId) return false;
        if (Double.compare(menu.weight, weight) != 0) return false;
        if (Double.compare(menu.calories, calories) != 0) return false;
        if (sectionId != menu.sectionId) return false;
        if (nameFood != null ? !nameFood.equals(menu.nameFood) : menu.nameFood != null) return false;
        if (picturePath != null ? !picturePath.equals(menu.picturePath) : menu.picturePath != null) return false;
        if (composition != null ? !composition.equals(menu.composition) : menu.composition != null) return false;
        if (cookingTime != null ? !cookingTime.equals(menu.cookingTime) : menu.cookingTime != null) return false;
        if (discount != null ? !discount.equals(menu.discount) : menu.discount != null) return false;
        return price != null ? price.equals(menu.price) : menu.price == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (foodId ^ (foodId >>> 32));
        result = 31 * result + (nameFood != null ? nameFood.hashCode() : 0);
        result = 31 * result + (picturePath != null ? picturePath.hashCode() : 0);
        result = 31 * result + (composition != null ? composition.hashCode() : 0);
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(calories);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (cookingTime != null ? cookingTime.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (int) (sectionId ^ (sectionId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Menu{");
        sb.append("foodId=").append(foodId);
        sb.append(", nameFood='").append(nameFood).append('\'');
        sb.append(", picturePath='").append(picturePath).append('\'');
        sb.append(", composition='").append(composition).append('\'');
        sb.append(", weight=").append(weight);
        sb.append(", calories=").append(calories);
        sb.append(", cookingTime=").append(cookingTime);
        sb.append(", discount=").append(discount);
        sb.append(", price=").append(price);
        sb.append(", sectionId=").append(sectionId);
        sb.append('}');
        return sb.toString();
    }
}
