package com.JQuirke;

public class CalcLogic {
    
   
    private Double currentTotal;  
    private Double percent;
    
    /** Constructor */
    
    public CalcLogic() {
        currentTotal = 0.0;
    }
    
    public String getTotalString() {
        return "" + currentTotal;
    }
    
    public void setTotal(String n) {
        currentTotal = convertToNumber(n);
    }
    
    public void add(String n) {
        currentTotal += convertToNumber(n);
    }
    
    public void subtract(String n) {
        currentTotal -= convertToNumber(n);
    }
    
    public void multiply(String n) {
        currentTotal *= convertToNumber(n);
    }
    
    public void divide(String n) {
        currentTotal /= convertToNumber(n);
    }
    
    /*
     * Percent method
     */
    public void percent(String n) {
    	
    	Double d = Double.parseDouble(n);
    	percent = d / 1000 * d;
    	currentTotal = convertToNumber(d);
    	System.out.println("here is percent " + d);
        
    }
    
    
    /*
     * Method to convert strings to doubles
     */
    private Double convertToNumber(String n) {
    	
    	Double d = Double.parseDouble(n);
    	return d;
     
    }
    
    
private Double convertToNumber(Double n) {
	
	currentTotal = n;
    	return currentTotal;
     
    }
}