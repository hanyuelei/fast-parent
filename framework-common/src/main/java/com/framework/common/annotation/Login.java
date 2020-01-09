
package com.framework.common.annotation;

import java.lang.annotation.*;

/**
 *  登录效验
 * Created by hanyl on 2019年12月17日.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
}
