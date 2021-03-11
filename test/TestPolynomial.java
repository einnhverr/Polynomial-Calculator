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

    Monomial current;
    List<Monomial> terms;

    @Before
    public void setUp() {

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
    public void testAddition_q_less_than_p() {

	// 5x^3 - x^2 + x + 2
	terms = new ArrayList<>();
	current = new Monomial(5, 3);
	terms.add(current);
	current = new Monomial(-1, 2);
	terms.add(current);
	current = new Monomial(1, 1);
	terms.add(current);
	current = new Monomial(2, 0);
	terms.add(current);
	expected = new Polynomial(terms);
	actual = poly_p.add(poly_q);
	assertEquals(expected, actual);
    }

    @Test
    public void testAddiditon_p_less_than_q() {

	// 5x^3 - x^2 + x + 2
	terms = new ArrayList<>();
	current = new Monomial(5, 3);
	terms.add(current);
	current = new Monomial(-1, 2);
	terms.add(current);
	current = new Monomial(1, 1);
	terms.add(current);
	current = new Monomial(2, 0);
	terms.add(current);
	expected = new Polynomial(terms);
	actual = poly_q.add(poly_p);
	assertEquals(expected, actual);
    }

    @Test
    public void testAddition_p_equals_q() {

	// 10X^3 - 2X^2 + 12
	terms = new ArrayList<>();
	current = new Monomial(10, 3);
	terms.add(current);
	current = new Monomial(-2, 2);
	terms.add(current);
	current = new Monomial(12, 0);
	terms.add(current);
	expected = new Polynomial(terms);
	actual = poly_p.add(poly_p);
	assertEquals(expected, actual);
    }

    @Test
    public void testAddition_p_positive_q_negative() {

	// 5x^3 + x^2 - x + 2
	terms = new ArrayList<>();
	current = new Monomial(5, 3);
	terms.add(current);
	current = new Monomial(1, 2);
	terms.add(current);
	current = new Monomial(-1, 1);
	terms.add(current);
	current = new Monomial(2, 0);
	terms.add(current);
	expected = new Polynomial(terms);
	actual = poly_positive.add(poly_negative);
	assertEquals(expected, actual);
    }

    @Test
    public void testAddition_p_negative_q_positive() {

	// 5x^3 + x^2 - x + 2
	terms = new ArrayList<>();
	current = new Monomial(5, 3);
	terms.add(current);
	current = new Monomial(1, 2);
	terms.add(current);
	current = new Monomial(-1, 1);
	terms.add(current);
	current = new Monomial(2, 0);
	terms.add(current);
	expected = new Polynomial(terms);
	actual = poly_negative.add(poly_positive);
	assertEquals(expected, actual);
    }

    @Test
    public void testAddition_p_zero_q_nonzero() {

	// x^1 - 4
	terms = new ArrayList<>();
	current = new Monomial(1, 1);
	terms.add(current);
	current = new Monomial(-4, 0);
	terms.add(current);
	expected = new Polynomial(terms);
	//actual = zero.add(poly_p);
	actual = emptyZero.add(poly_q);
	assertEquals(expected, actual);
    }

    @Test
    public void testAddition_p_nonzero_q_zero() {

	// 5x^3 - x^2 + 6
	terms = new ArrayList<>();
	current = new Monomial(5, 3);
	terms.add(current);
	current = new Monomial(-1, 2);
	terms.add(current);
	current = new Monomial(6, 0);
	terms.add(current);
	expected = new Polynomial(terms);
	actual = poly_p.add(emptyZero);
	assertEquals(expected, actual);
    }

    @Test
    public void testAddition_p_one_q_notone() {

	// x^1 - 3
	terms = new ArrayList<>();
	current = new Monomial(1, 1);
	terms.add(current);
	current = new Monomial(-3, 0);
	terms.add(current);
	expected = new Polynomial(terms);
	actual = poly_one.add(poly_q);
	assertEquals(expected, actual);
    }

    @Test
    public void testAddition_p_notone_q_one() {

	// 5x^3 - x^2 + 7
	terms = new ArrayList<>();
	current = new Monomial(5, 3);
	terms.add(current);
	current = new Monomial(-1, 2);
	terms.add(current);
	current = new Monomial(7, 0);
	terms.add(current);
	expected = new Polynomial(terms);
	actual = poly_p.add(poly_one);
	assertEquals(expected, actual);
    }

    @Test
    public void testSubtraction() {
	// 5x^3 - x^2 - x + 10
	terms = new ArrayList<>();
	current = new Monomial(5, 3);
	terms.add(current);
	current = new Monomial(-1, 2);
	terms.add(current);
	current = new Monomial(-1, 1);
	terms.add(current);
	current = new Monomial(10, 0);
	terms.add(current);
	expected = new Polynomial(terms);
	actual = poly_p.subtract(poly_q);
	assertEquals(expected, actual);
    }

    @Test
    public void testMultiplication() {
	// 5x^4 - 19x^3 + 4x^2 + 6x - 24
	terms = new ArrayList<>();
	current = new Monomial(5, 4);
	terms.add(current);
	current = new Monomial(-21, 3);
	terms.add(current);
	current = new Monomial(4, 2);
	terms.add(current);
	current = new Monomial(6, 1);
	terms.add(current);
	current = new Monomial(-24, 0);
	terms.add(current);
	expected = new Polynomial(terms);
	actual = poly_p.multiply(poly_q);
	assertEquals(expected, actual);
    }

    @Test
    public void testDivision() {
	// x^2 - 9x - 10
	terms = new ArrayList<>();
	current = new Monomial(1, 2);
	terms.add(current);
	current = new Monomial(-9, 1);
	terms.add(current);
	current = new Monomial(-10, 0);
	terms.add(current);
	Polynomial dividend = new Polynomial(terms);

	// x - 1
	terms = new ArrayList<>();
	current = new Monomial(1, 1);
	terms.add(current);
	current = new Monomial(1, 0);
	terms.add(current);
	Polynomial divisor = new Polynomial(terms);

	// x - 10
	terms = new ArrayList<>();
	current = new Monomial(1, 1);
	terms.add(current);
	current = new Monomial(-10, 0);
	terms.add(current);
	Polynomial quotient = new Polynomial(terms);

	// zero
	Polynomial remainder = zero;
	Pair<Polynomial,Polynomial> expected;
	expected = Pair.with(quotient,remainder);
	Pair<Polynomial,Polynomial> actual;
	actual = dividend.divide(divisor);
	assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void testDivision_odd() {

	// q = 4x^2 - x - 7
	terms = new ArrayList<>();
	current = new Monomial(4, 2);
	terms.add(current);
	current = new Monomial(-1, 1);
	terms.add(current);
	current = new Monomial(-7, 0);
	terms.add(current);
	Polynomial quotient = new Polynomial(terms);

	// r = 11x^1 + 15
	terms = new ArrayList<>();
	current = new Monomial(11, 1);
	terms.add(current);
	current = new Monomial(15, 0);
	terms.add(current);
	Polynomial remainder = new Polynomial(terms);

	Pair<Polynomial,Polynomial> expected;
	expected = Pair.with(quotient, remainder);
	Pair<Polynomial,Polynomial> actual;
	actual = poly_x.divide(poly_y);
	assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void testDifferentiate() {

	// 15x^2 - 2x^1
	terms = new ArrayList<>();
	current = new Monomial(15, 2);
	terms.add(current);
	current = new Monomial(-2, 1);
	terms.add(current);
	expected = new Polynomial(terms);
	actual = poly_p.differentiate();
	assertEquals(expected, actual);
    }

    @Test
    public void testIntegrate() {

	// 5/4x^4 - 1/3x^3 + 6x
	terms = new ArrayList<>();
	current = new Monomial(5.0/4.0, 4);
	terms.add(current);
	current = new Monomial(-1.0/3.0, 3);
	terms.add(current);
	current = new Monomial(6, 1);
	terms.add(current);
	expected = new Polynomial(terms);
	actual = poly_p.integrate();
	assertEquals(expected, actual);
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
