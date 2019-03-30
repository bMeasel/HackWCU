import java.awt.Frame;
import javax.swing.*;

public class GUI extends Frame
{
	public JFrame frame;
	public JPanel mainPanel;
	public JButton convertBtn;
	public JComboBox dropDownListLeft, dropDownListRight;
	
	public GUI()
	{
		//Panels and Frame
		frame = new JFrame("Code Converter");
		mainPanel = new JPanel();
		frame.add(mainPanel);
		
		//Buttons
		convertBtn = new JButton("Convert");
		mainPanel.add(convertBtn);
		
		//ComboBox
		dropDownListLeft = new JComboBox();
		dropDownListRight = new JComboBox();
		mainPanel.add(dropDownListLeft);
		mainPanel.add(dropDownListRight);
		
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
}
