package by.epam.finalproject.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * The type Order.
 */
public class Order extends CustomEntity {

    /**
     * The enum Order state.
     */
    public enum OrderState {
        /**
         * New order state.
         */
        NEW("new"),
        /**
         * Processing order state.
         */
        PROCESSING("processing"),
        /**
         * Cancelled order state.
         */
        CANCELLED("cancelled"),
        /**
         * Received order state.
         */
        RECEIVED("received"),
        /**
         * Completed order state.
         */
        COMPLETED("completed");

        /**
         * The State.
         */
        String state;

        OrderState(String state){
            this.state = state;
        }

        /**
         * Get state string.
         *
         * @return the string
         */
        public String getState(){
            return state;
        }
    }

    /**
     * The enum Type payment.
     */
    public enum TypePayment {
        /**
         * Cash type payment.
         */
        CASH("cash"),
        /**
         * Card type payment.
         */
        CARD("card");
        /**
         * The Payment.
         */
        String payment;

        TypePayment(String payment){
            this.payment = payment;
        }

        /**
         * Get payment string.
         *
         * @return the string
         */
        public String getPayment(){
            return payment;
        }
    }

    private long orderId;
    private LocalDateTime orderDate;
    private OrderState orderState;
    private TypePayment typePayment;
    private String address;
    private BigDecimal totalCost;
    private String userComment;
    private long userId;

    /**
     * Instantiates a new Order.
     */
    public Order(){}

    /**
     * Instantiates a new Order.
     *
     * @param orderId         the order id
     * @param orderChangeDate the order change date
     * @param orderState      the order state
     * @param typePayment     the type payment
     * @param address         the address
     * @param total_cost      the total cost
     * @param userComment     the user comment
     * @param userId          the user id
     */
    public Order(long orderId, LocalDateTime orderChangeDate,
                 OrderState orderState, TypePayment typePayment,
                 String address, BigDecimal total_cost, String userComment, long userId) {
        this.orderId = orderId;
        this.orderDate = orderChangeDate;
        this.orderState = orderState;
        this.typePayment = typePayment;
        this.address = address;
        this.totalCost = total_cost;
        this.userComment = userComment;
        this.userId = userId;
    }

    /**
     * Instantiates a new Order.
     *
     * @param orderChangeDate the order change date
     * @param orderState      the order state
     * @param typePayment     the type payment
     * @param address         the address
     * @param total_cost      the total cost
     * @param userComment     the user comment
     * @param userId          the user id
     */
    public Order(LocalDateTime orderChangeDate, OrderState orderState,
                 TypePayment typePayment, String address, BigDecimal total_cost,
                 String userComment, long userId) {
        this.orderDate = orderChangeDate;
        this.orderState = orderState;
        this.typePayment = typePayment;
        this.address = address;
        this.totalCost = total_cost;
        this.userComment = userComment;
        this.userId = userId;
    }

    /**
     * Gets order id.
     *
     * @return the order id
     */
    public long getOrderId() {
        return orderId;
    }

    /**
     * Sets order id.
     *
     * @param orderId the order id
     */
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    /**
     * Gets order date.
     *
     * @return the order date
     */
    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    /**
     * Sets order date.
     *
     * @param orderDate the order date
     */
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Gets order state.
     *
     * @return the order state
     */
    public OrderState getOrderState() {
        return orderState;
    }

    /**
     * Sets order state.
     *
     * @param orderState the order state
     */
    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    /**
     * Gets type payment.
     *
     * @return the type payment
     */
    public TypePayment getTypePayment() {
        return typePayment;
    }

    /**
     * Sets type payment.
     *
     * @param typePayment the type payment
     */
    public void setTypePayment(TypePayment typePayment) {
        this.typePayment = typePayment;
    }

    /**
     * Gets user comment.
     *
     * @return the user comment
     */
    public String getUserComment() {
        return userComment;
    }

    /**
     * Sets user comment.
     *
     * @param userComment the user comment
     */
    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets total cost.
     *
     * @return the total cost
     */
    public BigDecimal getTotalCost() {
        return totalCost;
    }

    /**
     * Sets total cost.
     *
     * @param totalCost the total cost
     */
    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (userId != order.userId) return false;
        if (orderDate != null ? !orderDate.equals(order.orderDate) : order.orderDate != null)
            return false;
        if (orderState != order.orderState) return false;
        if (typePayment != order.typePayment) return false;
        if (address != null ? !address.equals(order.address) : order.address != null) return false;
        if (totalCost != null ? !totalCost.equals(order.totalCost) : order.totalCost != null) return false;
        return userComment != null ? userComment.equals(order.userComment) : order.userComment == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (orderState != null ? orderState.hashCode() : 0);
        result = 31 * result + (typePayment != null ? typePayment.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (totalCost != null ? totalCost.hashCode() : 0);
        result = 31 * result + (userComment != null ? userComment.hashCode() : 0);
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("orderId=").append(orderId);
        sb.append(", orderDate=").append(orderDate);
        sb.append(", orderState=").append(orderState);
        sb.append(", typePayment=").append(typePayment);
        sb.append(", address='").append(address).append('\'');
        sb.append(", totalCost=").append(totalCost);
        sb.append(", userComment='").append(userComment).append('\'');
        sb.append(", userId=").append(userId);
        sb.append('}');
        return sb.toString();
    }
}
