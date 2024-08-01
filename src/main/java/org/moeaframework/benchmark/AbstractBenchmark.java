/* Copyright 2009-2024 David Hadka
 *
 * This file is part of the MOEA Framework.
 *
 * The MOEA Framework is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * The MOEA Framework is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with the MOEA Framework.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.moeaframework.benchmark;

import java.io.IOException;
import java.util.function.Supplier;

import org.moeaframework.algorithm.NSGAII;
import org.moeaframework.core.Algorithm;
import org.moeaframework.core.Problem;

public class AbstractBenchmark implements Benchmark {
	
	private final String name;
	
	private final Supplier<Problem> problemSupplier;
	
	public AbstractBenchmark(String name, Supplier<Problem> problemSupplier) {
		super();
		this.name = name;
		this.problemSupplier = problemSupplier;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void run(int NFE) throws IOException, InterruptedException {
		try (Problem problem = problemSupplier.get()) {
			Algorithm algorithm = new NSGAII(problem);
			algorithm.run(NFE);
		}
	}

}
