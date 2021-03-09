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

/**
 * The class Polynomial is an abstract class containing abstract
 * methods for operations with polynomial.
 *
 * @author Erhard Muresan
 */
public abstract class Polynomial implements PolyOps<Polynomial> {


    /**
     * Verifies if the given polynomial has all the coefficients Zero
     *
     * @return true if all coefficients are zero, false otherwise
     */
    public abstract boolean isZero();

    /**
     * Computes the polynomial degree
     *
     * @return the degree of the polynomial
     */
    public abstract Integer degree();

    /**
     * The String representation of a Polynomial
     *
     * @return a polynomial in the form of: aX^n + bX^n-1... + y
     */
    public abstract String toString();
}
