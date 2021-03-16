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
package ch.einnhverr.pt.polynomials.controller;

import ch.einnhverr.pt.polynomials.view.GUI;

public class PolynomialApplication {

    private PolynomialController controller;
    private GUI view;

    public PolynomialApplication() {
	setUp();
    }

    private static void init() {
	PolynomialApplication application = new PolynomialApplication();
    }

    private void setUp() {
	// view
	view = new GUI();

	// controller
	controller = new PolynomialController(view);

	view.setTitle("Polynomial Calculator");
	view.setVisible(true);
    }

    public void exit(int status) {
	System.exit(status);
    }

    public static void main(String[] args) {
	javax.swing.SwingUtilities.invokeLater(new Runnable() {
		public void run() {
		    init();
		}
	    });
    }
}
