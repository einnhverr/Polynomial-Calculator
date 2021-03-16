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
package ch.einnhverr.pt.polynomials.model;

import java.util.Objects;

/**
 * The class Monomial represents a polynomial with only one term.
 *
 * @author Erhard Muresan
 */
public class Monomial implements Comparable<Monomial> {

    private double coefficient;
    private int exponent;

    public Monomial(double coefficient, int exponent) {
	this.coefficient = coefficient;
	this.exponent = exponent;
    }

    /**
     * Outputs the monomial exponent
     *
     * @return the exponent of the monomial
     */
    public int exponent() {
	return exponent;
    }

    /**
     * Outputs the monomial coeffcient.
     *
     * @retrun the coefficient of the monomial
     */
    public double coefficient() {
	return coefficient;
    }

    public void zero() {
	coefficient = 0;
	exponent = 0;
    }

    @Override
    public boolean equals(Object obj) {
	if ( obj instanceof Monomial ) {
	    Monomial another = (Monomial) obj;
	    if ( coefficient == another.coefficient &&
		 exponent == another.exponent ) {
		return true;
	    }
	}

	return false;
    }

    @Override
    public int hashCode() {
	return Objects.hash(coefficient, exponent);
    }

    @Override
    public int compareTo(Monomial monomial) {
	int result = 0;
	double temp;
	if (exponent - monomial.exponent == 0) {
	    return 0;
	} else {
	    result = exponent - monomial.exponent;
	}
	return result;
    }

    @Override
    public String toString() {
	StringBuilder output = new StringBuilder();
	if ( coefficient == 0 ) {
	} else if ( coefficient == 1 ) {
	    output.append("+");
	} else if ( coefficient > 0 ) {
	    output.append("+");
	    output.append(String.format("%.2f", coefficient));
	} else {
	    output.append(String.format("%.2f", coefficient));
	}
	output.append("X^");
	output.append(exponent);

	return output.toString();
    }
}
