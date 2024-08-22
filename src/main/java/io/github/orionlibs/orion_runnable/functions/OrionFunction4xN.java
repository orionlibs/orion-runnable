package io.github.orionlibs.orion_runnable.functions;

@FunctionalInterface
public interface OrionFunction4xN<T1, T2, T3, T4, R> extends OrionFunction
{
    R[] run(T1 t1, T2 t2, T3 t3, T4 t4);
}