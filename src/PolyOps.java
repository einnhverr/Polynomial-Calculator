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
 * Interface for operations with polynomials.
 *
 * @author Erhard Muresan
 */
public interface PolyOps<T> {

    /**
     * This method adds a polynomial to the current one
     *
     * @param poly - which polynomial it adds with
     * @return the resulting polynomial
     */
    public T add(T poly);

    /**
     * This method subtracts the current polynomial
     *
     * @param poly - which polynomial it subtracts with
     * @return the resulting polynomial
     */
    public T subtract(T poly);

    /**
     * This method multiplies the current polynomial
     *
     * @param poly - which polynomial it multiplies with
     * @return	the resulting polynomial
     */
    public T multiply(T poly);

    /**
     * This method divides the current polynomial
     *
     * @param poly - which polynomial it divides to
     * @return a tuple with following representation (Quotient, Remainder)
     */
    public Pair<T,T> divide(T poly) throws IllegalArgumentException;

    /**
     * This method expands(to power) the current polynomial
     *
     * @param poly - which polynomial it expands to
     * @return the resulting polynomial
     */
    public T expand(T poly);
}
