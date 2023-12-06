package adapter;

public class Common {

  public class User {
    public String name;
    public String email;
    public String password;
    public String phone;
    public UserRoles role;
    public String ManagerName;
  }

  public class Customer {
    public String name;
    public String email;
    public String address;
    public String phone;
    public String saleProcess;
    public String ManagerName;
    public String TeamMemberName;
  }

  public enum SaleStatus {
    ACTIVE,
    INACTIVE,
    PENDING,
    CANCELED,
  }

  public enum UserRoles {
    MANAGER,
    TEAM_MEMBER,
  }

  public enum UserInteractionPages {
    MAIN_MENU,
    LOGIN,
    SIGNUP,
    HOME,
    PROFILE,
    CONTACTS,
    CUSTOMERS,
    REGISTER_CUSTOMER,
    SALES_PROCESS,
    SIMPLE_DASHBOARD,
    COMPLEX_DASHBOARD,
  }
}
