
Decompilers online

    File Name: CreateJweServlet.class, Done.

    Java decompilers
    APK decompiler
    Download Jad

Decompilation Results
Decompilation Results
File Name: CreateJweServlet.class
Decompiler: CFR
Job status: Done.



Twitter Facebook Google+ Stumbleupon LinkedIn

    CreateJweServlet.class 

/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  javax.servlet.RequestDispatcher
 *  javax.servlet.ServletContext
 *  javax.servlet.ServletException
 *  javax.servlet.ServletRequest
 *  javax.servlet.ServletResponse
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  jp.co.nri.di.nikkei.openidc.rp.util.AesEncryptor
 *  jp.co.nri.di.nikkei.openidc.rp.util.ByteUtil
 *  jp.co.nri.di.nikkei.openidc.rp.util.StringUtil
 *  org.apache.commons.codec.binary.Base64
 *  org.apache.log4j.Logger
 */
package jp.co.nri.di.nikkei.openidc.rp.servlet;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Calendar;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jp.co.nri.di.nikkei.openidc.rp.util.AesEncryptor;
import jp.co.nri.di.nikkei.openidc.rp.util.ByteUtil;
import jp.co.nri.di.nikkei.openidc.rp.util.StringUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

public class CreateJweServlet
extends HttpServlet {
    private static final long serialVersionUID = 1;
    Logger logger = Logger.getLogger((Class)CreateJweServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.execute(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.execute(request, response);
    }

    private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean firstNum = false;
        int middleNum = 16;
        int latterNum = 32;
        String alg = request.getParameter("credential_alg");
        String enc = request.getParameter("credential_enc");
        String userLogin = request.getParameter("credential_user_login");
        String userPassword = request.getParameter("credential_user_password");
        String timestamp = request.getParameter("credential_timestamp");
        String timestampFlag = request.getParameter("credential_timestamp_flag");
        if (!StringUtil.isNullOrEmpty((String)timestampFlag)) {
            Calendar cal = Calendar.getInstance();
            cal.set(14, 0);
            timestamp = "" + cal.getTimeInMillis();
        }
        String autoLogin = request.getParameter("credential_auto_login");
        String clientSecret = request.getParameter("credential_client_secret");
        String initializationVector = request.getParameter("credential_initialization_vector");
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e) {
            this.logger.error((Object)"\u5171\u901a\u79d8\u5bc6\u9375\u5217\u306e\u5909\u63db\u306b\u5931\u6557\u3057\u307e\u3057\u305f\u3002");
            e.printStackTrace();
        }
        md.update(clientSecret.getBytes("UTF-8"));
        byte[] clientSecretSha256 = md.digest();
        byte[] clientSecretSha256First = Arrays.copyOfRange(clientSecretSha256, 0, 16);
        byte[] clientSecretSha256Latter = Arrays.copyOfRange(clientSecretSha256, 16, 32);
        String protectedHeader = "{\"alg\":\"" + alg + "\",\"enc\":\"" + enc + "\"}";
        byte[] encodedBase64ProtectedHeader = null;
        encodedBase64ProtectedHeader = Base64.encodeBase64URLSafe((byte[])protectedHeader.getBytes("UTF-8"));
        byte[] initializationVectorByte = initializationVector.getBytes("UTF-8");
        String cipherText = "{\"user_login\":\"" + userLogin + "\",\"user_password\":\"" + userPassword + "\",\"timestamp\":" + timestamp + ",\"auto_login\":" + autoLogin + "}";
        AesEncryptor aesEncryptor = null;
        try {
            aesEncryptor = new AesEncryptor();
        }
        catch (Exception e) {
            this.logger.error((Object)"AesEncryptor\u306e\u30a4\u30f3\u30b9\u30bf\u30f3\u30b9\u4f5c\u6210\u306b\u5931\u6557\u3057\u307e\u3057\u305f\u3002");
            e.printStackTrace();
        }
        aesEncryptor.setPassPhrase(clientSecretSha256Latter);
        byte[] cipherTextByte = null;
        try {
            cipherTextByte = aesEncryptor.encryptWithPadding(initializationVector.getBytes("UTF-8"), cipherText.getBytes("UTF-8"));
        }
        catch (Exception e) {
            this.logger.error((Object)"JWE Ciphertext\u306e\u4f5c\u6210\u306b\u5931\u6557\u3057\u307e\u3057\u305f\u3002");
            request.setAttribute("jweResult", (Object)e);
            e.printStackTrace();
        }
        if (cipherTextByte != null) {
            byte[] authenticationTagRes2 = null;
            try {
                authenticationTagRes2 = ByteUtil.concat((byte[][])new byte[][]{encodedBase64ProtectedHeader, initializationVectorByte, cipherTextByte, ByteUtil.toByteArray((long)((long)encodedBase64ProtectedHeader.length * 8))});
            }
            catch (Exception e1) {
                this.logger.error((Object)"\u5404\u30d1\u30e9\u30e1\u30fc\u30bf\u306e\u9023\u7d50\u306b\u5931\u6557\u3057\u307e\u3057\u305f\u3002");
                e1.printStackTrace();
            }
            byte[] digest = null;
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(clientSecretSha256First, "HmacSHA256");
                Mac mac = Mac.getInstance("HmacSHA256");
                mac.init(secretKeySpec);
                digest = mac.doFinal(authenticationTagRes2);
            }
            catch (InvalidKeyException e) {
                this.logger.error((Object)"HmacSHA256\u30cf\u30c3\u30b7\u30e5\u5024\u306e\u751f\u6210\u306b\u5931\u6557\u3057\u307e\u3057\u305f\u3002");
                e.printStackTrace();
            }
            catch (NoSuchAlgorithmException e) {
                this.logger.error((Object)"HmacSHA256\u30cf\u30c3\u30b7\u30e5\u5024\u306e\u751f\u6210\u306b\u5931\u6557\u3057\u307e\u3057\u305f\u3002");
                e.printStackTrace();
            }
            catch (IllegalStateException e) {
                this.logger.error((Object)"HmacSHA256\u30cf\u30c3\u30b7\u30e5\u5024\u306e\u751f\u6210\u306b\u5931\u6557\u3057\u307e\u3057\u305f\u3002");
                e.printStackTrace();
            }
            byte[] authenticationTag = Arrays.copyOfRange(digest, 0, 16);
            String result = String.valueOf(new String(encodedBase64ProtectedHeader, "UTF-8")) + ".." + new String(Base64.encodeBase64URLSafe((byte[])initializationVector.getBytes("UTF-8")), "UTF-8") + "." + new String(Base64.encodeBase64URLSafe((byte[])cipherTextByte), "UTF-8") + "." + new String(Base64.encodeBase64URLSafe((byte[])authenticationTag), "UTF-8");
            request.setAttribute("jweResult", (Object)result);
        }
        this.getServletContext().getRequestDispatcher("/create_jwe_res.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
}

Need to know what traffic your competitors are generating? Check out my new service.
Estimated traffic and alexa history for any website

Privacy Policy
