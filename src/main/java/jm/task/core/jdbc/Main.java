package jm.task.core.jdbc;



import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

/*                  ВНИМАНИЕ!!!ВНИМАНИЕ!!!ВНИМАНИЕ!!!

        При создании объекта от UserService идея требует указать в сигнатуре main SqlException для saveUser() !!!
        Я перерыл всё и спросил у всех кого знаю, не кто не смог мне объяснить что с этим делать.
        При создании объекта от UserDao такой проблемы нет

        Буду очень признателен и благодарен если вы мне объясните в чем дело ну или хотя бы направите меня.

                        БЛАГОДАРЮ ЗА ВНИМАНИЕ!!!
 */
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Name1", "LastName1", (byte) 20);
        userService.saveUser("Name2", "LastName2", (byte) 25);
        userService.saveUser("Name3", "LastName3", (byte) 31);
        userService.saveUser("Name4", "LastName4", (byte) 38);

        userService.removeUserById(1);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
