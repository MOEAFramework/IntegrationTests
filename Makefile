# Configure the platform-specific settings
ifeq ($(OS),Windows_NT)
    SEPARATOR := ;
else
    SEPARATOR := :
endif

CLASSPATH := lib/*$(SEPARATOR)native/NativeC/*$(SEPARATOR)native/NativeCPP/*$(SEPARATOR)native/NativeFortran/*$(SEPARATOR)bin

# Configure benchmark settings
SAMPLES ?= 10
NFE ?= 100000

build:
	make -C perf	
	mkdir -p bin
	javac -d bin -classpath "$(CLASSPATH)" src/org/moeaframework/performance/*.java
	
run:
	java -classpath "$(CLASSPATH)" org.moeaframework.performance.Benchmark $(SAMPLES) $(NFE)

clean:
	make -C perf clean
	rm -rf bin