package edu.uta.cse.serveme.aop;

import com.github.pagehelper.PageHelper;
import edu.uta.cse.serveme.constant.Constant;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author housirvip
 */
@Aspect
@Component
public class PageHelperAop {

    @Before("execution(public com.github.pagehelper.Page edu.uta.cse.serveme.service.*Service.page*(..))")
    public void doBefore(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();
        if (args != null
                && args.length > 1
                && args[1] instanceof Integer
                && args[2] instanceof Integer) {

            int pageNum = Optional.of((int) args[1]).orElse(Constant.PAGE_NUM_VALUE);
            int pageSize = Optional.of((int) args[2]).orElse(Constant.PAGE_SIZE_VALUE);

            PageHelper.startPage(pageNum, pageSize);
        }
    }
}
