package com.zgb.premain;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * @author xmly
 * @email guanbao.zhou@ximalaya.com
 * @Date 2019/11/6 8:40 上午
 * @Created By guanbao.zhou
 */
public class PremainDemo {
  public static void premain(String agentArgs, Instrumentation inst) {
    System.out.println("agentArgs: " + agentArgs);
    inst.addTransformer(new PremainTransformer(), true);
  }

  static class PremainTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
      System.out.println("premain load class: " + className);
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
