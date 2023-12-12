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
    public Customer getCustomerByIndex(int index) {
        return dataManager.getCustomerByIndex(index);
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

    @Override
    public void updateCustomer(Customer customer) {
        dataManager.updateCustomer(customer);
    }

    @Override
    public void generateSaleReport(User user) {
        dataManager.generateSaleReport(user);
    }

    @Override
    public Sale getSalebyIndex(User user, int index) {
        return dataManager.getSalebyIndex(user, index);
    }

    @Override
    public User getUserByName(String name) {
        return dataManager.getUserByName(name);
    }
}
