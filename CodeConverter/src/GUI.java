import java.awt.*;
import javax.swing.*;

public class GUI extends JFrame
{
	public JFrame frame;
	public JPanel mainPanel, dropDownPanel, leftPanel, rightPanel, combinedPanel;
	public JButton convertBtn;
	public JComboBox dropDownListLeft, dropDownListRight;
	public JLabel codeLbl, leftDropDownLbl, rightDropDownLbl;
	public JTextArea inputText, outputText;
	
	public GUI()
	{
		//Panels and Frame
		frame = new JFrame("Code Converter");
		mainPanel = new JPanel(new GridLayout(3,1));
		dropDownPanel = new JPanel();
		leftPanel = new JPanel(new GridLayout(2,1));
		rightPanel = new JPanel();
		combinedPanel = new JPanel();
		
		frame.add(mainPanel);
		
		combinedPanel.add(leftPanel);
		combinedPanel.add(rightPanel);
		mainPanel.add(combinedPanel);
		leftPanel.add(dropDownPanel);
		
		//ComboBox
		dropDownListLeft = new JComboBox();
		dropDownListRight = new JComboBox();
		dropDownPanel.add(dropDownListLeft);
		dropDownPanel.add(dropDownListRight);
		
		//Buttons
		convertBtn = new JButton("Convert");
		mainPanel.add(convertBtn);
		
		//TxtAreas
		inputText = new JTextArea(10,10);
		outputText = new JTextArea(10,10);
		leftPanel.add(inputText);
		rightPanel.add(outputText);
		
		//Frame setup
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
}
