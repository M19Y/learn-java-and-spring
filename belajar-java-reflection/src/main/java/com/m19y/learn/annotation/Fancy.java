package com.m19y.learn.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME) // run at runtime
public @interface Fancy {

  String name(); // wajib di isi oleh pengguna annotation
  String[] tags() default {}; // tidak wajib di isi karena memiliki nilai default
}
