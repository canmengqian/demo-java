package apache.commonsCodec;

import cn.hutool.core.io.FastByteArrayOutputStream;
import cn.hutool.crypto.CipherWrapper;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className RsaUtil
 * @description TODO
 * @date 2024/6/4
 */
public class RsaUtil {
    /**
     * 非对称加密密钥算法
     */
    public static final String KEY_ALGORITHM_RSA = "RSA";

    /**
     * 公钥
     */
    public static final String RSA_PUBLIC_KEY = "RSAPublicKey";

    /**
     * 私钥
     */
    public static final String RSA_PRIVATE_KEY = "RSAPrivateKey";
    private static final int KEY_SIZE = 1024;

    // 生成公私钥对


    public static Map<String, Key> genKeyPairMap(byte[] seed) throws NoSuchAlgorithmException {
        // 实例化密钥对生成器
        KeyPairGenerator keyPairGen = KeyPairGenerator
                .getInstance(KEY_ALGORITHM_RSA);
        // 初始化密钥对生成器
        keyPairGen.initialize(KEY_SIZE, new SecureRandom(seed));
        // 生成密钥对
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // 公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        // 私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 封装密钥
        Map<String, Key> keyMap = new HashMap<String, Key>(2);
        keyMap.put(RSA_PUBLIC_KEY, publicKey);
        keyMap.put(RSA_PRIVATE_KEY, privateKey);
        return keyMap;
    }

    public static Map<String, Key> genKeyPairMap() throws NoSuchAlgorithmException {
        return genKeyPairMap(UUID.randomUUID().toString().getBytes());
    }

    public static Map<String, Key> genKeyPairMap(String seed) throws NoSuchAlgorithmException {
        return genKeyPairMap(seed.getBytes());
    }

    public static Map<String, String> genKeyPairStr(byte[] seed) throws NoSuchAlgorithmException {
        Map<String, Key> keys = genKeyPairMap(seed);
        Map<String, String> keyMap = new HashMap<String, String>(2);
        keyMap.put(RSA_PUBLIC_KEY, Base64.encodeBase64String(keys.get(RSA_PUBLIC_KEY).getEncoded()));
        keyMap.put(RSA_PRIVATE_KEY, Base64.encodeBase64String(keys.get(RSA_PRIVATE_KEY).getEncoded()));
        return keyMap;
    }

    public static KeyPair genKeyPair(byte[] seed) throws NoSuchAlgorithmException {
        Map<String, Key> keys = genKeyPairMap(seed);
        return new KeyPair((PublicKey) keys.get(RSA_PUBLIC_KEY), (PrivateKey) keys.get(RSA_PRIVATE_KEY));
    }

