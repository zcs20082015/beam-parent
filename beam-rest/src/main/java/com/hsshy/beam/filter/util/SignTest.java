package com.hsshy.beam.filter.util;

import com.hsshy.beam.filter.security.impl.Base64SecurityAction;
import com.hsshy.beam.common.utils.MD5Util;

public class SignTest {

    public static void main(String[] args) {
        Base64SecurityAction base64SecurityAction = new Base64SecurityAction();

        String a = "{\n" +
                "\n" +
                "  \"id\": 0\n" +
                "\n" +
                "}";
        String b = "{wordName: \"哎呀妈呀脑瓜疼脑瓜疼脑瓜疼\", wordId: null, totalMoney: \"1\", totalCount: \"1\", serviceMoney: \"0.02\",orderNo:null}";

        String o = base64SecurityAction.doAction(a);
        System.out.println(o);
        String encrypt =  MD5Util.encrypt(o + "evsae7");
        System.out.println(encrypt);
    }


}
