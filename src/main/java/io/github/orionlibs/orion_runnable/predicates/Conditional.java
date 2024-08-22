package io.github.orionlibs.orion_runnable.predicates;

import io.github.orionlibs.orion_assert.Assert;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Conditional<T> implements Cloneable
{
    /*public static void main(String[] args)
    {
        Conditional.<String>ifTrueThenDo(s -> !s.isEmpty(), "hello",
                        s -> System.out.println(s), "world")
                        .elseIfTrueThenDo(s -> s.startsWith("1"), "123",
                                        x -> System.out.println(Double.parseDouble((String)x)), "34.56778")
                        .elseIfTrueThenDo(s -> s.startsWith("2"), "234",
                                        x -> System.out.println(Double.parseDouble((String)x)), "1234.56789")
                        .elseDo(x -> System.out.println(Integer.parseInt((String)x)), "34");
    }*/
    private Predicate<T> predicate;
    private T valueForPredicate;
    private boolean predicateTrue;


    public Conditional(Predicate<T> predicate, T valueForPredicate, Consumer<Object> action, Object valueForOperation)
    {
        Assert.notNull(predicate, "The given predicate input cannot be null.");
        this.predicate = predicate;
        this.valueForPredicate = valueForPredicate;
        this.predicateTrue = apply(valueForPredicate);
        if(isPredicateTrue())
        {
            action.accept(valueForOperation);
        }
    }


    public static <T> Conditional<T> ifTrueThenDo(Predicate<T> predicate, T valueForPredicate, Consumer<Object> action, Object valueForOperation)
    {
        return new Conditional<T>(predicate, valueForPredicate, action, valueForOperation);
    }


    public static <T> Conditional<T> ifFalseThenDo(Predicate<T> predicate, T valueForPredicate, Consumer<Object> action, Object valueForOperation)
    {
        return new Conditional<T>(predicate.negate(), valueForPredicate, action, valueForOperation);
    }


    public Conditional<T> elseIfTrueThenDo(Predicate<T> predicate2, T valueForPredicate2, Consumer<Object> action, Object valueForOperation)
    {
        if(!isPredicateTrue())
        {
            this.predicate = predicate2;
            this.valueForPredicate = valueForPredicate2;
            this.predicateTrue = apply(valueForPredicate);
            if(predicate2.test(valueForPredicate))
            {
                action.accept(valueForOperation);
            }
        }
        return this;
    }


    public void elseDo(Consumer<Object> action, Object valueForOperation)
    {
        if(!isPredicateTrue())
        {
            action.accept(valueForOperation);
        }
    }


    public boolean apply(T t)
    {
        return this.predicate.test(t);
    }


    public Predicate<T> getPredicate()
    {
        return this.predicate;
    }


    public boolean isPredicateTrue()
    {
        return this.predicateTrue;
    }


    public T getValueForPredicate()
    {
        return this.valueForPredicate;
    }
}