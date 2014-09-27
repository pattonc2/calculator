package calculator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CalculatorControllerTest {
	
	private CalculatorModel model;
	private CalculatorController controller;

	@Before
	public void setupTests() {
		model = new CalculatorModel();
		controller = new CalculatorController(model);
	}

	@Test
	public void shouldClearToZero() {
		
		controller.buttonPushed("C");
		assertEquals("0.",model.getDisplay());
	}
	
	 @Test
     public void shouldShowDigit() {
		
		controller.buttonPushed("8");
		assertEquals("8.",model.getDisplay());
	}
	 
	 @Test
	 public void shouldShowMultipleDigits(){
		 
		 controller.buttonPushed("2");
		 controller.buttonPushed("5");
		 controller.buttonPushed("7");
		 assertEquals("257.",model.getDisplay());
	 }
	 
	 @Test
	 public void shouldShowMultipleDigitsAfterClear(){
		 
		 controller.buttonPushed("5");
		 controller.buttonPushed("2");
		 controller.buttonPushed("C");
		 controller.buttonPushed("7");
		 controller.buttonPushed("8");
		 controller.buttonPushed("8");
		 assertEquals("788.",model.getDisplay());
	 }
	 
	 @Test
	 public void shouldAddTwoNumbers(){
		 
		 controller.buttonPushed("2");
		 controller.buttonPushed("+");
		 controller.buttonPushed("2");
		 controller.buttonPushed("=");
		 assertEquals("4.",model.getDisplay());
	 }  
	 
	 @Test
	 public void shouldSubtractTwoNumbers(){
		 
		 controller.buttonPushed("4");
		 controller.buttonPushed("0");
		 controller.buttonPushed("-");
		 controller.buttonPushed("2");
		 controller.buttonPushed("0");
		 controller.buttonPushed("=");
		 assertEquals("20.",model.getDisplay());
	 }  
	 
	 @Test
	 public void shouldShowOperationAmountsWithNoEquals(){
	 	controller.buttonPushed("20");
	 	controller.buttonPushed("+");
	 	controller.buttonPushed("10");
	 	controller.buttonPushed("*");
		controller.buttonPushed("5");
		controller.buttonPushed("+");
		assertEquals("5.",model.getDisplay());
	 }
	 
	@Test
	public void shouldShowOperationAmountsWithNoEquals2(){
		controller.buttonPushed("2");
		controller.buttonPushed("0");
		controller.buttonPushed("-");
		controller.buttonPushed("5");
		controller.buttonPushed("0");
		controller.buttonPushed("*");
		controller.buttonPushed("3");
		controller.buttonPushed("/");
		controller.buttonPushed("6");
		controller.buttonPushed("-");
		assertEquals("-15.",model.getDisplay());
		 }
	
	@Test
	public void showMinusNumbers(){
		controller.buttonPushed("2");
		controller.buttonPushed("0");
		controller.buttonPushed("-");
		controller.buttonPushed("4");
		controller.buttonPushed("0");
		controller.buttonPushed("-");
		assertEquals("-20.",model.getDisplay());
	}
	 }
	 

