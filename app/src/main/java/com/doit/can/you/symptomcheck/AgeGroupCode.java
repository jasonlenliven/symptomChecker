package com.doit.can.you.symptomcheck;

import java.util.HashMap;

/**
 * Created by jason.le on 7/16/15.
 */
public class AgeGroupCode {

    static HashMap<String, String> map = new HashMap();
    static {
        init();
    }
    public static void init() {
        map.put("newborn", "d0");
        map.put("1-10", "d1");
        map.put("11-20", "d2");
        map.put("21-30", "d3");
        map.put("31-40", "d4");
        map.put("41-50", "d5");
        map.put("51-60", "d6");
        map.put("61-70", "d7");
        map.put("71-80", "d8");
        map.put("81-90", "d9");
        map.put("91-100", "d10");
        map.put("101-110", "d11");
        map.put("111-120", "d12");
    }

    public static String getCode(String param) {
        return map.get(param);
    }
}
