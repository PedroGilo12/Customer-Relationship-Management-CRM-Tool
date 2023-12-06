package userInterface;

import adapter.UserInteraction;
import adapter.Common.Customer;
import adapter.Common.SaleStatus;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import adapter.Common;

public class DesktopInterface implements UserInteraction {

    private Common commonInstance;

    @Override
    public void displayPage(Common.UserInteractionPages page, Object data) {
        System.out.printf("[page: %s]\n\n", page.name());
        switch (page) {
            case MAIN_MENU:
                System.out.println("Bem vindo ao ClietConnect");
                System.out.printf("[1] Para prosseguir com o login.\n[2] Para prosseguir com o cadastro.\n");
                break;

            case LOGIN:
                System.out.println("Bem vindo ao ClietConnect, por favor efetue o login\n");
                System.out.printf("[1] Prosseguir com o login, envie 1.\n[2] Efetuar o cadastro, envie 2.\n");
                break;

            case SIGNUP:
                System.out.println("Bem vindo ao ClietConnect, por favor registre-se\n");
                System.out.printf("[1] Para prosseguir com o cadastro.\n[2] Efetuar o login.\n");
                break;

            case HOME:
                Common.User user = (Common.User) data;
                System.out.printf("Seja bem vindo %s\n\n", user.name);
                break;

            default:
                System.out.println("Página desconhecida");
                break;
        }

        System.out.printf("\n");
    }

    @Override
    public void displayMessage(String message, Object data) {
        System.out.printf(message);
    }

    @Override
    public Object getUserGenericInput() {
        return System.console().readLine();
    }

	@Override
	public Common.User loginUser() {
		Common.User user = null; 
		String users_file = "user.json";

		System.out.println("Por favor, digite seu email: ");
		String email = System.console().readLine();

		System.out.println("Por favor, digite sua senha: ");
		String password = System.console().readLine();

		try {
			File file = new File(users_file);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String line;

			while((line  = bufferedReader.readLine()) != null) {
				Gson gson = new Gson();
				user = gson.fromJson(line, Common.User.class);

				if(user.email.equals(email) && user.password.equals(password)) {
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
    public Common.User registerUser() {
        Common common = new Common();
        Common.User newUser = common.new User();

        System.out.println("Por favor, digite seu nome: ");
        newUser.name = System.console().readLine();

        System.out.println("Por favor, digite seu email: ");
        newUser.email = System.console().readLine();

        System.out.println("Por favor, digite sua senha: ");
        newUser.password = System.console().readLine();

        System.out.println("Por favor, digite seu telefone: ");
        newUser.phone = System.console().readLine();

        newUser.role = Common.UserRoles.MANAGER;
        newUser.ManagerName = "nobody";

        String users_file = "user.json";
        try {
            FileWriter fileWriter = new FileWriter(users_file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            Gson gson = new Gson();
            String userJson = gson.toJson(newUser);
            bufferedWriter.write(userJson);
            bufferedWriter.newLine();

            bufferedWriter.close();
            fileWriter.close();

            return newUser;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public Customer registerCustomer() {
        Common.Customer customer = commonInstance.new Customer();
        return customer;
    }

    @Override
    public String getCustomerContact() {
        return "customer contact";
    }

    @Override
    public SaleStatus getSaleStatus(String email) {
        return Common.SaleStatus.PENDING;
    }
}
