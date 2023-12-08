package utilities;

public class Common {

  public enum userActionState {
    HOME,
    LOGIN,
    SIGNUP,
    UNREGISTER,
    SALE_MANAGER,
    CUSTOMER_MANAGER,
    ACTIVITY_TRACKING,
  }

  public class User {

    public User(String name, String email, String password, String phone,
                String role, String managerName) {
      this.name = name;
      this.email = email;
      this.password = password;
      this.phone = phone;
      this.role = role;
      this.managerName = managerName;
    }

    public String name;
    public String email;
    public String password;
    public String phone;
    public String role;
    public String managerName;
  }

  public class Customer {
    public String name;
    public String email;
    public String address;
    public String phone;
    public String sales;
    public String managerName;
    public String vendorName;
  }

  public class Sale {
    public Sale(String Product, String Status, String Customer) {
      this.Product = Product;
      this.Status = Status;
      this.Customer = Customer;
    }

    public String Product;
    public String Status;
    public String Customer;
  }

  public class Menu {

    public Menu(String option0, String option1, String option2,
                String option3) {
      this.option0 = option0;
      this.option1 = option1;
      this.option2 = option2;
      this.option3 = option3;
    }

    public String option0;
    public String option1;
    public String option2;
    public String option3;
  }
}
