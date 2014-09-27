//Charlie Patton  Student Number = D12123539


package calculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
public class CalculatorController {

	private final CalculatorModel model;
	MathContext context = new MathContext(32, RoundingMode.HALF_UP);//Context for Big Integers
	
	private String currentValueConcat = ""; //initial string for current value concatenation
	private String operator = "+"; //current operator
	private BigDecimal currentAmount = new BigDecimal("0");//current amount
	private BigDecimal totalAmount = new BigDecimal("0");//total amount
	private String getGoing = "off";
	private String equalsFlag = "off"; //Indicate if equals has been pressed at least once
	public CalculatorController(CalculatorModel model) {
		this.model = model;
		model.setDisplay("0.");//Set Initial Display
	}
	
	
	public void buttonPushed(String buttonLabel) {
		
				
		if(buttonLabel.equals("C"))
		{
			clearDisplay();
		}
		
		if(buttonLabel.equals("sqrt")){
			squareRoot();
		}
		
		else if(buttonLabel.equals("CE")){
			
			clearLastEntry();
		}
		
		else if(buttonLabel.equals("1/x")){
			oneOverIt();
		}
		
		
		
		else if(buttonLabel.equals("+"))
			{   
				getGoing = "on";
				addition(currentValueConcat, operator, currentAmount, totalAmount, equalsFlag);
				
				
			} 
		
		else if(buttonLabel.equals("-"))
		{   getGoing = "on";
			subtraction(currentValueConcat, operator, currentAmount, totalAmount, equalsFlag);
		}
		
		else if(buttonLabel.equals("Backspace")){
			backSpace();
		}
			
				
		else if(buttonLabel.equals("*"))
		{   

			getGoing = "on";
			multiplication(currentValueConcat, operator, currentAmount, totalAmount, equalsFlag);
			
		}
		
		else if(isDigitPressed(buttonLabel) || isPeriodPressed(buttonLabel))
		{
			
			if(equalsFlag == "on"){
				   System.out.println("yessss");
			   
			    	
			    	currentValueConcat = "";
			    	//set Total Amount to zero
					if(totalAmount.doubleValue() >= 0){//positive amount
						totalAmount = (totalAmount.subtract(totalAmount));
					}
					
					else{//negative amount
						totalAmount = (totalAmount.add(totalAmount.abs()));
					}
					equalsFlag = "off";
					getGoing = "off";
			    } 
			
			
		    if(buttonLabel == "0" && currentValueConcat == ""){
		    	displayNumber("0");
		    	return;
		    }
		    
		    
		    if(buttonLabel == "." && currentValueConcat == ""){
		    	displayNumber("0");
		    	currentValueConcat = currentValueConcat + "0" + buttonLabel;
		    	System.out.println("Yipee1");
		    	return;
		    }
		    
		    if(buttonLabel == "."){
		    	
		    	currentValueConcat = currentValueConcat + buttonLabel;
		    	int first = currentValueConcat.indexOf(".");
		    	int last = currentValueConcat.lastIndexOf(".");
		    	if(first != last){
		    		currentValueConcat = currentValueConcat.substring(0, currentValueConcat.length()-1);
		    	}
		    	return;
		    }
		   
		    currentValueConcat = currentValueConcat + buttonLabel;
			displayNumber(currentValueConcat);
			
		   
		    }
	    	
		
		
		else if(buttonLabel.equals("/"))
		{   
			getGoing = "on";
			division(currentValueConcat, operator, currentAmount, totalAmount, equalsFlag);
			
		}
		
		else if(buttonLabel.equals("="))
		{
			equalize(currentValueConcat, operator, currentAmount, totalAmount, equalsFlag, getGoing);
		}
		
		else if(buttonLabel.equals("+/-"))
		{
	       plusMinus(currentValueConcat, equalsFlag);		
		}
		  }


