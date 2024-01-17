package org.talend.metrics;

import java.io.File;
import java.util.concurrent.atomic.AtomicLong;

public class FileReadIOCount {

    public static void start(File file, String path) {
        if (path != null) {
            String[] notCountFile = {".class", "/conf/security/java.security", "/lib/security/cacerts", "/lib/tzdb.dat", "/log4j2.xml", "/conf/logging.properties", "/conf/net.properties", "/templates/jobInfo_template.properties", "/contexts/Default.properties"};
            boolean notCount = false;
            for (int i = 0; i < notCountFile.length; i++) {
                if (path.endsWith(notCountFile[i])) {
                    notCount = true;
                    break;
                }
            }
            if (notCount) {
                count.getAndAdd(0 - file.length());
            }
        }
    }

    private static AtomicLong count = new AtomicLong(0l);

    public static void add(String path, int val) {
        if (val > 0) {
            if(path !=null && !(path.endsWith("/dev/urandom") || path.endsWith("/dev/random"))) {
                count.getAndAdd(val);
            }
        }
    }

    public static void add(String path, long val) {
        if (val > 0) {
            if(path !=null && !(path.endsWith("/dev/urandom") || path.endsWith("/dev/random"))) {
                count.getAndAdd(val);
            }
        }
    }

    public static AtomicLong get() {
        return count;
    }
}
