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
package org.einnhverr.pt.polynomials.controller;

import org.einnhverr.pt.polynomials.model.Monomial;
import org.einnhverr.pt.polynomials.model.Polynomial;
import org.einnhverr.pt.polynomials.view.GUI;
import org.javatuples.Pair;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PolynomialController {

    // view
    private GUI view;
    // model
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
    }

    private boolean queryData() {
	boolean match = false;

	try {
	    sPoly1 = view.getFirstOperand();
	} catch (NullPointerException e) {
	    view.showError("Please enter 1st Polynomial!");
	    return false;
	}
	try {
	    sPoly2 = view.getSecondOperand();
	} catch (NullPointerException e) {
	    view.showError("Please enter 2nd Polynomial!");
	    return false;
	}

	if (sPoly1.matches("[0-9].[0-9]") || sPoly2.matches("[0-9].[0-9]")) {
	    view.showError("Only integer polynomials supported!");
	    return false;
	} else {
	    String[] sTemp;
	    sTemp = sPoly1.split(",");
	    Monomial current;
	    List<Monomial> terms = new ArrayList<>();
	    for (int i = 0; i < sTemp.length; i++) {
		try {
		    current = new Monomial(Integer.valueOf(sTemp[i]), i);
		    terms.add(current);
		} catch (NumberFormatException e) {
		    view.showError("Please check 1st Polynomial coefficients!");
		    return false;
		}
	    }
	    if (match = sPoly1.matches(",")) {
		view.showError("Please check 1st Polynomial format!");
	    }
	    poly1 = new Polynomial(terms);
	}
	if (sPoly2.matches("[0-9].[0-9]") || sPoly2.matches("[0-9].[0-9]")) {
	    view.showError("Only integer polynomials supported!");
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
		    view.showError("Please check 2nd Polynomial coefficients!");
		    return false;
		}
	    }
	    if (match = sPoly2.matches(",")) {
		view.showError("Please check 2nd Polynomial format!");
	    }
	    poly2 = new Polynomial(terms);
	}
	return true;
    }

    class AddPolynomialListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    if( !queryData() ) {
		return;
	    }
	    if ( switchSelection ) {
		result = poly1.add(poly2);

	    } else {
		result = poly2.add(poly1);
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
		result = poly1.subtract(poly2);
	    } else {
		result = poly2.subtract(poly1);
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
		result = poly1.multiply(poly2);
	    } else {
		result = poly2.multiply(poly1);
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
		    resultD = poly1.divide(poly2);
		} catch (IllegalArgumentException ex) {
		    view.showError(ex.getMessage());
		    return;
		}
	    } else {
		try {
		    resultD = poly2.divide(poly1);
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
		result = poly1.differentiate();

	    } else {
		result = poly2.differentiate();
	    }
	    view.setResult(result.toString());
	}
    }

    class IntegratePolynomialListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	}
    }

    class OperandSelectionListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    switchSelection = !switchSelection;
	}
    }
}
