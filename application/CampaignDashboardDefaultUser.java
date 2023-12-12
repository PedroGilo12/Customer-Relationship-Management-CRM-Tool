package application;

import adapter.dataManager;
import dataInterface.*;
import java.util.*;
import userInterface.*;
import utilities.*;
import utilities.Common.Customer;
import utilities.Common.Menu;
import utilities.Common.User;

public class CampaignDashboardDefaultUser implements UserActionState {

  private String name = "Default User Dashboard";
  private UserAction userAction;

  Common common = new Common();
  Menu menu = common.new Menu("Send email to manager.", "", "", "Return.");

  public CampaignDashboardDefaultUser(UserAction userAction) {
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
    String managerName = userAction.ActiveUser.managerName;
    User ManagerUser = userAction.dataManager.getUserByName(managerName);

    if (ManagerUser == null) {
      userAction.userInteraction.updatePage("Manager not found. Press any key to return.");
      userAction.userInteraction.executeUserAction();
      userAction.setState(new CampaignDashboardDefaultUser(userAction));
    }

    userAction.userInteraction.updatePage("Write the body of the email: ");
    String body = userAction.userInteraction.getUserResponse();

    userAction.userInteraction.updatePage("Email sent to: " + ManagerUser.email + "\nPress any key to return.");
    userAction.userInteraction.getUserResponse();

    userAction.setState(new CampaignDashboardDefaultUser(userAction));
  }

  @Override
  public void action1() {userAction.setState(new CampaignDashboardDefaultUser(userAction));}

  @Override
  public void action2() {userAction.setState(new CampaignDashboardDefaultUser(userAction));}

  @Override
  public void action3() {
    userAction.setState(new HomeState(userAction));
  }
}
