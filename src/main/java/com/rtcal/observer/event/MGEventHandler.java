package com.rtcal.observer.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MGEventHandler {

    byte priority() default 0;

    boolean ignoreCancelled() default false;

}
