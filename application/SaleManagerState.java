package application;

import adapter.dataManager;
import dataInterface.*;
import java.util.*;
import userInterface.*;
import utilities.*;
import utilities.Common.Customer;
import utilities.Common.Menu;
import utilities.Common.Sale;
import utilities.Common.User;


public class SaleManagerState implements UserActionState {

  private String name = "Sale Manager";
  private UserAction userAction;

  Common common = new Common();

  Menu menu = common.new Menu("Update or create new sale.",
                              "Generate sales report.", "Return", "");

  public SaleManagerState(UserAction userAction) {
    this.userAction = userAction;
  }

  @Override
  public void entryState() {
    userAction.userInteraction.displayPage(Common.userActionState.SALE_MANAGER);
    userAction.userInteraction.updatePage("Welcome " +
                                          userAction.ActiveUser.name + "!");

    int index = 0;

    while (true) {
      Sale sale =
          userAction.dataManager.getSalebyIndex(userAction.ActiveUser, index);

      if (sale == null) {
        break;
      }

      userAction.userInteraction.updatePage("\nSale number " + index + ": ");
      userAction.userInteraction.updatePage("    Customer: " + sale.Customer);
      userAction.userInteraction.updatePage("    product: " + sale.Product);
      userAction.userInteraction.updatePage("    status: " + sale.Status);

      index++;
    }

    userAction.userInteraction.updateMenu(menu);
    userAction.userInteraction.executeUserAction();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void action0() {
    userAction.userInteraction.updatePage(
        "Type the name of the product or service: ");
    String product = userAction.userInteraction.getUserResponse();

    userAction.userInteraction.updatePage("Type the sale's status: ");
    String status = userAction.userInteraction.getUserResponse();

    userAction.userInteraction.updatePage("Type the email of the customer: ");
    String email = userAction.userInteraction.getUserResponse();

    Sale sale = common.new Sale(product, status, email);

    userAction.dataManager.createSale(userAction.ActiveUser, sale);

    userAction.setState(new SaleManagerState(userAction));
  }

  @Override
  public void action1() {
    userAction.dataManager.generateSaleReport(userAction.ActiveUser);
    userAction.setState(new SaleManagerState(userAction));
  }

  @Override
  public void action2() {
    userAction.setState(new HomeState(userAction));
  }

  @Override
  public void action3() {
    userAction.setState(new SaleManagerState(userAction));
  }
}
