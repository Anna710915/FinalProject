package by.epam.finalproject.model.mapper.impl;

import by.epam.finalproject.model.entity.Order;
import by.epam.finalproject.model.mapper.CustomRowMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Optional;

import static by.epam.finalproject.model.mapper.impl.UserMapper.USER_ID;

/**
 * The type OrderMapper. Extract orders rows form ResultSet.
 */
public class OrderMapper implements CustomRowMapper<Order> {
    private static final Logger logger = LogManager.getLogger();
    /**
     * The constant ORDER_ID.
     */
    public static final String ORDER_ID = "order_id";
    /**
     * The constant ORDER_DATE.
     */
    public static final String ORDER_DATE = "order_date";
    /**
     * The constant ORDER_STATE.
     */
    public static final String ORDER_STATE = "order_state";
    /**
     * The constant TYPE_PAYMENT.
     */
    public static final String TYPE_PAYMENT = "type_payment";
    /**
     * The constant USER_COMMENT.
     */
    public static final String USER_COMMENT = "user_comment";
    /**
     * The constant ADDRESS.
     */
    public static final String ADDRESS = "address";
    /**
     * The constant TOTAL_COST.
     */
    public static final String TOTAL_COST = "total_cost";

    @Override
    public Optional<Order> mapRow(ResultSet resultSet){
        Order order = new Order();
        Optional<Order> optionalOrder;
        try {
            order.setOrderId(resultSet.getLong(ORDER_ID));
            order.setOrderDate(resultSet.getTimestamp(ORDER_DATE).toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime());
            logger.log(Level.INFO, "Order date: " + order.getOrderDate());
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
