# Edit the following to configure the compiler and required libraries
FORTRAN = gfortran
LIBS =
FFLAGS = -O3

# Configure the platform-specific settings
ifeq ($(OS),Windows_NT)
    SEPARATOR := ;
else
    SEPARATOR := :
endif

CLASSPATH := ../../lib/*$(SEPARATOR)NativeFortran.jar$(SEPARATOR).
SYSARCH := $(shell java -classpath "$(CLASSPATH)" org.moeaframework.builder.JNAInfo --sysArch)
LIBNAME := $(shell java -classpath "$(CLASSPATH)" org.moeaframework.builder.JNAInfo --libName --problem NativeFortran)

# Compile the shared library
build:
	mkdir -p bin/
	mkdir -p bin/$(SYSARCH)
	$(FORTRAN) $(FFLAGS) -c -fPIC -o bin/NativeFortran.o NativeFortran.f90 $(LIBS)
	$(FORTRAN) -shared -o bin/$(SYSARCH)/$(LIBNAME) bin/NativeFortran.o
	cp -r META-INF bin
	javac -classpath "$(CLASSPATH)" -d bin src/org/moeaframework/benchmark/*.java
	jar -cf NativeFortran.jar -C bin META-INF/ -C bin org/moeaframework/benchmark -C bin $(SYSARCH)/
	java -classpath "$(CLASSPATH)" org.moeaframework.builder.JNAInfo --test --problem NativeFortran

clean:
	rm -rf bin/ NativeFortran.jar
	
run:
	java -classpath "$(CLASSPATH)" org.moeaframework.benchmark.Example
