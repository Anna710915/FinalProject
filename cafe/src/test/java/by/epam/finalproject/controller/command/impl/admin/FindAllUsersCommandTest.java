package by.epam.finalproject.controller.command.impl.admin;

import by.epam.finalproject.controller.Router;
import by.epam.finalproject.exception.CommandException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.entity.User;
import by.epam.finalproject.model.service.UserService;
import by.epam.finalproject.model.service.impl.UserServiceImpl;
import org.mockito.Mockito;
import org.powermock.reflect.internal.WhiteboxImpl;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

import static org.testng.Assert.*;

public class FindAllUsersCommandTest {
    UserService mockService;
    HttpServletRequest mock;
    FindAllUsersCommand command;

    @BeforeMethod
    public void setUp(){
        command = new FindAllUsersCommand();
        mock = Mockito.mock(HttpServletRequest.class);
        mockService = Mockito.mock(UserServiceImpl.class);
        WhiteboxImpl.setInternalState(command, "userService", mockService);
    }

    @DataProvider(name = "users")
    public Object[][] signInData() {
        return new Object[][]{
                {List.of(new User(4, "Anna", "Merkul", "merkul",
                        "5f4dcc3b5aa765d61d8327deb882cf99", "merkulanna7@gmail.com", 291111111,
                        LocalDate.parse("2002-08-16"), 2, User.UserRole.ADMIN, User.UserState.ACTIVE)), "/jsp/pages/admin/users.jsp"},
        };
    }

    @Test(dataProvider = "users")
    public void findAllClientsTest(List<User> userList, String expected) throws CommandException, ServiceException {
        Mockito.when(mockService.findAllClients())
                .thenReturn(userList);
        Router router = command.execute(mock);
        Mockito.verify(mockService, Mockito.times(1)).findAllClients();
        assertEquals(router.getCurrentPage(), expected);
    }

}