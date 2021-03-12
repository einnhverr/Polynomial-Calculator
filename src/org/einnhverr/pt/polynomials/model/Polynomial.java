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
package org.einnhverr.pt.polynomials.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.javatuples.Pair;

/**
 * The class Polynomial a class representing a polynomial.
 *
 * @author Erhard Muresan
 */
public class Polynomial implements PolyOps {

    private List<Monomial> terms;

    public Polynomial(List<Monomial> terms) {
	this.terms = terms;
    }

    public Pair<Polynomial, Polynomial> divide(Polynomial poly) throws IllegalArgumentException {
	this.collapse();
	poly.collapse();
	Polynomial dividend = this;
	Polynomial divisor = poly;
	Polynomial quotient;
	Polynomial remainder;
	Polynomial temp;
	Pair<Polynomial,Polynomial> result;

	if ( divisor.isZero() ) {
	    throw new IllegalArgumentException("Divisor polynomial should not be zero(0)!");
	}
	List<Monomial> zeroTerms = new ArrayList<>();
	zeroTerms.add(new Monomial(0,0));
	quotient = new Polynomial(zeroTerms);
	remainder = dividend;
	// while ( !remainder.isZero() && (remainder.degree() >= poly.degree()) ) {
	//     temp = remainder.singleTermDivide(divisor);
	//     quotient = quotient.add(temp);
	//     remainder = remainder.subtract(temp.multiply(divisor));
	// }
	quotient.collapse();
	remainder.collapse();
	result = Pair.with(quotient,remainder);
	return result;
    }

    public Polynomial differentiate() {
	List<Monomial> copyThis = new ArrayList<>(terms);
	List<Monomial> result = new ArrayList<>();

	for ( Monomial term : copyThis ) {
	    Monomial mon = term.differentiate();
	    result.add(mon);
	}
	Polynomial pResult = new Polynomial(result);
	pResult.collapse();
	return pResult;
    }

    public Polynomial integrate() {
	List<Monomial> copyThis = new ArrayList<>(terms);
	List<Monomial> result = new ArrayList<>();

	for ( Monomial term : copyThis ) {
	    Monomial mon = term.integrate();
	    result.add(mon);
	}
	Polynomial pResult = new Polynomial(result);
	pResult.collapse();
	return pResult;
    }

    public boolean isZero() {
	boolean zero = true;
	if ( terms.isEmpty() ) {
	    zero = true;
	} else {
	    for ( Monomial term : terms ) {
		if ( term.coefficient() != 0 ) {
		    zero = false;
		}
		if ( term.exponent() != 0 ) {
		    zero = false;
		}
	    }
	}
	return zero;
    }

    public int degree() {
	int max = terms.get(0).exponent();
	for (Monomial term : terms) {
	    if ( term.exponent() > max ) {
		max = term.exponent();
	    }
	}
	return max;
    }

    public String toString() {
	this.collapse();
	String output = new String("");
	for (Monomial term : terms) {
	    output = new String(output + term.toString());
	}
	return output;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj instanceof Polynomial) {
	    Polynomial another = (Polynomial) obj;
	    if ( this.terms.equals(another.terms) ) {
		return true;
	    }
	}
	return false;
    }

    private Monomial lead() {
	Monomial lead = terms.get(0);
	for (Monomial term : terms) {
	    if ( term.exponent() > lead.exponent() ) {
		lead = term;
	    }
	}
	return lead;
    }

    public List<Monomial> getPolynomialTerms()
    {
	return this.terms;
    }

    public void collapse() {
	List<Monomial> visited = new ArrayList<>();
	List<Monomial> zero = new ArrayList<>();
	List<Monomial> result = new ArrayList<>();

	terms.forEach(term -> {
		terms.forEach(term2 -> {
			if ( term != term2 ) {
			    if ( term.exponent() == term2.exponent()
				 && term.coefficient() != 0 && term2.coefficient() != 0) {
				result.add(new Monomial(term.coefficient() + term2.coefficient(),
								term.exponent()));
				term.zero();
				term2.zero();
			    }
			}
		    });
	    });

	terms.forEach(term -> {
		if ( term.coefficient() == 0 ) {
		    zero.add(term);
		}
	    });
	terms.removeAll(zero);
	terms.addAll(result);
	Collections.sort(terms);
	Collections.reverse(terms);
    }

    private Polynomial singleTermDivide(Polynomial poly) throws IllegalArgumentException {
	List<Monomial> result = new ArrayList<>();

	result.add(this.lead().divide(poly.lead()));
	return new Polynomial(result);
    }
}
