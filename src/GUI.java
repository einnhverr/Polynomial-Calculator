/*
 * Copyright (c) 2021 Erhard Muresan.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.javatuples.Pair;

/**
 * Application's Graphical User Interface.
 *
 * @author Erhard Muresan
 */
public class GUI extends JFrame implements ActionListener {


    private static final long serialVersionUID = 1L;
    private JLabel lPoly1;
    private JLabel lPoly2;
    private JLabel lResult;
    private JTextField tPoly1;
    private JTextField tPoly2;
    private JTextField tResult;
    private JButton add;
    private JButton subtract;
    private JButton multiply;
    private JButton divide;
    private JButton toPower;
    private JPanel poly1Panel;
    private JPanel poly2Panel;
    private JPanel resultPanel;
    private JPanel opPanel;
    private JPanel buttonsPanel;
    private JPanel mainPanel;
    private JRadioButton firstOpSecond;
    private JRadioButton secondOpFirst;
    private ButtonGroup op;
    //data
    private Polynomial pPoly1;
    private Polynomial pPoly2;
    private Polynomial pPolyR;
    private Pair<Polynomial,Polynomial> divPolyResult;

    private String sPoly1;
    private String sPoly2;

    public GUI() {
	initGUI();
    }


    private void initGUI() {
	lPoly1 = new JLabel("Polynomial 1");
	lPoly2 = new JLabel("Polynomial 2");
	lResult = new JLabel("Result");
	tPoly1 = new JTextField(20);
	tPoly2 = new JTextField(20);
	tResult = new JTextField(30);
	add = new JButton("+");
	add.addActionListener(this);
	subtract = new JButton("-");
	subtract.addActionListener(this);
	multiply = new JButton("*");
	multiply.addActionListener(this);
	divide = new JButton("/");
	divide.addActionListener(this);
	toPower = new JButton("^");
	toPower.addActionListener(this);
	firstOpSecond = new JRadioButton("1[OP]2");
	secondOpFirst = new JRadioButton("2[OP]1");
	op = new ButtonGroup();
	op.add(firstOpSecond);
	op.add(secondOpFirst);
	firstOpSecond.addActionListener(this);
	firstOpSecond.setSelected(true);
	secondOpFirst.addActionListener(this);
	poly1Panel = new JPanel();
	poly1Panel.setLayout(new FlowLayout());
	poly1Panel.add(lPoly1);
	poly1Panel.add(tPoly1);
	poly2Panel = new JPanel();
	poly2Panel.setLayout(new FlowLayout());
	poly2Panel.add(lPoly2);
	poly2Panel.add(tPoly2);
	resultPanel = new JPanel();
	resultPanel.setLayout(new FlowLayout());
	resultPanel.add(lResult);
	resultPanel.add(tResult);
	opPanel = new JPanel();
	opPanel.setLayout(new BorderLayout());
	opPanel.add(firstOpSecond, BorderLayout.NORTH);
	opPanel.add(secondOpFirst, BorderLayout.CENTER);
	buttonsPanel = new JPanel();
	buttonsPanel.setLayout(new FlowLayout());
	buttonsPanel.add(add);
	buttonsPanel.add(subtract);
	buttonsPanel.add(multiply);
	buttonsPanel.add(divide);
	buttonsPanel.add(toPower);
	mainPanel = new JPanel();
	BoxLayout mainLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
	mainPanel.setLayout(mainLayout);
	mainPanel.add(poly1Panel);
	mainPanel.add(poly2Panel);
	mainPanel.add(opPanel);
	mainPanel.add(resultPanel);
	mainPanel.add(buttonsPanel);


	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.add(mainPanel);
	this.pack();
    }

