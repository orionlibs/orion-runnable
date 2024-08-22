package io.github.orionlibs.orion_runnable.functions;

@FunctionalInterface
public interface OrionFunction1x0<T1> extends OrionFunction
{
    void run(T1 t1);
}