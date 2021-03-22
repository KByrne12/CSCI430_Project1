package main;

public class ClientUI {
	
	public void ClientUIDisplay ()
	{
		System.out.println("1: Show Client Details");
		System.out.println("2: Show Products");
		System.out.println("3: Show Transaction History");
		System.out.println("4: Modify Shopping Cart");
		System.out.println("5: Show waitlist");
		System.out.println("0: Logout");
	}
	
	public void ModifyShoppingCartDisplay()
	{
		System.out.println("1: Add Item to Cart");
		System.out.println("2: Remove from Cart");
		System.out.println("3: Change Quantity");
		System.out.println("0: Return");
	}
	
	public void ShoppingCartProcess() {
		int cartCommand;
		while((cartCommand = getCommand()) != EXIT)
		{
			ModifyShoppingCartDisplay();
			switch (cartCommand) {
				case 1:
					addToCart();
					break;
				case 2:
					removeFromCart();
					break;
				case 3:
					changeQuantity();
					break;
			}
		}
	}
	
	public void ClientProcess () {
		int clientCommand;
		while ((clientCommand = getCommand()) != EXIT)
		{
			ClientUIDisplay();
			switch(clientCommand) {
				case 1:
					ClientInfo();
					break;
				case 2:
					ShowProducts();
					break;
				case 3:
					ShowTransactionHistory();
					break;
				case 4:
					ShoppingCartProcess();
					break;
				case 5:
					ShowWaitlist();
					break;
			}
			
		}
		
	}

	
	public void ClientInfo (Client client) {
		int clientID = Integer.parseInt(getToken("Enter ID of client to edit"));
		position = clientList.IDcheck(clientID);
		Client client = clientList.get_listed_obj(position);
		System.out.println("ID: " + client.getId());
		System.out.println("Name: " + client.getName());
		System.out.println("Address: " + client.getAddress());
	}
	
	public void ShowProducts() {
		Product pr;
		for (pr : productList)
		{
			System.out.println("Name: " + pr.getName());
			System.out.println("ID: " + pr.getID());
			System.out.println("Price: " + pr.getPrice());
			System.out.println("Stock: " + pr.getQuantity());
			System.out.println("------------------------------");
		}
		
	}
	
	public void ShowTransactionHistory(Client client) {
		client.DisplayInvoices();
	}
	
	public void ShowWaitlist() {
		//show waitlist
		
	}

}
