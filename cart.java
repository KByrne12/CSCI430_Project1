
import java.util.*;

public class cart {
	
	LinkedList<Product> shoppingCart = new LinkedList<Product>();
	
	public void adder(Product pr, int quantity)
	{
		shoppingCart.add(pr);
		quantity += pr.getQuantity;
		pr.setQuantity(quantity);
	}
	
	public void printer()
	{
		for(Product pr : shoppingCart)
		{	
			System.out.println(pr.toString());
		}	
		System.out.println("Total cost:" + shoppingCart.total());
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
	}
}
