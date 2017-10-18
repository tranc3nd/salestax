/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salestax2;

/**
 *
 * @author jasonjingco
 */
class PurchaseItem
{
	int qty = 0;
	String name = null;
	float price = 0.0f;
        
	boolean imported = false; // 5%
	boolean exempted = false; // 10%
 
	float tax = 0.0f;
        
	public String toString()
	{
		return qty+" "+name+" at $"+price+" ";
	}
	
	void computeTax()
	{
		float totTaxPercent = 0.0f;
 
		// check tax
		if(imported)
			totTaxPercent = 0.05f;
		if(!exempted)
			totTaxPercent += 0.1f;               
 
		// compute tax
		tax = totTaxPercent * price;
                
	}
}
