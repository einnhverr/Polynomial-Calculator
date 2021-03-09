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
 * The class RealPoly contains methods for basic operations with polynomials.
 * It is assumed polynomials have one variable and and it's coefficients are
 * given by an Double array.
 *
 * @author Erhard Muresan
 */
public class RealPoly extends Polynomial {

    private Double[] polynomial;

    public RealPoly(Double[] a) {
	polynomial = a;

	for (int i = 0; i < polynomial.length; i++) {
	    if (polynomial[i] == null)
		    polynomial[i] = 0.0;
	}
    }
    /**
     * This method gets the values of the polynomial
     *
     * @return An array of Doubles, each index representing an polynomial term
     */
    public Double[] getValues() {
	return polynomial;
    }

    /**
     * This method sets the values of the polynomial
     *
     * @param values - the values of the polynomial
     */
    public void setValues(Double[] values) {
	polynomial = values;
    }

    @Override
    public Polynomial add(Polynomial poly) {
	RealPoly polyI = (RealPoly)poly;
	Double[] tempT;
	Double[] tempP;

	if (this.degree().compareTo(polyI.degree()) < 0) {
	    tempT = new Double[polyI.degree().intValue()+1];
	    tempP = new Double[polyI.degree().intValue()+1];
	} else {
	    tempT = new Double[this.degree().intValue()+1];
	    tempP = new Double[this.degree().intValue()+1];
	}

	tempT = this.paddWithZero(tempT);
	tempT = this.copyOver(this.polynomial, tempT);
	tempP = this.paddWithZero(tempP);
	tempP = this.copyOver(polyI.getValues(), tempP);

	for (int i = 0; i < tempT.length; i++) {
	    tempT[i] = tempT[i] + tempP[i];
	}
	return new RealPoly(tempT);
    }

    @Override
    public Polynomial subtract(Polynomial poly) {
	RealPoly polyI = (RealPoly)poly;
	Double[] tempT;
	Double[] tempP;

	if (this.degree().compareTo(polyI.degree()) < 0) {
	    tempT = new Double[polyI.degree().intValue()+1];
	    tempP = new Double[polyI.degree().intValue()+1];
	} else {
	    tempT = new Double[this.degree().intValue()+1];
	    tempP = new Double[this.degree().intValue()+1];
	}

	tempT = this.paddWithZero(tempT);
	tempT = this.copyOver(this.polynomial, tempT);
	tempP = this.paddWithZero(tempP);
	tempP = this.copyOver(polyI.getValues(), tempP);

	for (int i = 0; i < tempT.length; i++) {
	    tempT[i] = tempT[i] - tempP[i];
	}
	return new RealPoly(tempT);
    }

    @Override
    public Polynomial multiply(Polynomial poly) {
	RealPoly polyI = (RealPoly)poly;
	Double[] tempT = this.polynomial;
	Double[] tempP = polyI.getValues();
	Double[] tempR;

	if (this.degree().compareTo(polyI.degree()) < 0) {
	    tempR = new Double[polyI.degree().intValue()*2];
	} else {
	    tempR = new Double[this.degree().intValue()*2];
	}

	tempR = this.paddWithZero(tempR);

	for (int i = 0; i < tempT.length; i++) {
	    for (int j = 0; j < tempP.length; j++) {
		tempR[i+j] = tempR[i+j] + (tempT[i] * tempP[j]);
	    }
	}
	return new RealPoly(tempR);
    }

    @Override
    public Pair<Polynomial, Polynomial> divide(Polynomial poly)
	throws IllegalArgumentException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Polynomial expand(Polynomial poly) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public boolean isZero() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public Integer degree() {
	Double[] temp = this.getValues();
	for (int i = temp.length - 1; i >= 0; i--) {
	    if (temp[i] != 0.0) {
		return i;
	    }
	}
	return 0;
    }

    @Override
    public String toString() {
	Double[] temp = this.polynomial;
	String out = new String("");
	for (int i = temp.length - 1; i >= 0; i--) {
	    if (temp[i] == 0.0) {
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

    private Double[] paddWithZero(Double[] array) {
	for (int i = 0; i < array.length; i++) {
	    array[i] = 0.0;
	}
	return array;
    }

    private Double[] copyOver(Double[] source, Double[] destination) {
	for (int i = 0; i < source.length; i++) {
	    destination[i] = source[i];
	}
	return destination;
    }
}
