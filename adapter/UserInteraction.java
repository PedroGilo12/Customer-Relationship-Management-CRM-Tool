package adapter;

import utilities.*;

public interface UserInteraction {

  void displayPage(Common.userActionState state);
  void updatePage(String info);

  void updateMenu(Common.Menu menu);
  void executeUserAction();
  String getUserResponse();

}
