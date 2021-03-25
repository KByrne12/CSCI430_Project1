
import java.util.*;
import java.io.*;

public class ClientUI extends WareState {
	
	private static Warehouse warehouse;
	private WareContext context;
	private static ClientUI instance;
	
	private ClientList clientList;
	private ProductList productList;
	private SupplierList supplierList;
	
	private ClientUI() {
		super();
		supplierList = SupplierList.instance();
		productList = ProductList.instance();
		clientList = ClientList.instance();
		int positon = ClientList.IDcheck((WareContext.instance()).getUID());
		if (position = -1 || (WareContext.instance()).getUID() == 1) {
			System.out.println("Adding dummy client as no client currently exist");
			Client dummy = new Client("Joe Schmoe", "123 Nowheres Ville");
			ClientList.insertMember(dummy);
		} else if (position == -1) {
			System.out.println("Invalid ID, try 1");
		}
	}
	
	public static ClientUI instance() {
		 if (instance == null) {
			 instance = new ClientUI();
		 }	        
        return instance;
	    
	 }
	
	public void ClientUIDisplay ()
	{
		System.out.println("1: Show Client Details");
		System.out.println("2: Show Products");
		System.out.println("3: Show Transaction History");
		System.out.println("4: Show waitlist");
		System.out.println("5: Add Item to Cart");
		System.out.println("6: Remove from Cart");
		System.out.println("7: Change Quantity");
		System.out.println("0: Logout");
	}
	
	
	public void ClientProcess () {
		int position = ClientList.IDcheck((WareContext.instance()).getUID());
		Client loggedInClient = ClientList.get_listed_obj(position);
		int clientCommand = 1;
		while ((clientCommand = IOHelper.GetCmd()) != 0)
		{
			ClientUIDisplay();
			switch(clientCommand) {
				case 1:
					ClientInfo(loggedInClient);
					break;
				case 2:
					ShowProducts();
					break;
				case 3:
					ShowTransactionHistory(loggedInClient);
					break;
				case 4:
					ShowWaitlist();
					break;
				case 5:
					AddItemsToCart(loggedInClient);
					break;
				case 6:
					RemoveCartItem(loggedInClient);
					break;
				case 7:
					ChangeCartQuantity(loggedInClient);
					break;
			}
			
		}
		
		logout();
		
	}

	
	public void ClientInfo (Client client) {
		System.out.println("ID: " + client.getId());
		System.out.println("Name: " + client.getName());
		System.out.println("Address: " + client.getAddress());
	}
	
	
	public void ShowProducts() {
        Iterator<Product> P_Iterator = productList.getProducts();
        System.out.println("Printing Supplier List");
        while (P_Iterator.hasNext()) {
            Product item = P_Iterator.next();
			System.out.println("Name: " + item.getName());
			System.out.println("ID: " + item.getID());
			System.out.println("Price: " + item.getPrice());
			System.out.println("------------------------------");
        }
     }
	
	
	
	
	
	public void ShowTransactionHistory(Client client) {
		client.DisplayInvoices();
	}
	
	public void ShowWaitlist() {
		//show waitlist
		
	}
	
	public void AddItemsToCart(Client client) {
		int productToAddID = Integer.parseInt(getToken("Please enter the product ID you wish to add: "));
		Product productToAdd = productList.search(productToAddID);
		int quant = Integer.parseInt(getToken("Enter the quantity to be added to the cart: "));
		client.AddToCart(productToAdd, quant);
		
	}
	
	public void ChangeCartQuantity(Client client) {
		int productID = Integer.parseInt(getToken("Enter the product ID you wish to change the quantity of: "));
		int qty = Integer.parseInt(getToken("Enter the new quantity: "));
		client.ChangeQuantity(productID, qty);	
	}
	
	public void RemoveCartItem(Client client) {
		int productID = Integer.parseInt(getToken("Please enter the product ID you wish to delete: "));
		client.RemoveCartProduct(productID);	
	}
	
	public void logout() {
		
		(WareContext.instance()).changeState(0);
	}
	
	
	public void run() {
		ClientProcess();
	}

}
