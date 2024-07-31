package org.moeaframework.benchmark;

import org.moeaframework.algorithm.NSGAII;
import org.moeaframework.core.Problem;

public class Example {

	public static void main(String[] args) throws Exception {
		try (Problem problem = new NativeFortran()) {
			NSGAII algorithm = new NSGAII(problem);
			algorithm.run(10000);
			algorithm.getResult().display();
		}
	}

}