	private void division(String currentValueConcat2, String operator2,
			BigDecimal currentAmount2, BigDecimal totalAmount2, String equalsFlag2) {
		
			currentValueConcat = currentValueConcat2;
			operator = operator2;
			currentAmount = currentAmount2;
			totalAmount = totalAmount2;
			equalsFlag = equalsFlag2;
		
			if(currentValueConcat == "" && equalsFlag == "off"){ //Pressing / First
			
				displayNumber("0");	
			}
			
			else if(equalsFlag == "on"){
				operator = "/";
				equalsFlag = "off";
				currentValueConcat = ""; //Set Back to Nothing
				return;
			}
			
			else if(operator == "/"){
				
				BigDecimal temp1 = new BigDecimal(currentValueConcat);//Temporary Big Decimal To Hold Current Value
				currentAmount = currentAmount.add(temp1);  //Set Current Amount to currentValueConcat
				System.out.println(currentAmount.toString());
				System.out.println(totalAmount.toString());
				totalAmount = totalAmount.divide(currentAmount, new MathContext(32,RoundingMode.HALF_UP));// (currentAmount, context);//Divide Current Amount from Total Amount
				//totalAmount = totalAmount.stripTrailingZeros(); //Strip Trailing Zeros
				displayNumber(totalAmount.toString());       //Display Total Amount after Division
				currentValueConcat = ""; //Set Back to Nothing
				
				//set Current Amount to zero
				if(currentAmount.doubleValue() >= 0){//positive amount
					currentAmount = (currentAmount.subtract(currentAmount));
				}
				
				else{//negative amount
					currentAmount = (currentAmount.add(currentAmount.abs()));
				}
				
			}
			
			else if(operator != "/"){
				
			
			 if(operator == "*"){ //Needs Multiplication First
				BigDecimal temp1 = new BigDecimal(currentValueConcat);//Temporary Big Decimal To Hold Current Value
				totalAmount = totalAmount.multiply(temp1);//Multiply Amounts
				currentValueConcat = "";
				operator = "/";
				displayNumber(totalAmount.toString());       //Display Total Amount after multiplication
			}
			
			else if(operator == "+"){ //Needs Addition First
				BigDecimal temp1 = new BigDecimal(currentValueConcat);//Temporary Big Decimal To Hold Current Value
				totalAmount = totalAmount.add(temp1);//Add Amounts
				currentValueConcat = "";
				operator = "/";
				displayNumber(totalAmount.toString());       //Display Total Amount after multiplication
			}
			
			else if(operator == "-"){ //Needs Subtraction First
				BigDecimal temp1 = new BigDecimal(currentValueConcat);//Temporary Big Decimal To Hold Current Value
				totalAmount = totalAmount.subtract(temp1);//Multiply Amounts
				currentValueConcat = "";
				operator = "/";
				displayNumber(totalAmount.toString());       //Display Total Amount after multiplication
			}
			
			}
			
			
	}


