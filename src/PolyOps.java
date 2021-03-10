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

import java.util.Collection;

/**
 * Interface for operations with polynomials.
 *
 * @author Erhard Muresan
 */
public interface PolyOps {

    /**
     * This method adds a polynomial to the current one
     *
     * @param poly - which polynomial it adds with
     * @return the resulting polynomial
     */
    public Polynomial add(Polynomial poly);

    /**
     * This method subtracts the current polynomial
     *
     * @param poly - which polynomial it subtracts with
     * @return the resulting polynomial
     */
    public Polynomial subtract(Polynomial poly);

    /**
     * This method multiplies the current polynomial
     *
     * @param poly - which polynomial it multiplies with
     * @return	the resulting polynomial
     */
    public Polynomial multiply(Polynomial poly);

    /**
     * This method divides the current polynomial.
     *
     * @param poly - which polynomial it divides to
     * @return a tuple with following representation (Quotient, Remainder)
     */
    public Pair<Polynomial,Polynomial> divide(Polynomial poly) throws IllegalArgumentException;

    /**
     * This method return true if the polynomial has no terms or each of its
     * terms have the coefficient and the exponent 0(zero).
     *
     * @return true if no terms or zero value(coefficient or exponent) terms
     */
    public boolean isZero();

    public int degree();
}
