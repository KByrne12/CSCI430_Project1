

import java.util.*;

public class InvoiceList {
	
	private List<Invoice> invoices = new LinkedList<Invoice>();
	
	
	
	
	public void AddInvoice(cart userCart, int total)
	{
		Invoice newInvoice = new Invoice(userCart,total);
		invoices.add(newInvoice);
		userCart.emptyCart();
	}
	
	
}
