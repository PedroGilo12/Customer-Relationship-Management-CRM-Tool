package userInterface;

import adapter.*;
import application.*;
import java.util.Scanner;
import utilities.*;

public class DesktopInterface implements UserInteraction {

  UserAction userAction;

  public DesktopInterface(UserAction userAction) {
    this.userAction = userAction;
  }

  @Override
  public void displayPage(Common.userActionState page) {
    switch (page) {
      case UNREGISTER:
        System.out.println("Welcome to ClientConnect System\n");
        System.out.println("Already have an account?");
        break;

      case LOGIN:
        System.out.println("Login");
      break;

      case SIGNUP:
        System.out.println("Sign Up");
      break;

      case HOME:
        System.out.println("Home");
      break;

      case SALE_MANAGER:
        System.out.println("Welcome to Sale Manager");
      break;

      case CUSTOMER_MANAGER:
        System.out.println("Welcome to Customer Manager");
      break;

      case ACTIVITY_TRACKING:
        System.out.println("Welcome to Activity Tracking");
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

    if(!menu.option0.equals("")) {
      System.out.printf("[0] %s\n",menu.option0);
    }

    if(!menu.option1.equals("")) {
      System.out.printf("[1] %s\n",menu.option1);
    }

    if(!menu.option2.equals("")) {
      System.out.printf("[2] %s\n",menu.option2);
    }

    if(!menu.option3.equals("")) {
      System.out.printf("[3] %s\n",menu.option3);
    }

  }

  @Override
  public void executeUserAction() {
    Scanner scanner = new Scanner(System.in);

    int option = scanner.nextInt();

    switch (option) {
      case 0:
        this.userAction.action0();
        break;

      case 1:
        this.userAction.action1();
        break;

      case 2:
        this.userAction.action2();
        break;

      case 3:
        this.userAction.action3();
        break;
    }

    scanner.close();
  }

  @Override
  public String getUserResponse() {
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }
}
