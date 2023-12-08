package application;

import adapter.*;
import userInterface.*;

public class UserAction {
    
    private static DesktopInterface desktopInterface = new DesktopInterface();
    public UserInteractionAdapter userInteraction =
        new UserInteractionAdapter(desktopInterface);

    protected UserActionState previousUserActionState;
    protected UserActionState userActionState = new UnregisterState(this);

    public UserActionState getState() {
        return this.userActionState;
    }

    public void setState(UserActionState userActionState) {
        this.userActionState = userActionState;
    }

    public String getStateName() {
        return this.userActionState.getName();
    }

    public void action0() {
        this.userActionState.action0();
    }

    public void action1() {
        this.userActionState.action1();
    }

    public void action2() {
        this.userActionState.action2();
    }

    public void action3() {
        this.userActionState.action3();
    }

    public void backAction() {
        this.userActionState = this.previousUserActionState;
    }
}
