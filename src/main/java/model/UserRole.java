package model;

public enum UserRole {
    CUSTOMER,
    MANAGER;

    public static UserRole getUserRole(boolean userRole) {
        return userRole ? MANAGER: CUSTOMER;
    }

    public static boolean getUserRoleIndex(UserRole userRole) {
        return switch (userRole) {
            case CUSTOMER -> false;
            case MANAGER -> true;
        };
    }

}