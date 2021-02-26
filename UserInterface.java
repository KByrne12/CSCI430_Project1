import java.util.*;
import java.text.*;
import java.io.*;

public class UserInterface {
	private static UserInterface userInterface;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static Client client = new Client("Billy", 12345, "123 Sample Ln");
	private ClientList clientList;
	private ProductList productList;
	private SupplierList supplierList;
	private static final int EXIT = 0;
	private static final int ADD_PRODUCT = 1;
	private static final int ADD_CLIENT = 2;
	private static final int ADD_SUPPLIER = 3;
	private static final int ADD_TO_CART = 4;
	private static final int EDIT_A_FIELD = 5;
	private static final int MENU = 6;
	private static final String NAME = "NAME";
	private static final String PRICE = "PRICE";
	private static final String QUANTITY = "QUANTITY";
	private static final String ADDRESS = "ADDRESS";
	
	private UserInterface() {
		supplierList = SupplierList.instance();
		productList = ProductList.instance();
		clientList = ClientList.instance();
	}

	public static UserInterface instance() {
		if (userInterface == null) {
			return userInterface = new UserInterface();
		} else {
			return userInterface;
		}
	}
	
	public String getToken(String prompt) {
    		do {
      			try {
        			System.out.println(prompt);
        			String line = reader.readLine();
        			StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
        			if (tokenizer.hasMoreTokens()) {
          			return tokenizer.nextToken();
        			}
      			} catch (IOException ioe) {
        			System.exit(0);
      			}
    		} while (true);
  	}
	
	public int getCommand() {
		do {
			try {
				int value = Integer.parseInt(getToken("Enter a command. [Use command " + MENU + " for a list of commands]"));
				if (value >= EXIT && value <= MENU) {
					return value;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Please enter a number");
			}
		} while (true);
	}
	
	public void menu() {
		System.out.println("Enter the number corresponding to the desired command:");
		System.out.println("Exit: " + EXIT);
		System.out.println("Add a Product: " + ADD_PRODUCT);
		System.out.println("Add a Client: " + ADD_CLIENT);
		System.out.println("Add a Supplier: " + ADD_SUPPLIER);
		System.out.println("Add to Cart: " + ADD_TO_CART);
		System.out.println("Edit a Field: " + EDIT_A_FIELD);
		System.out.println("Menu: " + MENU);
	}
	
	public void addProduct() {
		boolean successful;
		String productName = getToken("Enter the product name: ");
		int productQuantity = Integer.parseInt(getToken("Enter the product quantity: "));
		String productID = getToken("Enter the product ID: ");
		int productPrice = Integer.parseInt(getToken("Enter the product price: "));

		Product newProduct = new Product(productName, productID, productQuantity, productPrice);
		successful = productList.insertProduct(newProduct);
		
		if (successful) {
			System.out.println("Product with details (" + newProduct.toString() + ") added successfully");
		} else {
			System.out.println("Issue adding product!");
		}
	}
	
	public void addClient() {
		boolean successful;
		String clientName = getToken("Enter the client's name: ");
		int clientID = Integer.parseInt(getToken("Enter the client's ID: "));
		String clientAddress = getToken("Enter the client's address: ");

		Client newClient = new Client(clientName, clientID, clientAddress); //DOESNT WANT TO CREATE THE CLIENT OR ADD TO LIST
		successful = clientList.insertMember(newClient);

		if (successful) {
			System.out.println("Client with details (" + newClient.toString() + ") added successfully");
		} else {
			System.out.println("Issue adding client!");
		}
		
	}
	
	public void addSupplier() {
		boolean successful;
		int supplierID = Integer.parseInt(getToken("Enter the supplier ID: "));
		String supplierName = getToken("Enter the supplier name:");

		Supplier newSupplier = new Supplier(supplierID, supplierName);

		successful = supplierList.insertSupplier(newSupplier);
		if (successful) {
			System.out.println("Supplier with name " + newSupplier.get_name() + " and ID " + newSupplier.get_ID() + " added successfully");
		} else {
			System.out.println("Issue adding supplier!");
		}
	}
	
	public void addToCart() { //PLAN TO ADD SUPPORT FOR SPECIFYING CLIENT BY ID TO ADD TO CART
		String productToAddID = getToken("Please enter the product ID string you wish to add: ");
		Product productToAdd = productList.search(productToAddID);
		int quant = Integer.parseInt(getToken("Enter the quantity to be added to the cart: "));
		client.AddToCart(productToAdd, quant);
	}

	public void editAField() {
		System.out.println("Would you like to edit");
		System.out.println("1. Product");
		System.out.println("2. Client");
		System.out.println("3. Supplier");
		int choice = Integer.parseInt(getToken("Enter your choice: "));
		
		switch (choice) {
			case 1: String productID = getToken("Enter ID of product to edit");
				int position = productList.IDcheck(productID);
				Product product = productList.get_listed_obj(position);
				product = edit_product(product);
				productList.set_listed_obj(position, product);
				break;
			case 2: int clientID = Integer.parseInt(getToken("Enter ID of client to edit"));
				int position = clientList.IDcheck(clientID);
				Client client = clientList.get_listed_obj(position);
				client = edit_client(client);
				clientList.set_listed_obj(position, client);
				break;
			case 3: int supplierID = Integer.parseInt(getToken("Enter ID of supplier to edit"));
				int position = supplierList.IDcheck(supplierID);
				Supplier supplier = supplierList.get_listed_obj(position);
				supplier = edit_supplier(supplier);
				supplierList.set_listed_obj(position, supplier);
				break;
			default:
				System.out.println("Incorrect choice");
		}	
	}
	public Supplier edit_supplier (Supplier item) {
		String userEdit = getToken("Edit NAME?");
		switch (userEdit) {
			case NAME: String name = getToken("To what name?");
				item.setName(name);
				break;
			default: System.out.println("no action chosen, none taken");
		}
		return item;
	}
	public Client edit_client (Client item) {
		String userEdit = getToken("Edit NAME or ADDRESS");
		switch (userEdit) {
			case NAME: String name = getToken("To what name?");
				item.setName(name);
				break;
			case ADDRESS: String address = getToken("To what address?");
				item.setAddress(address);
				break;
			default: System.out.println("no action chosen, none taken");
		}
		return item;
	}
	public Product edit_product (Product item) {
		String userEdit = getToken("Edit NAME, PRICE, OR QUANTITY");
		switch (userEdit) {
			case NAME: String name = getToken("To what name?");
				item.setName(name);
				break;
			case PRICE: int price = Integer.parseInt(getToken("To what price"));
				item.setPrice(price);
				break;
			case QUANTITY: int quantity = Integer.parseInt(getToken("To what quantity"));
				item.setQuantity(quantity);
				break;
			default: System.out.println("no action chosen, none taken");
		}
		return item;
	}
	public void process() {
		int command;
		menu();
		while ((command = getCommand()) != EXIT) {
			switch (command) {
				case ADD_PRODUCT:
					addProduct();
					break;
				case ADD_CLIENT:
					addClient();
					break;
				case ADD_SUPPLIER:
					addSupplier();
					break;
				case ADD_TO_CART:
					addToCart();
					break;
				case EDIT_A_FIELD:
					editAField();
					break;
				case MENU:
					menu();
					break;
				default:
					System.out.println("Not a valid command");
					command = EXIT;
			}
		}
	}
					
	public static void main(String[] args) {
		UserInterface.instance().process();
	}
}
