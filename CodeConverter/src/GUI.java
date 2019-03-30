import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener
{
	public JFrame frame;
	public JPanel mainPanel, dropDownPanel, leftPanel, rightPanel, combinedPanel;
	public JButton convertBtn;
	public JComboBox dropDownListLeft, dropDownListRight;
	public JLabel codeLbl, leftDropDownLbl, rightDropDownLbl;
	public JTextArea inputText, outputText;
	
	public GUI()
	{
		//Initializations
		String[] codeList = {"Java", "C++"};
		
		//Panels and Frame
		frame = new JFrame("Code Converter");
		mainPanel = new JPanel(new GridLayout(2,1));
		dropDownPanel = new JPanel();
		leftPanel = new JPanel(new GridLayout(2,1));
		rightPanel = new JPanel();
		combinedPanel = new JPanel();
		
		frame.add(mainPanel);
		
		leftPanel.add(dropDownPanel);
		combinedPanel.add(leftPanel);
		combinedPanel.add(rightPanel);
		mainPanel.add(combinedPanel);
		
		//ComboBox
		dropDownListLeft = new JComboBox(codeList);
		dropDownListRight = new JComboBox(codeList);
		dropDownPanel.add(dropDownListLeft);
		dropDownPanel.add(dropDownListRight);
		
		//Button
		convertBtn = new JButton("Convert");
		convertBtn.setBounds(100, 100, 100, 80);
		convertBtn.addActionListener(this);
		mainPanel.add(convertBtn);
		
		//TxtAreas
		inputText = new JTextArea(10,10);
		outputText = new JTextArea(10,10);
		leftPanel.add(inputText);
		rightPanel.add(outputText);
		
		//Frame setup
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(1000, 1000);
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		//If the convert button is pressed
		if(convertBtn == e.getSource())
		{
			CodeConverterDriver CCD = new CodeConverterDriver();
			outputText.setText(CCD.ConvertSelector(dropDownListLeft.getSelectedIndex(), dropDownListRight.getSelectedIndex(), inputText.getText()));
		}
	}
}
