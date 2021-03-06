/*
 * Copyright 2010-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.lhfei.selenium.is.nc.sdk;

import com.alibaba.fastjson.JSONObject;

import cn.lhfei.selenium.is.nc.sdk.entity.VerifyResult;
import cn.lhfei.selenium.is.nc.sdk.utils.HttpConnectionUtils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @created Jul 05, 2021
 */
public class NECaptchaVerifier {
    public static final String VERIFY_API = "http://c.dun.163yun.com/api/v2/verify"; // verify接口地址
    public static final String REQ_VALIDATE = "NECaptchaValidate"; // 二次验证带过来的validate

    private static final String VERSION = "v2";
    private String captchaId = ""; // 验证码id
    private NESecretPair secretPair = null; // 密钥对

    public NECaptchaVerifier(String captchaId, NESecretPair secretPair) {
        Validate.notBlank(captchaId, "captchaId为空");
        Validate.notNull(secretPair, "secret为null");
        Validate.notBlank(secretPair.secretId, "secretId为空");
        Validate.notBlank(secretPair.secretKey, "secretKey为空");
        this.captchaId = captchaId;
        this.secretPair = secretPair;
    }

    /**
     * 二次验证
     *
     * @param validate 验证码组件提交上来的NECaptchaValidate值
     * @param user     用户
     * @return
     */
    public VerifyResult verify(String validate, String user) {
        if (StringUtils.isEmpty(validate) || StringUtils.equals(validate, "null")) {
            return VerifyResult.fakeFalseResult("validate data is empty");
        }
        user = (user == null) ? "" : user; // bugfix:如果user为null会出现签名错误的问题
        Map<String, String> params = new HashMap<String, String>();
        params.put("captchaId", captchaId);
        params.put("validate", validate);
        params.put("user", user);
        // 公共参数
        params.put("secretId", secretPair.secretId);
        params.put("version", VERSION);
        params.put("timestamp", String.valueOf(System.currentTimeMillis()));
        params.put("nonce", String.valueOf(ThreadLocalRandom.current().nextInt()));
        // 计算请求参数签名信息
        String signature = sign(secretPair.secretKey, params);
        params.put("signature", signature);
        String resp = "";
        try {
            resp = HttpConnectionUtils.readContentFromPost(VERIFY_API, params);
        } catch (IOException ex) {
            System.out.println("http connect occur exception,please check !");
            ex.printStackTrace();
        }
        System.out.println("resp = " + resp);
        return verifyRet(resp);
    }

    /**
     * 生成签名信息
     *
     * @param secretKey 验证码私钥
     * @param params    接口请求参数名和参数值map，不包括signature参数名
     * @return
     */
    public static String sign(String secretKey, Map<String, String> params) {
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);
        StringBuffer sb = new StringBuffer();
        for (String key : keys) {
            sb.append(key).append(params.get(key));
        }
        sb.append(secretKey);
        try {
            return DigestUtils.md5Hex(sb.toString().getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();// 一般编码都支持的。。
        }
        return null;
    }

    /**
     * 验证返回结果<br>
     * 1. 当易盾服务端出现异常或者返回异常时，优先使用返回true的结果，反之阻塞用户的后序操作<br>
     * 2. 如果想修改为返回false结果。可以调用VerifyResult.fakeFalseResult(java.lang.String)函数
     *
     * @param resp
     * @return
     */
    private VerifyResult verifyRet(String resp) {
        if (StringUtils.isEmpty(resp)) {
            return VerifyResult.fakeTrueResult("return empty response");
        }
        try {
            VerifyResult verifyResult = JSONObject.parseObject(resp, VerifyResult.class);
            return verifyResult;
        } catch (Exception ex) {
            System.out.println("yidun captcha return error response ,please check!");
            ex.printStackTrace();
            return VerifyResult.fakeTrueResult(resp);
        }
    }
}
