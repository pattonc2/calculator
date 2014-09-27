package calculator;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class CalculatorApplication {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}

		JFrame frame = new JFrame("Calculator");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	    CalculatorModel model = new CalculatorModel();
	    CalculatorController controller = new CalculatorController(model);
	    CalculatorView view = new CalculatorView(model, controller);
	    view.createUI(frame.getContentPane());
	    view.updateView();
	
	    frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}

}
