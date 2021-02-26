

import java.util.*;
import java.io.*;

public class Product implements Serializable {
   private static final long serialVersionUID = 1L;
   private String name;
   private String id;
   private int quantity;
   private int price;
   private static final String PRODUCT_STRING ="P";
   private List waitlists = new LinkedList();
   public Product (String name, String id, int quantity, int price) {
      this.name     = name;
      this.quantity = quantity;
      this.id	    =PRODUCT_STRING + (ProductIDServer.instance()).getID(); 
	  this.price    = price;
   }

//   public void waitlistItem(Waitlist waitlist) {
//	waitlists.add(waitlist);
//   }
   
   public String getName(){
      return name;
   }
   public String getId() {
      return id;
   }
   public int getQuantity() {
      return quantity;
   }
   public int getPrice() {
	  return price;
	}
   public void setName(String newName){
      name = newName;
   }
   public void setQuantity(int newQuantity) {
      quantity = newQuantity;
   }
   public void setId(int newId) {
      id = newId;
   }
   public void setPrice(int newPrice) {
	  price = newPrice;
	}
//   public boolean equals(String id) {
//      return this.id.equals(id);
//   }
   public String toString() {
      String PString = "Product id " + id + " Product name " + name + " quantity " + quantity + " price " + price;
      return PString;
   }
}

