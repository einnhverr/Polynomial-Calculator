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

public class TestPolynomialOperations extends TestCase {

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
    public void teadDown() {
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
	actual = op.add(poly_p, poly_q);
	assertEquals(expected, actual);
    }

    @Test
    public void testAddition_p_less_than_q() {

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
	actual = op.add(poly_q, poly_p);
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
	actual = op.add(poly_p, poly_p);
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
	actual = op.add(poly_positive, poly_negative);
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
	actual = op.add(poly_negative, poly_positive);
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
	actual = op.add(emptyZero, poly_q);
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
	actual = op.add(poly_p, emptyZero);
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
	actual = op.add(poly_one, poly_q);
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
	actual = op.add(poly_p, poly_one);
	assertEquals(expected, actual);
    }

    @Test
    public void testSubtraction_q_less_than_p() {

	// -5x^3 + x^2 + x - 10
	terms = new ArrayList<>();
	current = new Monomial(-5, 3);
	terms.add(current);
	current = new Monomial(1, 2);
	terms.add(current);
	current = new Monomial(1, 1);
	terms.add(current);
	current = new Monomial(-10, 0);
	terms.add(current);
	expected = new Polynomial(terms);
	actual = op.subtract(poly_q, poly_p);
	assertEquals(expected, actual);
    }

    @Test
    public void testSubtraction_p_less_than_q() {

	//5x^3 - x^2 - x + 10
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
	actual = op.subtract(poly_p, poly_q);
	assertEquals(expected, actual);
    }

    @Test
    public void testSubtraction_p_equals_q() {

	// emptyZero
	expected = emptyZero;
	actual = op.subtract(poly_p, poly_p);
	assertEquals(expected, actual);
    }

    @Test
    public void testSubtraction_p_positive_q_negative() {

	// 5x^3 + x^2 + x + 10
	terms = new ArrayList<>();
	current = new Monomial(5, 3);
	terms.add(current);
	current = new Monomial(1, 2);
	terms.add(current);
	current = new Monomial(1, 1);
	terms.add(current);
	current = new Monomial(10, 0);
	terms.add(current);
	expected = new Polynomial(terms);
	actual = op.subtract(poly_positive, poly_negative);
	assertEquals(expected, actual);
    }

    @Test
    public void testSubtraction_p_zero_q_nonzero() {

	// -x + 4
	terms = new ArrayList<>();
	current = new Monomial(-1, 1);
	terms.add(current);
	current = new Monomial(4, 0);
	terms.add(current);
	expected = new Polynomial(terms);
	actual = op.subtract(emptyZero, poly_q);
	assertEquals(expected, actual);
    }

    @Test
    public void testSubtraction_p_nonzero_q_zero() {

	// 5x^3 - x^2 + 6
	terms = new ArrayList<>();
	current = new Monomial(5, 3);
	terms.add(current);
	current = new Monomial(-1, 2);
	terms.add(current);
	current = new Monomial(6, 0);
	terms.add(current);
	expected = new Polynomial(terms);
	actual = op.subtract(poly_p, emptyZero);
	assertEquals(expected, actual);
    }

    @Test
    public void testSubtraction_p_one_q_notone() {

	// -x + 5
	terms = new ArrayList<>();
	current = new Monomial(-1, 1);
	terms.add(current);
	current = new Monomial(5, 0);
	terms.add(current);
	expected = new Polynomial(terms);
	actual = op.subtract(poly_one, poly_q);
	assertEquals(expected, actual);
    }

    @Test
    public void testSubtraction_p_notone_q_one() {

	// 5x^3 - x^2 + 5
	terms = new ArrayList<>();
	current = new Monomial(5, 3);
	terms.add(current);
	current = new Monomial(-1, 2);
	terms.add(current);
	current = new Monomial(5, 0);
	terms.add(current);
	expected = new Polynomial(terms);
	actual = op.subtract(poly_p, poly_one);
	assertEquals(expected, actual);
    }

    @Test
    public void testMultiplication_q_less_than_p() {

	// 5x^4 - 21x^3 + 4x^2 +6x - 24
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
	actual = op.multiply(poly_p, poly_q);
	assertEquals(expected, actual);
    }

    @Test
    public void testMultiplication_p_less_than_q() {

	// 5x^4 - 21x^3 + 4x^2 +6x - 24
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
	actual = op.multiply(poly_q, poly_p);
	assertEquals(expected, actual);
    }

    @Test
    public void testMultiplication_p_equals_q() {

	// 25x^6 - 10x^5 + x^4 + 60x^3 - 12x^2 + 36
	terms = new ArrayList<>();
	current = new Monomial(25, 6);
	terms.add(current);
	current = new Monomial(-10, 5);
	terms.add(current);
	current = new Monomial(1, 4);
	terms.add(current);
	current = new Monomial(60, 3);
	terms.add(current);
	current = new Monomial(-12, 2);
	terms.add(current);
	current = new Monomial(36, 0);
	terms.add(current);
	expected = new Polynomial(terms);
	actual = op.multiply(poly_p, poly_p);
	assertEquals(expected, actual);
    }

    @Test
    public void testMultiplication_p_positive_q_negative() {

	// -5x^4 - 21x^3 - 4x^2 - 6x^1 - 24
	terms = new ArrayList<>();
	current = new Monomial(-5, 4);
	terms.add(current);
	current = new Monomial(-21, 3);
	terms.add(current);
	current = new Monomial(-4, 2);
	terms.add(current);
	current = new Monomial(-6, 1);
	terms.add(current);
	current = new Monomial(-24, 0);
	terms.add(current);
	expected = new Polynomial(terms);
	actual = op.multiply(poly_positive, poly_negative);
	assertEquals(expected, actual);
    }

    @Test
    public void testMultiplication_p_negative_q_positive() {

	// -5x^4 - 21x^3 - 4x^2 - 6x^1 - 24
	terms = new ArrayList<>();
	current = new Monomial(-5, 4);
	terms.add(current);
	current = new Monomial(-21, 3);
	terms.add(current);
	current = new Monomial(-4, 2);
	terms.add(current);
	current = new Monomial(-6, 1);
	terms.add(current);
	current = new Monomial(-24, 0);
	terms.add(current);
	expected = new Polynomial(terms);
	actual = op.multiply(poly_negative, poly_positive);
	assertEquals(expected, actual);
    }

    @Test
    public void testMultiplication_p_zero_q_nonzero() {

	// 0
	actual = op.multiply(emptyZero, poly_q);
	assertTrue(actual.isZero());
    }

    @Test
    public void testMultiplication_p_nonzero_q_zero() {

	// 0
	actual = op.multiply(poly_p, zero);
	assertTrue(actual.isZero());
    }

    @Test
    public void testMultiplication_p_one_q_notone() {

	// x - 4
	terms = new ArrayList<>();
	current = new Monomial(1, 1);
	terms.add(current);
	current = new Monomial(-4, 0);
	terms.add(current);
	expected = new Polynomial(terms);
	actual = op.multiply(poly_one, poly_q);
	assertEquals(expected, actual);
    }

    @Test
    public void testMultiplication_p_notone_q_one() {

	// 5x^3 - x^2 + 6
	terms = new ArrayList<>();
	current = new Monomial(5, 3);
	terms.add(current);
	current = new Monomial(-1, 2);
	terms.add(current);
	current = new Monomial(6, 0);
	terms.add(current);
	expected = new Polynomial(terms);
	actual = op.multiply(poly_p, poly_one);
    }
}
