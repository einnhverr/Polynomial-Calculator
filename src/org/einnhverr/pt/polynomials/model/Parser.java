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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class Parser is a helper class for polynomial parsing.
 *
 * @author Erhard Muresan
 */

public class Parser {

    public static Polynomial parsePolynomial(String stringPolynomial) {
	List<Monomial> terms;
	Pattern pattern = Pattern.compile("([+-]?\\d*)[xX]\\^(\\d+)?|([+-]?\\d*)[xX]|([+-]?\\d+)");
	Matcher matcher = pattern.matcher(stringPolynomial);
	terms = new ArrayList<>();
	while (matcher.find()) {
	    if (matcher.group(1) != null && matcher.group(2) != null) {
		if (matcher.group(1).equals("-")) {
		    terms.add(new Monomial(Double.valueOf("-1"), Integer.valueOf(matcher.group(2))));
		} else {
		    terms.add(new Monomial(Double.valueOf(matcher.group(1)), Integer.valueOf(matcher.group(2))));
		}
	    } else if (matcher.group(3) != null) {
		if (matcher.group(3).equals("-")) {
		    terms.add(new Monomial(Double.valueOf("-1"), Integer.valueOf(1)));
		} else {
		    terms.add(new Monomial(Double.valueOf(matcher.group(3)), Integer.valueOf(1)));
		}
	    } else if (matcher.group(4) != null) {
		terms.add(new Monomial(Double.valueOf(matcher.group(4)), Integer.valueOf(0)));
	    }
	}
	return new Polynomial(terms);
    }
}
