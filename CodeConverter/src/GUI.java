import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame implements ActionListener
{
	public JFrame frame;
	public JPanel mainPanel, convertPanel;
	public JButton convertBtn;
	public JComboBox dropDownListLeft, dropDownListRight;
	public JLabel inputLbl, outputLbl, leftDropDownLbl, rightDropDownLbl;
	public JTextArea inputText, outputText;
	
	public GUI()
	{
		//Initializations
		String[] codeList = {"Java", "C++"};
		
		//Gridlayout
		GridLayout convertGL = new GridLayout(2,2);
		convertGL.setHgap(10);
		convertGL.setVgap(10);
		
		GridLayout mainGL = new GridLayout(2,1);
		mainGL.setHgap(10);
		mainGL.setVgap(10);
		
		//Panels and Frame
		frame = new JFrame("Code Converter");
		mainPanel = new JPanel(mainGL);
		convertPanel = new JPanel(convertGL);
		
		frame.add(mainPanel);
		mainPanel.add(convertPanel);
		
		//Padding
		//convertPanel.setBorder(new EmptyBorder(10,10,10,10));
		mainPanel.setBorder(new EmptyBorder(25,25,25,25));
		
		//ComboBox
		dropDownListLeft = new JComboBox(codeList);
		dropDownListRight = new JComboBox(codeList);
		convertPanel.add(dropDownListLeft);
		convertPanel.add(dropDownListRight);
		
		//Button
		convertBtn = new JButton("Convert");
		convertBtn.setPreferredSize(new Dimension(50,40));
		convertBtn.addActionListener(this);
		mainPanel.add(convertBtn);
		
		//TxtAreas
		inputText = new JTextArea(5,10);
		inputText.setLineWrap(true);
		outputText = new JTextArea(5,10);
		outputText.setLineWrap(true);
		convertPanel.add(inputText);
		convertPanel.add(outputText);
		
		//Labels
		inputLbl = new JLabel("Input Code: ");
		outputLbl = new JLabel("Output Code: ");
		leftDropDownLbl = new JLabel("From: ");
		rightDropDownLbl = new JLabel("To: ");
		
		
		//Frame setup
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(500, 400);
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
