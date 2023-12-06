package userInterface;

import adapter.Common;
import adapter.Common.Customer;
import adapter.Common.SaleStatus;
import adapter.UserInteraction;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DesktopInterface implements UserInteraction {

  private Common commonInstance;

  private final String users_file = "user.json";
  private final String customers_file = "customer.json";

  public DesktopInterface() { commonInstance = new Common(); }

  @Override
  public void displayPage(Common.UserInteractionPages page, Object data) {
    System.out.printf("[page: %s]\n\n", page.name());
    Common.User user = (Common.User)data;

    switch (page) {
    case MAIN_MENU:
      System.out.println("Welcome to ClietConnect");
      System.out.printf(
          "[1] To proceed with login.\n[2] To proceed with registration.\n");
      break;

    case LOGIN:
      System.out.println("Welcome to ClietConnect, please log in\n");
      System.out.printf(
          "[1] Proceed with login, send 1.\n[2] Register, send 2.\n");
      break;

    case SIGNUP:
      System.out.println("Welcome to ClietConnect, please register\n");
      System.out.printf("[1] To proceed with registration.\n[2] Log in.\n");
      break;

    case HOME:

      System.out.printf("Welcome %s\n\nSelect an option:\n", user.name);
      System.out.println("[1] Contact Management");
      System.out.println("[2] Sales Management");
      System.out.println("[3] Activity tracker");
      System.out.println("[4] Change dashboard");
      System.out.println("[5] Register new customer");
      System.out.println("[6] Logout");

      break;

    case REGISTER_CUSTOMER:
      System.out.println("Register new customer\n");
      break;

    case CONTACTS:
      System.out.println("Contacts\n");

      try {
        File file = new File(customers_file);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;

        while ((line = bufferedReader.readLine()) != null) {
          Gson gson = new Gson();
          Common.Customer customer = gson.fromJson(line, Common.Customer.class);

          if (customer.ManagerName.equals(user.name)) {
            System.out.printf("name: %s\n  email: %s\n  phone: %s\n",
                              customer.name, customer.email, customer.phone);
          }

          System.out.println();
        }

        bufferedReader.close();
      } catch (IOException e) {
        e.printStackTrace();
      }

      System.out.println("[9] Back");

      break;

    default:
      System.out.println("Unknown page");
      break;
    }

    System.out.printf("\n");
  }

  @Override
  public void displayMessage(String message, Object data) {
    System.out.printf(message);
  }

  @Override
  public Object getUserGenericInput() {
    return System.console().readLine();
  }

  @Override
  public Common.User loginUser() {
    Common.User user = null;

    System.out.println("Please enter your email: ");
    String email = System.console().readLine();

    System.out.println("Please enter your password: ");
    String password = System.console().readLine();

    try {
      File file = new File(users_file);
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      String line;

      while ((line = bufferedReader.readLine()) != null) {
        Gson gson = new Gson();
        user = gson.fromJson(line, Common.User.class);

        if (user.email.equals(email) && user.password.equals(password)) {
          bufferedReader.close();
          return user;
        }
      }

      bufferedReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public Common.User registerUser() {
    Common common = new Common();
    Common.User newUser = common.new User();

    System.out.println("Please enter your name: ");
    newUser.name = System.console().readLine();

    System.out.println("Please enter your email: ");
    newUser.email = System.console().readLine();

    System.out.println("Please enter your password: ");
    newUser.password = System.console().readLine();

    System.out.println("Please enter your phone number: ");
    newUser.phone = System.console().readLine();

    newUser.role = Common.UserRoles.MANAGER;
    newUser.ManagerName = newUser.name;

    try {
      FileWriter fileWriter = new FileWriter(users_file, true);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

      Gson gson = new Gson();
      String userJson = gson.toJson(newUser);
      bufferedWriter.write(userJson);
      bufferedWriter.newLine();

      bufferedWriter.close();
      fileWriter.close();

      return newUser;
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public Customer registerCustomer() {
    Common.Customer customer = commonInstance.new Customer();

    System.out.println("Please enter the customer's name: ");
    customer.name = System.console().readLine();

    System.out.println("Please enter the customer's email: ");
    customer.email = System.console().readLine();

    System.out.println("Please enter the customer's phone number: ");
    customer.phone = System.console().readLine();

    System.out.println("Please enter the customer's address: ");
    customer.address = System.console().readLine();

    System.out.println("Please enter the customer's sale process: ");
    customer.saleProcess = System.console().readLine();

    System.out.println("Please enter the customer's manager name: ");
    customer.ManagerName = System.console().readLine();

    try {
      FileWriter fileWriter = new FileWriter(customers_file, true);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

      Gson gson = new Gson();
      String customerJson = gson.toJson(customer);
      bufferedWriter.write(customerJson);
      bufferedWriter.newLine();

      bufferedWriter.close();
      fileWriter.close();

      return customer;
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public String getCustomerContact() {
    return "customer contact";
  }

  @Override
  public SaleStatus getSaleStatus(String email) {
    return Common.SaleStatus.PENDING;
  }
}
