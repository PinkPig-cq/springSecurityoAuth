package com.qz.jwttoken.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qz.jwttoken.entity.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.security.ec.ECPrivateKeyImpl;
import sun.security.util.DerValue;

import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Token工具类
 */
public class JwtTokenUtil {

    private static Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    /**
     * 20分钟
     */
    private static final long EXPIRE = 1000 * 60 * 20;

    /**
     * 创建Token对象
     */
    public static String createToken(Users user) {
        String token;

        //对象转换
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> objMap = objectMapper.convertValue(user, HashMap.class);


        /*
        * Token 有3个部分   header:  用于携带 加密算法等信息
        *                   body:   真正存数据的地方
        *                   sign:   存签证，该签证由上面2部分加密得到。用header+body加密后与sign对比，防止篡改。
        *
        * */
        token = Jwts.builder().setSubject("发起者:qinhu")  //token发起者，可以无
                .setClaims(objMap)  //在token中存的额外数据
                .setIssuedAt(new Date())  //token开始时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))  //token过期时间
                .signWith(getKey("我的密钥"), SignatureAlgorithm.HS512)   //鉴证，用于验证Token中的数据是否被修改
                .compact();  //完成
        return token;
    }

    /**
     *将加密的Token解密
     */
    public static Users decodeToken(String token) {

        Claims claims
                = Jwts.parser()  //解密流程
                .setSigningKey(getKey("我的密钥"))  //上面的密钥,header中带有加密方式的信息
                .parseClaimsJws(token)  //解密
                .getBody();  //获取中间的Body部分
        ObjectMapper objectMapper = new ObjectMapper();
        Users users = objectMapper.convertValue(claims, Users.class);

        return users;
    }

    /**
     * 由String创建以个Key
     */
    private static Key getKey(String value) {
        byte[] byteKey = Base64.getDecoder().decode("465467");
        PrivateKey privateKey = null;
        PKCS8EncodedKeySpec x509EncodedKeySpec = new PKCS8EncodedKeySpec(byteKey);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            try {
                privateKey = keyFactory.generatePrivate(x509EncodedKeySpec);
            } catch (InvalidKeySpecException e) {
                logger.warn("生成密钥失败 !!");
            }
        } catch (NoSuchAlgorithmException e) {
            logger.warn("创建密钥生成工厂-----> keyFactory  创建失败!!");
        }

        return privateKey;
    }
}
