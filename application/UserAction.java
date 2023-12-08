package application;

import adapter.*;
import dataInterface.*;
import userInterface.*;
import utilities.Common.User;;

public class UserAction {

  public DesktopInterface desktopInterface = new DesktopInterface(this);
  public UserInteractionAdapter userInteraction =
      new UserInteractionAdapter(desktopInterface);

  public JsonManagerInterface jsonManager = new JsonManagerInterface(this);
  public dataManagerAdapter dataManager = new dataManagerAdapter(jsonManager);

  protected UserActionState previousUserActionState;
  protected UserActionState userActionState = new UnregisterState(this);
  protected User ActiveUser;

  public UserActionState getState() { return this.userActionState; }

  public void setState(UserActionState userActionState) {
    this.userActionState = userActionState;
    this.userActionState.entryState();
  }

  public String getStateName() { return this.userActionState.getName(); }

  public void action0() { this.userActionState.action0(); }

  public void action1() { this.userActionState.action1(); }

  public void action2() { this.userActionState.action2(); }

  public void action3() { this.userActionState.action3(); }

  public void backAction() {
    this.userActionState = this.previousUserActionState;
  }
}
