package org.moeaframework.integration;

import org.junit.Assert;
import org.junit.Test;
import org.moeaframework.algorithm.Algorithm;
import org.moeaframework.core.population.NondominatedPopulation;
import org.moeaframework.core.spi.AlgorithmFactory;
import org.moeaframework.problem.Problem;
import org.moeaframework.problem.CEC2009.UF1;

public class JMetalTest {
	
	@Test
	public void test() {
		Problem problem = new UF1();

		Algorithm algorithm = AlgorithmFactory.getInstance().getAlgorithm("AbYSS-JMetal", problem);
		algorithm.run(10000);

		NondominatedPopulation result = algorithm.getResult();
		
		Assert.assertNotNull(result);
		Assert.assertTrue(result.size() > 0);
	}

}
