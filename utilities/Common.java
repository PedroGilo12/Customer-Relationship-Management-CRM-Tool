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

    public class Menu {
        public String option0;
        public String option1;
        public String option2;
        public String option3;
    }

}
