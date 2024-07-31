package org.moeaframework.integration;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.moeaframework.Executor;
import org.moeaframework.algorithm.NSGAII;
import org.moeaframework.core.NondominatedPopulation;
import org.moeaframework.core.Problem;
import org.moeaframework.problem.DTLZ.DTLZ2;

public class BasicTests {
	
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
		NondominatedPopulation result = new Executor()
				.withProblem("UF1")
				.withAlgorithm("NSGAII")
				.withMaxEvaluations(10000)
				.run();
		
		Assert.assertNotNull(result);
		Assert.assertTrue(result.size() > 0);
	}
	
	@Test
	public void testReferenceSet() throws IOException {
		Assert.assertNotNull(NondominatedPopulation.loadReferenceSet("pf/DTLZ2.2D.pf"));
	}

}
