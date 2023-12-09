package adapter;

import utilities.*;
import utilities.Common.Customer;
import utilities.Common.Sale;
import utilities.Common.User;

public interface dataManager {  

    User getUser(String email, String password);
    void createUser(User user);

    Customer getCustomer(String email);
    Customer getCustomerByIndex(int index);
    void createCustomer(Customer customer);
    void updateCustomer(Customer customer);

    Sale getSales(User user, Customer customer);
    Sale getSalebyIndex(User user, int index);
    void updateSaleStatus(User user, Sale sale);
    void createSale(User user, Sale sale);

    void generateSaleReport(User user);
    
}
