package app.util;

import app.model.User;

public class Util {
    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Util.currentUser = currentUser;
    }
}
