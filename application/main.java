package application;

import utilities.*;
import adapter.*;
import java.util.Locale;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    
    Scanner scanner = new Scanner(System.in);
    System.out.println("Hello, welcome to CRM Tool. What is your name?");
    String name = scanner.nextLine();
    scanner.close();

    //UserAction userAction = new UserAction();
    //userAction.setState(new UnregisterState(userAction));

  }

}
