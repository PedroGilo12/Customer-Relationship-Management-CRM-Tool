package dataInterface;

import adapter.*;
import application.UserAction;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import utilities.Common;
import utilities.Common.Customer;
import utilities.Common.CustomerInformations;
import utilities.Common.Sale;
import utilities.Common.User;

public class JsonManagerInterface implements dataManager {

  private Common common = new Common();

  public String userFolderPath = "Data/Users/";
  public String customerFolderPath = "Data/Customers/";
  public String usersFile = "users.txt";
  public String customersFile = "customers.txt";

  private String getUsersFilePath() { return userFolderPath + usersFile; }

  private String getCustomersFilePath() {
    return customerFolderPath + customersFile;
  }

  UserAction userAction;

  public JsonManagerInterface(UserAction userAction) {
    this.userAction = userAction;
  }

  @Override
  public void createUser(User user) {

    File users_file = new File(getUsersFilePath());

    File userSaleFile = new File(this.userFolderPath + user.email + ".txt");

    if (!userSaleFile.exists()) {
      try {
        userSaleFile.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    try {
      FileWriter fileWriter = new FileWriter(users_file, true);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

      Gson gson = new Gson();
      String userJson = gson.toJson(user);
      bufferedWriter.write(userJson);
      bufferedWriter.newLine();

      bufferedWriter.close();
      fileWriter.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public User getUser(String email, String password) {

    Common.User user = null;
    File users_file = new File(getUsersFilePath());

    try {
      FileReader fileReader = new FileReader(users_file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      String line;

      while ((line = bufferedReader.readLine()) != null) {
        Gson gson = new Gson();
        user = gson.fromJson(line, Common.User.class);

        if (user.email.equals(email) && user.password.equals(password)) {
          bufferedReader.close();
          return user;
        }
      }

      bufferedReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }
  @Override
  public Customer getCustomer(String email) {
    Customer customer = null;
    File customersFile = new File(getCustomersFilePath());

    try {
      FileReader fileReader = new FileReader(customersFile);
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      String line;

      while ((line = bufferedReader.readLine()) != null) {
        Gson gson = new Gson();
        customer = gson.fromJson(line, Customer.class);

        if (customer.email.equals(email)) {
          bufferedReader.close();
          return customer;
        }
      }

      bufferedReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public void createCustomer(Customer customer) {
    File customersFile = new File(getCustomersFilePath());

    try {
      FileWriter fileWriter = new FileWriter(customersFile, true);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

      Gson gson = new Gson();
      String customerJson = gson.toJson(customer);
      bufferedWriter.write(customerJson);
      bufferedWriter.newLine();

      bufferedWriter.close();
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void createSale(User user, Sale sale) {
    File userSaleFile = new File(this.userFolderPath + user.email + ".txt");

    try {

      FileWriter fileWriter = new FileWriter(userSaleFile, true);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

      Gson gson = new Gson();
      String saleJson = gson.toJson(sale);
      bufferedWriter.write(saleJson);
      bufferedWriter.newLine();

      bufferedWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Sale getSales(User user, Customer customer) {
    Sale sale = null;
    File userSaleFile = new File(this.userFolderPath + user.email + ".txt");

    try {
      FileReader fileReader = new FileReader(userSaleFile);
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      String line;

      while ((line = bufferedReader.readLine()) != null) {
        Gson gson = new Gson();
        sale = gson.fromJson(line, Sale.class);

        if (sale.Customer.equals(customer.email)) {
          bufferedReader.close();
          return sale;
        }
      }

      bufferedReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return sale;
  }

  @Override
  public void updateSaleStatus(User user, Sale sale) {
    File userSaleFile = new File(this.userFolderPath + user.email + ".txt");
    File tempFile = new File(this.userFolderPath + "temp.txt");

    try {
      FileReader fileReader = new FileReader(userSaleFile);
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      FileWriter fileWriter = new FileWriter(tempFile, true);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

      String line;

      while ((line = bufferedReader.readLine()) != null) {
        Gson gson = new Gson();
        Sale existingSale = gson.fromJson(line, Sale.class);

        if (existingSale.Customer.equals(sale.Customer) &&
            existingSale.Product.equals(sale.Product)) {
          existingSale.Status = sale.Status;
        }

        String updatedSaleJson = gson.toJson(existingSale);
        bufferedWriter.write(updatedSaleJson);
        bufferedWriter.newLine();
      }

      bufferedReader.close();
      bufferedWriter.close();

      userSaleFile.delete();
      tempFile.renameTo(userSaleFile);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Customer getCustomerByIndex(int index) {
    Customer customer = null;
    File customersFile = new File(getCustomersFilePath());

    try {
      FileReader fileReader = new FileReader(customersFile);
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      String line;
      int currentIndex = 0;

      while ((line = bufferedReader.readLine()) != null) {
        if (currentIndex == index) {
          Gson gson = new Gson();
          customer = gson.fromJson(line, Customer.class);
          bufferedReader.close();
          return customer;
        }

        currentIndex++;
      }

      bufferedReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public void updateCustomer(Customer updatedCustomer) {
    File customersFile = new File(getCustomersFilePath());
    File tempFile = new File(customerFolderPath + "tempCustomers.txt");

    try {
      FileReader fileReader = new FileReader(customersFile);
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      FileWriter fileWriter = new FileWriter(tempFile, true);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

      String line;

      while ((line = bufferedReader.readLine()) != null) {
        Gson gson = new Gson();
        Customer existingCustomer = gson.fromJson(line, Customer.class);

        if (existingCustomer.email.equals(updatedCustomer.email)) {
          existingCustomer.name = updatedCustomer.name;
          existingCustomer.address = updatedCustomer.address;
          existingCustomer.phone = updatedCustomer.phone;
          existingCustomer.sales = updatedCustomer.sales;
          existingCustomer.managerName = updatedCustomer.managerName;
          existingCustomer.satisfaction = updatedCustomer.satisfaction;
          existingCustomer.vendorName = updatedCustomer.vendorName;
          existingCustomer.customerInformations = updatedCustomer.customerInformations;
        }

        String updatedCustomerJson = gson.toJson(existingCustomer);
        bufferedWriter.write(updatedCustomerJson);
        bufferedWriter.newLine();
      }

      bufferedReader.close();
      bufferedWriter.close();

      customersFile.delete();
      tempFile.renameTo(customersFile);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void generateSaleReport(User user) {
    Document document = new Document();

    try {
      File userSaleFile = new File(this.userFolderPath + user.email + ".txt");

      int totalSales = 0;
      int totalCustomers = 0;

      try (BufferedReader bufferedReader =
               new BufferedReader(new FileReader(userSaleFile))) {
        while (bufferedReader.readLine() != null) {
          totalSales++;
        }
      }

      File customersFile = new File(getCustomersFilePath());

      try (BufferedReader bufferedReader =
               new BufferedReader(new FileReader(customersFile))) {
        while (bufferedReader.readLine() != null) {
          totalCustomers++;
        }
      }

      PdfWriter.getInstance(
          document,
          new FileOutputStream(userFolderPath + user.email + "_report.pdf"));
      document.open();

      document.add(new Paragraph("User Sales Report: " + user.email));
      document.add(new Paragraph("Total Sales: " + totalSales));
      document.add(new Paragraph("Total Customers: " + totalCustomers));
      document.add(new Paragraph("\n"));

      document.add(new Paragraph("Detailed Sales:"));
      document.add(new Paragraph("\n"));

      Sale sale;

      try (BufferedReader bufferedReader =
               new BufferedReader(new FileReader(userSaleFile))) {
        String line;
        while ((line = bufferedReader.readLine()) != null) {
          Gson gson = new Gson();
          sale = gson.fromJson(line, Sale.class);

          document.add(new Paragraph("Product: " + sale.Product));
          document.add(new Paragraph("Status: " + sale.Status));
          document.add(new Paragraph("Customer: " + sale.Customer));
          document.add(new Paragraph("\n"));
        }
      }

      document.close();
      System.out.println("Sales report generated successfully!");

    } catch (DocumentException | IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Sale getSalebyIndex(User user, int index) {
    Sale sale = null;
    File userSaleFile = new File(this.userFolderPath + user.email + ".txt");

    try {
      FileReader fileReader = new FileReader(userSaleFile);
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      String line;
      int currentIndex = 0;

      while ((line = bufferedReader.readLine()) != null) {
        if (currentIndex == index) {
          Gson gson = new Gson();
          sale = gson.fromJson(line, Sale.class);
          bufferedReader.close();
          return sale;
        }

        currentIndex++;
      }

      bufferedReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }
}
