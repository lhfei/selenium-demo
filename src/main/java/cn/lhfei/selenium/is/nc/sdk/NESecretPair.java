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

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @created Jul 05, 2021
 */
public class NESecretPair {
    public final String secretId;
    public final String secretKey;

    /**
     * 构造函数
     * @param secretId 密钥对id
     * @param secretKey 密钥对key
     */
    public NESecretPair(String secretId, String secretKey) {
        this.secretId = secretId;
        this.secretKey = secretKey;
    }
}
