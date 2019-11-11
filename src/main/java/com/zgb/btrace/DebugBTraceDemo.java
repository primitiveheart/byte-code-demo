package com.zgb.btrace;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Duration;
import com.sun.btrace.annotations.Export;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.OnTimer;
import com.sun.btrace.annotations.Return;
import com.sun.btrace.annotations.Self;

/**
 * @author xmly
 * @email guanbao.zhou@ximalaya.com
 * @Date 2019/11/10 4:59 下午
 * @Created By guanbao.zhou
 */
@BTrace
public class DebugBTraceDemo {
  @Export static long counter;

  @OnMethod(clazz = "com.zgb.BTraceDemo", method = "add", location = @Location(Kind.RETURN))
  public static void run(@Self Object self, int a, int b, @Return int result, @Duration long time) {
    BTraceUtils.println("parameter: a= " + a + ", b=" + b);
    BTraceUtils.println("return " +  result);
    BTraceUtils.println("cost time "+ time);
    counter++;
  }

  @OnTimer(1000)
  public static void run() {
    BTraceUtils.println("execute count : " + counter);
  }

}
