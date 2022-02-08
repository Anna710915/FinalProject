package by.epam.finalproject.model.service;

import by.epam.finalproject.model.entity.Menu;
import by.epam.finalproject.model.entity.UserDiscount;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

/**
 * The type CalculateService class.
 */
public class CalculateService {
    private static final CalculateService instance = new CalculateService();
    private CalculateService(){}

    /**
     * Get instance calculate service.
     *
     * @return the CalculateService object
     */
    public static CalculateService getInstance(){
        return instance;
    }

    /**
     * Calculate total price for order. If a product discount equals zero
     * it uses a user discount for calculating the total price.
     *
     * @param discount the discount
     * @param map      the map
     * @return the big decimal
     */
    public BigDecimal calculateTotalPrice(UserDiscount discount, Map<Menu, Integer> map){
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Menu item: map.keySet()){
            int numberProduct = map.get(item);
            BigDecimal itemPrice = item.getPrice();
            BigDecimal itemDiscount = item.getDiscount();
            if(itemDiscount.equals(BigDecimal.valueOf(0.00)) && discount != null){
                itemDiscount = discount.getDiscount();
            }
            totalPrice = totalPrice.add(itemPrice.multiply(BigDecimal.valueOf(numberProduct))
                    .subtract(itemDiscount.multiply(itemPrice.multiply(BigDecimal.valueOf(numberProduct)))));
        }
        return totalPrice.setScale(2, RoundingMode.HALF_UP);
    }
}
