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
import static org.junit.Assert.*;

import junit.framework.TestCase;
import org.junit.*;

/**
 * The class TestIntegerPoly test all the PolyOps interface provided
 *  methods implemented by IntegerPoly class.
 *
 * @author Erhard Muresan
 */
public class TestIntegerPoly extends TestCase {

    Polynomial a;
    Polynomial b;

    @Before
    public void setUp() {
	a = construct("1,2,3,4,5,6,7,8,9");
	b = construct("9,8,7,6,5,4,3,2,1");
    }

    @Test
    public void testAddition() {
	Polynomial result = construct("10,10,10,10,10,10,10,10,10");
	Polynomial actual = a.add(b);
	assertEquals("failure - equal result", result.toString(),
		     actual.toString());
    }

    @Test
    public void testSubtraction() {
	Polynomial result = construct("-8,-6,-4,-2,0,2,4,6,8");
	Polynomial actual = a.subtract(b);
	assertEquals("failure", result.toString(), actual.toString());
    }

    @Test
    public void testMultiplication() {
	Polynomial result = construct("9,0,16,0,21,0,24,0,25,0,24,0,21,0,16,0,9");
	Polynomial actual = a.multiply(b);
	assertEquals("failure", result.toString(), actual.toString());
    }

    @After
    public void tearDown() {
	a = null;
	b = null;
    }

    /**
     * Takes a String coefficients and return a polynomial
     */
    private IntegerPoly construct(String input) {
	String[] sTemp;
	sTemp = input.split(",");
	Integer[] poly = new Integer[sTemp.length];
	for (int i = 0; i < sTemp.length; i++) {
	    poly[i] = new Integer(sTemp[i]);
	}
	IntegerPoly output = new IntegerPoly(poly);
	return output;
    }
}
