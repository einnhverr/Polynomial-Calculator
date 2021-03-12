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
import org.einnhverr.pt.polynomials.model.Monomial;
import org.einnhverr.pt.polynomials.model.Polynomial;
import org.einnhverr.pt.polynomials.model.PolynomialOperations;
import static org.junit.Assert.*;
import junit.framework.TestCase;
import org.junit.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.javatuples.Pair;

public class TestPolynomial extends TestCase{

    Polynomial emptyZero;
    Polynomial zero;

    Polynomial poly_p;
    Polynomial poly_q;
    Polynomial poly_negative;
    Polynomial poly_positive;
    Polynomial poly_one;
    Polynomial poly_x;
    Polynomial poly_y;

    Polynomial expected;
    Polynomial actual;

    PolynomialOperations op;

    Monomial current;
    List<Monomial> terms;

    @Before
    public void setUp() {
	// Operations initialization
	op = new PolynomialOperations();

	// empty zero
	terms = new ArrayList<>();
	emptyZero = new Polynomial(terms);

	// zero value terms
	terms = new ArrayList<>();
	current = new Monomial(0, 0);
	terms.add(current);
	current = new Monomial(0, 0);
	terms.add(current);
	zero = new Polynomial(terms);

	// P(x) = 5x^3 - x^2 + 6
	terms = new ArrayList<>();
	current = new Monomial(5, 3);
	terms.add(current);
	current = new Monomial(-1, 2);
	terms.add(current);
	current = new Monomial(6, 0);
	terms.add(current);
	poly_p = new Polynomial(terms);

	// Q(x) = x - 4
	terms = new ArrayList<>();
	current = new Monomial(1, 1);
	terms.add(current);
	current = new Monomial(-4, 0);
	terms.add(current);
	poly_q = new Polynomial(terms);

	// - x - 4
	terms = new ArrayList<>();
	current = new Monomial(-1, 1);
	terms.add(current);
	current = new Monomial(-4, 0);
	terms.add(current);
	poly_negative = new Polynomial(terms);

	// 5x^3 + x^2 + 6
	terms = new ArrayList<>();
	current = new Monomial(5, 3);
	terms.add(current);
	current = new Monomial(1, 2);
	terms.add(current);
	current = new Monomial(6, 0);
	terms.add(current);
	poly_positive = new Polynomial(terms);

	// 1
	terms = new ArrayList<>();
	current = new Monomial(1, 0);
	terms.add(current);
	poly_one = new Polynomial(terms);

	// X(x) = 4x^4 + 3x^3 + 2x + 1
	terms = new ArrayList<>();
	current = new Monomial(4, 4);
	terms.add(current);
	current = new Monomial(3, 3);
	terms.add(current);
	current = new Monomial(2, 1);
	terms.add(current);
	current = new Monomial(1, 0);
	terms.add(current);
	poly_x = new Polynomial(terms);

	// Y(x) = x^2 + x + 2
	terms = new ArrayList<>();
	current = new Monomial(1, 2);
	terms.add(current);
	current = new Monomial(1, 1);
	terms.add(current);
	current = new Monomial(2, 0);
	terms.add(current);
	poly_y = new Polynomial(terms);
    }

    @After
    public void tearDown() {
	emptyZero = null;
	zero = null;
	poly_p = null;
	poly_q = null;
	poly_negative = null;
	poly_positive = null;
	poly_one = null;
	expected = null;
	actual = null;
    }

    @Test
    public void testZero() {
	assertTrue(emptyZero.isZero());
	assertTrue(zero.isZero());
    }

    @Test
    public void testDegree() {
	// 5x^3 - x^2 + 6
	int expected = 3;
	int actual = poly_p.degree();
	assertEquals(expected, actual);
    }

    @Test
    public void testEquals() {
	// 5x^3 - x^2 + 6
	terms = new ArrayList<>();
	current = new Monomial(5, 3);
	terms.add(current);
	current = new Monomial(-1, 2);
	terms.add(current);
	current = new Monomial(6, 0);
	terms.add(current);
	Polynomial one = new Polynomial(terms);

	// 5x^3 - x^2 + 6
	terms = new ArrayList<>();
	current = new Monomial(5, 3);
	terms.add(current);
	current = new Monomial(-1, 2);
	terms.add(current);
	current = new Monomial(6, 0);
	terms.add(current);
	Polynomial two = new Polynomial(terms);
	assertEquals(one,two);

	// -X^2 + 5x^3 + 6
	terms = new ArrayList<>();
	current = new Monomial(-1, 2);
	terms.add(current);
	current = new Monomial(5, 3);
	terms.add(current);
	current = new Monomial(6, 0);
	terms.add(current);
	Collections.sort(terms);
	Collections.reverse(terms);
	Polynomial three = new Polynomial(terms);
	assertEquals(two,three);
    }

    @Test
    public void testToString() {
	// 3x^3 + 4x^3 - 2x^1 + 3x^1 + 6 - 7
	terms = new ArrayList<>();
	current = new Monomial(3, 3);
	terms.add(current);
	current = new Monomial(4, 3);
	terms.add(current);
	current = new Monomial(-2, 1);
	terms.add(current);
	current = new Monomial(3, 1);
	terms.add(current);
	current = new Monomial(6, 0);
	terms.add(current);
	current = new Monomial(-7, 0);
	terms.add(current);
	Polynomial poly = new Polynomial(terms);
	assertEquals("+7.00X^3+X^1-1.00X^0", poly.toString());
    }
}
