package org.moeaframework.benchmark;

import org.moeaframework.core.FrameworkException;
import org.moeaframework.core.spi.RegisteredProblemProvider;

public class NativeCPPProvider extends RegisteredProblemProvider {

	public NativeCPPProvider() {
		super();
		
		register("NativeCPP", NativeCPP::new, null);
		register("NativeCPPDirectMapping", NativeCPPDirectMapping::new, null);
		registerDiagnosticToolProblems(getRegisteredProblems());
	}
	
}