	private void multiplication(String currentValueConcat2, String operator2,
			BigDecimal currentAmount2, BigDecimal totalAmount2, String equalsFlag2) {
		
			currentValueConcat = currentValueConcat2;
			operator = operator2;
			currentAmount = currentAmount2;
			totalAmount = totalAmount2;
			equalsFlag = equalsFlag2;
			
			if(currentValueConcat == "" && equalsFlag == "off"){ //Pressing * First
				
				displayNumber("0");
			}
			
			else if(equalsFlag == "on"){
				operator = "*";
				equalsFlag = "off";
				currentValueConcat = ""; //Set Back to Nothing
				return;
			}
			
			else if(operator == "*")
			{
				
				BigDecimal temp = new BigDecimal(currentValueConcat);
				
					
				
				
				totalAmount = totalAmount.multiply(temp,context);
				
				currentValueConcat = "";
				displayNumber(totalAmount.toString()); //Display Total Amount after multiplication
			}
			
			else if(operator != "*"){
				
				
				if(operator == "+"){ //Needs Addition First
					BigDecimal temp1 = new BigDecimal(currentValueConcat);//Temporary Big Decimal To Hold Current Value
					totalAmount = totalAmount.add(temp1);//Add Amounts
					currentValueConcat = "";
					operator = "*";
					displayNumber(totalAmount.toString());       //Display Total Amount after multiplication
				}
				
				else if(operator == "-"){ //Needs Subtraction First
					BigDecimal temp1 = new BigDecimal(currentValueConcat);//Temporary Big Decimal To Hold Current Value
					totalAmount = totalAmount.subtract(temp1);//Multiply Amounts
					currentValueConcat = "";
					operator = "*";
					displayNumber(totalAmount.toString());       //Display Total Amount after multiplication
				}
				
				else if(operator == "/"){
					
					BigDecimal temp1 = new BigDecimal(currentValueConcat);//Temporary Big Decimal To Hold Current Value
					currentAmount = currentAmount.add(temp1);  //Set Current Amount to currentValueConcat
					totalAmount = totalAmount.divide(currentAmount, context);//Divide Current Amount from Total Amount
					totalAmount = totalAmount.stripTrailingZeros(); //Strip Trailing Zeros
					displayNumber(totalAmount.toString());       //Display Total Amount after Division
					currentValueConcat = ""; //Set Back to Nothing
					operator = "*";
					//set Current Amount to zero
					if(currentAmount.doubleValue() >= 0){//positive amount
						currentAmount = (currentAmount.subtract(currentAmount));
					}
					
					else{//negative amount
						currentAmount = (currentAmount.add(currentAmount.abs()));
					}
				} }
	}


	//Addition Method
	private void addition(String currentValueConcat2, String operator2,
			BigDecimal currentAmount2, BigDecimal totalAmount2, String equalsFlag2) {
	
		currentValueConcat = currentValueConcat2;
	    operator = operator2;
		currentAmount = currentAmount2;
		totalAmount = totalAmount2;
		equalsFlag = equalsFlag2;
		
		if(currentValueConcat == "" && equalsFlag == "off"){ //Pressing + First
			
			displayNumber("0");	
		}
		
		
		
		
		else if(equalsFlag == "on"){
			operator = "+";
			equalsFlag = "off";
			currentValueConcat = ""; //Set Back to Nothing
			return;
		}
		
		else if(operator == "+"){
			
			 
			BigDecimal temp1 = new BigDecimal(currentValueConcat);//Temporary Big Decimal To Hold Current Value
			currentAmount = currentAmount.add(temp1);  //Set Current Amount to currentValueConcat
			totalAmount = totalAmount.add(currentAmount);//Add Current Amount to Total Amount
			displayNumber(totalAmount.stripTrailingZeros().toString());       //Display Total Amount after addition
			currentValueConcat = ""; //Set Back to Nothing
			
			//set Current Amount to zero
			if(currentAmount.doubleValue() >= 0){//positive amount
				currentAmount = (currentAmount.subtract(currentAmount));
			}
			
			else{//negative amount
				currentAmount = (currentAmount.add(currentAmount.abs()));
			}
			
		}
		
		else if(operator != "+"){
			
		System.out.println("got here2");
		if(operator == "*"){ //Needs Multiplication First
			BigDecimal temp1 = new BigDecimal(currentValueConcat);//Temporary Big Decimal To Hold Current Value
			totalAmount = totalAmount.multiply(temp1);//Multiply Amounts
			currentValueConcat = "";
			operator = "+";
			displayNumber(totalAmount.toString());       //Display Total Amount after multiplication
			System.out.println("got here too");
		}
		
		else if(operator == "/"){ //Needs Division First
			BigDecimal temp1 = new BigDecimal(currentValueConcat);//Temporary Big Decimal To Hold Current Value
			totalAmount = totalAmount.divide(temp1, context);//Multiply Amounts
			totalAmount = totalAmount.stripTrailingZeros(); //Strip trailing Zeros
			currentValueConcat = "";
			operator = "+";
			displayNumber(totalAmount.toString());       //Display Total Amount after multiplication
		}
		
		
		
		else if(operator == "-") //Needs Subtraction First
		{
			BigDecimal temp1 = new BigDecimal(currentValueConcat);//Temporary Big Decimal To Hold Current Value
			currentAmount = currentAmount.add(temp1);  //Set Current Amount to currentValueConcat
			totalAmount = totalAmount.subtract(currentAmount);//Subtract Current Amount from Total Amount
			displayNumber(totalAmount.toString());       //Display Total Amount after addition
			currentValueConcat = ""; //Set Back to Nothing
			
			//set Current Amount to zero
			if(currentAmount.doubleValue() >= 0){//positive amount
				currentAmount = (currentAmount.subtract(currentAmount));
			}
			
			else{//negative amount
				currentAmount = (currentAmount.add(currentAmount.abs()));
			}
			
			operator = "+";//Reset operator
		}
		}
		
		
	}
	
