package io.github.orionlibs.orion_runnable.functions;

@FunctionalInterface
public interface OrionFunctionNxN<T, R> extends OrionFunction
{
    @SuppressWarnings("unchecked")
    R[] run(T... args);
}