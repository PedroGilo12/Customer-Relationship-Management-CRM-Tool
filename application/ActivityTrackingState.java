package application;

import adapter.dataManager;
import dataInterface.*;
import java.util.*;
import userInterface.*;
import utilities.*;
import utilities.Common.Menu;
import utilities.Common.User;

public class ActivityTrackingState implements UserActionState{

  private String name = "Activity Tracking";
  private UserAction userAction;
  Common common = new Common();
  Menu menu =
      common.new Menu("Update customer information.", "Show customer document",
                      "Campaign dashboard", "Return");

                      
  public ActivityTrackingState(UserAction userAction) {
    this.userAction = userAction;
  }

  @Override
  public void entryState() {
    userAction.userInteraction.displayPage(Common.userActionState.ACTIVITY_TRACKING);
    userAction.userInteraction.updateMenu(menu);
    userAction.userInteraction.executeUserAction();
  }

  @Override
  public String getName() {
    return name;
  }

  public void action0() {
      //TODO
  }

  public void action1() {
    //TODO
  }

  public void action2() {
    //TODO
  }

  public void action3() {
    userAction.setState(new HomeState(userAction));
  }

}
