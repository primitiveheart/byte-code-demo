
package com.zgb.btrace;

import com.zgb.ByteCodeDemo;

public class TargetDemo {
  public static void main(String[] args) throws InterruptedException {
    ByteCodeDemo byteCodeDemo = new ByteCodeDemo();
    byteCodeDemo.handle();
    while (true) {
      Thread.sleep(500);
      // 此时字节码已经加载到内存中了
      ByteCodeDemo byteCodeDemo1 = new ByteCodeDemo();
      byteCodeDemo1.handle();
    }
  }
}