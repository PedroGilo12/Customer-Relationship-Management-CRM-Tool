package application;

public class LoginState implements UserActionState {

  private String name = "Login";
  private UserAction userAction;

  public LoginState(UserAction userAction) { this.userAction = userAction; };

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void action0(){

  };

  @Override
  public void action1(){

  };

  @Override
  public void action2(){

  };

  @Override
  public void action3(){

  };
}
