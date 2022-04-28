package com.example.commonui;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: hepan
 * @date: 2022/4/11
 * @desc: 工厂
 */
public class CardFactory {
    protected static Map<String, Class<? extends View>> mSubViewClassMap = new HashMap<>();


    /**
     * @description 注册 SubView
     */
    public static void register(String type, Class<? extends View> clazz) {
        if (!mSubViewClassMap.containsKey(type)) {
            mSubViewClassMap.put(type, clazz);
        }
    }

    /**
     * @return subview or null
     * @description 获取基金卡片
     */
    public static View getFundSubView(Context ctx, String type) {
        if (ctx == null || type == null || TextUtils.isEmpty(type)) {
            return null;
        }
        Class<? extends View> clazz = mSubViewClassMap.get(type);
        if (clazz == null) {
            return null;
        }
        try {
            Constructor<? extends View> constructor = clazz.getConstructor(Context.class);
            return constructor.newInstance(ctx);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
