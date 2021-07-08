/*
 * Copyright 2010-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package cn.lhfei.selenium.resource;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import cn.lhfei.selenium.is.nc.sdk.NECaptchaVerifier;
import cn.lhfei.selenium.is.nc.sdk.NESecretPair;
import cn.lhfei.selenium.is.nc.sdk.entity.VerifyResult;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @created Jul 05, 2021
 */
@Controller
public class LoginResource {
  private final Logger LOG = LoggerFactory.getLogger(LoginResource.class);
  private static final String captchaId = "1a623022803d4cbc86fa157ec267bb36"; // 验证码id
  private static final String secretId = "YOUR_SECRET_ID"; // 密钥对id
  private static final String secretKey = "YOUR_SECRET_KEY"; // 密钥对key
  private final NECaptchaVerifier verifier =
      new NECaptchaVerifier(captchaId, new NESecretPair(secretId, secretKey));

  @PostMapping("/login")
  public String verify(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String validate = request.getParameter(NECaptchaVerifier.REQ_VALIDATE); // 从请求体里获得验证码validate数据
    String user = "{'id':'123456'}";

    VerifyResult result = verifier.verify(validate, user); // 发起二次校验

    LOG.info(String.format("validate = {},  isValid = {} , msg = {} ", validate,
        result.isResult(), result.getMsg()));
    
    if (result.isResult()) {
      return "success";
    } else {
      return "fail";
    }
  }
  
  @GetMapping("/hello")
  public String hello(Model model) {
    model.addAttribute("name", "Hefei Li");
    return "hello";
  }
  
  @GetMapping("/popup-demo")
  public String popupDemo() {
    return "popup-demo";
  }
  
  @GetMapping("/simulate")
  public String simulate() {
    return "simulate";
  }
}
