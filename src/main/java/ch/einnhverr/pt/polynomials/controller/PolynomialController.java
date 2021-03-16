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
package ch.einnhverr.pt.polynomials.controller;

import ch.einnhverr.pt.polynomials.model.Monomial;
import ch.einnhverr.pt.polynomials.model.Polynomial;
import ch.einnhverr.pt.polynomials.model.PolynomialOperations;
import ch.einnhverr.pt.polynomials.view.GUI;

import org.javatuples.Pair;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolynomialController {

    // view
    private GUI view;

    // model
    private PolynomialOperations op;
    private Polynomial poly1;
    private Polynomial poly2;
    private Polynomial result;

    private String sPoly1;
    private String sPoly2;

    private boolean switchSelection;

    public PolynomialController(GUI view) {
	this.view = view;

	// add listeners to the view
	view.addAddListener(new AddPolynomialListener());
	view.addSubtractListener(new SubtractPolynomialListener());
	view.addMultiplyListener(new MultiplyPolynomialListener());
	view.addDivideListener(new DividePolynomialListener());
	view.addDifferentiateListener(new DifferentiatePolynomialListener());
	view.addIntegrateListener(new IntegratePolynomialListener());
	view.addOperandSelectionListener(new OperandSelectionListener());

	// set initial operand order
	switchSelection = view.getInitialOperandOrder();

	// Initialize model
	op = new PolynomialOperations();
    }

    private boolean queryData() {
	Pattern pattern = Pattern.compile("([+-]?\\d*)[xX]\\^(\\d+)?|([+-]?\\d*)[xX]|([+-]?\\d+)");
	Matcher matcher;

	try {
	    sPoly1 = view.getFirstOperand();
	    if (sPoly1.length() == 0) {
		view.showError("Please enter 1st Polynomial!");
		return false;
	    }
	} catch (NullPointerException e) {
	    view.showError("Please enter 1st Polynomial!");
	    return false;
	}
	try {
	    sPoly2 = view.getSecondOperand();
	    if (sPoly2.length() == 0) {
		view.showError("Please enter 2nd Polynomial!");
		return false;
	    }
	} catch (NullPointerException e) {
	    view.showError("Please enter 2nd Polynomial!");
	    return false;
	}

	if (!pattern.matcher(sPoly1).find()) {
	    view.showError("Please check 1st Polynomial format!\n" +
			   "Expected format: aX^n+bX^n-1");
	    return false;
	} else {
	    poly1 = new Polynomial(sPoly1);
	}
	if (!pattern.matcher(sPoly2).find()) {
	    view.showError("Please check 2nd Polynomial format!\n" +
			   "Expected format: aX^n+bX^n-1");
	    return false;
	} else {
	    poly2 = new Polynomial(sPoly2);
	}
	return true;
    }

    class AddPolynomialListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    if( !queryData() ) {
		return;
	    }
	    if ( switchSelection ) {
		result = op.add(poly1, poly2);
	    } else {
		result = op.add(poly2, poly1);
	    }
	    view.setResult(result.toString());
	}
    }

    class SubtractPolynomialListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    if ( !queryData() ) {
		return;
	    }
	    if ( switchSelection ) {
		result = op.subtract(poly1, poly2);
	    } else {
		result = op.subtract(poly2, poly1);
	    }
	    view.setResult(result.toString());
	}
    }

    class MultiplyPolynomialListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    if ( !queryData() ) {
		return;
	    }
	    if ( switchSelection ) {
		result = op.multiply(poly1, poly2);
	    } else {
		result = op.multiply(poly2, poly1);
	    }
	    view.setResult(result.toString());
	}
    }

    class DividePolynomialListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    if ( !queryData() ) {
		return;
	    }
	    Pair<Polynomial,Polynomial> resultD;
	    if ( switchSelection ) {
		try {
		    resultD = op.divide(poly1, poly2);
		} catch (IllegalArgumentException ex) {
		    view.showError(ex.getMessage());
		    return;
		}
	    } else {
		try {
		    resultD = op.divide(poly2, poly1);
		} catch (IllegalArgumentException ex) {
		    view.showError(ex.getMessage());
		    return;
		}
	    }
	    view.setResult("Q: " + resultD.getValue0().toString() +
			   " R: " + resultD.getValue1().toString());
	}
    }

    class DifferentiatePolynomialListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    if ( !queryData() ) {
		return;
	    }
	    if ( switchSelection ) {
		result = op.differentiate(poly1);

	    } else {
		result = op.differentiate(poly2);
	    }
	    view.setResult(result.toString());
	}
    }

    class IntegratePolynomialListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    if ( !queryData() ) {
		return;
	    }
	    if ( switchSelection ) {
		result = op.integrate(poly1);
	    } else {
		result = op.integrate(poly2);
	    }
	    view.setResult(result.toString());
	}
    }

    class OperandSelectionListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    switchSelection = !switchSelection;
	}
    }
}