	//Subtraction Method
	private void subtraction(String currentValueConcat2, String operator2,
			BigDecimal currentAmount2, BigDecimal totalAmount2, String equalsFlag2) {
		
		currentValueConcat = currentValueConcat2;
	    operator = operator2;
		currentAmount = currentAmount2;
		totalAmount = totalAmount2;
		equalsFlag = equalsFlag2;
		
		if(currentValueConcat == "" && equalsFlag == "off"){ //Pressing - First
			displayNumber("0");	
		}
		
		else if(equalsFlag == "on"){
			operator = "+";
			equalsFlag = "off";
			currentValueConcat = ""; //Set Back to Nothing
			return;
		}
		
		if(operator == "-")
		{
			
			BigDecimal temp2 = new BigDecimal(currentValueConcat);//Temporary Big Decimal To Hold Current Value
			currentAmount = currentAmount.add(temp2);//Set Current Amount to currentValueConcat
			totalAmount = totalAmount.subtract(currentAmount);//Add Current Amount to Total Amount
			displayNumber(totalAmount.toString());       //Display Total Amount after addition
			currentValueConcat = ""; //Set Back to Nothing
			operator = "+"; //Reset Operator
			
			//set Current Amount to zero
			if(currentAmount.doubleValue() >= 0){//positive amount
				currentAmount = (currentAmount.subtract(currentAmount));
			}
			
			else{//negative amount
				currentAmount = (currentAmount.add(currentAmount.abs()));
			}
		}
		
		else if(operator != "-"){
			
			
			if(operator == "*"){ //Needs Multiplication First
				BigDecimal temp1 = new BigDecimal(currentValueConcat);//Temporary Big Decimal To Hold Current Value
				totalAmount = totalAmount.multiply(temp1);//Multiply Amounts
				currentValueConcat = "";
				operator = "-";
				displayNumber(totalAmount.toString());       //Display Total Amount after multiplication
		}
		
		
		else if(operator == "+")//First Time Addition to Total Amount
		{
			BigDecimal temp2 = new BigDecimal(currentValueConcat);//Temporary Big Decimal To Hold Current Value
			currentAmount = currentAmount.add(temp2);//Set Current Amount to currentValueConcat
			totalAmount = totalAmount.add(currentAmount);//Add Current Amount to Total Amount
			displayNumber(totalAmount.toString());       //Display Total Amount after addition
			currentValueConcat = ""; //Set Back to Nothing
			operator = "-";
			
			//set Current Amount to zero
			if(currentAmount.doubleValue() >= 0){//positive amount
				currentAmount = (currentAmount.subtract(currentAmount));
			}
			
			else{//negative amount
				currentAmount = (currentAmount.add(currentAmount.abs()));
			}
			
		}
		
		else if(operator == "/"){ //Needs Division First
			BigDecimal temp1 = new BigDecimal(currentValueConcat);//Temporary Big Decimal To Hold Current Value
			totalAmount = totalAmount.divide(temp1, context);//Multiply Amounts
			//totalAmount = totalAmount.stripTrailingZeros(); //Strip trailing Zeros
			currentValueConcat = "";
			operator = "-";
			displayNumber(totalAmount.toString());       //Display Total Amount after multiplication
		}	
		}
	}
	
