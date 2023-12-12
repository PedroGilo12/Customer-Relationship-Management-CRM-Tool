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
    CAMPAIGN_DASHBOARD,
  }

  public class CustomerInformations {

    public CustomerInformations(int meetNumber, String scheduledAppointments,
                                String primaryInterest,
                                String secondaryInterest, String cpf, String rg,
                                String activeCapaing) {
      this.meetNumber = meetNumber;
      this.scheduledAppointments = scheduledAppointments;
      this.primaryInterest = primaryInterest;
      this.secondaryInterest = secondaryInterest;
      this.cpf = cpf;
      this.rg = rg;
      this.activeCapaing = activeCapaing;
    }

    public int meetNumber;
    public String scheduledAppointments;
    public String primaryInterest;
    public String secondaryInterest;
    public String cpf;
    public String rg;
    public String activeCapaing;
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

    public Customer(String name, String email, String address, String phone,
                    String sales, String managerName, int satisfaction,
                    String vendorName,
                    CustomerInformations customerInformations) {
      this.name = name;
      this.email = email;
      this.address = address;
      this.phone = phone;
      this.sales = sales;
      this.managerName = managerName;
      this.satisfaction = satisfaction;
      this.vendorName = vendorName;
      this.customerInformations = customerInformations;
    }
    public String name;
    public String email;
    public String address;
    public String phone;
    public String sales;
    public String managerName;
    public int satisfaction;
    public String vendorName;
    public CustomerInformations customerInformations;
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
