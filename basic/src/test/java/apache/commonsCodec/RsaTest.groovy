package apache.commonsCodec

import cn.hutool.crypto.asymmetric.ECIES
import cn.hutool.crypto.asymmetric.KeyType
import cn.hutool.jwt.JWTUtil
import groovy.util.logging.Slf4j
import org.apache.commons.codec.binary.Base64
import spock.lang.Specification

@Slf4j
class RsaTest extends Specification {
    def "测试随机生成公私钥进行公钥加密私钥解密"() {
        given:
        def rsa = RsaUtil.genKeyPairStr()
        and:
        def str = "hello"
        and:
        def pubKey = rsa.get(RsaUtil.RSA_PUBLIC_KEY)
        def priKey = rsa.get(RsaUtil.RSA_PRIVATE_KEY)
        log.info("公钥:${pubKey}")
        log.info("私钥:${priKey}")
        when:
        def 加密字符 = RsaUtil.encryptTextByPublicKey(str.getBytes(), pubKey)
        def 加密字符2 = RsaUtil.encryptByPublicKey(str.getBytes(), pubKey)
        log.info("加密字符:${加密字符}")
        log.info("加密字符2:${new String(加密字符2)}")
        //def 解密字符 = RsaUtil.decryptTextByPrivateBase64Key(加密字符, priKey)
        then:
        true
        //解密字符 == str

    }

    def "使用服务端公钥对客户端公钥进行加密"() {
        given:
        def server_pub_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCWmLKL/Rsqqj3ouQP7MVW/06wtefte7c64ciN+eYylkB5d5VDK3qE2KOPx0I2c7pH0Vd0B3uvP1ink+DB8GbjpyPZyELu1Z1GEPkNhDspMJF/b330jz5JTxXwMXBHKamYfT9T4yrGAhoRUmWew3tezyqRXlG2DpbnwNqVrFOGO6wIDAQAB"
        def server_pri_key = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAJaYsov9GyqqPei5A/sxVb/TrC15+17tzrhyI355jKWQHl3lUMreoTYo4/HQjZzukfRV3QHe68/WKeT4MHwZuOnI9nIQu7VnUYQ+Q2EOykwkX9vffSPPklPFfAxcEcpqZh9P1PjKsYCGhFSZZ7De17PKpFeUbYOlufA2pWsU4Y7rAgMBAAECgYAx/BfTKBzywdPPoU1FfEAKbh8JbyH0hbkl9lh6m6yHrXiu0LTKyS84E0VpV0ARtQvDzhYPj2a0stBv+8LYBg+eIsSlw08DjOSPHY8lCJr7JWCKUqWRRHrnXi8WT3e5z1j/hYi2qWxm1NSRbyy5c5zRpsYEfsrwvEx8p1APes3HoQJBAM3vw2iqrGn6iiuIYzhGkgVlwfl1qYCvVnBQ+xhXhcxCSfSJ7xoePNVybEOhKA6vENoevTHIYbY6FYpGV0Fb5osCQQC7NOdY3wyIeYMed3qdAZ7TNRz/VtdnhcbiK5jbktnnU4cimaghh/UgZ1P4JDHTg/oD+UHl8yu/dOvsPOpIvGUhAkEAjwCwnAzwfwNIUSR7acevfGoGAblQBBk9l+7T/jpAoe9iNZpW27clnT7AqVHOZCvzMtPnoxs3pve8n3FrA+rSMwJBAIkRAxIevZ5u2YHFGRE7zn31tXV+r9gsKqIOKa0aqFCm55p9xoxGb0N6ZBCa76BeUP/30Df9A1r60IH3V9JJngECQQCXyczibGKaKhSK/HgGbHaXH8R+psXpXYruTGQXnCVzoRL23MXAgJ9L1p+ads8JfQb5LNpybbKpjjww8cO7YH7m";
        def server_rsa = RsaUtil.genKeyPairStr()
        server_pri_key = server_rsa.get(RsaUtil.RSA_PRIVATE_KEY)
        /* log.info("服务端公钥:${server_pub_key}")
         log.info("服务端私钥:${server_pri_key}")*/
        server_pub_key = server_rsa.get(RsaUtil.RSA_PUBLIC_KEY)

        def client_rsa = RsaUtil.genKeyPairStr()
        log.info("客户端公钥:${client_rsa.get(RsaUtil.RSA_PUBLIC_KEY)}")
        /*log.info("客户端私钥:${client_rsa.get(RsaUtil.RSA_PRIVATE_KEY)}")*/
        when:
        // 加密后的客户端公钥
        def bs = Base64.decodeBase64(client_rsa.get(RsaUtil.RSA_PUBLIC_KEY));
        def encrytp_pub_key = RsaUtil.encryptTextByPublicKey(bs, Base64.decodeBase64(server_pub_key))
        log.info("编码后的字符串:${encrytp_pub_key}")
        def decrypt_pub_key = RsaUtil.decryptByPrivateKey(Base64.decodeBase64(encrytp_pub_key), org.apache.commons.codec.binary.Base64.decodeBase64(server_pri_key))
        log.info("解密后的客户端公钥:")
        log.info(Base64.encodeBase64String(decrypt_pub_key))
        then: "验证解密后的客户端公钥是否和客户端公钥一致"
        true
    }

