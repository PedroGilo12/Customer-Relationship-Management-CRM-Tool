package application;

import java.util.*;
import userInterface.*;
import dataInterface.*;
import utilities.*;
import utilities.Common.Menu;
import utilities.Common.User;

public class SignupState implements UserActionState {

    private String name = "Signup";
    private UserAction userAction;

    Common common = new Common();
    Menu menu = common.new Menu("Sign Up", "Return", "", "");

    public SignupState(UserAction userAction) {
        this.userAction = userAction;
    }

    public void entryState() {
        userAction.userInteraction.displayPage(Common.userActionState.SIGNUP);
        userAction.userInteraction.updateMenu(menu);
        userAction.userInteraction.executeUserAction();
    }

    @Override
    public String getName() {
      return this.name;
    }
  
    @Override
    public void action0(){
        userAction.userInteraction.updatePage("Type your name: ");
        String name = userAction.userInteraction.getUserResponse();

        userAction.userInteraction.updatePage("Type your email: ");
        String email = userAction.userInteraction.getUserResponse();

        userAction.userInteraction.updatePage("Type your password: ");
        String password = userAction.userInteraction.getUserResponse();

        userAction.userInteraction.updatePage("Type your phone: ");
        String phone = userAction.userInteraction.getUserResponse();

        userAction.userInteraction.updatePage("Type your role: ");
        String role = userAction.userInteraction.getUserResponse();

        userAction.userInteraction.updatePage("Type your manager name (manager or vendor): ");
        String managerName = userAction.userInteraction.getUserResponse();

        User user = common.new User(name, email, password, phone, role, managerName);

        userAction.dataManager.createUser(user);

        userAction.setState(new LoginState(userAction));
    };
  
    @Override
    public void action1(){
        userAction.setState(new UnregisterState(userAction));
    };
  
    @Override
    public void action2(){
  
    };
  
    @Override
    public void action3(){
  
    };

}
