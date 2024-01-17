package org.talend.metrics;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;

import java.io.File;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarFile;

public class TalendAgent {

    private static final String BOOTJAR = "bootjar";

    private static void javassist(String arguments, Instrumentation instrumentation) throws UnmodifiableClassException, IOException {
        String bootjar = null;
        if(arguments != null) {
            String[] kv = arguments.split("=");
            if(kv!=null && kv.length == 2) {
                bootjar = kv[1];
            }
        }
        
        if(bootjar == null) {
            throw new RuntimeException("Miss boot jar, please set like this : -javaagent:${agentjarpath}=bootjar=${bootjarpath}");
        }
        
        System.out.println("Boot jar:" + bootjar);
        
        instrumentation.appendToBootstrapClassLoaderSearch(new JarFile(new File(bootjar)));
        instrumentation.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                final ClassPool cp = ClassPool.getDefault();
                if ("java/io/FileInputStream".equals(className)) {
                    System.out.println("Begin convert java/io/FileInputStream: ");
                    try {
                        CtClass ctClass = cp.get("java.io.FileInputStream");

                        CtConstructor constructor = ctClass.getDeclaredConstructor(new CtClass[]{cp.get("java.io.File")});
                        constructor.insertAfter("org.talend.metrics.FileReadIOCount.start($1, path);");

                        CtMethod m1 = ctClass.getDeclaredMethod("read", new CtClass[]{cp.get("byte[]"), CtClass.intType, CtClass.intType});
                        m1.insertAfter( "org.talend.metrics.FileReadIOCount.add(path, $_);");

                        CtMethod m2 = ctClass.getDeclaredMethod("read");
                        m2.insertAfter( "org.talend.metrics.FileReadIOCount.add(path, $_);");

                        CtMethod m3 = ctClass.getDeclaredMethod("read", new CtClass[]{cp.get("byte[]")});
                        m3.insertAfter( "org.talend.metrics.FileReadIOCount.add(path, $_);");

                        byte[] byteCode = ctClass.toBytecode();
                        ctClass.detach();
                        return byteCode;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else if("java/net/Socket$SocketInputStream".equals(className)) {//jre 17 use this
                    System.out.println("Begin convert java/net/Socket$SocketInputStream: ");
                    try {
                        CtClass ctClass = cp.get("java.net.Socket$SocketInputStream");

                        CtMethod m1 = ctClass.getDeclaredMethod("read", new CtClass[]{cp.get("byte[]"), CtClass.intType, CtClass.intType});
                        m1.insertAfter( "if($_ > 0) { if($1.length == 1) { org.talend.metrics.NetworkReadIOCount.add( 1 ); } else { org.talend.metrics.NetworkReadIOCount.add( $_ ); } }");

                        byte[] byteCode = ctClass.toBytecode();
                        ctClass.detach();
                        return byteCode;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else if("java/net/SocketInputStream".equals(className)) {//jre 8/11 use this, (also jre 17 have this code, but not sure when use it)
                    System.out.println("Begin convert java/net/SocketInputStream: ");
                    try {
                        CtClass ctClass = cp.get("java.net.SocketInputStream");

                        CtMethod m1 = ctClass.getDeclaredMethod("read", new CtClass[]{cp.get("byte[]"), CtClass.intType, CtClass.intType});
                        m1.insertAfter( "if($_ > 0) { if($1.length == 1) { org.talend.metrics.NetworkReadIOCount.add( 1 ); } else { org.talend.metrics.NetworkReadIOCount.add( $_ ); } }");

                        byte[] byteCode = ctClass.toBytecode();
                        ctClass.detach();
                        return byteCode;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                return null;
            }
        }, true);

        //those rt.jar classes already be loaded before call transform method, so need retransform, this jre 17 test result, need to test more jre 8/11 and different jre implement
        List<String> listenedClass = Arrays.asList("java/io/FileInputStream");

        List<Class<?>> list = new ArrayList<>();
        for (Class clazz : instrumentation.getAllLoadedClasses()) {
            if (instrumentation.isModifiableClass(clazz)) {
                String cname = clazz.getName().replace('.', '/');
                if (listenedClass.contains(cname)) {
                    list.add(clazz);
                }
            }
        }

        for (Class clazz : list) {
            instrumentation.retransformClasses(clazz);
        }
    }

    public static void premain(String arguments, Instrumentation instrumentation) throws UnmodifiableClassException, IOException {
        System.out.println("Talend Agent is loaded!");
        System.out.println("Current java version : " + System.getProperty("java.version"));
        System.out.println("Current arguments : " + arguments);

        javassist(arguments, instrumentation);
    }

}