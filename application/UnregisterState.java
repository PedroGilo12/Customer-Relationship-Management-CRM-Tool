package application;

import java.util.*;
import userInterface.*;
import dataInterface.*;
import utilities.*;
import utilities.Common.Menu;
import utilities.Common.User;


public class UnregisterState implements UserActionState {

  private String name = "Unregister";
  private UserAction userAction;

  Common common = new Common();
  Menu menu = common.new Menu("Login", "Sign Up", "Exit", "");

  public UnregisterState(UserAction userAction) {
    this.userAction = userAction;
  };

  @Override
  public void entryState() {
    userAction.userInteraction.displayPage(Common.userActionState.UNREGISTER);
    userAction.userInteraction.updateMenu(menu);
    userAction.userInteraction.executeUserAction();
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void action0() {
    this.userAction.setState(new LoginState(this.userAction));
  };  

  @Override
  public void action1(){
      this.userAction.setState(new SignupState(this.userAction));
  };

  @Override public void action2(){};
  @Override public void action3(){};
}
