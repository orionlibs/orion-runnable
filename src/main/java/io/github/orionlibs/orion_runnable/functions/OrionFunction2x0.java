package io.github.orionlibs.orion_runnable.functions;

@FunctionalInterface
public interface OrionFunction2x0<T1, T2> extends OrionFunction
{
    void run(T1 t1, T2 t2);
}