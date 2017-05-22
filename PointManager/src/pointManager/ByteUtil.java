/*
 * Decompiled with CFR 0_115.
 *
 * Could not load the following classes:
 *  jp.co.nri.uniid.nl.NkException
 *  org.apache.commons.codec.binary.Base64
 */

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import jp.co.nri.uniid.nl.NkException;
import org.apache.commons.codec.binary.Base64;

public class ByteUtil {
    public static /* varargs */ byte[] concat(byte[] ... byteArrays) {
        if (byteArrays == null) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (byte[] byteArray : byteArrays) {
            if (byteArray == null) continue;
            try {
                baos.write(byteArray);
                continue;
            }
            catch (IOException e) {
                throw new NkException("byte\u914d\u5217\u306e\u9023\u7d50\u306b\u5931\u6557\u3057\u307e\u3057\u305f\u3002", (Throwable)e);
            }
        }
        return baos.toByteArray();
    }

    public static byte[] toByteArray(int i) {
        byte[] bytes = new byte[]{(byte)(i >> 24), (byte)(i >> 16), (byte)(i >> 8), (byte)i};
        return bytes;
    }

    public static byte[] toByteArray(long l) {
        byte[] bytes = new byte[]{(byte)(l >> 56), (byte)(l >> 48), (byte)(l >> 40), (byte)(l >> 32), (byte)(l >> 24), (byte)(l >> 16), (byte)(l >> 8), (byte)l};
        return bytes;
    }

    public static byte[] base64encode(byte[] target) {
        if (target == null) {
            return null;
        }
        return Base64.encodeBase64((byte[])target);
    }

    public static /* varargs */ byte[] base64encode(byte[] ... targets) {
        if (targets == null) {
            return null;
        }
        byte[] concatted = ByteUtil.concat(targets);
        return ByteUtil.base64encode(concatted);
    }

    public static byte[] base64decode(byte[] target) {
        if (target == null) {
            return null;
        }
        return Base64.decodeBase64((byte[])target);
    }

    public static String toStringByUTF8(byte[] bytes) {
        return ByteUtil.toString(bytes, "UTF-8");
    }

    public static String toString(byte[] bytes, String encoding) {
        if (bytes == null) {
            return null;
        }
        try {
            return new String(bytes, encoding);
        }
        catch (UnsupportedEncodingException e) {
            throw new NkException("\u4e0d\u6b63\u306a\u6587\u5b57\u30a8\u30f3\u30b3\u30fc\u30c9\u540d\u304c\u6307\u5b9a\u3055\u308c\u3066\u3044\u307e\u3059\u3002encoding=" + encoding, (Throwable)e);
        }
    }

    public static byte[] subsequence(byte[] src, int offset, int length) {
        if (offset < 0 || length < 0) {
            throw new NkException("\u90e8\u5206\u30d0\u30a4\u30c8\u914d\u5217\u53d6\u5f97\u6642\u306e\u30a4\u30f3\u30c7\u30c3\u30af\u30b9\u6307\u5b9a\u304c\u4e0d\u6b63\u3067\u3059\u3002offset=" + offset + ", length=" + length);
        }
        if (src == null) {
            return null;
        }
        int orgLength = src.length;
        if (orgLength <= offset + length) {
            throw new NkException("\u90e8\u5206\u30d0\u30a4\u30c8\u914d\u5217\u53d6\u5f97\u6642\u306e\u30a4\u30f3\u30c7\u30c3\u30af\u30b9\u6307\u5b9a\u304c\u4e0d\u6b63\u3067\u3059\u3002array length=" + orgLength + "offset=" + offset + ", length=" + length);
        }
        byte[] dest = new byte[length];
        for (int index = 0; index < length; ++index) {
            dest[index] = src[offset + index];
        }
        return dest;
    }

    public static boolean equals(byte[] b1, byte[] b2) {
        if (b1 == null && b2 == null) {
            return true;
        }
        if (b1 == null && b2 != null) {
            return false;
        }
        if (b1 != null && b2 == null) {
            return false;
        }
        if (b1.length != b2.length) {
            return false;
        }
        for (int i = 0; i < b1.length; ++i) {
            if (b1[i] == b2[i]) continue;
            return false;
        }
        return true;
    }
}