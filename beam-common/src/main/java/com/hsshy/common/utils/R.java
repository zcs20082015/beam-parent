/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.hsshy.common.utils;


import com.hsshy.common.enumeration.RetEnum;

import java.util.HashMap;

/**
 * @description: 封装返回结果类
 * @author: hs
 * @create: 2018-09-21 22:42:04
 **/
public class R<T> extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public R() {
		put("code", RetEnum.SUCCESS.getRet());
		put("msg", RetEnum.SUCCESS.getMsg());
		put("data", null);
		put("error",false);
	}
	
	public static R fail() {
		return fail(RetEnum.SERVER_EXCEPTION.getRet(), RetEnum.SERVER_EXCEPTION.getMsg());
	}
	
	public static R fail(String msg) {
		return fail(RetEnum.SERVER_EXCEPTION.getRet(), msg);
	}
	
	public static R fail(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		r.put("error",true);

		return r;
	}



	public static R ok() {

		return new R();
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		r.put("code",RetEnum.SUCCESS.getRet());
		r.put("data",null);
		r.put("error",false);

		return r;
	}

	public static R ok(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		r.put("data", null);
		r.put("error",false);

		return r;
	}

	public static <T> R<T> ok(T data) {
		R r = new R();
		r.put("msg", RetEnum.SUCCESS.getRet());
		r.put("code",RetEnum.SUCCESS.getRet());
		r.put("data",data);
		r.put("error",false);
		return r;
	}


	public static <T> R<T> ok(int code,String msg,T data) {
		R r = new R();
		r.put("code",code);
		r.put("msg",msg);
		r.put("data",data);
		r.put("error",false);
		return r;
	}


	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
