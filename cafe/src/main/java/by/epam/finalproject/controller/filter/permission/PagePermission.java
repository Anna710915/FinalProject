package by.epam.finalproject.controller.filter.permission;
import by.epam.finalproject.controller.PathPage;

import java.util.Set;

import static by.epam.finalproject.controller.PathPage.*;


/**
 * The enum Page permission.
 */
public enum PagePermission {
    /**
     * Admin page permission.
     */
    ADMIN(Set.of(START_PAGE,
            HOME_PAGE,
            ERROR_404,
            REGISTRATION_PAGE,
            USERS_PAGE,
            PROFILE_PAGE,
            MENU_PAGE,
            ADD_MENU_PAGE,
            SETTINGS_PAGE,
            ERROR_500,
            UPDATE_PRODUCT_PAGE,
            ORDERS_PAGE,
            CONTACTS_PAGE,
            SECTION_PAGE,
            PASSWORD_PAGE,
            RESTORE_PAGE)),
    /**
     * Client page permission.
     */
    CLIENT(Set.of(START_PAGE,
            HOME_PAGE,
            SIGN_PAGE,
            ERROR_404,
            PROFILE_PAGE,
            DISCOUNT_PAGE,
            MENU_PAGE,
            SETTINGS_PAGE,
            ERROR_500,
            BASKET_PAGE,
            SUCCESS_PAGE,
            ORDERS_PAGE,
            CONTACTS_PAGE,
            PASSWORD_PAGE)),
    /**
     * Guest page permission.
     */
    GUEST(Set.of(START_PAGE,
            HOME_PAGE,
            SIGN_PAGE,
            ERROR_404,
            REGISTRATION_PAGE,
            ERROR_500,
            CONTACTS_PAGE,
            MENU_PAGE));

    private final Set<String> userPages;

    PagePermission(Set<String> userPages){
        this.userPages = userPages;
    }

    /**
     * Get user pages set.
     *
     * @return the set
     */
    public Set<String> getUserPages(){
        return userPages;
    }
}
