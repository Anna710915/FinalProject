package by.epam.finalproject.model.service;

import by.epam.finalproject.model.entity.Menu;
import by.epam.finalproject.model.entity.UserDiscount;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class CalculateService {
    private static CalculateService instance;
    private CalculateService(){}

    public static CalculateService getInstance(){
        if(instance == null){
            instance = new CalculateService();
        }
        return instance;
    }

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
