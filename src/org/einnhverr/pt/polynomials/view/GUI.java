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
package org.einnhverr.pt.polynomials.view;

import org.einnhverr.pt.polynomials.model.Monomial;
import org.einnhverr.pt.polynomials.model.Polynomial;

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
public class GUI extends JFrame {

    // panels
    private JPanel poly1Panel;
    private JPanel poly2Panel;
    private JPanel resultPanel;
    private JPanel opPanel;
    private JPanel buttonsPanel;
    private JPanel mainPanel;

    // labels
    private JLabel lPoly1;
    private JLabel lPoly2;
    private JLabel lResult;

    // text fields
    private JTextField tPoly1;
    private JTextField tPoly2;
    private JTextField tResult;

    // buttons
    private JButton add;
    private JButton subtract;
    private JButton multiply;
    private JButton divide;
    private JButton differentiate;
    private JRadioButton firstOpSecond;
    private JRadioButton secondOpFirst;
    private ButtonGroup op;

    // select operand order
    private boolean initialOperandOrder;

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
	subtract = new JButton("-");
	multiply = new JButton("*");
	divide = new JButton("/");
	differentiate = new JButton("d");
	firstOpSecond = new JRadioButton("1[OP]2");
	secondOpFirst = new JRadioButton("2[OP]1");
	op = new ButtonGroup();
	op.add(firstOpSecond);
	op.add(secondOpFirst);
	firstOpSecond.setSelected(true);
	initialOperandOrder = true;
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
	buttonsPanel.add(differentiate);
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

    public String getFirstOperand() {
	return tPoly1.getText();
    }

    public String getSecondOperand() {
	return tPoly2.getText();
    }

    public boolean getInitialOperandOrder() {
	return initialOperandOrder;
    }

    public void setResult(String result) {
	tResult.setText(result);
    }

    public void showError(String message) {
	JOptionPane.showMessageDialog(this, message);
    }

    public void addAddListener(ActionListener listener) {
	add.addActionListener(listener);
    }

    public void addSubtractListener(ActionListener listener) {
	subtract.addActionListener(listener);
    }

    public void addMultiplyListener(ActionListener listener) {
	multiply.addActionListener(listener);
    }

    public void addDivideListener(ActionListener listener) {
	divide.addActionListener(listener);
    }

    public void addDifferentiateListener(ActionListener listener) {
	differentiate.addActionListener(listener);
    }

    public void addIntegrateListener(ActionListener listener) {
    }

    public void addOperandSelectionListener(ActionListener listener) {
	firstOpSecond.addActionListener(listener);
	secondOpFirst.addActionListener(listener);
    }
}
