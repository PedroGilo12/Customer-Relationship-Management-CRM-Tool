package userInterface;

import adapter.*;
import application.*;
import java.util.Scanner;
import utilities.*;

public class DesktopInterface implements UserInteraction {

  public DesktopInterface() {
  }

  @Override
  public void displayPage(Common.userActionState page) {
    switch (page) {
      case UNREGISTER:
        System.out.println("Inicio");
        break;

      default:

        break;
    }
  }

  @Override
  public void updatePage(String info) {
    System.out.println(info);
  }

  @Override
  public void updateMenu(Common.Menu menu) {
    System.out.printf("\n[0] %s\n[1] %s\n[2] %s\n[3] %s\n\n", menu.option0,
                      menu.option1, menu.option2, menu.option3);
  }

  @Override
  public void executeUserAction() {
    Scanner scanner = new Scanner(System.in);

    int option = scanner.nextInt();
    UserAction userAction = new UserAction();

    switch (option) {
      case 0:
        userAction.action0();
        break;

      case 1:
        userAction.action1();
        break;

      case 2:
        userAction.action2();
        break;

      case 3:
        userAction.action3();
        break;
    }

    scanner.close();
  }

  @Override
  public String getUserResponse() {
    Scanner scanner = new Scanner(System.in);
    String response = scanner.nextLine();
    scanner.close();

    return response;
  }
}
