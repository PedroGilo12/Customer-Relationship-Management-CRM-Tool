package adapter;

public class UserInteractionAdapter {

  private UserInteraction userInteraction;

  public UserInteractionAdapter(UserInteraction userInteraction) {
    this.userInteraction = userInteraction;
  }

  public void displayPage(Common.UserInteractionPages page, Object data) {
    userInteraction.displayPage(page, data);
  }

  public void displayMessage(String message, Object data) {
    userInteraction.displayMessage(message, data);
  }

  public Common.User loginUser() { return userInteraction.loginUser(); }

  public Common.User registerUser() { return userInteraction.registerUser(); }

  public Common.Customer registerCustomer() {
    return userInteraction.registerCustomer();
  }

  public String getCustomerContact() {
    return userInteraction.getCustomerContact();
  }

  public Common.SaleStatus getSaleStatus(String email) {
    return userInteraction.getSaleStatus(email);
  }

  public Object getUserGenericInput() {
    return userInteraction.getUserGenericInput();
  }
}
