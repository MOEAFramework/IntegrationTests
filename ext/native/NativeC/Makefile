# Edit the following to configure the compiler and required libraries
CC = gcc
LIBS = -lm
CFLAGS = -O3

# Configure the platform-specific settings
ifeq ($(OS),Windows_NT)
    SEPARATOR := ;
else
    SEPARATOR := :
endif

CLASSPATH := ../../lib/*$(SEPARATOR)NativeC.jar$(SEPARATOR).
SYSARCH := $(shell java -classpath "$(CLASSPATH)" org.moeaframework.builder.JNAInfo --sysArch)
LIBNAME := $(shell java -classpath "$(CLASSPATH)" org.moeaframework.builder.JNAInfo --libName --problem NativeC)

# Compile the shared library
build:
	mkdir -p bin/
	mkdir -p bin/$(SYSARCH)
	$(CC) $(CFLAGS) -c -fPIC -o bin/NativeC.o NativeC.c $(LIBS)
	$(CC) -shared -o bin/$(SYSARCH)/$(LIBNAME) bin/NativeC.o
	cp -r META-INF bin
	javac -classpath "$(CLASSPATH)" -d bin src/org/moeaframework/benchmark/*.java
	jar -cf NativeC.jar -C bin META-INF/ -C bin org/moeaframework/benchmark -C bin $(SYSARCH)/
	java -classpath "$(CLASSPATH)" org.moeaframework.builder.JNAInfo --test --problem NativeC

clean:
	rm -rf bin/ NativeC.jar
	
run:
	java -classpath "$(CLASSPATH)" org.moeaframework.benchmark.Example
