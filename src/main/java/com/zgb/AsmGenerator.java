package com.zgb;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author xmly
 * @email guanbao.zhou@ximalaya.com
 * @Date 2019/11/3 10:41 下午
 * @Created By guanbao.zhou
 */
public class AsmGenerator {
  public static void main(String[] args) throws IOException {
    // 读取字节码
    ClassReader classReader = new ClassReader("com/zgb/ByteCodeDemo");
    ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
    // 处理字节码
    ClassVisitor classVisitor = new MyClassVisitor(classWriter);
    classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);
    byte[] data = classWriter.toByteArray();
    // 输出字节码
    File file = new File("/Users/xmly/studycode/byte-code-demo/target/classes/com/zgb/ByteCodeDemo.class");
    FileOutputStream fos = new FileOutputStream(file);
    fos.write(data);
    fos.flush();
    fos.close();
    System.out.println("new generator cc success");
  }
}
