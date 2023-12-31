package application;

import adapter.dataManager;
import dataInterface.*;
import java.util.*;
import userInterface.*;
import utilities.*;
import utilities.Common.Customer;
import utilities.Common.CustomerInformations;
import utilities.Common.Menu;
import utilities.Common.User;

public class CustomerManagerState implements UserActionState {

  private String name = "Customer Manager";
  private UserAction userAction;

  Common common = new Common();
  Menu menu =
      common.new Menu("Add customer", "More information about the customer",
                      "Update Satisfaction", "Return");

  public CustomerManagerState(UserAction userAction) {
    this.userAction = userAction;
  }

  @Override
  public void entryState() {
    userAction.userInteraction.displayPage(
        Common.userActionState.CUSTOMER_MANAGER);

    int index = 0;
    int maxSales = 0;
    Set<String> potentialLeaderNames = new HashSet<>();

    while (true) {
      Customer customer = userAction.dataManager.getCustomerByIndex(index);

      if (customer == null) {
        break;
      }

      if (customer.managerName.equals(userAction.ActiveUser.managerName)) {
        int currentSales = Integer.parseInt(customer.sales);
        if (currentSales > maxSales) {
          maxSales = currentSales;
        }
      }

      index++;
    }

    index = 0;
    while (true) {
      Customer customer = userAction.dataManager.getCustomerByIndex(index);

      if (customer == null) {
        break;
      }

      if (customer.managerName.equals(userAction.ActiveUser.managerName)) {
        if (Integer.parseInt(customer.sales) > 0.5 * maxSales) {
          potentialLeaderNames.add(
              customer.customerInformations.primaryInterest);
        }
      }

      index++;
    }

    index = 0;
    while (true) {
      Customer customer = userAction.dataManager.getCustomerByIndex(index);

      if (customer == null) {
        break;
      }

      if (userAction.ActiveUser.role.equals("manager")) {
        if (customer.managerName.equals(userAction.ActiveUser.name)) {
          userAction.userInteraction.updatePage("\nCustomer: ");
          userAction.userInteraction.updatePage("    name: " + customer.name);
          userAction.userInteraction.updatePage("    email: " + customer.email);

          if (potentialLeaderNames.contains(
                  customer.customerInformations.primaryInterest)) {
            userAction.userInteraction.updatePage(
                "   *Status: POTENTIAL LEADER");
          }
        }
      } else {
        if (customer.vendorName.equals(userAction.ActiveUser.name)) {
          userAction.userInteraction.updatePage("\nCustomer: ");
          userAction.userInteraction.updatePage("    name: " + customer.name);
          userAction.userInteraction.updatePage("    email: " + customer.email);

          if (potentialLeaderNames.contains(
                  customer.customerInformations.primaryInterest)) {
            userAction.userInteraction.updatePage(
                "   *Status: POTENTIAL LEADER");
          }
        }
      }

      index++;
    }

    userAction.userInteraction.updatePage("");
    userAction.userInteraction.updateMenu(menu);
    userAction.userInteraction.executeUserAction();
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void action0() {
    userAction.userInteraction.updatePage("Enter customer name: ");
    String name = userAction.userInteraction.getUserResponse();

    userAction.userInteraction.updatePage("Enter customer email: ");
    String email = userAction.userInteraction.getUserResponse();

    userAction.userInteraction.updatePage("Enter customer phone: ");
    String phone = userAction.userInteraction.getUserResponse();

    userAction.userInteraction.updatePage("Enter customer address: ");
    String address = userAction.userInteraction.getUserResponse();

    userAction.userInteraction.updatePage(
        "Enter how many customer purchases: ");
    String sales = userAction.userInteraction.getUserResponse();

    userAction.userInteraction.updatePage("Enter customer satisfaction: ");
    int satisfaction =
        Integer.parseInt(userAction.userInteraction.getUserResponse());

    userAction.userInteraction.updatePage("Enter customer manager name: ");
    String managerName = userAction.userInteraction.getUserResponse();

    userAction.userInteraction.updatePage("Enter customer vendor name: ");
    String vendorName = userAction.userInteraction.getUserResponse();

    userAction.userInteraction.updatePage("Enter customer cpf: ");
    String cpf = userAction.userInteraction.getUserResponse();

    userAction.userInteraction.updatePage("Enter customer rg: ");
    String rg = userAction.userInteraction.getUserResponse();

    userAction.userInteraction.updatePage("Enter customer primary interest: ");
    String primaryInterest = userAction.userInteraction.getUserResponse();

    userAction.userInteraction.updatePage(
        "Enter customer secondary interest: ");
    String secondaryInterest = userAction.userInteraction.getUserResponse();

    CustomerInformations customerInformations = common.new CustomerInformations(
        0, "", primaryInterest, secondaryInterest, cpf, rg, "none");

    Customer customer =
        common.new Customer(name, email, address, phone, sales, managerName,
                            satisfaction, vendorName, customerInformations);

    userAction.dataManager.createCustomer(customer);

    userAction.setState(new CustomerManagerState(userAction));
  }

  @Override
  public void action1() {
    userAction.userInteraction.updatePage("Enter customer email: ");
    String email = userAction.userInteraction.getUserResponse();

    Customer customer = userAction.dataManager.getCustomer(email);

    if (customer == null) {
      userAction.userInteraction.updatePage("Customer not found!\n");
      return;
    }

    userAction.userInteraction.updatePage("Customer name: " + customer.name);
    userAction.userInteraction.updatePage("Customer email: " + customer.email);
    userAction.userInteraction.updatePage("Customer phone: " + customer.phone);
    userAction.userInteraction.updatePage("Customer address: " +
                                          customer.address);
    userAction.userInteraction.updatePage("Customer sales: " + customer.sales);
    userAction.userInteraction.updatePage("Customer satisfaction: " +
                                          customer.satisfaction);

    userAction.userInteraction.getUserResponse();
    userAction.setState(new CustomerManagerState(userAction));
  }

  @Override
  public void action2() {
    userAction.userInteraction.updatePage("Enter customer email: ");
    String email = userAction.userInteraction.getUserResponse();

    Customer customer = userAction.dataManager.getCustomer(email);

    if (customer == null) {
      userAction.userInteraction.updatePage("Customer not found!\n");
      return;
    }

    userAction.userInteraction.updatePage("Enter customer satisfaction: ");
    int satisfaction =
        Integer.parseInt(userAction.userInteraction.getUserResponse());

    customer.satisfaction = satisfaction;

    userAction.dataManager.updateCustomer(customer);
  }

  @Override
  public void action3() {
    userAction.setState(new HomeState(userAction));
  }
}
