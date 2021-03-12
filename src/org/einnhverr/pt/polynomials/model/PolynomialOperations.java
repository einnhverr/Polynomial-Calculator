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
import java.util.List;

public class PolynomialOperations {

    /**
     * This method adds two polynomials
     *
     * @param poly1 - first polynomial
     * @param poly2 - second polynomial
     * @return resulting new polynomial
     */
    public Polynomial add(Polynomial poly1, Polynomial poly2) {
	List<Monomial> poly1Copy = new ArrayList<>(poly1.getPolynomialTerms());
	List<Monomial> poly2Copy = new ArrayList<>(poly2.getPolynomialTerms());
	List<Monomial> poly1Visited = new ArrayList<>();
	List<Monomial> poly2Visited = new ArrayList<>();
	List<Monomial> result = new ArrayList<>();

	poly1Copy.forEach( first -> {
		poly2Copy.forEach( second -> {
			if ( first.exponent() == second.exponent() ) {
			    result.add(new Monomial(first.coefficient() + second.coefficient(),
						    first.exponent()));
			    poly1Visited.add(first);
			    poly2Visited.add(second);
			}
		    });
	    });
	poly1Copy.removeAll(poly1Visited);
	poly2Copy.removeAll(poly2Visited);
	result.addAll(poly1Copy);
	result.addAll(poly2Copy);
	Polynomial pResult = new Polynomial(result);
	pResult.collapse();
	return pResult;
    }

    /**
     * This method subtracts two polynomials
     *
     * @param poly1 - first polynomial
     * @param poly2 - second polynomial
     * @return resulting new polynomial
     */
    public Polynomial subtract(Polynomial poly1, Polynomial poly2) {
	List<Monomial> poly1Copy = new ArrayList<>(poly1.getPolynomialTerms());
	List<Monomial> poly2Copy = new ArrayList<>(poly2.getPolynomialTerms());
	List<Monomial> poly1Visited = new ArrayList<>();
	List<Monomial> poly2Visited = new ArrayList<>();
	List<Monomial> result = new ArrayList<>();

	poly1Copy.forEach( first -> {
		poly2Copy.forEach( second -> {
			if ( first.exponent() == second.exponent() ) {
			    result.add(new Monomial(first.coefficient() - second.coefficient(),
						    first.exponent()));
			    poly1Visited.add(first);
			    poly2Visited.add(second);
			}
		    });
	    });
	poly1Copy.removeAll(poly1Visited);
	poly2Copy.removeAll(poly2Visited);
	result.addAll(poly1Copy);

	poly2Copy.forEach( mon -> {
		result.add(new Monomial(-mon.coefficient(), mon.exponent()));
	    });
	Polynomial pResult = new Polynomial(result);
	pResult.collapse();
	return pResult;
    }

    /**
     * This method multiplies two polynomials
     *
     * @param poly1 - first polynomial
     * @param poly2 - second polynomial
     * @return resulting new polynomial
     */
    public Polynomial multiply(Polynomial poly1, Polynomial poly2) {
	List<Monomial> result = new ArrayList<>();

	poly1.getPolynomialTerms().forEach( first -> {
		poly2.getPolynomialTerms().forEach( second -> {
			result.add(new Monomial(first.coefficient() * second.coefficient(),
						first.exponent() + second.exponent()));
		    });
	    });
	Polynomial pResult = new Polynomial(result);
	pResult.collapse();
	return pResult;
    }
}
