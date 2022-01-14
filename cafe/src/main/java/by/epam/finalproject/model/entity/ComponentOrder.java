package by.epam.finalproject.model.entity;

public class ComponentOrder extends CustomEntity{
    private String nameFood;
    private int amount;

    public ComponentOrder(String nameFood, int amount) {
        this.nameFood = nameFood;
        this.amount = amount;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public int getAmount() {
        return amount;
    }

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
