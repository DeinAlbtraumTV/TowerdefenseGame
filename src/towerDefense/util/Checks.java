package towerDefense.util;

import java.util.Collection;

public class Checks {

    public static void noneNull(final Collection<?> argument, final String name) {
        notNull(argument, name);
        argument.forEach(iter -> notNull(iter, name));
    }

    public static void noneNull(final Object[] argument, final String name) {
        notNull(argument, name);
        for (Object iter : argument) {
            notNull(iter, name);
        }
    }

    public static void notNull(final Object argument, final String name) {
        if (argument == null)
            throw new IllegalArgumentException(name + "may not be null");
    }
}
