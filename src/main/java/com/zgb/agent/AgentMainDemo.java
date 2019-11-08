package com.zgb.agent;

import com.zgb.ByteCodeDemo;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;

/**
 * @author xmly
 * @email guanbao.zhou@ximalaya.com
 * @Date 2019/11/4 9:39 上午
 * @Created By guanbao.zhou
 */
public class AgentMainDemo {
  public static void agentmain(String agentArgs, Instrumentation instrumentation) {
    System.out.println("agent main agentArgs: " + agentArgs);
    instrumentation.addTransformer(new DefinedTransformer(), true);
    try {
      instrumentation.retransformClasses(ByteCodeDemo.class);
    } catch (UnmodifiableClassException e) {
      e.printStackTrace();
    }
  }

  static class DefinedTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className,
                            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
      System.out.println("load class name: " + className);
      try {
        if (!className.equals("com/zgb/ByteCodeDemo")) {
          return null;
        }
        ClassPool cp = ClassPool.getDefault();
        CtClass ctClass = cp.get("com.zgb.ByteCodeDemo");
        CtMethod method = ctClass.getDeclaredMethod("handle");
        method.insertBefore("{System.out.println(\"start\");}");
        method.insertAfter("{System.out.println(\"end\");}");
        return ctClass.toBytecode();
      } catch (Exception e) {
        e.printStackTrace();
      }
      return null;
    }
  }
}
