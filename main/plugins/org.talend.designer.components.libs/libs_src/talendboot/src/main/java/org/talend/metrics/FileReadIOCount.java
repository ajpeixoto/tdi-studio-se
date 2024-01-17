package org.talend.metrics;

import java.io.File;
import java.util.concurrent.atomic.AtomicLong;

public class FileReadIOCount {

    //we use this for this pattern:
    //create fileinputstream and read it at once
    //can't fix this pattern: create fileinputstream A, and create another fileinputstream B, and read A and read B, but is ok, as we only use this filter for some jvm inside usage, that should match the first pattern
    private static final ThreadLocal<Boolean> threadLocal = new ThreadLocal() {
        @Override
        protected Boolean initialValue() {
            return Boolean.TRUE;
        }
    };

    public static void start(File file, String path) {
        threadLocal.set(Boolean.TRUE);
        if (path != null) {
            if (path.equals("/dev/urandom")) {
                threadLocal.set(Boolean.FALSE);
            } else if (path.endsWith(".class") || path.endsWith("/conf/security/java.security") || path.endsWith("/lib/tzdb.dat") || path.endsWith("/log4j2.xml") || path.endsWith("/conf/logging.properties") || path.endsWith("/conf/net.properties") || path.endsWith("/templates/jobInfo_template.properties") || path.endsWith("/contexts/Default.properties")) {
                count.getAndAdd(0 - file.length());
            }
        }
    }

    private static AtomicLong count = new AtomicLong(0l);

    public static void add(int val) {
        if(val > 0) {
            if (threadLocal.get()) count.getAndAdd(val);
        }
    }

    public static void add(long val) {
        if(val > 0) {
            if (threadLocal.get()) count.getAndAdd(val);
        }
    }

    public static AtomicLong get() {
        return count;
    }
}
