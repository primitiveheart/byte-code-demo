package com.zgb.btrace;

import java.util.Random;

/**
 * @author xmly
 * @email guanbao.zhou@ximalaya.com
 * @Date 2019/11/10 4:50 下午
 * @Created By guanbao.zhou
 */
public class BTraceDemo {
    public static Random random = new Random();

    public static void main(String[] args) throws Exception {
      new BTraceDemo().run();
    }

    public void run() throws Exception {
      while (true) {
        add(random.nextInt(10), random.nextInt(10));
      }
    }

    public int add(int a, int b) throws Exception {
      Thread.sleep(random.nextInt(10) * 100);
      return a + b;
    }
}
