package DAO;

import util.DBHelper;
import util.PropertyReader;

import java.util.Properties;

public class UserDaoFactory {
    public static UserDAO create() {
        Properties properties = PropertyReader.getProperties();
        assert properties != null;
        String typeOfDao = properties.getProperty("daotype");
        UserDAO userDAO = new UserHibernateDAO();
        switch (typeOfDao) {
            case "jdbc":
                userDAO = new UserJdbcDAO(DBHelper.getMySqlConnectionJDBC());
        }
        return userDAO;
    }
}
