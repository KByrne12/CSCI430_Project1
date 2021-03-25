

import java.util.*;
import java.text.*;
import java.io.*;

public class ManagerState extends WareState {
 
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Warehouse warehouse;
    private WareContext context;
    private static ManagerState instance;
	private ClientList clientList;
	private ProductList productList;
	private SupplierList supplierList;
    //manager specific calls
    private static final int EXIT = IOHelper.EXIT;
    private static final int ADD_PRODUCT = 1;
    private static final int ADD_SUPPLIER = 2;
    private static final int SHOW_SUPPLIER_LIST= 3;
    private static final int SHOW_SUPPLIER_PRODUCTS = 4;
    private static final int SHOW_PRODUCT_SUPPLIERS = 5;
    private static final int UPDATE_PRODUCT_PRICE = 6;
    private static final int SALES_MENU = 7;
    private static final int HELP = IOHelper.HELP;
    
    private ManagerState() {
        super();
		supplierList = SupplierList.instance();
		productList = ProductList.instance();
		clientList = ClientList.instance();
        //context = WareContext.instance();
    }

    public static ManagerState instance() {
        if (instance == null) {
            instance = new ManagerState();
        }
        return instance;
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
    public void addProduct() {
        boolean successful;
        String productName = getToken("Enter the product name: ");
        int productQuantity = Integer.parseInt(getToken("Enter the product quantity: "));
        int productPrice = Integer.parseInt(getToken("Enter the product price: "));
        Product newProduct = new Product(productName, productQuantity, productPrice);
        successful = productList.insertProduct(newProduct);
        
        if (successful) {
            System.out.println("Product with details (" + newProduct.toString() + ") added successfully");
        } else {
            System.out.println("Issue adding product!");
        }
    
   public void addSupplier() {
        boolean successful;
        String supplierName = getToken("Enter the supplier name:");

        Supplier newSupplier = new Supplier(supplierName);

        successful = supplierList.insertSupplier(newSupplier);
        if (successful) {
            System.out.println("Supplier with name " + newSupplier.getName() + " and ID " + newSupplier.get_ID() + " added successfully");
        } else {
            System.out.println("Issue adding supplier!");
        }
    }

     public void showSupplierList() {
        Iterator<Supplier> S_Iterator = supplierList.getSuppliers();
        System.out.println("Printing Supplier List");		
		while (S_Iterator.hasNext()) {
			Supplier item = S_Iterator.next();
			System.out.println("ID: " + item.get_ID() + "Name: " + item.getName());
		}
     }

	public void showSuppliersProduct() {
		int productID = Integer.parseInt(getToken("Enter the product ID you wish to suppliers of: "));
		Iterator<Supplier> S_Iterator = supplierList.getSuppliers();
        System.out.println("Printing All Suppliers of a Product");		
		while (S_Iterator.hasNext()) {
			Supplier item = S_Iterator.next();
			Iterator<Supply> productSup = item.shipment();
			while (productSup.hasNext()) {
				Supply itemTwo = productSup.next();
				if (productID == itemTwo.get_ID()) {
					System.out.println("ID: " + item.get_ID() + "Name: " + item.getName());
				}
			}
		} 
     }

    public void showProductSuppliers() {
        int supplierID = Integer.parseInt(getToken("Enter the ID of the supplier to get list of products"));
		int position1 = supplierList.IDcheck(supplierID);
		Supplier item = supplierList.get_listed_obj(position1);
		item.print_list();
     }


     public void updatePrice() {
		int supplierID = Integer.parseInt(getToken("Enter the ID of the supplier to get list of products"));
		int position1 = supplierList.IDcheck(supplierID);
		Supplier item = supplierList.get_listed_obj(position1);
		item.print_list();
		int id = Integer.parseInt(getToken("ID of product to add, delete or edit"));
		int IDcheck = productList.IDcheck(id);
		if (IDcheck == -1) {
			System.out.println("No product with that ID");
		} else {
			String choice = getToken("INSERT, REMOVE, or CHANGE?");
			switch (choice) {
				case "INSERT": String nameP = getToken("enter the product name");
					String price_info = getToken("enter the product price information");
					double price = Double.parseDouble(getToken("enter the product price"));
					item.create_item(price, id, nameP, price_info);
					break;
				case "REMOVE":
					item.delete_item(id);
					break;
				case "CHANGE":
					item.grab_item(id);
					break;
				default: System.out.println("no action chosen, none taken");
			}
		}
     }


     
    private void salesMenu()
    {     
      (WareContext.instance()).changeState(2); //go to clerk state
    }
    
    private void help() {
        IOHelper.Println("Enter a number between " + EXIT + " and " + HELP + " as explained below:");
        IOHelper.Println(EXIT + " to Exit\n");
        IOHelper.Println(ADD_PRODUCT+ " to add a product");
        IOHelper.Println(ADD_SUPPLIER + " to add Supplier");
        IOHelper.Println(SHOW_SUPPLIER_LIST+ " to show supplier list");
        IOHelper.Println(SHOW_SUPPLIER_PRODUCTS + " to  display supplier of a product");
        
        IOHelper.Println(SHOW_PRODUCT_SUPPLIERS + " to  display product supplied by the supplier ");
         IOHelper.Println(UPDATE_PRODUCT_PRICE + " to  update product and price");
        IOHelper.Println(SALES_CLERK + " to  switch to the Sales Clerk menu");
        
        IOHelper.Println(HELP + " for help");
    }
    
    public void logout()
    {
        (WareContext.instance()).changeState(0); // exit
    }
    
    public void process() {
        int command;
        help();
        while ((command = IOHelper.GetCmd()) != EXIT) {
            switch (command) {

                case ADD_PRODUCT: addProduct();
                                break;
                case ADD_SUPPLIER: addSupplier();
                                break;
                case SHOW_SUPPLIER_LIST: showSupplierList();
                                break;
                case SHOW_SUPPLIER_PRODUCTS: showSuppliersProduct();
                                break;
                case SHOW_PRODUCT_SUPPLIERS: showProductSuppliers();
                                break;
                case UPDATE_PRODUCT_PRICE: updatePrice();
                                break;             
                case SALES_MENU: salesMenu();
                                break;
                case HELP: help();
                                break;
            }
        }

        logout();
    }
    
    public void run() {
        process();
    }
}
