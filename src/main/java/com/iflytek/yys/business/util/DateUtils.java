package com.iflytek.yys.business.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Description:
 *
 * @author createdBy huizhang43.
 * @date createdAt 2022/5/5 15:36
 **/
public class DateUtils {

    /**
     * 判断一个月份有多少天
     *
     * @param year
     * @param month
     * @return
     */
    public static int daysOfMonth(int year, int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                return isLeapYear(year) ? 29 : 28;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return 0;
        }
    }

    /**
     * 获取两个日期之间的跨度日期，包含起始日期和终止日期
     *
     * @param startTime 起始日期
     * @param endTime 终止日期
     * @param suffix 附加后缀
     * @return
     */
    public static List<String> getDays(String startTime, String endTime, String suffix) {

        // 返回的日期集合
        List<String> days = new ArrayList<String>();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat inDateFormat = new SimpleDateFormat("yyyyMMdd");

        try {
            Date start = dateFormat.parse(startTime);
            Date end = dateFormat.parse(endTime);

            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);

            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            // 日期加1(包含结束)
            tempEnd.add(Calendar.DATE, +1);
            if (StrUtil.isEmpty(suffix)) {
                while (tempStart.before(tempEnd)) {
                    days.add(dateFormat.format(tempStart.getTime()));
                    tempStart.add(Calendar.DAY_OF_YEAR, 1);
                }
            } else {
                while (tempStart.before(tempEnd)) {
                    days.add(inDateFormat.format(tempStart.getTime()) + suffix);
                    tempStart.add(Calendar.DAY_OF_YEAR, 1);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    /**
     * 判断年份是否是 闰年
     *
     * @param year
     * @return
     */
    private static boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            return true;
        }
        return false;
    }

    /**
     * 是否本月
     *
     * @param year
     * @param month
     * @return
     */
    public static boolean isCurrentMonth(int year, int month) {
        Date now = new Date();
        return DateUtil.year(now) == year && (DateUtil.month(now) + 1) % 12 == month;
    }

    /**
     * 根据生日 算年龄
     *
     * @param birth
     * @return
     */
    public static String getAge(Date birth) {
        if (birth == null) {
            return "";
        }
        Calendar nowCal = Calendar.getInstance();
        Calendar birthCal = Calendar.getInstance();
        birthCal.setTime(birth);
        Date now = nowCal.getTime();
        long diff = now.getTime() - birth.getTime();
        int diffYear = nowCal.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR);
        int diffMonth = nowCal.get(Calendar.MONTH)
                - birthCal.get(Calendar.MONTH);
        int diffDay = nowCal.get(Calendar.DAY_OF_MONTH)
                - birthCal.get(Calendar.DAY_OF_MONTH);
        // 大于等于5岁
        if (diffYear >= 6) {
            if (diffMonth < 0)
                return (diffYear - 1) + "岁";
            else if (diffDay >= 0 || diffMonth > 0) {
                return diffYear + "岁";
            } else {
                return (diffYear - 1) + "岁";
            }
        }
        // 5岁
        if (diffYear == 5 && (diffMonth > 0 || (diffMonth == 0 && diffDay >= 0))) {
            return "5岁";
        }
        // 小于5岁大于1岁
        if (diffYear >= 2) {
            if (diffDay >= 0 && diffMonth > 0) {
                return diffYear + "岁" + diffMonth + "个月";
            } else if (diffDay < 0 && diffMonth >= 2) {
                return diffYear + "岁" + (diffMonth - 1) + "个月";
            } else if (diffDay < 0 && diffMonth == 1) {
                return diffYear + "岁整";
            } else if (diffDay >= 0 && diffMonth == 0) {
                return diffYear + "岁整";
            } else if (diffDay < 0 && diffMonth == 0) {
                return (diffYear - 1) + "岁11个月";
            } else if (diffDay >= 0 && diffMonth < 0) {
                return (diffYear - 1) + "岁"
                        + (diffMonth + 12) + "个月";
            } else if (diffDay < 0 && diffMonth == -11) {
                return (diffYear - 1) + "岁整";
            } else if (diffDay < 0 && diffMonth < 0) {
                return (diffYear - 1) + "岁"
                        + (diffMonth + 12 - 1) + "个月";
            }
        }
        // 1岁多,或者小于1岁但是跨年
        if (diffYear == 1) {
            if (diffMonth > 0) {
                if (diffDay >= 0) {
                    return "1岁" + diffMonth + "个月";
                } else if (diffMonth >= 2) {
                    return "1岁" + (diffMonth - 1) + "个月";
                } else {
                    return "1岁整";
                }
            } else if (diffMonth == 0) {
                if (diffDay >= 0) {
                    return "1岁整";
                } else if (diffDay < 0) {
                    int resDay = birthCal
                            .getActualMaximum(Calendar.DAY_OF_MONTH)
                            - birthCal.get(Calendar.DAY_OF_MONTH);
                    return "11个月"
                            + (resDay
                            + nowCal.get(Calendar.DAY_OF_MONTH)) + "天";
                }
            } else if (diffMonth < 0) {
                // 1月与上年12月
                if (diffMonth == -11) {
                    return oneMonthNearby(diffDay, diff);
                } else {
                    if (diffDay > 0) {
                        return (diffMonth + 12) + "个月" + diffDay
                                + "天";
                    } else if (diffDay == 0) {
                        return (diffMonth + 12) + "个月整";
                    } else {
                        int resDay = birthCal
                                .getActualMaximum(Calendar.DAY_OF_MONTH)
                                - birthCal.get(Calendar.DAY_OF_MONTH);
                        return (diffMonth + 12 - 1)
                                + "个月"
                                + (resDay
                                + nowCal.get(Calendar.DAY_OF_MONTH))
                                + "天";
                    }
                }
            }
        }
        // 出生于今年，小于一岁
        if (diffMonth >= 2) {
            if (diffDay > 0) {
                return diffMonth + "个月" + diffDay + "天";
            } else if (diffDay == 0) {
                return diffMonth + "个月整";
            } else {
                int resDay = birthCal.getActualMaximum(Calendar.DAY_OF_MONTH)
                        - birthCal.get(Calendar.DAY_OF_MONTH);
                return (diffMonth - 1)
                        + "个月"
                        + (resDay
                        + nowCal.get(Calendar.DAY_OF_MONTH)) + "天";
            }
        } else if (diffMonth == 1) {
            return oneMonthNearby(diffDay, diff);
        } else if (diffMonth == 0) {
            long day = diff / (24L * 60 * 60 * 1000);
            if (day >= 3) {
                return day + "天";
            } else {
                return diff / (60L * 60 * 1000) + "小时";
            }
        }
        return diffYear + "岁";
    }

    private static String oneMonthNearby(int diffDay, long diff) {
        if (diffDay > 0) {
            return "1个月" + diffDay + "天";
        } else if (diffDay == 0) {
            return "1个月整";
        } else {
            long day = diff / (24L * 60 * 60 * 1000);
            if (day >= 3) {
                return day + "天";
            } else {
                return diff / (60L * 60 * 1000) + "小时";
            }
        }
    }
}
