package com.zgb;

/**
 * @author xmly
 * @email guanbao.zhou@ximalaya.com
 * @Date 2019/11/3 9:33 下午
 * @Created By guanbao.zhou
 */
public class ByteCodeDemo {
  private final int version = 52;
  private int a = 1;

  public void handle(){
    int b = 2;
    System.out.println(a + b);
  }
}
