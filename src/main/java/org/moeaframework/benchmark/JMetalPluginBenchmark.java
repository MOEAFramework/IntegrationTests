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

import org.moeaframework.core.Algorithm;
import org.moeaframework.core.spi.AlgorithmFactory;
import org.moeaframework.problem.DTLZ.DTLZ2;
import org.moeaframework.util.TypedProperties;

public class JMetalPluginBenchmark implements Benchmark {

	@Override
	public String getName() {
		return "JMetal (Plugin)";
	}

	@Override
	public void run(int NFE) throws IOException, InterruptedException {
		DTLZ2 problem = new DTLZ2(2);

		// this must be set to ensure JMetal runs for the correct number of evaluations!
		TypedProperties properties = new TypedProperties();
		properties.setInt("maxEvaluations", NFE);

		Algorithm algorithm = AlgorithmFactory.getInstance().getAlgorithm("NSGAII-JMetal", properties, problem);
		algorithm.run(NFE);
	}

}
