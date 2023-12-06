package application;

import adapter.*;
import java.util.Locale;
import userInterface.DesktopInterface;


public class main {

  private static DesktopInterface desktopInterface = new DesktopInterface();
  private static UserInteractionAdapter userInteraction =
      new UserInteractionAdapter(desktopInterface);

  private static Common.User user;
  private static Common.User activeUser = null;

  interface UserAction {
    void execute();
  }

  public static void main(String[] args) {
    mainMenu();
  }

  private static void mainMenu() {

    userInteraction.displayPage(Common.UserInteractionPages.MAIN_MENU, null);
    String userResponse = (String)userInteraction.getUserGenericInput();

    UserAction userAction = null;

    switch (userResponse) {
    case "1":
      userAction = () -> loginProcess();
      break;

    case "2":
      userAction = () -> registerProcess();
      break;

    default:
			userAction = () -> mainMenu();
      break;
    }

    if (userAction != null) {
      userAction.execute();
    }
  }

  private static void registerProcess() {
    userInteraction.displayPage(Common.UserInteractionPages.SIGNUP, null);

    String userResponse = (String)userInteraction.getUserGenericInput();

    UserAction userAction = null;

    switch (userResponse) {
    case "1":
      user = userInteraction.registerUser();
      userAction = () -> loginProcess();
      break;

    case "2":
      userAction = () -> loginProcess();
      break;

    default:
			userAction = () -> registerProcess();
      break;
    }

    if (userAction != null) {
      userAction.execute();
    }
  }

  private static void loginProcess() {
    userInteraction.displayPage(Common.UserInteractionPages.LOGIN, null);

    String userResponse = (String)userInteraction.getUserGenericInput();

    UserAction userAction = null;

    switch (userResponse) {
    case "1":
      user = userInteraction.loginUser();

      if (user != null) {
        activeUser = user;
        userAction = () -> userHomePage();
      } else {
        userInteraction.displayMessage("User not found.\n", null);
        userAction = () -> loginProcess();
      }
      break;

    case "2":
      userAction = () -> registerProcess();
      break;

    default:
			userAction = () -> loginProcess();
      break;
    }

    if (userAction != null) {
      userAction.execute();
    }
  }

  private static void userHomePage() {
    userInteraction.displayPage(Common.UserInteractionPages.HOME, activeUser);

    String userResponse = (String)userInteraction.getUserGenericInput();

    UserAction userAction = null;

    switch (userResponse) {
    case "1":
      userAction = () -> Contacts();

      break;
    case "5":
      userAction = () -> registerCustomerProcess();
      break;

		case "6":
			activeUser = null;
			userAction = () -> mainMenu();
		break;

    default:
			userAction = () -> userHomePage();
      break;
    }

    if (userAction != null) {
      userAction.execute();
    }
  }

  private static void registerCustomerProcess() {

    userInteraction.displayPage(Common.UserInteractionPages.REGISTER_CUSTOMER,
                                activeUser);
    userInteraction.registerCustomer();

    UserAction userAction = () -> userHomePage();

    if (userAction != null) {
      userAction.execute();
    }
  }

  private static void Contacts() {
    userInteraction.displayPage(Common.UserInteractionPages.CONTACTS,
                                activeUser);

		String userResponse = (String)userInteraction.getUserGenericInput();

		UserAction userAction = null;

		switch (userResponse) {
			case "9":
				userAction = () -> userHomePage();
			break;

			default:
				userAction = () -> Contacts();
			break;
		}

		if (userAction != null) {
			userAction.execute();
		}
  }
}
