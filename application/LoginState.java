package application;

import adapter.dataManager;
import dataInterface.*;
import java.util.*;
import userInterface.*;
import utilities.*;
import utilities.Common.Menu;
import utilities.Common.User;


public class LoginState implements UserActionState {

  private String name = "Login";
  private UserAction userAction;

  Common common = new Common();
  Menu menu = common.new Menu("Login", "Return", "", "");

  public LoginState(UserAction userAction) { this.userAction = userAction; };

  public void entryState() {
    userAction.userInteraction.displayPage(Common.userActionState.LOGIN);
    userAction.userInteraction.updateMenu(menu);
    userAction.userInteraction.executeUserAction();
  };

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void action0() {
    userAction.userInteraction.updatePage("Type your email:");
    String email = userAction.userInteraction.getUserResponse();

    userAction.userInteraction.updatePage("Type your password:");
    String password = userAction.userInteraction.getUserResponse();

    User user = userAction.dataManager.getUser(email, password);

    if (user != null) {
      userAction.userInteraction.updatePage("Login successful!\n");
      userAction.ActiveUser = user;
      userAction.setState(new HomeState(userAction));
    } else {
      userAction.userInteraction.updatePage("Login failed!\n");
      userAction.setState(new LoginState(userAction));
    }
  };

  @Override
  public void action1() {
    userAction.setState(new UnregisterState(userAction));
  };

  @Override
  public void action2(){
    userAction.setState(new LoginState(userAction));
  };

  @Override
  public void action3(){
    userAction.setState(new LoginState(userAction));
  };
}
