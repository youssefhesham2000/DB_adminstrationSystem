package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    public int ID;
    public String firstName;
    public String lastName;
    public String email;
    public String shippingAddress;
    public String phoneNumber;
    public UserRole role;
    public String password;

    public static User getUserFromResult(ResultSet resultSet, boolean returnPassword) throws SQLException {
        User user = new User();
        user.ID = resultSet.getInt("ID");
        user.firstName = resultSet.getString("firstName");
        user.lastName = resultSet.getString("lastName");
        user.email = resultSet.getString("email");
        user.shippingAddress = resultSet.getString("shippingAddress");
        user.phoneNumber = resultSet.getString("phoneNumber");
        user.role = UserRole.getUserRole(resultSet.getBoolean("role"));
        if (returnPassword)
            user.password = resultSet.getString("password");
        return user;
    }
}
