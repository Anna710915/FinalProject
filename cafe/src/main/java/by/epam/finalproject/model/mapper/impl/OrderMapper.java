package by.epam.finalproject.model.mapper.impl;

import by.epam.finalproject.model.entity.Order;
import by.epam.finalproject.model.mapper.CustomRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static by.epam.finalproject.model.mapper.impl.UserMapper.USER_ID;

public class OrderMapper implements CustomRowMapper<Order> {
    public static final String ORDER_ID = "order_id";
    public static final String ORDER_DATE = "order_date";
    public static final String ORDER_STATE = "order_state";
    public static final String TYPE_PAYMENT = "type_payment";
    public static final String USER_COMMENT = "user_comment";
    public static final String ADDRESS = "address";
    public static final String TOTAL_COST = "total_cost";

    @Override
    public Optional<Order> mapRow(ResultSet resultSet){
        Order order = new Order();
        Optional<Order> optionalOrder;
        try {
            order.setOrderId(resultSet.getLong(ORDER_ID));
            order.setOrderDate(resultSet.getTimestamp(ORDER_DATE).toLocalDateTime());
            order.setOrderState(Order.OrderState.valueOf(resultSet.getString(ORDER_STATE)
                    .trim().toUpperCase()));
            order.setTypePayment(Order.TypePayment.valueOf(resultSet.getString(TYPE_PAYMENT)
                    .trim().toUpperCase()));
            order.setAddress(resultSet.getString(ADDRESS));
            order.setTotalCost(resultSet.getBigDecimal(TOTAL_COST));
            order.setUserComment(resultSet.getString(USER_COMMENT));
            order.setUserId(resultSet.getLong(USER_ID));
            optionalOrder = Optional.of(order);
        } catch (SQLException e) {
            optionalOrder = Optional.empty();
        }
        return optionalOrder;
    }
}
