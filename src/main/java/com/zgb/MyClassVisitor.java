package com.zgb;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author xmly
 * @email guanbao.zhou@ximalaya.com
 * @Date 2019/11/3 10:42 下午
 * @Created By guanbao.zhou
 */
public class MyClassVisitor extends ClassVisitor {

  public MyClassVisitor(ClassVisitor cv) {
    super(Opcodes.ASM5, cv);
  }

  @Override
  public void visit(int version, int access, String name,
                    String signature, String superName, String[] interfaces) {
    super.visit(version, access, name, signature, superName, interfaces);
  }

  @Override
  public MethodVisitor visitMethod( int access, String name,
                                    String signature, String superName, String[] interfaces) {
    MethodVisitor mv = cv.visitMethod(access, name, signature, superName, interfaces);
    if (!name.equals("<init>") && mv != null) {
        return new MyMethodVisitor(mv);
    }
    return mv;
  }

  class MyMethodVisitor extends MethodVisitor implements Opcodes {

    public MyMethodVisitor(MethodVisitor mv) {
      super(Opcodes.ASM5, mv);
    }

    /**
     * 访问方法的code区域,就是方法的属性表
     */
    @Override
    public void visitCode() {
      super.visitCode();
      mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
      mv.visitLdcInsn("start");
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    }

    @Override
    public void visitInsn(int opcode) {
      if ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN) || opcode == Opcodes.ATHROW) {
        //方法在返回之前，打印"end"
        //获取System类中的out静态变量，并将其压入栈顶
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        // 将end常量压入栈顶
        mv.visitLdcInsn("end");
        // 调用out对象中的println方法
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
      }
      mv.visitInsn(opcode);
    }
  }
}
