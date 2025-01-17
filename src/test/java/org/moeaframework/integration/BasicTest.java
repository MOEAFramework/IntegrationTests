package org.moeaframework.integration;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.moeaframework.algorithm.Algorithm;
import org.moeaframework.algorithm.NSGAII;
import org.moeaframework.core.population.NondominatedPopulation;
import org.moeaframework.core.spi.AlgorithmFactory;
import org.moeaframework.problem.Problem;
import org.moeaframework.problem.DTLZ.DTLZ2;

public class BasicTest {
	
	@Test
	public void testDirect() {
		Problem problem = new DTLZ2(2);
		
		NSGAII algorithm = new NSGAII(problem);
		algorithm.run(10000);
		
		NondominatedPopulation result = algorithm.getResult();
		
		Assert.assertNotNull(result);
		Assert.assertTrue(result.size() > 0);
	}
	
	@Test
	public void testSPI() {
		Problem problem = new DTLZ2(2);

		Algorithm algorithm = AlgorithmFactory.getInstance().getAlgorithm("NSGAII", problem);
		algorithm.run(10000);
		
		NondominatedPopulation result = algorithm.getResult();
		
		Assert.assertNotNull(result);
		Assert.assertTrue(result.size() > 0);
	}
	
	@Test
	public void testReferenceSet() throws IOException {
		Assert.assertNotNull(NondominatedPopulation.load("pf/DTLZ2.2D.pf"));
	}

}
