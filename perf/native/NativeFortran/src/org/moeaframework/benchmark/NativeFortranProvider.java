package org.moeaframework.benchmark;

import org.moeaframework.core.FrameworkException;
import org.moeaframework.core.spi.RegisteredProblemProvider;

public class NativeFortranProvider extends RegisteredProblemProvider {

	public NativeFortranProvider() {
		super();
		
		register("NativeFortran", NativeFortran::new, null);
		register("NativeFortranDirectMapping", NativeFortranDirectMapping::new, null);
		registerDiagnosticToolProblems(getRegisteredProblems());
	}
	
}
