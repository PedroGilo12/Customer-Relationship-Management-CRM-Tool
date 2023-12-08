package adapter;

import utilities.Common;

public class UserInteractionAdapter {

  private UserInteraction userInteraction;

  public UserInteractionAdapter(UserInteraction userInteraction) {
    this.userInteraction = userInteraction;
  }

  public void displayPage(Common.userActionState data) {
    userInteraction.displayPage(data);
  }

  public void updatePage(String info) {
    userInteraction.updatePage(info);
  }

  public void updateMenu(Common.Menu menu) {
    userInteraction.updateMenu(menu);
  }

  public void executeUserAction() {
    userInteraction.executeUserAction();
  }
}
