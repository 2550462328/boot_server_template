package com.iflytek.yys.business.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Description:
 *
 * @author createdBy huizhang43.
 * @date createdAt 2022/11/21 16:07
 **/
public class CollectionUtils {

    /**
     * @param <T>
     * @param list
     *         要排序的集合
     * @param sortField
     *         排序字段
     * @param sortRule
     *         排序规则：(asc or desc)
     */
    public static <T> void sort(List<T> list, final String sortField, final String sortRule) {

        Collections.sort(list, new Comparator<T>() {

            @Override
            public int compare(T o1, T o2) {
                // 通过反射获取方法名称，如getId
                String methodName = "get" + sortField.substring(0, 1).toUpperCase() + sortField.substring(1);
                try {
                    Method method1 = o1.getClass().getMethod(methodName);
                    Method method2 = o2.getClass().getMethod(methodName);
                    if ("desc".equals(sortRule)) {
                        return method2.invoke(o2).toString().compareTo(method1.invoke(o1).toString());
                    } else {
                        return method1.invoke(o1).toString().compareTo(method2.invoke(o2).toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
    }

    /**
     * 将 list 分成 qty 份
     * @param list
     * @param qty
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> groupListByQty(List<T> list, int qty) {
        if (list == null || list.size() == 0 || qty <= 0) {
            return Collections.EMPTY_LIST;
        }
        List<List<T>> result = new ArrayList<>();
        int count = 0;
        while (count < list.size()) {
            result.add(new ArrayList<>(list.subList(count, Math.min((count + qty), list.size()))));
            count += qty;
        }
        return result;
    }
}