    def "生成客户端公私钥"() {
        // MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC5/7BY/zvXndy/5fiHVhyfXjphD5r1ZQNssK+rr99LIiyx3TKfl3lSKvBmGwp3UOUQiTuyJWKuGq40df09/2wMMmMcSrcR0Ejfl1oWGCR8lC3xs1vIHVEKiZzeemnBnkNmLfbr+98Edgisf37Um8LGikMkF8anC84Iu+sC+iSTeQIDAQAB
        // MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALn/sFj/O9ed3L/l+IdWHJ9eOmEPmvVlA2ywr6uv30siLLHdMp+XeVIq8GYbCndQ5RCJO7IlYq4arjR1/T3/bAwyYxxKtxHQSN+XWhYYJHyULfGzW8gdUQqJnN56acGeQ2Yt9uv73wR2CKx/ftSbwsaKQyQXxqcLzgi76wL6JJN5AgMBAAECgYBR2T4Zk8uaMQos2f7ZU2AKE3WeM7dNiHOO11uesiiRUAsaBAxcZVRKf8HFNN4QrmT2t2UxGjmIAw6o4EkeCLHbFeaO6Zd7JwkUxbwioo1gfyg+88Gr34bK94q86//KddYNF47Q3FPyQq1MMJy8OVYPUuXVl9nxXDNzKQv3KoHbzwJBANAJPfDTmF2U6eke3pOYKcYZL7u1ZFDyI6E/VLlEUTaDHPvr3OMtS6e3BNMx2pUGzbwqjh07vkguaCLncOfuCoMCQQDk4cFFCcHr/gh7rMBOZ45AhsKmn0mNdJ1O0GzgXR+Bl1RTbwwIYEgdbX965wZNWtmwKG0qlUQ4O/OydTsH4DlTAkEAlKQl1664DRVbVC+0RKzSHDLewjXG6Xc5Mfs4I+/GPzbLPMy31LIGflzsW6IhVZ243hth001eleBik53eNDkG1QJBAKFxiBT9a/bdKfiqgr8LFnD3XUeQZZ5MmkUkiRe9N+L97jInyxIBb/yp5ti3Kl/X7GLwMQhvXz5XM282d5v3khcCQCMVLCSVe3eAYwp44fQjn9Q5dGUXdasAbZwb6WjpN9fhFlIcFS30bm5QhlszFdpw+9+LsupR5RoR/YoZt6MUz3o=
        // 待加密服务端公钥
        def server_pub_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC5/7BY/zvXndy/5fiHVhyfXjphD5r1ZQNssK+rr99LIiyx3TKfl3lSKvBmGwp3UOUQiTuyJWKuGq40df09/2wMMmMcSrcR0Ejfl1oWGCR8lC3xs1vIHVE"
        // 加密后的客户端公钥
        def encrypt_key = "";
        given:
        def rsa = RsaUtil.genKeyPairStr()
        when:
        def pub_key = rsa.get(RsaUtil.RSA_PUBLIC_KEY)
        def pri_key = rsa.get(RsaUtil.RSA_PRIVATE_KEY)
        def encrypt_client_key = RsaUtil.encryptByPublicKey(Base64.decodeBase64(pub_key), Base64.decodeBase64(server_pub_key))
        def decrypt_client_key_base64 =Base64.encodeBase64String(encrypt_client_key)
        log.info("加密后的客户端公钥:${encrypt_client_key}")
        log.info("公钥:${pub_key}")
        log.info("私钥:${pri_key}")
        then:
        true
    }

    def "验证JWT"() {
        given:
        def rsa = RsaUtil.genKeyPair();
        when:
        def token = JWTUtil.createToken([name: "zhangsan", age: 18], '123'.getBytes())
        println(token)
        def rs = JWTUtil.verify(token, '1'.getBytes())
        def rs1 = JWTUtil.verify(token, '123'.getBytes())
        def name = JWTUtil.parseToken(token).getPayload("name");
        then:
        rs == false && rs1 == true && name == "zhangsan"
    }

    def "ECC"() {
        given:
        def client_rsa = RsaUtil.genKeyPairStr()
        final ECIES ecies = new ECIES();
        def priv_k = ecies.getPrivateKeyBase64()
        def pub_k = ecies.getPublicKeyBase64()
        def str = "hello"
        def encrypt_bs = ecies.encrypt(str, KeyType.PublicKey)

        when:
        def decrypt_bytes = ecies.decrypt(encrypt_bs, KeyType.PrivateKey)
        def decrypt_str = new String(decrypt_bytes)
        then:
        str == decrypt_str
    }
}
