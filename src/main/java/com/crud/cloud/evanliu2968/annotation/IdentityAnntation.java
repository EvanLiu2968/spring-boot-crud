package com.crud.cloud.evanliu2968.annotation;

import com.crud.cloud.evanliu2968.annotation.impl.IdentityAnntationImpl;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 类描述。
 *
 * @author jinpeng.bu
 * @version v1.0.0
 * @since 2019/4/23 17:07
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Repeatable(IdentityAnntation.List.class)
@Documented
@Constraint(validatedBy = IdentityAnntationImpl.class)
public @interface IdentityAnntation {

    String message() default "身份证号码不正确";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    /**
     * 列表时判断
     */
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        IdentityAnntation[] value();
    }
}
