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

package cn.lhfei.selenium.appium;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.appium.java_client.windows.WindowsDriver;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @created 9月 26, 2021
 */

public class TaxApp {
  private static WindowsDriver taxSession = null;
  private static WebElement CalculatorResult = null;
  private static final Logger LOG = LoggerFactory.getLogger(TaxApp.class);

  @BeforeAll
  public static void setup() {
    try {
      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setCapability("app", "D:\\wssb_sz\\EPEvenue.exe");
      taxSession = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
      taxSession.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

      CalculatorResult = taxSession.findElementByAccessibilityId("CalculatorResults");

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
    }
  }

  @AfterAll
  public static void TearDown() {
    CalculatorResult = null;
    if (taxSession != null) {
      taxSession.quit();
    }
    taxSession = null;
  }

  @Test
  public void launch() {
    LOG.info("///");
  }
  
  public static void main(String[] args) {

  }

}
