package by.epam.finalproject.controller.factory;

import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.controller.command.impl.admin.*;
import by.epam.finalproject.controller.command.impl.client.*;
import by.epam.finalproject.controller.command.impl.common.*;

import java.util.Optional;

public enum CommandType {
    INSERT_NEW_PRODUCT(new InsertNewProductCommand()),
    SIGN_IN(new SignInCommand()),
    SIGN_OUT(new SignOutCommand()),
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    REGISTRATION(new RegistrationCommand()),
    FIND_ALL_MENU(new FindAllMenuCommand()),
    FIND_ALL_USERS(new FindAllUsersCommand()),
    DELETE_USER(new DeleteUserCommand()),
    UPLOAD_PRODUCT_PHOTO(new UploadProductPhotoCommand()),
    UPDATE_USER_PROFILE(new UpdateUserProfileCommand()),
    CHANGE_PASSWORD(new ChangePasswordCommand()),
    GO_TO_UPDATE_PRODUCT_PAGE(new GoToUpdateProductPageCommand()),
    DELETE_PRODUCT(new DeleteProductCommand()),
    BLOCK_USER(new BlockUserByIdCommand()),
    UNBLOCK_USER(new UnblockUserByIdCommand()),
    CREATE_ORDER(new CreateOrderCommand()),
    ADD_PRODUCT_TO_CART(new AddProductToCartCommand()),
    GO_TO_BASKET_PAGE(new GoToBasketPageCommand()),
    UPDATE_PRODUCT(new UpdateProductCommand()),
    DELETE_PRODUCT_IN_BASKET(new DeleteProductInBasketCommand()),
    CALCULATE_USER_DISCOUNT(new CalculateUserDiscountCommand()),
    GO_TO_ORDERS_PAGE(new GoToOrdersPageCommand()),
    FIND_ALL_ORDERS(new FindAllOrdersCommand()),
    CHANGE_ORDER_STATE(new ChangeOrderStateCommand());
    private final Command command;

    CommandType(Command command){
        this.command = command;
    }

    public Command getCommand(){
        return command;
    }

    public static Optional<Command> provideCommand(String command){
        Optional<Command> resultCommand;
        if(command == null || command.isEmpty()){
            return Optional.empty();
        }
        try{
            CommandType commandType = CommandType.valueOf(command.toUpperCase());
            resultCommand = Optional.of(commandType.getCommand());
        }catch (IllegalArgumentException exp){
            resultCommand = Optional.empty();
        }
        return resultCommand;
    }
}
