package com.zgb.agent;

import com.zgb.ByteCodeDemo;

/**
 * @author xmly
 * @email guanbao.zhou@ximalaya.com
 * @Date 2019/11/6 8:23 上午
 * @Created By guanbao.zhou
 */
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
