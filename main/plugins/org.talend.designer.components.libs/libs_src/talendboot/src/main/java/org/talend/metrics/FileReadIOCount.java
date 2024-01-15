package org.talend.metrics;

import java.util.concurrent.atomic.AtomicLong;

public class FileReadIOCount {
    private static AtomicLong count = new AtomicLong(0l);

    public static void add(int val) {
        count.addAndGet(val);
    }

    public static AtomicLong get() {
        return count;
    }
}
