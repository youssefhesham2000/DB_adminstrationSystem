import model.CartItem;
import model.User;
import service.BookManager;
import service.CartManager;
import service.DBConnection;
import service.UserManager;

public class ApplicationLogic {

    private static ApplicationLogic applicationLogic;
    private User loggedInUser;
    private DBConnection dbConnection;
    private UserManager userManager;
    private BookManager bookManager;
    private CartManager cartManager;
    private ApplicationLogic() {}

    public void initializeApplication(DBConnection dbConnection) {
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

    }

    public boolean logout() {

    }



}
