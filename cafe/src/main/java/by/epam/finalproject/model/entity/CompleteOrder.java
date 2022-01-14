package by.epam.finalproject.model.entity;

import java.util.List;

public class CompleteOrder extends CustomEntity{
    private User user;
    private Order order;
    private List<ComponentOrder> menuList;

    public CompleteOrder(User user, Order order, List<ComponentOrder> menuList) {
        this.user = user;
        this.order = order;
        this.menuList = menuList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<ComponentOrder> getMenuList() {
        return List.copyOf(menuList);
    }

    public void setMenuList(List<ComponentOrder> menuList) {
        this.menuList = menuList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompleteOrder that = (CompleteOrder) o;

        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (order != null ? !order.equals(that.order) : that.order != null) return false;
        return menuList != null ? menuList.equals(that.menuList) : that.menuList == null;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (order != null ? order.hashCode() : 0);
        result = 31 * result + (menuList != null ? menuList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CompleteOrder{");
        sb.append("user=").append(user);
        sb.append(", order=").append(order);
        sb.append(", menuList=").append(menuList);
        sb.append('}');
        return sb.toString();
    }
}
