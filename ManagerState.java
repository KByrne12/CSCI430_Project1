

import java.util.*;
import java.text.*;
import java.io.*;

public class ManagerState extends WareState {
 
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Warehouse warehouse;
    private WareContext context;
    private static ManagerState instance;
  
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
        warehouse = Warehouse.instance();
        //context = WareContext.instance();
    }

    public static ManagerState instance() {
        if (instance == null) {
            instance = new ManagerState();
        }
        return instance;
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

     public void showProductList() {
        System.out.println("Dummy Action");
     }

     public void showSupplierList() {
        System.out.println("Dummy Action");
     }

       public void showSuppliersProduct() {
        System.out.println("Dummy Action");
     }

    public void showProductSuppliers() {
        System.out.println("Dummy Action");
     }


     public void updatePrice() {
        System.out.println("Dummy Action");
     }


     
    private void salesMenu()
    {     
      (WareContext.instance()).changeState(WareContext.SALES_STATE); //go to sales state
    }
    
    private void save() {
        if (warehouse.save()) {
            System.out.println(" The warehouse has been successfully saved in the file WarehouseData \n");
        } else {
            System.out.println(" There has been an error in saving \n");
        }
    }

    private void retrieve() {
        try {
            Warehouse tempLibrary = Warehouse.retrieve();
            if (tempLibrary != null) {
                System.out.println(" The warehouse has been successfully retrieved from the file WarehouseData \n");
                warehouse = tempLibrary;
            } else {
                System.out.println("File doesnt exist; creating new warehouse");
                warehouse = Warehouse.instance();

            }
        } catch (Exception cnfe) {
            cnfe.printStackTrace();
        }
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
        (WareContext.instance()).changeState(WareContext.MANAGER_STATE); // exit
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
