package com.future.utils;

import java.util.logging.Logger;

public class LogUtil {
    private static Logger log = Logger.getLogger("futureLog");

    public static void i(String msg) {
        log.info(msg);
    }

    public static void f(String msg) {
        log.fine(msg);
    }

    public static void main(String[] args) {
//        log.setLevel(Level.INFO);
        f("hello");
        i("world");
        log.info("zhoujie");
        log.warning("warning");
    }
}
