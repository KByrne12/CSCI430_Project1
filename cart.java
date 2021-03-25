import java.util.*;
import java.io.*;
public class cart {
	
	LinkedList<Product> shoppingCart = new LinkedList<>();
	
	public void adder(Product pr, int quantity)
	{
		shoppingCart.add(pr);
		quantity += pr.getQuantity();
		pr.setQuantity(quantity);
	}
	
	public void printer()
	{
		for(Product pr : shoppingCart)
		{	
			System.out.println(pr.toString());
		}	
		System.out.println("Total cost:" + total());
	}
	
	public int total()
	{
		int total = 0;
		for(Product pr : shoppingCart)
		{
			total += (pr.getQuantity() * pr.getPrice());
		}
		return total;
	}
	
	public void emptyCart()
	{
		shoppingCart.clear();
		System.out.println("The client's cart has been emptied.");
	}
	
	public void RemoveProduct(Product item)
	{
		shoppingCart.remove(item);
		System.out.println("Product: " + item + " has been removed.");
	}
	
	 public int IDcheck (int ID) {
	        for (int i=0; i < shoppingCart.size(); i++) {
	            Product temp = shoppingCart.get(i);
	            if (ID == temp.getId()) {
	                System.out.println("ID found");
	                return i;
	            }
	        }
	        System.out.println("ERROR: ID is not in database");
	        return -1;
	    }
	 
	public Product get_listed_obj (int position) {
		return shoppingCart.get(position);
	}
	public void set_listed_obj (int position, Product update) {
		shoppingCart.set(position, update);
	}
}