	//Equals Method
	public void equalize(String currentValueConcat2, String operator2,
			BigDecimal currentAmount2, BigDecimal totalAmount2, String equalsFlag2, String getGoing2){
		
		currentValueConcat = currentValueConcat2;
	    operator = operator2;
		currentAmount = currentAmount2;
		totalAmount = totalAmount2;
		equalsFlag = equalsFlag2;
		getGoing = getGoing2;
		
		if(getGoing == "off"){
			return;
		}
		
		try{
		
		equalsFlag = "on";
		
		
		if(operator == "+"){
			
			BigDecimal temp = new BigDecimal(currentValueConcat);//Temporary Big Decimal To Hold Current Value
			currentAmount = currentAmount.add(temp);//Set Current Amount to currentValueConcat
			totalAmount = totalAmount.add(currentAmount);//Add Current Amount to Total Amount
			displayNumber(totalAmount.toString());       //Display Total Amount
			
			//set Current Amount to zero
			if(currentAmount.doubleValue() >= 0){//positive amount
				currentAmount = (currentAmount.subtract(currentAmount));
			}
			
			else{//negative amount
				currentAmount = (currentAmount.add(currentAmount.abs()));
			}
			
		}
		
		else if(operator == "-"){
			
			BigDecimal temp = new BigDecimal(currentValueConcat);//Temporary Big Decimal To Hold Current Value
			currentAmount = currentAmount.add(temp);//Set Current Amount to currentValueConcat
			totalAmount = totalAmount.subtract(currentAmount);//Subtract Current Amount to Total Amount
			displayNumber(totalAmount.toString());       //Display Total Amount
		
			//set Current Amount to zero
			if(currentAmount.doubleValue() >= 0){//positive amount
				currentAmount = (currentAmount.subtract(currentAmount));
			}
			
			else{//negative amount
				currentAmount = (currentAmount.add(currentAmount.abs()));
			}
			
		}
	
		
		else if(operator == "*"){
			
			BigDecimal temp = new BigDecimal(currentValueConcat);//Temporary Big Decimal To Hold Current Value
			System.out.println(temp.toString());
			if(temp.toString() == "0"){
				displayNumber("0");
				System.out.println("hell yeah");
				//set Total Amount to zero
				if(totalAmount.doubleValue() >= 0){//positive amount
					totalAmount = (totalAmount.subtract(totalAmount));
				}
				
				else{//negative amount
					totalAmount = (totalAmount.add(totalAmount.abs()));
				}
			}
			else{
			currentAmount = currentAmount.add(temp);//Set Current Amount to currentValueConcat
			totalAmount = totalAmount.multiply(currentAmount,context);//Multiply Current Amount to Total Amount
			displayNumber(totalAmount.toString());       //Display Total Amount
			}
			
			//set Current Amount to zero
			if(currentAmount.doubleValue() >= 0){//positive amount
				currentAmount = (currentAmount.subtract(currentAmount));
			}
			
			else{//negative amount
				currentAmount = (currentAmount.add(currentAmount.abs()));
			}
			
		}
		
		else if(operator == "/"){
			
			BigDecimal temp = new BigDecimal(currentValueConcat);//Temporary Big Decimal To Hold Current Value
			currentAmount = currentAmount.add(temp);//Set Current Amount to currentValueConcat
			totalAmount = totalAmount.divide(currentAmount, context);//Divide Current Amount from Total Amount
			//totalAmount = totalAmount.stripTrailingZeros();           
			displayNumber(totalAmount.toString());       //Display Total Amount
		
			//set Current Amount to zero
			if(currentAmount.doubleValue() >= 0){//positive amount
				currentAmount = (currentAmount.subtract(currentAmount));
			}
			
			else{//negative amount
				currentAmount = (currentAmount.add(currentAmount.abs()));
			}
			
		}
		
		}
		
		catch (NumberFormatException e) { // catch divide-by-zero error 
			model.setDisplay("Cannot divide by zero.");
		}
	}
	

