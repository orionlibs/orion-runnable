package io.github.orionlibs.orion_runnable.consumers;

@FunctionalInterface
public interface Consumer3<T1, T2, T3> extends OrionConsumer
{
    void run(T1 t1, T2 t2, T3 t3);
}