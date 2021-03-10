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

/**
 * The class Monomial represents a polynomial with only one term.
 *
 * @author Erhard Muresan
 */
public class Monomial implements Comparable<Monomial> {

    private int coefficient;
    private int exponent;

    public Monomial(int coefficient, int exponent) {
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
    public int coefficient() {
	return coefficient;
    }

    public Monomial add(Monomial monomial) {
	coefficient = coefficient + monomial.coefficient;
	return this;
    }

    public Monomial subtract(Monomial monomial) {
	coefficient = coefficient - monomial.coefficient;
	return this;
    }

    public Monomial multiply(Monomial monomial) {
	return new Monomial(coefficient * monomial.coefficient,
			    exponent + monomial.exponent);
    }

    public Monomial divide(Monomial monomial) throws IllegalArgumentException {
	if ( monomial.exponent == 0 ) {
	    if ( monomial.coefficient == 0 ) {
		throw new IllegalArgumentException("Divisor should not be zero(0)");
	    }
	}
	int result = coefficient / monomial.coefficient;
	int remainder = coefficient % monomial.coefficient;
	if ( remainder != 0 ) {
	    throw new IllegalArgumentException("Dividend should be divisible by divisor");
	}
	return new Monomial(result, exponent - monomial.exponent);
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
	return exponent;
    }

    @Override
    public int compareTo(Monomial monomial) {
	int result = 0;
	if (exponent - monomial.exponent == 0) {
	    result = coefficient - monomial.coefficient;
	} else {
	    result = exponent - monomial.exponent;
	}
	return result;
    }

    @Override
    public String toString() {
	String output = "";
	if ( coefficient == 0 ) {
	    output = output + "";
	} else if ( coefficient == 1 ) {
	    output = output + "+";
	} else if ( coefficient > 0 ) {
	    output = output + "+" + coefficient;
	} else {
	    output = output + coefficient;
	}
	output = output + "X^" + exponent;

	return output;
    }
}
