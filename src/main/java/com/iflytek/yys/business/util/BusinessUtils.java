package com.iflytek.yys.business.util;

import cn.hutool.core.util.StrUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Description:
 * 业务上的工具方法
 *
 * @author createdBy huizhang43.
 * @date createdAt 2022/5/4 14:15
 **/
public class BusinessUtils {

    /**
     * 去除 content 中的 h5标签
     * @param content
     * @param charsetName
     * @return
     */
    public static String tranferDoc(String content, String charsetName) {
        try {
            StringBuilder sb = new StringBuilder();
            Document document = Jsoup.parse(content, charsetName);
            sb.append(document.body().text()).append(" ");
            Elements input = document.select("input");
            for (Element element : input) {
                sb.append(element.attr("value")).append(" ");
            }
            return sb.toString().replaceAll("[\\u00A0\\u3000]+", " ");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


}
