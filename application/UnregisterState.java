package application;

import utilities.*;
import java.util.*;
import userInterface.*;

public class UnregisterState implements UserActionState {

    private String name = "Unregister";
    private UserAction userAction;

    public UnregisterState(UserAction userAction) {
        this.userAction = userAction;
        userAction.userInteraction.displayPage(Common.userActionState.UNREGISTER);
        userAction.userInteraction.executeUserAction();
    };

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void action0() {
        this.userAction.previousUserActionState = this.userAction.userActionState;
        this.userAction.setState(new LoginState(this.userAction));
    };

    @Override
    public void action1() {
        //this.userAction.setState(new RegisterState(this.userAction));
    };

    @Override
    public void action2() {};
    @Override
    public void action3() {};

}
