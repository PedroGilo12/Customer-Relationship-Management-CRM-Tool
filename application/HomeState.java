package application;

import adapter.dataManager;
import dataInterface.*;
import java.util.*;
import userInterface.*;
import utilities.*;
import utilities.Common.Menu;
import utilities.Common.User;

public class HomeState implements UserActionState {

  private String name = "Login";
  private UserAction userAction;

  Common common = new Common();
  Menu menu = common.new Menu("Activity Tracking", "Customer Manager",
                              "Sale Manager", "Logout");

  public HomeState(UserAction userAction) {
    this.userAction = userAction;
  }

  public void entryState() {
    userAction.userInteraction.displayPage(Common.userActionState.HOME);
    userAction.userInteraction.updatePage("Welcome " + userAction.ActiveUser.name+"!\n");
    userAction.userInteraction.updateMenu(menu);
    userAction.userInteraction.executeUserAction();
  }

  public String getName() {
    return name;
  }

  @Override
  public void action0() {
    userAction.setState(new ActivityTrackingState(userAction));
  }

  @Override
  public void action1() {
    userAction.setState(new CustomerManagerState(userAction));
  }

  @Override
  public void action2() {
    userAction.setState(new SaleManagerState(userAction));
  }

  @Override
  public void action3() {
    userAction.ActiveUser = null;
    userAction.setState(new UnregisterState(userAction));
  }
}
