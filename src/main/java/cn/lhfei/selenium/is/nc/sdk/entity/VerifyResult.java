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

package cn.lhfei.selenium.is.nc.sdk.entity;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @created Jul 05, 2021
 */

public class VerifyResult {

  public VerifyResult() {}

  /**
   * 异常代号
   */
  private int error;
  /**
   * 错误描述信息
   */
  private String msg;
  /**
   * 二次校验结果 true:校验通过 false:校验失败
   */
  private boolean result;

  /**
   * 短信上行发送的手机号码 仅限于短信上行的验证码类型
   */
  private String phone;

  /**
   * 额外字段
   */
  private String extraData;

  public int getError() {
    return error;
  }

  public void setError(int error) {
    this.error = error;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public boolean isResult() {
    return result;
  }

  public void setResult(boolean result) {
    this.result = result;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getExtraData() {
    return extraData;
  }

  public void setExtraData(String extraData) {
    this.extraData = extraData;
  }

  public static VerifyResult fakeFalseResult(String resp) {
    VerifyResult result = new VerifyResult();
    result.setResult(false);
    result.setError(0);
    result.setMsg(resp);
    result.setPhone("");
    return result;
  }

  public static VerifyResult fakeTrueResult(String resp) {
    VerifyResult result = new VerifyResult();
    result.setResult(true);
    result.setError(0);
    result.setMsg(resp);
    result.setPhone("");
    return result;
  }
}
