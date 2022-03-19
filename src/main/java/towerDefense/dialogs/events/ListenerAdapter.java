package towerDefense.dialogs.events;

import towerDefense.dialogs.events.mouse.ClickEvent;
import towerDefense.dialogs.events.mouse.HoverEvent;
import towerDefense.util.ClassWalker;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/*
Inspired by/copied from https://github.com/DV8FromTheWorld/JDA/blob/8cbc2674138bde6d9797a4dfc9f52151b0b1a291/src/main/java/net/dv8tion/jda/api/hooks/ListenerAdapter.java
 */

public class ListenerAdapter implements EventListener {

    public void onGenericEvent(GenericEvent event) {}

    public void onClickEvent(ClickEvent event) {}
    public void onHoverEvent(HoverEvent event) {}

    private static final MethodHandles.Lookup lookup = MethodHandles.lookup();
    private static final ConcurrentMap<Class<?>, MethodHandle> methods = new ConcurrentHashMap<>();
    private static final Set<Class<?>> unresolved;
    static
    {
        unresolved = ConcurrentHashMap.newKeySet();
        Collections.addAll(unresolved,
                Object.class,
                Event.class,
                GenericEvent.class
        );
    }

    @Override
    public final void onEvent(GenericEvent event)
    {
        onGenericEvent(event);

        for (Class<?> clazz : ClassWalker.range(event.getClass(), GenericEvent.class))
        {
            if (unresolved.contains(clazz))
                continue;
            MethodHandle handle = methods.computeIfAbsent(clazz, ListenerAdapter::findMethod);
            if (handle == null)
            {
                unresolved.add(clazz);
                continue;
            }

            try
            {
                handle.invoke(this, event);
            }
            catch (Throwable throwable)
            {
                if (throwable instanceof RuntimeException)
                    throw (RuntimeException) throwable;
                if (throwable instanceof Error)
                    throw (Error) throwable;
                throw new IllegalStateException(throwable);
            }
        }
    }

    private static MethodHandle findMethod(Class<?> clazz)
    {
        String name = clazz.getSimpleName();
        MethodType type = MethodType.methodType(Void.TYPE, clazz);
        try
        {
            name = "on" + name;
            return lookup.findVirtual(ListenerAdapter.class, name, type);
        }
        catch (NoSuchMethodException | IllegalAccessException ignored) {}
        return null;
    }
}
