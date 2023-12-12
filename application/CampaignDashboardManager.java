package application;

import adapter.dataManager;
import dataInterface.*;
import java.util.*;
import userInterface.*;
import utilities.*;
import utilities.Common.Customer;
import utilities.Common.Menu;
import utilities.Common.User;

public class CampaignDashboardManager implements UserActionState {

  private String name = "CapaignDashboard";
  private UserAction userAction;

  Common common = new Common();
  Menu menu = common.new Menu("Create new campaign.", "Remove campaign.",
                              "Send email to campaign.", "Return.");

  public CampaignDashboardManager(UserAction userAction) {
    this.userAction = userAction;
  };

  @Override
  public void entryState() {
    userAction.userInteraction.displayPage(
        Common.userActionState.CAMPAIGN_DASHBOARD);

    int index = 0;

    while (true) {
      Customer customer = userAction.dataManager.getCustomerByIndex(index);

      if (customer == null) {
        break;
      }
      if (customer.managerName.equals(userAction.ActiveUser.managerName)) {
        userAction.userInteraction.updatePage("\nCustomer: " + customer.name);
        userAction.userInteraction.updatePage("    Email: " + customer.email);
        userAction.userInteraction.updatePage(
            "    Campaign: " + customer.customerInformations.activeCapaing);
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
    userAction.userInteraction.updatePage("Enter campaign name: ");
    String campaign = userAction.userInteraction.getUserResponse();

    userAction.userInteraction.updatePage(
        "Enter the email address of the customers who will participate in the campaign, or 0 to finish: ");
    while (true) {
      String email = userAction.userInteraction.getUserResponse();
      Customer customer = userAction.dataManager.getCustomer(email);

      if (email.equals("0")) {
        break;
      }

      if (customer == null) {
        userAction.userInteraction.updatePage("Customer not found!\n");
        continue;
      }

      customer.customerInformations.activeCapaing = campaign;
      userAction.dataManager.updateCustomer(customer);
    }

    userAction.setState(new CampaignDashboardManager(userAction));
  }

  @Override
  public void action1() {
    userAction.userInteraction.updatePage("Enter campaign name: ");
    String campaign = userAction.userInteraction.getUserResponse();

    int index = 0;

    while (true) {
      Customer customer = userAction.dataManager.getCustomerByIndex(index);

      if (customer == null) {
        break;
      }
      if (customer.managerName.equals(userAction.ActiveUser.managerName) &&
          customer.customerInformations.activeCapaing.equals(campaign)) {
        customer.customerInformations.activeCapaing = "none";

        userAction.dataManager.updateCustomer(customer);
      }

      index++;
    }

    userAction.setState(new CampaignDashboardManager(userAction));
  }

  @Override
  public void action2() {
    userAction.userInteraction.updatePage("Enter campaign name: ");
    String campaign = userAction.userInteraction.getUserResponse();

    userAction.userInteraction.updatePage("Write the body of the email: ");
    String body = userAction.userInteraction.getUserResponse();

    int index = 0;

    while (true) {
      Customer customer = userAction.dataManager.getCustomerByIndex(index);

      if (customer == null) {
        break;
      }
      if (customer.managerName.equals(userAction.ActiveUser.managerName) &&
          customer.customerInformations.activeCapaing.equals(campaign)) {
        userAction.userInteraction.updatePage(
            "\nEmail sent to: " + customer.name +
            " - Email: " + customer.email);
      }

      index++;
    }

    userAction.userInteraction.getUserResponse();
    userAction.setState(new CampaignDashboardManager(userAction));
  }

  @Override
  public void action3() {
    userAction.setState(new HomeState(userAction));
  }
}