	//Check if Digit Pressed
	private boolean isDigitPressed(String buttonLabel){
		
		return 	buttonLabel.equals("0") || buttonLabel.equals("1") ||
				buttonLabel.equals("2") || buttonLabel.equals("3") ||
				buttonLabel.equals("4") || buttonLabel.equals("5") ||
				buttonLabel.equals("6") || buttonLabel.equals("7") ||
				buttonLabel.equals("8") || buttonLabel.equals("9");
	}
	
	//Check if Period Pressed(".")
	private boolean isPeriodPressed(String buttonLabel){
		
		return buttonLabel.equals(".");
	}
	
	private void clearLastEntry(){
		model.setDisplay("0.");
		currentValueConcat = "";
		
	}
	
	private void squareRoot(){
		String test = model.getDisplay();
		String test2 = test.substring(0,test.length()-1);
		double test3 = Double.parseDouble(test2);

		double test4 = Math.sqrt(test3);
		
		String temp1;
		temp1 = Double.toString(test4);
	
		model.setDisplay(temp1);
		
		
	}
	
	private void oneOverIt(){
		
		String test = model.getDisplay();
		String test2 = test.substring(0,test.length()-1);
		BigDecimal temp1 = new BigDecimal(test2);//Got Number
		
		BigDecimal temp2 = new BigDecimal("1");
		
		
		
		temp2 = temp2.divide(temp1,context);
		model.setDisplay(temp2.toString());
		
		//set Total Amount to zero
				if(totalAmount.doubleValue() >= 0){//positive amount
					totalAmount = (totalAmount.subtract(totalAmount));
				}
				
				else{//negative amount
					totalAmount = (totalAmount.add(totalAmount.abs()));
				}
				
		totalAmount = totalAmount.add(temp2);
		currentValueConcat = "";
		
		
	}
	
	private void backSpace(){
		String test = model.getDisplay();
		String test2 = test.substring(0,test.length()-1);
		String test3 = test2.substring(0,test.length()-1);
		
		displayNumber(test3);
		//currentValueConcat = test3;
	}
	
	private void clearDisplay(){
		
		model.setDisplay("0.");
		currentValueConcat = "";
		operator = "+";
		equalsFlag = "off";
		getGoing = "off";
		
		//set Current Amount to zero
		if(currentAmount.doubleValue() >= 0){//positive amount
			currentAmount = (currentAmount.subtract(currentAmount));
		}
		
		else{//negative amount
			currentAmount = (currentAmount.add(currentAmount.abs()));
		}
		
		//set Total Amount to zero
		if(totalAmount.doubleValue() >= 0){//positive amount
			totalAmount = (totalAmount.subtract(totalAmount));
		}
		
		else{//negative amount
			totalAmount = (totalAmount.add(totalAmount.abs()));
		}
		
		}
	
	private void displayNumber(String number){ //method that displays number with/without decimal correctly
		
		int i = number.indexOf(".");
		if(i == -1){
			model.setDisplay(number + ".");
		}
		
		else {
			model.setDisplay(number);
		}
		
	}
	
	// +/- Method Changes Sign Of Number
	private void plusMinus(String currentValueConcat2, String equalsFlag2){
		currentValueConcat = currentValueConcat2;
		equalsFlag = equalsFlag2;
		
		if(currentValueConcat == "" && equalsFlag == "off"){ //Pressing +/-  First
			displayNumber("0");	
		}
		
		else if(currentValueConcat == "0"){
			displayNumber("0");
		}
		
		String test = model.getDisplay();
		String test2 = test.substring(0,test.length()-1);
		
		
		BigDecimal temp1 = new BigDecimal(test2);
		BigDecimal temp2 = new BigDecimal("-1");
		temp1 = temp1.multiply(temp2);
		displayNumber(temp1.toString());
		
		
		if(totalAmount.toString() == temp1.toString()){
			totalAmount = totalAmount.multiply(temp2);
		}
		
		else{
			
			currentValueConcat = temp1.toString();
			totalAmount = totalAmount.multiply(temp2);
		}
		
		
		
		
	}
	
}


