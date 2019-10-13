package com.crud.cloud.evanliu2968.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.crud.cloud.evanliu2968.annotation.NoLogin;
import com.crud.cloud.evanliu2968.constant.ResponseCodeEnum;
import com.crud.cloud.evanliu2968.constant.admin.AdminTokenEnum;
import com.crud.cloud.evanliu2968.dto.ResponseData;
import com.crud.cloud.evanliu2968.dto.response.admin.AdminPermissionsRes;
import com.crud.cloud.evanliu2968.service.admin.AdminPermissionService;
import com.crud.cloud.evanliu2968.util.ObjectUtil;
import com.crud.cloud.evanliu2968.util.StringUtil;
import com.crud.cloud.evanliu2968.util.UserCommonUtil;
import com.crud.cloud.evanliu2968.util.session.ServiceHeader;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 * 权限拦截器，这里两种拦截方式搭配使用。
 * 1、自定义不拦截注解（NoLogin，对应白名单）
 * 2、exclude不拦截的列表，在配置中过滤
 *
 * @author guohao.yang
 * @version v1.0.0
 * @since 2019/5/7 10:53
 */
@Component
@Log4j2
public class AuthorityInterceptor implements HandlerInterceptor {

    @Autowired
    private AdminPermissionService adminPermissionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.debug("====author====" + request.getRequestURI());

        ResponseData<Object> responseData = null;
        //1、有免登陆注解的请求，直接放行（对应请求白名单）
        HandlerMethod method = (HandlerMethod) handler;
        Class<?> controllerType = method.getBeanType();
        try {

            if (method.getMethodAnnotation(NoLogin.class) != null || controllerType.getAnnotation(NoLogin.class) != null) {
                return true;
            }
        } catch (Exception e) { //这里捕捉到异常，说明dispatcher servlet没有分发到对应的controller，即无效请求url
            responseData = ResponseData.fail(ResponseCodeEnum.ERROR_URL_INVALID);
            this.addJsonData(response, responseData);
            return false;
        }

        //2、其他请求，全部校验token
        String token = request.getHeader(AdminTokenEnum.ACCESS_TOKEN.getValue());
        //这里注意校验token有效性（过期token，非法token等）
        if (StringUtil.isEmpty(token)) {//无效token，直接返回false，不经过其他处理
            //返回错误信息，这里注意是否需要具体返回过期token，非法token等
            responseData = ResponseData.fail(ResponseCodeEnum.TOKEN_INVALID);
            this.addJsonData(response, responseData);
            return false;
        } else {
            String serviceHeaderString = (String)request.getSession().getAttribute(AdminTokenEnum.TOKEN_KEY.getValue()+token);
            JSONObject jsStr = JSONObject.parseObject(serviceHeaderString);
            ServiceHeader serviceHeader = JSONObject.toJavaObject(jsStr,ServiceHeader.class);
            if (ObjectUtil.isEmpty(serviceHeader)) {
                responseData = ResponseData.fail(ResponseCodeEnum.TOKEN_INVALID);
                this.addJsonData(response, responseData);
                return false;
            }

            return  true;

//            //如果token有效，继续校验用户是否有这个url权限
//            Integer userId = UserCommonUtil.getUserId();
//            String requestUri = request.getRequestURI();
//            requestUri = requestUri.replaceAll("\\/\\d+$", "");
//            List<AdminPermissionsRes> dAdminPermissionsResponses = this.adminPermissionService.selectPermissionsByAdminId(userId, requestUri);
//            if (ObjectUtil.isEmpty(dAdminPermissionsResponses)) {
//                responseData = ResponseData.fail(ResponseCodeEnum.PERMISSION_NOT);
//                this.addJsonData(response, responseData);
//                return false;
//            } else {
//                return true;
//            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * 在response中返回json数据。
     *
     * @param response HttpServletResponse
     * @param data     Object
     */
    private void addJsonData(HttpServletResponse response, Object data) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(JSONObject.toJSONString(data));
            out.flush();
            out.close();
        } catch (Exception e) {
            log.error("response json exception:", e);
        }
    }
}
