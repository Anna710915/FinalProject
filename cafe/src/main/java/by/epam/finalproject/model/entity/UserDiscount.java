package by.epam.finalproject.model.entity;

import java.math.BigDecimal;

/**
 * The type User discount.
 */
public class UserDiscount extends CustomEntity{
    private long discountId;
    private BigDecimal discount;
    private int yearOrders;

    /**
     * Instantiates a new User discount.
     */
    public UserDiscount(){}

    /**
     * Instantiates a new User discount.
     *
     * @param discount   the discount
     * @param yearOrders the year orders
     */
    public UserDiscount(BigDecimal discount, int yearOrders) {
        this.discount = discount;
        this.yearOrders = yearOrders;
    }

    /**
     * Instantiates a new User discount.
     *
     * @param discountId the discount id
     * @param discount   the discount
     * @param yearOrders the year orders
     */
    public UserDiscount(long discountId, BigDecimal discount, int yearOrders){
        this.discountId = discountId;
        this.discount = discount;
        this.yearOrders = yearOrders;
    }

    /**
     * Gets discount id.
     *
     * @return the discount id
     */
    public long getDiscountId() {
        return discountId;
    }

    /**
     * Sets discount id.
     *
     * @param discountId the discount id
     */
    public void setDiscountId(long discountId) {
        this.discountId = discountId;
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
     * Gets year orders.
     *
     * @return the year orders
     */
    public int getYearOrders() {
        return yearOrders;
    }

    /**
     * Sets year orders.
     *
     * @param yearOrders the year orders
     */
    public void setYearOrders(int yearOrders) {
        this.yearOrders = yearOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDiscount that = (UserDiscount) o;

        if (discountId != that.discountId) return false;
        if (yearOrders != that.yearOrders) return false;
        return discount.equals(that.discount);
    }

    @Override
    public int hashCode() {
        int result = (int) (discountId ^ (discountId >>> 32));
        result = 31 * result + discount.hashCode();
        result = 31 * result + yearOrders;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserDiscount{");
        sb.append("discountId=").append(discountId);
        sb.append(", discount=").append(discount);
        sb.append(", yearOrders=").append(yearOrders);
        sb.append('}');
        return sb.toString();
    }
}
