package application;

import java.util.Locale;
import userInterface.DesktopInterface;
import adapter.*;

public class main {

    private static DesktopInterface desktopInterface = new DesktopInterface();
    private static UserInteractionAdapter userInteraction = new UserInteractionAdapter(desktopInterface);

    private static Common.User user;
    private static Common.User activeUser = null;

    interface UserAction {
        void execute();
    }

    public static void main(String[] args) {
        userInteraction.displayPage(Common.UserInteractionPages.MAIN_MENU, null);
        String userResponse = (String) userInteraction.getUserGenericInput();

        UserAction userAction = null;

        switch (userResponse) {
            case "1":
                userAction = () -> loginProcess();
                break;

            case "2":
                userAction = () -> registerProcess();
                break;

            default:

                break;
        }

        if (userAction != null) {
            userAction.execute();
        }
    }

    private static void registerProcess() {
        userInteraction.displayPage(Common.UserInteractionPages.SIGNUP, null);

        String userResponse = (String) userInteraction.getUserGenericInput();

        UserAction userAction = null;

        switch (userResponse) {
            case "1":
                user = userInteraction.registerUser();

                break;

            case "2":
                userAction = () -> loginProcess();
                break;

            default:
                break;
        }

        if (userAction != null) {
            userAction.execute();
        }
    }

    private static void loginProcess() {
        userInteraction.displayPage(Common.UserInteractionPages.LOGIN, null);

        String userResponse = (String) userInteraction.getUserGenericInput();

        UserAction userAction = null;

        switch (userResponse) {
            case "1":
                user = userInteraction.loginUser();

                if(user != null){
                    activeUser = user;
                    userAction = () -> userHomePage();
                } else {
                    userInteraction.displayMessage("Usuario ou senha invalidos.\n", null);
                    userAction = () -> loginProcess();
                }
                break;

            case "2":
                userAction = () -> registerProcess();
                break;

            default:

                break;
        }

        if (userAction != null) {
            userAction.execute();
        }
    }

    private static void userHomePage() {
        userInteraction.displayPage(Common.UserInteractionPages.HOME, activeUser);
    }
}
