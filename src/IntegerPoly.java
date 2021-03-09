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
import org.javatuples.Pair;

/**
 * The class IntegerPoly contains methods for basic operations with polynomials.
 * It is assumed polynomials have one variable and it's coefficients are
 * given by an Integer array.
 * 
 * @author Erhard Muresan
 */
public class IntegerPoly extends Polynomial {

    private Integer[] polynomial;

    public IntegerPoly(Integer[] a) {
	polynomial = a;

	for (int i = 0; i < polynomial.length; i++) {
	    if (polynomial[i] == null)
		    polynomial[i] = 0;
	}
    }

    /**
     * This method gets the values of the polynomial
     *
     * @return An array of Integers, each index representing an polynomial term
     */
    public Integer[] getValues () {
	return polynomial;
    }
	
    /**
     * This method sets the values of the polynomial
     *
     * @param values - the values of the polynomial
     */
    public void setValues(Integer[] values) {
	polynomial = values;
    }

    @Override
    public Polynomial add(Polynomial poly) {
	IntegerPoly polyI = (IntegerPoly)poly;
	Integer[] tempT;
	Integer[] tempP;

	if (this.degree().compareTo(polyI.degree()) < 0) {
	    tempT = new Integer[polyI.degree().intValue()+1];
	    tempP = new Integer[polyI.degree().intValue()+1];
	} else {
	    tempT = new Integer[this.degree().intValue()+1];
	    tempP = new Integer[this.degree().intValue()+1];
	}

	tempT = this.paddWithZero(tempT);
	tempT = this.copyOver(this.polynomial, tempT);
	tempP = this.paddWithZero(tempP);
	tempP = this.copyOver(polyI.getValues(), tempP);

	for (int i = 0; i < tempT.length; i++) {
	    tempT[i] = tempT[i] + tempP[i];
	}
	return new IntegerPoly(tempT);
    }

    @Override
    public Polynomial subtract(Polynomial poly) {
	IntegerPoly polyI = (IntegerPoly)poly;
	Integer[] tempT;
	Integer[] tempP;

	if (this.degree().compareTo(polyI.degree()) < 0) {
	    tempT = new Integer[polyI.degree().intValue()+1];
	    tempP = new Integer[polyI.degree().intValue()+1];
	} else {
	    tempT = new Integer[this.degree().intValue()+1];
	    tempP = new Integer[this.degree().intValue()+1];
	}

	tempT = this.paddWithZero(tempT);
	tempT = this.copyOver(this.polynomial, tempT);
	tempP = this.paddWithZero(tempP);
	tempP = this.copyOver(polyI.getValues(), tempP);

	for (int i = 0; i < tempT.length; i++) {
	    tempT[i] = tempT[i] - tempP[i];
	}
	return new IntegerPoly(tempT);
    }

    @Override
    public Polynomial multiply(Polynomial poly) {
	IntegerPoly polyI = (IntegerPoly)poly;
	Integer[] tempT = this.polynomial;
	Integer[] tempP = polyI.getValues();
	Integer[] tempR;

	if (this.degree().compareTo(polyI.degree()) < 0) {
	    tempR = new Integer[(polyI.degree().intValue()+1)*2];
	} else {
	    tempR = new Integer[(this.degree().intValue()+1)*2];
	}

	tempR = this.paddWithZero(tempR);

	for (int i = 0; i < tempT.length; i++) {
	    for (int j = 0; j < tempP.length; j++) {
		tempR[i+j] = tempR[i+j] + (tempT[i] * tempP[j]);
	    }
	}
	return new IntegerPoly(tempR);
    }

    @Override
    public Pair<Polynomial, Polynomial> divide(Polynomial poly)
	throws IllegalArgumentException {
	IntegerPoly dividend = this;
	IntegerPoly divisor = (IntegerPoly)poly;
	IntegerPoly quotient;
	IntegerPoly remainder;
	IntegerPoly t;
	Pair<Polynomial,Polynomial> temp;

	if (this.getValues().length == 0) {
	    throw new IllegalArgumentException("Divisor should not be 0(zero)");
	} else if (this.degree().compareTo(poly.degree()) < 0) {
	    throw new IllegalArgumentException("Divisor degree should be less"
					       + " than dividend's");
	}

	Integer[] zeros = new Integer[1];
	zeros[0] = new Integer(0);
	dividend = new IntegerPoly(polynomial);
	quotient = new IntegerPoly(zeros);
	remainder = dividend;
	temp = Pair.with((Polynomial)quotient, (Polynomial)remainder);

	while (!remainder.isZero()
	      && (remainder.degree().compareTo(poly.degree()) >= 0)) {

	    Integer remainderLead = new Integer(lead(remainder));
	    Integer dividendLead = new Integer(lead(dividend));

	    t = new IntegerPoly(new Integer[remainderLead.intValue()
					    / dividendLead.intValue()]);

	    IntegerPoly p0 = (IntegerPoly)temp.getValue0();
	    IntegerPoly p1 = (IntegerPoly)temp.getValue1();

	    p0.add(t);
	    p1 = (IntegerPoly) p1.subtract(t.multiply(p1));

	    temp = Pair.with((Polynomial)p0, (Polynomial)p1);
	}
	return temp;
    }

    @Override
    public Polynomial expand(Polynomial poly) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public boolean isZero() {
	boolean empty = true;
	for (int i = this.getValues().length - 1; i >= 0; i--) {
	    if (this.polynomial[i] != 0) {
		empty = false;
		return empty;
	    }
	}
	return empty;
    }

    @Override
    public Integer degree() {
	Integer[] temp = this.getValues();
	for (int i = temp.length - 1; i >= 0; i--) {
	    if (temp[i] != 0) {
		return i;
	    }
	}
	return 0;
    }

    @Override
    public String toString() {
	Integer[] temp = this.polynomial;
	String out = new String("");
	for (int i = temp.length - 1; i >= 0; i--) {
	    if (temp[i] == 0) {
		out = new String(out);
	    } else if (temp[i] < 0) {
		out = new String(out+temp[i]+"X^"+i);
	    } else {
		out = new String(out+"+"+temp[i]+"X^"+i);
	    }
	}
	if (out.charAt(0) == '+') {
	    out = new String(out.substring(1));
	}
	if (out.charAt(out.length()-1) == '0') {
	    out = new String(out.substring(0, out.length()-3));
	}
	return out;
    }

    private Integer[] paddWithZero(Integer[] array) {
	for (int i = 0; i < array.length; i++) {
	    array[i] = 0;
	}
	return array;
    }

    private Integer[] copyOver(Integer[] source, Integer[] destination) {
	for (int i = 0; i < source.length; i++) {
	    destination[i] = source[i];
	}
	return destination;
    }

    private Integer lead(IntegerPoly poly) {
	Integer[] temp = poly.getValues();
	for (int i = temp.length - 1; i >= 0; i--) {
	    if (temp[i] != 0) {
		return temp[i];
	    }
	}
	return 0;
    }
}
