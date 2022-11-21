package com.iflytek.yys.base.util;

/**
 * Description:
 *
 * 过滤掉 参数值中的 sql关键词 防止sql注入攻击
 *
 * @author createdBy huizhang43.
 * @date createdAt 2021/8/5 16:26
 **/
public class SqlUtil {
    
    public static String filterSQLInjection(String s) {
        if (s == null || "".equals(s)) {
            return "";
        }
        try {
            s = s.trim().replaceAll("'", "").replaceAll(">", "");
            s = s.trim().replaceAll(" [o,O][r,R]", ""); //or
            s = s.trim().replaceAll("sleep\\(", "").replaceAll("limit ", "").replaceAll("LIMIT ", "");
            s = s.trim().replaceAll("[d,D][e,E][l,L][e,E][t,T][e,E] ", "");// delete
            s = s.trim().replaceAll("[s,S][e,E][l,L][e,E][c,C][t,T] ", "");// select;
            s = s.trim().replaceAll("[u,U][p,P][d,D][a,A][t,T][e,E] ", "");// update
            s = s.trim().replaceAll("[d,D][e,E][l,L][a,A][y,Y] ", "").replaceAll("waitfor ", "");
            s = s.trim().replaceAll("print\\(", "").replaceAll("md5\\(", "");
            s = s.trim().replaceAll("cookie\\(", "").replaceAll("send\\(", "");
            s = s.trim().replaceAll("response\\.", "").replaceAll("write\\(", "")
                    .replaceAll("&", "");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return s;
    }
}
