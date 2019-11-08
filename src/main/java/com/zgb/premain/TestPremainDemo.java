package com.zgb.premain;

import com.zgb.ByteCodeDemo;

/**
 * @author xmly
 * @email guanbao.zhou@ximalaya.com
 * @Date 2019/11/6 8:44 上午
 * @Created By guanbao.zhou
 */
public class TestPremainDemo {
  public static void main(String[] args) {
    // -javaagent:/Users/xmly/studycode/byte-code-demo/target/byte-code-demo-1.0.jar
    // 原生的字节码已经被修改了，此时内存中的字节码不再原生的。
    ByteCodeDemo byteCodeDemo = new ByteCodeDemo();
    byteCodeDemo.handle();
  }
}