    private boolean queryData() {
	boolean match = false;

	try {
	    sPoly1 = new String(tPoly1.getText());
	} catch (NullPointerException e) {
	    JOptionPane.showMessageDialog(this, "Please enter 1st Polynomial!",
					  "Error", JOptionPane.ERROR_MESSAGE);
	    return false;
	}
	try {
	    sPoly2 = new String(tPoly2.getText());
	} catch (NullPointerException e) {
	    JOptionPane.showMessageDialog(this, "Please enter 2nd Polynomial!",
					  "Error", JOptionPane.ERROR_MESSAGE);
	    return false;
	}

	if (sPoly1.matches("[0-9].[0-9]") || sPoly2.matches("[0-9].[0-9]")) {
	    JOptionPane.showMessageDialog(this, "Only integer polynomial supported!",
					  "Error", JOptionPane.ERROR_MESSAGE);
	    return false;
	} else {
	    String[] sTemp;
	    sTemp = sPoly1.split(",");
	    Monomial current;
	    List<Monomial> terms = new ArrayList<>();
	    for (int i = 0; i < sTemp.length; i++) {
		try {
		    current = new Monomial(new Integer(sTemp[i]), new Integer(i));
		    terms.add(current);
		} catch (NumberFormatException e) {
		    JOptionPane.showMessageDialog(this, "Please check 1st"
						  + " Polynomial coefficients!",
						  "Error",
						  JOptionPane.ERROR_MESSAGE);
		    return false;
		}
	    }
	    if (match = sPoly1.matches(",")) {
		JOptionPane.showMessageDialog(this, "Please check 1st Polynomial"
					      + " format!",
					      "Error", JOptionPane.ERROR_MESSAGE);
	    }
	    pPoly1 = new Polynomial(terms);
	}
	if (sPoly2.matches("[0-9].[0-9]") || sPoly2.matches("[0-9].[0-9]")) {
	    JOptionPane.showMessageDialog(this, "Only integer polynomials supported!",
					  "Error", JOptionPane.ERROR_MESSAGE);
	    return false;
	} else {
	    String[] sTemp;
	    sTemp = sPoly2.split(",");
	    Monomial current;
	    List<Monomial> terms = new ArrayList<>();
	    for (int i = 0; i < sTemp.length; i++) {
		try {
		    current = new Monomial(new Integer(sTemp[i]), new Integer(i));
		    terms.add(current);
		} catch (NumberFormatException e) {
		    JOptionPane.showMessageDialog(this, "Please check 2nd"
						  + "Polynomial coefficients!",
						  "Error",
						  JOptionPane.ERROR_MESSAGE);
		    return false;
		}
	    }
	    if (match = sPoly2.matches(",")) {
		JOptionPane.showMessageDialog(this, "Please check 2nd Polynomial"
					      + "format!", "Error",
					      JOptionPane.ERROR_MESSAGE);
	    }
	    pPoly2= new Polynomial(terms);
	}
	return true;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
	String command = event.getActionCommand();

	if (add.getActionCommand() == command) {
	    if (queryData() == false) {
		return;
	    } else {
		if (firstOpSecond.isSelected()) {
		    pPolyR = pPoly1.add(pPoly2);
		    tResult.setText(pPolyR.toString());
		} else if (secondOpFirst.isSelected()) {
		    pPolyR = pPoly2.add(pPoly1);
		    tResult.setText(pPolyR.toString());
		}
	    }
	} else if (subtract.getActionCommand() == command) {
	    if (queryData() == false) {
		return;
	    } else {
		if (firstOpSecond.isSelected()) {
		    pPolyR = pPoly1.subtract(pPoly2);
		    try {
			tResult.setText(pPolyR.toString());
		    } catch (StringIndexOutOfBoundsException e) {
			tResult.setText("0");
		    }
		} else if (secondOpFirst.isSelected()) {
		    pPolyR = pPoly2.subtract(pPoly1);
		    try {
			tResult.setText(pPolyR.toString());
		    } catch (StringIndexOutOfBoundsException e) {
			tResult.setText("0");
		    }
		}
	    }
	} else if (multiply.getActionCommand() == command) {
	    if (queryData() == false) {
		return;
	    } else {
		if (firstOpSecond.isSelected()) {
		    pPolyR = pPoly1.multiply(pPoly2);
		    try {
			tResult.setText(pPolyR.toString());
		    } catch (StringIndexOutOfBoundsException e) {
			tResult.setText("0");
		    }
		} else if (secondOpFirst.isSelected()) {
		    pPolyR = pPoly2.multiply(pPoly1);
		    try {
			tResult.setText(pPolyR.toString());
		    } catch (StringIndexOutOfBoundsException e) {
			tResult.setText("0");
		    }
		}
	    }
	} else if (divide.getActionCommand() == command) {
	    if (queryData() == false) {
		return;
	    } else {
		if (firstOpSecond.isSelected()) {
		    try {
			divPolyResult = pPoly1.divide(pPoly2);
			tResult.setText("Q: " + divPolyResult.getValue0().toString() +
					" R: "+divPolyResult.getValue1().toString());
		    } catch (StringIndexOutOfBoundsException e) {
			tResult.setText("0");
		    } catch (IllegalArgumentException e2) {
			e2.printStackTrace();
			JOptionPane.showMessageDialog(this, e2.getMessage(), "Error",
						      JOptionPane.ERROR_MESSAGE);
		    }
		} else if (secondOpFirst.isSelected()) {
		    try {
			divPolyResult = pPoly2.divide(pPoly1);
			tResult.setText("Q: " + divPolyResult.getValue0().toString() +
					" R: " + divPolyResult.getValue1().toString());
		    } catch (StringIndexOutOfBoundsException e) {
			tResult.setText("0");
		    } catch (IllegalArgumentException e2) {
			e2.printStackTrace();
			JOptionPane.showMessageDialog(this, e2.getMessage(), "Error",
						      JOptionPane.ERROR_MESSAGE);
		    }
		}
	    }
	} else if (toPower.getActionCommand() == command) {
	    JOptionPane.showMessageDialog(this, "Work In Progress...");
	    return;
	}
    }

    public static void main(String[] args) {
	GUI g = new GUI();
	g.setTitle("Polynomial Calculator");
	g.setVisible(true);
    }
}
