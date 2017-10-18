/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salestax2;
import java.util.Vector;
import java.util.Scanner;
import java.math.RoundingMode;
import java.text.DecimalFormat;
/**
 *
 * @author jasonjingco
 */
public class TaxMainProg
{
	public TaxMainProg()
	{
	}
 
	public static void main(String [] args) throws Exception
	{
		TaxMainProg taxMainProg = new TaxMainProg();
 
		// gets the purchase item
		Vector<PurchaseItem> basket = taxMainProg.getPurchase();
		//System.out.println(basket);
 
		// prints the reciept
		taxMainProg.printReciept(basket);
 
	}
	
	public void printReciept(Vector<PurchaseItem> basket) throws Exception
	{
		float totPrice = 0.0f;
		float totTax = 0.0f;

 
		for(int i=0; i<basket.size(); i++)
		{
			PurchaseItem purchaseItem = basket.get(i);
 
			totPrice += purchaseItem.price;                 
			totTax += purchaseItem.tax;
			System.out.println(purchaseItem);
		}
                
		// round tax to near $0.05
		float reminder = totTax % 0.05f;
		if( reminder != 0.0f )
		{
                        
			if( reminder > 0.05f ) // rounding it up                    
				totTax -= reminder;
			else // rounding it up
				totTax += (0.05f - reminder);
		}
                
                DecimalFormat decim = new DecimalFormat("0.00");
                double newTotTax = totTax;
                double newTotal = totPrice+totTax;
                Double newTotTax2 = Double.parseDouble(decim.format(newTotTax));
                Double newTotal2 = Double.parseDouble(decim.format(newTotal));
		System.out.println("Sales Taxes: $"+newTotTax2);                
		System.out.println("Total: $"+(newTotal2));
	}
 
	public Vector<PurchaseItem> getPurchase() throws Exception
	{
		// keyboard input feedback
		Scanner keyboard = new Scanner(System.in);
		String userInput = null;
 
		Vector<PurchaseItem> basket = new Vector<PurchaseItem>();
 
		int no = 1;
		while(true)
		{
			PurchaseItem purchaseItem = new PurchaseItem();
 
			System.out.print("Item"+no+" Qty: ");
			userInput = keyboard.nextLine();
			purchaseItem.qty = Integer.parseInt(userInput);
 
			System.out.print("Item"+no+" Name: ");
			userInput = keyboard.nextLine();
			purchaseItem.name = userInput;
 
			System.out.print("Item"+no+" Price: $");
			userInput = keyboard.nextLine();
			purchaseItem.price = Float.parseFloat(userInput);
 
			System.out.print("Item"+no+" Imported[y/n]: ");
			userInput = keyboard.nextLine();
			if(userInput.toLowerCase().equals("y"))
				purchaseItem.imported = true;
 
			System.out.print("Item"+no+" Exempted[y/n]: ");
			userInput = keyboard.nextLine();
			if(userInput.toLowerCase().equals("y"))
				purchaseItem.exempted = true;
 
			// computes the tax
			purchaseItem.computeTax();
			
			// adding to the basket
			basket.add(purchaseItem);
			no++;
			
			System.out.print("More Item[y/n]: ");
			userInput = keyboard.nextLine();
			if(userInput.toLowerCase().equals("n"))
				break;
		}
		return basket;
	}
}