package by.epam.finalproject.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order extends CustomEntity {

    public enum OrderState {
        NEW("new"), PROCESSING("processing"), CANCELLED("cancelled"), RECEIVED("received"), COMPLETED("completed");

        String state;

        OrderState(String state){
            this.state = state;
        }

        public String getState(){
            return state;
        }
    }

    public enum TypePayment {
        CASH("cash"), CARD("card");
        String payment;

        TypePayment(String payment){
            this.payment = payment;
        }

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

    public Order(){}

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

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public TypePayment getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(TypePayment typePayment) {
        this.typePayment = typePayment;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

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
