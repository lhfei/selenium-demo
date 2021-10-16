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

package cn.lhfei.selenium.chrome;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @created Jun 23, 2021
 */

public class JiangsuTest {
  
  private static CloseableHttpClient httpClient = HttpClients.createDefault();

  public static void main(String[] args) {

    try {
      String nevalidate = "";
      HttpGet request = new HttpGet("http://139.196.160.191:8668/my_js_token");

      /*
       * CloseableHttpResponse response = httpClient.execute(request);
       * 
       * HttpEntity entity = response.getEntity(); if (entity != null) { // return it as a String
       * nevalidate = EntityUtils.toString(entity); }
       */

      // System Property for Chrome Driver
      System.setProperty("webdriver.chrome.driver",
          "D:\\DevProfile\\WebDriver\\94.0.4606.41\\chromedriver.exe");

      // Instantiate a ChromeDriver class.
      WebDriver driver = new ChromeDriver();

      // Launch Website
      driver.navigate().to("https://etax.jiangsu.chinatax.gov.cn/sso/login");

      // Maximize the browser
      driver.manage().window().maximize();

      // Scroll down the webpage by 5000 pixels
      JavascriptExecutor js = (JavascriptExecutor) driver;
      // js.executeScript("scrollBy(0, 5000)");

      // close message modal
      js.executeScript("$('.xubox_setwin>a').click();");

      // show login modal
      js.executeScript("$('.go_login>img').click();");

      // change login type to user_account
      js.executeScript("$('img.topc').click();");

      // auto enter username/password

      js.executeScript("$('#username_msg').val('91320105MA1XQ8KN8G')");
      js.executeScript("$('#password_msg').val('Xiaomi123456')");

      js.executeScript("$('div.yidun_panel').show()");
      js.executeScript("$('div.yidun_inference').css('display', 'block');");
      js.executeScript("$('div.yidun_tips__answer').removeClass('hide');");

      // js.executeScript("$('div.yidun_inference--1').trigger('click');");
      // js.executeScript("$('div.yidun_inference--4').trigger('click');");
      // js.executeScript("$('div.yidun_inference--6').trigger('click');");
      
      
      js.executeScript("$('#captcha_div').append('<div class=\"yidun yidun--light yidun--embed yidun--point yidun--success\" style=\"width: 320px; min-width: 220px\">   <div class=\"yidun_panel\" style=\"padding-bottom: 15px\">     <div class=\"yidun_panel-placeholder\">              <div class=\"yidun_bgimg\" style=\"border-radius: 2px\">         <img class=\"yidun_bg-img\" alt=\"验证码背景\" style=\"border-radius: 2px\" src=\"http://necaptcha.nosdn.127.net/e6bce573de8345859298613c3db4c763.jpg\">         <img class=\"yidun_jigsaw\" alt=\"验证码滑块\">                               <div class=\"yidun_inference yidun_inference--0\" draggable=\"true\">             <img class=\"yidun_inference__img\" draggable=\"false\">             <span class=\"yidun_inference__border\"></span>           </div>                                          <div class=\"yidun_inference yidun_inference--1\" draggable=\"true\">             <img class=\"yidun_inference__img\" draggable=\"false\">             <span class=\"yidun_inference__border\"></span>           </div>                                          <div class=\"yidun_inference yidun_inference--2\" draggable=\"true\">             <img class=\"yidun_inference__img\" draggable=\"false\">             <span class=\"yidun_inference__border\"></span>           </div>                                          <div class=\"yidun_inference yidun_inference--3\" draggable=\"true\">             <img class=\"yidun_inference__img\" draggable=\"false\">             <span class=\"yidun_inference__border\"></span>           </div>                                          <div class=\"yidun_inference yidun_inference--4\" draggable=\"true\">             <img class=\"yidun_inference__img\" draggable=\"false\">             <span class=\"yidun_inference__border\"></span>           </div>                                          <div class=\"yidun_inference yidun_inference--5\" draggable=\"true\">             <img class=\"yidun_inference__img\" draggable=\"false\">             <span class=\"yidun_inference__border\"></span>           </div>                                          <div class=\"yidun_inference yidun_inference--6\" draggable=\"true\">             <img class=\"yidun_inference__img\" draggable=\"false\">             <span class=\"yidun_inference__border\"></span>           </div>                                          <div class=\"yidun_inference yidun_inference--7\" draggable=\"true\">             <img class=\"yidun_inference__img\" draggable=\"false\">             <span class=\"yidun_inference__border\"></span>           </div>                             <div class=\"yidun_voice\">           <div class=\"yidun_voice__inner\">             <audio class=\"yidun_voice__source\" src=\"\"></audio>             <button type=\"button\" class=\"yidun_voice__play\"><span class=\"yidun_voice__txt\">播放语音验证码</span></button>             <input class=\"yidun_voice__input\" aria-label=\"输入听到的验证码\" placeholder=\"输入听到的验证码\" maxlength=\"10\">             <div class=\"yidun_voice_btns\">               <button type=\"button\" class=\"yidun_voice__refresh\"><span class=\"yidun_voice__txt\">刷新验证码</span></button>               <button type=\"button\" class=\"yidun_voice__back\"><span class=\"yidun_voice__txt\">返回</span></button>             </div>           </div>         </div>       <div class=\"yidun_icon-point yidun_point-1\" style=\"left: 167.5px; top: 85px;\"></div><div class=\"yidun_icon-point yidun_point-2\" style=\"left: 9.5px; top: 78px;\"></div><div class=\"yidun_icon-point yidun_point-3\" style=\"left: 105.5px; top: 99px;\"></div></div>       <div class=\"yidun_loadbox\" style=\"border-radius: 2px\">         <div class=\"yidun_loadbox__inner\">           <div class=\"yidun_loadicon\"></div>           <span class=\"yidun_loadtext\">加载中...</span>         </div>       </div>       <div class=\"yidun_top\">                  <a class=\"yidun_feedback\" href=\"http://support.dun.163.com/feedback/captcha?captchaId=1a623022803d4cbc86fa157ec267bb36&amp;token=3f63b404b3cf4073923087cf8995ba65\" target=\"_blank\" tabindex=\"0\"><span class=\"yidun_feedback_txt\">提交使用问题反馈</span></a>                  <div class=\"yidun_top__right\">           <button type=\"button\" class=\"yidun_refresh\">刷新验证码</button>                    </div>       </div>     </div>   </div>   <div class=\"yidun_control\" style=\"height: 40px; border-radius: 2px\" tabindex=\"0\" role=\"button\">     <div class=\"yidun_slide_indicator\" style=\"height: 40px; border-radius: 2px\"></div>     <div class=\"yidun_slider\" style=\"width: 40px; border-radius: 2px\">       <!-- 分支二兼容旧接口 -->              <span class=\"yidun_slider__icon\"></span>            </div>     <div class=\"yidun_tips\" style=\"line-height: 40px\" aria-live=\"polite\">       <span class=\"yidun_tips__icon\"></span>       <span class=\"yidun_tips__text yidun-fallback__tip\">验证成功</span>       <div class=\"yidun_tips__answer hide\">         <span class=\"yidun_tips__point\"> \"栏\" \"鞋\" \"朱\"</span>         <img class=\"yidun_tips__img\">       </div>     </div>   </div> </div> <input type=\"hidden\" name=\"NECaptchaValidate\" value=\"CN31_IBz4zI7fgo4XCx.yREY6WzFnBCf7_GFwCeK6_5nIgEDt7bFz2UH7Qpt2z.MxQP_Rziv7vSZb1kC-QJT2piGF2YF599rn5SuygPoKdVAVz1H0_BaE4v0.8.7H_rtaC0qRYchLzK-lxzDQdKAHR2I1oHAwn04JBvy.CTobZKLqTXta-hjaC8QW_qazKrZaAlqVRXxyNyOypsZEC.vRmdUhNPrbOm2ITpekWdwpZsLNhVjjVfzJljmJE4rF7IoRMdIw9ODLIC8cERCRCiTvOK.8-bcEl0vClInyAn2JQjINHeJXD1TENnOo8UHe0eUrD0XOme4RIMmRvFyes6UYYCJNBeak.sz7auF1_pfOogKE9NQCgynAGASb-0sS4kZBct1IDl.6Z1UkS5k7oOR7X0zuscohxQhAUybYIkYY96JN._rFXcM6r722vEI5CgtEsL.NwUiGDNi2o04B80ntFtQ8PPXc2VjjqA.F8Lhi7L9_YqwDo4H.QPlYiDswEyz3\" class=\"yidun_input\">')");
      js.executeScript("nevalidate='"+nevalidate+"'");
      
    } /*
       * catch (ClientProtocolException e) { e.printStackTrace(); } catch (IOException e) {
       * e.printStackTrace(); }
       */finally {
      
    }
	
		
	}
}
