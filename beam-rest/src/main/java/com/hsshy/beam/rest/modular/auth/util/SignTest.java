package com.hsshy.beam.rest.modular.auth.util;

import com.hsshy.beam.rest.modular.auth.security.impl.Base64SecurityAction;
import com.hsshy.beam.common.utils.MD5Util;

public class SignTest {

    public static void main(String[] args) {
        Base64SecurityAction base64SecurityAction = new Base64SecurityAction();

        String a = "{\n" +
                "\n" +
                "  \"id\": 0\n" +
                "\n" +
                "}";

        String o = base64SecurityAction.doAction(a);
        System.out.println(o);
        String encrypt =  MD5Util.encrypt(o + "evsae7");
        System.out.println(encrypt);
    }


}
