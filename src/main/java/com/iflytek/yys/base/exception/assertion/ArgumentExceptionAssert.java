package com.iflytek.yys.base.exception.assertion;

import cn.hutool.core.util.ArrayUtil;
import com.iflytek.yys.base.exception.ArgumentException;
import com.iflytek.yys.base.exception.BaseException;
import com.iflytek.yys.base.exception.assertion.common.Assert;
import com.iflytek.yys.base.exception.assertion.common.IResponseEnum;

import java.text.MessageFormat;

/**
 * <pre>
 *
 * </pre>
 *
 * @author sprainkle
 * @date 2019/5/2
 */
public interface ArgumentExceptionAssert extends IResponseEnum, Assert {

    @Override
    default BaseException newException(Object... args) {
        String msg = this.getMessage();
        if (ArrayUtil.isNotEmpty(args)) {
            msg = MessageFormat.format(this.getMessage(), args);
        }

        return new ArgumentException(this, args, msg);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = this.getMessage();
        if (ArrayUtil.isNotEmpty(args)) {
            msg = MessageFormat.format(this.getMessage(), args);
        }

        return new ArgumentException(this, args, msg, t);
    }

}
