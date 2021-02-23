
import java.util.*;
import java.time.LocalDate;

public class Invoice {
	LinkedList<Product> products = new LinkedList<Product>();
	int total;
	LocalDate date = LocalDate.now();
	boolean paid = false;
	
	
	
	public Invoice (cart userCart, int total)
	{
		products = userCart.shoppingCart;
		this.total = total;		
	}
	
	public void printInvoice()
	{
		System.out.println("Date: " + date);
		System.out.println("Products: " + products);
		System.out.println("total: " + total);
	}
	
	public void payInvoice()
	{
		System.out.println("Invoice has been paid.");
		paid = true;
	}

}
