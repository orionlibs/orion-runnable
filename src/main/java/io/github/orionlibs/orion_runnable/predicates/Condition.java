package io.github.orionlibs.orion_runnable.predicates;

import io.github.orionlibs.orion_assert.Assert;
import io.github.orionlibs.orion_object.CloningService;
import java.util.function.Predicate;

public class Condition<T> implements Cloneable
{
    private Predicate<T> predicate;


    public Condition(Predicate<T> predicate)
    {
        Assert.notNull(predicate, "The given predicate input cannot be null.");
        this.predicate = predicate;
    }


    public static <T> Condition<T> of(Predicate<T> predicate)
    {
        return new Condition<T>(predicate);
    }


    public void not()
    {
        predicate = predicate.negate();
    }


    public void or(Predicate<T> other)
    {
        predicate = predicate.or(other);
    }


    public void and(Predicate<T> other)
    {
        predicate = predicate.and(other);
    }


    public void nand(Predicate<T> other)
    {
        not();
        and(other);
    }


    public void nor(Predicate<T> other)
    {
        not();
        or(other);
    }


    public void xor(Predicate<T> other)
    {
        Predicate<T> notAandB = getPredicate().and(other).negate();
        Predicate<T> notNotAandNotB = getPredicate().negate().and(other.negate()).negate();
        predicate = notAandB.and(notNotAandNotB);
    }


    public void xnor(Predicate<T> other)
    {
        xor(other);
        not();
    }


    public boolean apply(T t)
    {
        return this.predicate.test(t);
    }


    @SuppressWarnings("unchecked")
    @Override
    public Condition<T> clone() throws CloneNotSupportedException
    {
        return (Condition<T>)CloningService.clone(this);
    }


    public Condition<T> getCopy()
    {
        try
        {
            return this.clone();
        }
        catch(CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        return null;
    }


    public Predicate<T> getPredicate()
    {
        return this.predicate;
    }
}