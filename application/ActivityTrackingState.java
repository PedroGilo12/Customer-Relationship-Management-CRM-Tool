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

public class ActivityTrackingState implements UserActionState {

  private String name = "Activity Tracking";
  private UserAction userAction;
  Common common = new Common();
  Menu menu = common.new Menu("Make an appointment.", "Show customer document",
                              "Campaign dashboard", "Return");

  private Map<String, Integer> primaryInterestCounters = new HashMap<>();
  private Map<String, Integer> secondaryInterestCounters = new HashMap<>();
  private List<String> appointments = new ArrayList<>();

  public ActivityTrackingState(UserAction userAction) {
    this.userAction = userAction;
  }

  @Override
  public void entryState() {
    userAction.userInteraction.displayPage(
        Common.userActionState.ACTIVITY_TRACKING);

    int index = 0;

    userAction.userInteraction.updatePage(
        "\nPrimary interests of your customers: \n");

    while (true) {
      Customer customer = userAction.dataManager.getCustomerByIndex(index);

      if (customer == null) {
        break;
      }

      if (customer.managerName.equals(userAction.ActiveUser.managerName)) {
        String primaryInterest = customer.customerInformations.primaryInterest;
        primaryInterestCounters.put(
            primaryInterest,
            primaryInterestCounters.getOrDefault(primaryInterest, 0) + 1);

        userAction.userInteraction.updatePage(
            primaryInterest + " (" +
            primaryInterestCounters.get(primaryInterest) + ")");

        if (!customer.customerInformations.scheduledAppointments.isEmpty()) {
          appointments.add(customer.customerInformations.scheduledAppointments +
                           " - " + customer.email);
        }
      }

      index++;
    }

    userAction.userInteraction.updatePage(
        "\nSecondary interests of your customers: \n");

    index = 0;
    while (true) {
      Customer customer = userAction.dataManager.getCustomerByIndex(index);

      if (customer == null) {
        break;
      }

      if (customer.managerName.equals(userAction.ActiveUser.managerName)) {
        String secondaryInterest =
            customer.customerInformations.secondaryInterest;
        secondaryInterestCounters.put(
            secondaryInterest,
            secondaryInterestCounters.getOrDefault(secondaryInterest, 0) + 1);

        userAction.userInteraction.updatePage(
            secondaryInterest + " (" +
            secondaryInterestCounters.get(secondaryInterest) + ")");
      }

      index++;
    }

    userAction.userInteraction.updatePage(
        "\nAppointments of your customers: \n");

    appointments.forEach(
        appointment -> userAction.userInteraction.updatePage(appointment));

    userAction.userInteraction.updatePage("");

    userAction.userInteraction.updateMenu(menu);
    userAction.userInteraction.executeUserAction();
  }

  @Override
  public String getName() {
    return name;
  }

  public void action0() {

    userAction.userInteraction.updatePage("Enter customer email: ");
    String email = userAction.userInteraction.getUserResponse();

    Customer customer = userAction.dataManager.getCustomer(email);

    if (customer == null) {
      userAction.userInteraction.updatePage("Customer not found!\n");
      return;
    }

    userAction.userInteraction.updatePage(
        "Enter appointment date (to clear an appointment press enter): ");
    String date = userAction.userInteraction.getUserResponse();

    if (date.isEmpty()) {
      customer.customerInformations.scheduledAppointments = "";
      userAction.dataManager.updateCustomer(customer);
      userAction.setState(new ActivityTrackingState(userAction));
      return;
    }

    userAction.userInteraction.updatePage(
        "Enter appoitment type (call our meet): \n");
    String type = userAction.userInteraction.getUserResponse();

    customer.customerInformations.scheduledAppointments = type + " - " + date;

    userAction.dataManager.updateCustomer(customer);
    userAction.setState(new ActivityTrackingState(userAction));
  }

  public void action1() {
    userAction.userInteraction.updatePage("Enter customer email: ");
    String email = userAction.userInteraction.getUserResponse();

    Customer customer = userAction.dataManager.getCustomer(email);

    if (customer == null) {
      userAction.userInteraction.updatePage("Customer not found!\n");
      return;
    }

    userAction.userInteraction.updatePage(customer.name + " documents: ");
    userAction.userInteraction.updatePage("    RG:" + customer.customerInformations.rg);
    userAction.userInteraction.updatePage("    CPF:" + customer.customerInformations.cpf);

    userAction.userInteraction.updatePage("Press enter to return");
    userAction.userInteraction.getUserResponse();
    userAction.setState(new ActivityTrackingState(userAction));
  }

  public void action2() {
    if(userAction.ActiveUser.role.equals("Manager")){
      userAction.setState(new CampaignDashboardManager(userAction));
    } else {
      userAction.setState(new CampaignDashboardDefaultUser(userAction));
    } 
  }

  public void action3() { userAction.setState(new HomeState(userAction)); }
}
