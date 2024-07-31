# Configure the platform-specific settings
ifeq ($(OS),Windows_NT)
    SEPARATOR := ;
else
    SEPARATOR := :
endif

CLASSPATH := perf/lib/*$(SEPARATOR)perf/native/NativeC/*$(SEPARATOR)perf/native/NativeCPP/*$(SEPARATOR)perf/native/NativeFortran/*$(SEPARATOR)bin

# Configure benchmark settings
SAMPLES ?= 10
NFE ?= 100000

build:
	mvn dependency:copy-dependencies
	mkdir -p perf/lib bin
	mv target/dependency/*.jar perf/lib
	make -C perf
	javac -d bin -classpath "$(CLASSPATH)" perf/src/org/moeaframework/performance/*.java
	
run:
	java -classpath "$(CLASSPATH)" org.moeaframework.performance.Benchmark $(SAMPLES) $(NFE)

clean:
	make -C perf clean
	rm -rf perf/lib bin