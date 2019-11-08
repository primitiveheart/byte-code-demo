package com.zgb;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

import java.io.IOException;

/**
 * @author xmly
 * @email guanbao.zhou@ximalaya.com
 * @Date 2019/11/4 9:24 上午
 * @Created By guanbao.zhou
 */
public class TestRunningClassReload {
  public static void main(String[] args) throws NotFoundException, CannotCompileException,
          IOException, IllegalAccessException, InstantiationException {
    // 先加载ByteCodeDemo的字节码
    ByteCodeDemo bcd = new ByteCodeDemo();
    bcd.handle();
    // 创建CtClass对象池，采用懒加载机制
    ClassPool cp = ClassPool.getDefault();
    // 从ClassPool池中获取CtClass对象，如果不存在，则通过类的全限定名，去加载类文件
    CtClass ctClass = cp.get("com.zgb.ByteCodeDemo");
    CtMethod method = ctClass.getDeclaredMethod("handle");
    method.insertBefore("{System.out.println(\"start\");}");
    method.insertAfter("{System.out.println(\"end\");}");
    Class<?> c = ctClass.toClass();
    // 将修改对文件存储到磁盘中
    ctClass.writeFile("/Users/xmly/studycode/byte-code-demo/src/main/java");
    // 创建对象
    ByteCodeDemo byteCodeDemo = (ByteCodeDemo) c.newInstance();
    byteCodeDemo.handle();
  }
}
