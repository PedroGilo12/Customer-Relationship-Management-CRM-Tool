package adapter;

import utilities.Common.User;
import utilities.Common.Customer;
import utilities.Common.Sale;

public class dataManagerAdapter implements dataManager {

    private dataManager dataManager;

    public dataManagerAdapter(dataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public User getUser(String email, String password) {
        return dataManager.getUser(email, password);
    }

    @Override
    public void createUser(User user) {
        dataManager.createUser(user);
    }

    @Override
    public Customer getCustomer(String email) {
        return dataManager.getCustomer(email);
    }

    @Override
    public void createCustomer(Customer customer) {
        dataManager.createCustomer(customer);
    }

    @Override
    public Sale getSales(User user, Customer customer) {
        return dataManager.getSales(user, customer);
    }

    @Override
    public void updateSaleStatus(User user, Sale sale) {
        dataManager.updateSaleStatus(user, sale);
    }

    @Override
    public void createSale(User user, Sale sale) {
        dataManager.createSale(user, sale);
    }

}
