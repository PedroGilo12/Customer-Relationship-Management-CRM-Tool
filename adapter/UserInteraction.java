package adapter;

public interface UserInteraction {

  void displayPage(Common.UserInteractionPages page, Object data);
  void displayMessage(String message, Object data);

  Object getUserGenericInput();
  Common.User loginUser();
  Common.User registerUser();
  Common.Customer registerCustomer();

  String getCustomerContact();
  Common.SaleStatus getSaleStatus(String email);
}
