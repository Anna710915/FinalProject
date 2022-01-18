package by.epam.finalproject.model.entity;

/**
 * The type Component order.
 */
public class ComponentOrder extends CustomEntity{
    private String nameFood;
    private int amount;

    /**
     * Instantiates a new Component order.
     *
     * @param nameFood the name food
     * @param amount   the amount
     */
    public ComponentOrder(String nameFood, int amount) {
        this.nameFood = nameFood;
        this.amount = amount;
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
     * Gets amount.
     *
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets amount.
     *
     * @param amount the amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComponentOrder that = (ComponentOrder) o;

        if (amount != that.amount) return false;
        return nameFood != null ? nameFood.equals(that.nameFood) : that.nameFood == null;
    }

    @Override
    public int hashCode() {
        int result = nameFood != null ? nameFood.hashCode() : 0;
        result = 31 * result + amount;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ComponentOrder{");
        sb.append("nameFood='").append(nameFood).append('\'');
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }
}
