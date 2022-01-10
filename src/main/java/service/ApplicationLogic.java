package service;

import model.User;

public class ApplicationLogic {

    private static ApplicationLogic applicationLogic;
    private User loggedInUser;
    private DBConnection dbConnection;
    private UserManager userManager;
    private BookManager bookManager;
    private CartManager cartManager;
    private ApplicationLogic() {}

    public void initializeApplication(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
        userManager = new UserManager(dbConnection);
        bookManager = new BookManager(dbConnection);
        cartManager = new CartManager(dbConnection);
    }


    public static ApplicationLogic getInstance() {
        if (applicationLogic == null)
                applicationLogic = new ApplicationLogic();
        return applicationLogic;
    }

    public boolean login(String email, String password) {
    return false;
    }

    public boolean logout() {
       // cartManager.deleteUserCartItems(loggedInUser);
        return false;
    }



}