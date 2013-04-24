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
    
    public void sqRoot(String n) {
    	
        currentTotal =  Math.sqrt(convertToNumber(n));
    }
    
public void reciprocalFunction(String n) {
    	
        currentTotal =  1 / convertToNumber(n);
    }

public void percent(String n) {
	
	currentTotal = convertToNumber(n) / 100;
}

public void clearExisting(String n){
	
	new CalFrame().getScreenTop().setText("Clear");       
	new CalFrame().getScreen().setText("0");       
  
   
}

    private Double convertToNumber(String n) {
    	
    	Double d = Double.parseDouble(n);
    	return d;
     
    }
    
    
private Double convertToNumber(Double n) {
	
	currentTotal = n;
    	return currentTotal;
     
    }


}