    public static KeyPair genKeyPair() throws NoSuchAlgorithmException {
        Map<String, Key> keys = genKeyPairMap(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
        return new KeyPair((PublicKey) keys.get(RSA_PUBLIC_KEY), (PrivateKey) keys.get(RSA_PRIVATE_KEY));
    }

    public static KeyPair genKeyPair(String seed) throws NoSuchAlgorithmException {
        Map<String, Key> keys = genKeyPairMap(seed.getBytes(StandardCharsets.UTF_8));
        return new KeyPair((PublicKey) keys.get(RSA_PUBLIC_KEY), (PrivateKey) keys.get(RSA_PRIVATE_KEY));
    }

    public static Map<String, String> genKeyPairStr() throws NoSuchAlgorithmException {
        return genKeyPairStr(UUID.randomUUID().toString().getBytes());
    }

    public static Map<String, String> genKeyPairStr(String seed) throws NoSuchAlgorithmException {
        return genKeyPairStr(seed.getBytes());
    }

    /**
     * 获取公钥
     *
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static PublicKey getPublicRSAKey(String key) throws Exception {
        return getPublicRSAKey(Base64.decodeBase64(key));
    }

    /**
     * 获取公钥
     *
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static PublicKey getPublicRSAKey(byte[] key) throws Exception {
        X509EncodedKeySpec x509 = new X509EncodedKeySpec(key);
        KeyFactory kf = KeyFactory.getInstance(KEY_ALGORITHM_RSA);
        return kf.generatePublic(x509);
    }

    /**
     * 获取私钥
     *
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static PrivateKey getPrivateRSAKey(String key) throws Exception {
        return getPrivateRSAKey(Base64.decodeBase64(key));
    }

    /**
     * 获取私钥
     *
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static PrivateKey getPrivateRSAKey(byte[] key) throws Exception {
        PKCS8EncodedKeySpec pkgs8 = new PKCS8EncodedKeySpec(key);
        KeyFactory kf = KeyFactory.getInstance(KEY_ALGORITHM_RSA);
        return kf.generatePrivate(pkgs8);
    }

    /**
     * 私钥解密
     *
     * @param data 待解密数据
     * @param key  私钥
     * @return byte[] 解密数据
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data, byte[] key)
            throws Exception {

        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);

        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);

        // 生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 对数据解密
        Cipher cipher = getCipher(keyFactory.getAlgorithm());
        int encryptBlockSize = -1;
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        if (encryptBlockSize < 0) {
            // 在引入BC库情况下，自动获取块大小
            final int blockSize = cipher.getBlockSize();
            if (blockSize > 0) {
                encryptBlockSize = blockSize;
            }
        }
        return doFinal(data, encryptBlockSize, cipher);

    }

    /**
     * 私钥解密
     *
     * @param data 待解密数据
     * @param key  私钥
     * @return byte[] 解密数据
     * @throws Exception
     */
    public static byte[] decryptByPrivateBase64Key(byte[] data, String key) throws Exception {
        return decryptByPrivateKey(data, Base64.decodeBase64(key));
    }

    /**
     * 私钥解密
     *
     * @param data 待解密数据
     * @param key  私钥
     */
    public static byte[] decryptByPrivateBase64Key(byte[] data, RSAPrivateKey key) throws Exception {
        return decryptByPrivateKey(data, key.getEncoded());
    }


    /**
     * 私钥解密
     *
     * @param data 待解密数据
     * @param key  私钥
     * @return byte[] 解密数据
     * @throws Exception
     */
    public static String decryptTextByPrivateBase64Key(byte[] data, String key) throws Exception {
        return new String(decryptByPrivateKey(data, Base64.decodeBase64(key)));
    }

    public static String decryptTextByPrivateBase64Key(byte[] data, byte[] key) throws Exception {
        return new String(decryptByPrivateKey(data, key));
    }

    public static String decryptTextByPrivateBase64Key(byte[] data, RSAPrivateKey key) throws Exception {
        return new String(decryptByPrivateKey(data, key.getEncoded()));
    }

    public static String decryptTextByPrivateBase64Key(String data, byte[] key) throws Exception {
        return new String(decryptByPrivateKey(data.getBytes(StandardCharsets.UTF_8), key));
    }

    public static String decryptTextByPrivateBase64Key(String data, String key) throws Exception {
        return Base64.encodeBase64String(decryptByPrivateKey(data.getBytes(StandardCharsets.UTF_8), Base64.decodeBase64(key)));
    }

    public static String decryptTextByPrivateBase64Key(String data, RSAPrivateKey key) throws Exception {
        return new String(decryptByPrivateKey(data.getBytes(StandardCharsets.UTF_8), key.getEncoded()));
    }

    /**
     * 公钥加密
     *
     * @param data 待加密数据
     * @param key  公钥
     * @return byte[] 加密数据
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, byte[] key)
            throws Exception {

        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);

        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);

        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);

        // 对数据加密
        Cipher cipher = getCipher(keyFactory.getAlgorithm());
        int encryptBlockSize = -1;
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        if (encryptBlockSize < 0) {
            // 在引入BC库情况下，自动获取块大小
            final int blockSize = cipher.getBlockSize();
            if (blockSize > 0) {
                encryptBlockSize = blockSize;
            }
        }
        return doFinal(data, encryptBlockSize, cipher);
    }

    private static Cipher getCipher(String algorithm) {
        return new CipherWrapper(algorithm).getCipher();
    }


    private static byte[] doFinal(byte[] data, int maxBlockSize, Cipher cipher) throws IllegalBlockSizeException, BadPaddingException, IOException {
        // 模长
        final int dataLength = data.length;

        // 不足分段
        if (dataLength <= maxBlockSize) {
            return cipher.doFinal(data, 0, dataLength);
        }

        // 分段解密
        return doFinalWithBlock(data, maxBlockSize, cipher);
    }

    /**
     * 分段加密或解密
     *
     * @param data         数据
     * @param maxBlockSize 最大分段的段大小，不能为小于1
     * @return 加密或解密后的数据
     * @throws IllegalBlockSizeException 分段异常
     * @throws BadPaddingException       padding错误异常
     * @throws IOException               IO异常，不会被触发
     */
    private static byte[] doFinalWithBlock(byte[] data, int maxBlockSize, Cipher cipher) throws IllegalBlockSizeException, BadPaddingException, IOException {
        final int dataLength = data.length;
        @SuppressWarnings("resource") final FastByteArrayOutputStream out = new FastByteArrayOutputStream();

        int offSet = 0;
        // 剩余长度
        int remainLength = dataLength;
        int blockSize;
        // 对数据分段处理
        while (remainLength > 0) {
            blockSize = Math.min(remainLength, maxBlockSize);
            out.write(cipher.doFinal(data, offSet, blockSize));

            offSet += blockSize;
            remainLength = dataLength - offSet;
        }

        return out.toByteArray();
    }

    public static byte[] encryptByPublicKey(String data, byte[] key) throws Exception {
        return encryptByPublicKey(data.getBytes(StandardCharsets.UTF_8), key);
    }

    public static byte[] encryptByPublicKey(byte[] data, String base64key) throws Exception {
        return encryptByPublicKey(data, Base64.decodeBase64(base64key));
    }

    public static byte[] encryptByPublicKey(String data, String base64key) throws Exception {
        return encryptByPublicKey(data.getBytes(StandardCharsets.UTF_8), Base64.decodeBase64(base64key));
    }

    public static byte[] encryptByPublicKey(byte[] data, RSAPublicKey publicKey) throws Exception {
        return encryptByPublicKey(data, publicKey.getEncoded());
    }

    public static byte[] encryptByPublicKey(String data, RSAPublicKey publicKey) throws Exception {
        return encryptByPublicKey(data.getBytes(StandardCharsets.UTF_8), publicKey.getEncoded());
    }

    public static String encryptTextByPublicKey(byte[] data, byte[] base64key) throws Exception {
        return Base64.encodeBase64String(encryptByPublicKey(data, base64key));
    }

    public static String encryptTextByPublicKey(byte[] data, String base64key) throws Exception {
        return Base64.encodeBase64String(encryptByPublicKey(data, Base64.decodeBase64(base64key)));
    }

    public static String encryptTextByPublicKey(byte[] data, RSAPublicKey publicKey) throws Exception {
        return Base64.encodeBase64String(encryptByPublicKey(data, publicKey.getEncoded()));
    }

    public static String encryptTextByPublicKey(String data, String base64key) throws Exception {
        return Base64.encodeBase64String(encryptByPublicKey(data.getBytes(StandardCharsets.UTF_8), Base64.decodeBase64(base64key)));
    }

    public static String encryptTextByPublicKey(String data, byte[] key) throws Exception {
        return Base64.encodeBase64String(encryptByPublicKey(data.getBytes(StandardCharsets.UTF_8), key));
    }

    public static String encryptTextByPublicKey(String data, RSAPublicKey publicKey) throws Exception {
        return Base64.encodeBase64String(encryptByPublicKey(data.getBytes(StandardCharsets.UTF_8), publicKey.getEncoded()));
    }


}
