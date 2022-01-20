package by.epam.finalproject.model.entity;

import java.math.BigDecimal;
import java.time.LocalTime;

/**
 * The type Menu.
 */
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
    private boolean isAccessible;

    /**
     * Instantiates a new Menu.
     */
    public Menu(){}

    /**
     * Instantiates a new Menu.
     *
     * @param foodId       the food id
     * @param nameFood     the name food
     * @param picturePath  the picture path
     * @param composition  the composition
     * @param weight       the weight
     * @param calories     the calories
     * @param cookingTime  the cooking time
     * @param discount     the discount
     * @param price        the price
     * @param sectionId    the section id
     * @param isAccessible the is accessible
     */
    public Menu(long foodId, String nameFood, String picturePath, String composition,
                double weight, double calories, LocalTime cookingTime, BigDecimal discount,
                BigDecimal price, long sectionId, boolean isAccessible) {
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
        this.isAccessible = isAccessible;
    }

    /**
     * Instantiates a new Menu.
     *
     * @param nameFood     the name food
     * @param picturePath  the picture path
     * @param composition  the composition
     * @param weight       the weight
     * @param calories     the calories
     * @param cookingTime  the cooking time
     * @param discount     the discount
     * @param price        the price
     * @param sectionId    the section id
     * @param isAccessible the is accessible
     */
    public Menu(String nameFood, String picturePath, String composition,
                double weight, double calories, LocalTime cookingTime,
                BigDecimal discount, BigDecimal price, long sectionId, boolean isAccessible) {

        this.nameFood = nameFood;
        this.picturePath = picturePath;
        this.composition = composition;
        this.weight = weight;
        this.calories = calories;
        this.cookingTime = cookingTime;
        this.discount = discount;
        this.price = price;
        this.sectionId = sectionId;
        this.isAccessible = isAccessible;
    }


    /**
     * Instantiates a new Menu.
     *
     * @param foodId       the food id
     * @param nameFood     the name food
     * @param composition  the composition
     * @param weight       the weight
     * @param calories     the calories
     * @param cookingTime  the cooking time
     * @param discount     the discount
     * @param price        the price
     * @param sectionId    the section id
     * @param isAccessible the is accessible
     */
    public Menu(long foodId, String nameFood, String composition, double weight,
                double calories, LocalTime cookingTime, BigDecimal discount,
                BigDecimal price, long sectionId, boolean isAccessible) {
        this.foodId = foodId;
        this.nameFood = nameFood;
        this.composition = composition;
        this.weight = weight;
        this.calories = calories;
        this.cookingTime = cookingTime;
        this.discount = discount;
        this.price = price;
        this.sectionId = sectionId;
        this.isAccessible = isAccessible;
    }

    /**
     * Gets food id.
     *
     * @return the food id
     */
    public long getFoodId() {
        return foodId;
    }

    /**
     * Sets food id.
     *
     * @param foodId the food id
     */
    public void setFoodId(long foodId) {
        this.foodId = foodId;
    }

    /**
     * Gets name food.
     *
     * @return the name food
     */
    public String getNameFood() {
        return nameFood;
    }

    /**
     * Sets name food.
     *
     * @param nameFood the name food
     */
    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    /**
     * Gets picture path.
     *
     * @return the picture path
     */
    public String getPicturePath() {
        return picturePath;
    }

    /**
     * Sets picture path.
     *
     * @param picturePath the picture path
     */
    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    /**
     * Gets composition.
     *
     * @return the composition
     */
    public String getComposition() {
        return composition;
    }

    /**
     * Sets composition.
     *
     * @param composition the composition
     */
    public void setComposition(String composition) {
        this.composition = composition;
    }

    /**
     * Gets weight.
     *
     * @return the weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets weight.
     *
     * @param weight the weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Gets calories.
     *
     * @return the calories
     */
    public double getCalories() {
        return calories;
    }

    /**
     * Sets calories.
     *
     * @param calories the calories
     */
    public void setCalories(double calories) {
        this.calories = calories;
    }

    /**
     * Gets cooking time.
     *
     * @return the cooking time
     */
    public LocalTime getCookingTime() {
        return cookingTime;
    }

    /**
     * Sets cooking time.
     *
     * @param cookingTime the cooking time
     */
    public void setCookingTime(LocalTime cookingTime) {
        this.cookingTime = cookingTime;
    }

    /**
     * Gets discount.
     *
     * @return the discount
     */
    public BigDecimal getDiscount() {
        return discount;
    }

    /**
     * Sets discount.
     *
     * @param discount the discount
     */
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets section id.
     *
     * @return the section id
     */
    public long getSectionId() {
        return sectionId;
    }

    /**
     * Sets section id.
     *
     * @param sectionId the section id
     */
    public void setSectionId(long sectionId) {
        this.sectionId = sectionId;
    }

    /**
     * Is accessible boolean.
     *
     * @return the boolean
     */
    public boolean isAccessible() {
        return isAccessible;
    }

    /**
     * Sets accessible.
     *
     * @param accessible the accessible
     */
    public void setAccessible(boolean accessible) {
        isAccessible = accessible;
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
