package application;

import utilities.*;
import adapter.*;
import java.util.Locale;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    UserAction userAction = new UserAction();
    userAction.setState(new UnregisterState(userAction));
  }

}
