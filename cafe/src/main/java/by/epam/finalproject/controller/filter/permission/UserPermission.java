package by.epam.finalproject.controller.filter.permission;

import by.epam.finalproject.controller.factory.CommandType;

import java.util.Set;
public enum UserPermission {
    ADMIN(Set.of(CommandType.CHANGE_LANGUAGE.name(),
            CommandType.REGISTRATION.name(),
            CommandType.FIND_ALL_USERS.name(),
            CommandType.SIGN_OUT.name(),
            CommandType.DELETE_USER.name(),
            CommandType.FIND_ALL_MENU.name(),
            CommandType.INSERT_NEW_PRODUCT.name(),
            CommandType.UPLOAD_PRODUCT_PHOTO.name(),
            CommandType.UPDATE_USER_PROFILE.name(),
            CommandType.CHANGE_PASSWORD.name(),
            CommandType.GO_TO_UPDATE_PRODUCT_PAGE.name(),
            CommandType.DELETE_PRODUCT.name(),
            CommandType.BLOCK_USER.name(),
            CommandType.UNBLOCK_USER.name(),
            CommandType.UPDATE_PRODUCT.name(),
            CommandType.FIND_ALL_ORDERS.name(),
            CommandType.CHANGE_ORDER_STATE.name())),
    CLIENT(Set.of(CommandType.CHANGE_LANGUAGE.name(),
            CommandType.SIGN_IN.name(),
            CommandType.SIGN_OUT.name(),
            CommandType.FIND_ALL_MENU.name(),
            CommandType.UPDATE_USER_PROFILE.name(),
            CommandType.CHANGE_PASSWORD.name(),
            CommandType.ADD_PRODUCT_TO_CART.name(),
            CommandType.CREATE_ORDER.name(),
            CommandType.GO_TO_BASKET_PAGE.name(),
            CommandType.DELETE_PRODUCT_IN_BASKET.name(),
            CommandType.CALCULATE_USER_DISCOUNT.name(),
            CommandType.GO_TO_ORDERS_PAGE.name())),
    GUEST(Set.of(CommandType.SIGN_IN.name(),
            CommandType.CHANGE_LANGUAGE.name(),
            CommandType.REGISTRATION.name()));
    private Set<String> commands;

    UserPermission(Set<String> commands){
        this.commands = commands;
    }

    public Set<String> getCommands(){
        return commands;
    }
}
