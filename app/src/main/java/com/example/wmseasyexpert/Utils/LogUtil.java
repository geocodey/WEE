/*
 * Copyright 2019 SSI Schaefer PEEM GmbH. All rights reserved.
 */

package com.example.wmseasyexpert.Utils;

import java.io.PrintStream;


/**
 * <p>
 * TODO JavaDoc according to <a
 * href="http://alfresco:8080/share/page/site/it/document-details?nodeRef=workspace://SpacesStore/a6584a66-1737-4b75-a88e-c843eae3ad7f">WAMAS C
 * conventions</a>
 * </p>
 * <p>
 * LogUtil provides methods to TODO.
 * </p>
 * <p>
 * LogUtil is TODO (<b>not</b>) immutable and may TODO (<b>not</b>) be freely exchanged between threads. <br />
 * Calls to methods of LogUtil are TODO (<b>not</b>) thread safe.
 * </p>
 * <p>
 * Usage: TODO add some usage examples.
 * </p>
 */

public class LogUtil {

  /** TODO meaning and possible values */
  private static final String PARAM = "{}";
  /** TODO meaning and possible values */
  private static final String SPACES = "   ";
  private final String className;


  private LogUtil(final String className) {
    this.className = className;

  }

  public static LogUtil getLogger() {
    final String className = getCaller(LogUtil.class).getClassName();
    return new LogUtil(className);
  }

  private static StackTraceElement getCaller(final Class<?> callee) {
    if (callee == null) {
      throw new IllegalArgumentException("Argumnet 'callee' must not be null");
    }

    final String clazz = callee.getName();

    boolean found = false;
    // walk through the stack trace and find the first class name after the callee
    final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
    for (final StackTraceElement element2 : stackTrace) {
      if (found && !element2.getClassName().equals(clazz)) {
        return element2;
      }
      if (element2.getClassName().equals(clazz) && !(LogUtil.class.isAssignableFrom(element2.getClass()))) {
        found = true;
      }
    }

    throw new IllegalStateException("Can't find calling class in stacktrace");
  }

  public void log(final String text) {
    System.out.println(text + SPACES + calcLocation());
  }

  public void log(final String text, final Exception e) {
    System.out.println(text + SPACES + calcLocation());
    e.printStackTrace();
  }

  public void log(final Exception e) {
    System.out.println(e.getStackTrace() + " : " + calcLocation());
    e.printStackTrace();
  }

  public void log(final String textToFormat, final Object... argv) {
    final StringBuilder sb = new StringBuilder();
    int toReplaceIndex = 0;
    int lastIndex = 0;
    int i = 0;
    final int argvLength = argv.length;
    while ((toReplaceIndex = textToFormat.indexOf(PARAM, lastIndex)) != -1) {
      sb.append(textToFormat.substring(lastIndex, toReplaceIndex));
      if (argvLength > i) {
        sb.append(argv[i]);
      } else {
        sb.append(PARAM);
      }
      i++;
      lastIndex = toReplaceIndex + PARAM.length();
    }
    sb.append(textToFormat.substring(lastIndex));
    // PrintStream br = new PrintStream(new File("mda"));
    final PrintStream def = System.out;
    System.out.println(sb.toString() + SPACES + calcLocation());
  }


  private StackTraceElement calcLocation() {
    if (className == null) {
      return null;
    }
    // LOG4J2-1029 new Throwable().getStackTrace is faster than Thread.currentThread().getStackTrace().
    final StackTraceElement[] stackTrace = new Throwable().getStackTrace();
    for (final StackTraceElement element : stackTrace) {
      final String stackClassName = element.getClassName();
      if (stackClassName.equals(className)) {
        return element;
      }
    }
    return null;
  }


}
