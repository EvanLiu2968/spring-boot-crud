package com.crud.cloud.evanliu2968.util;

import com.alibaba.fastjson.JSONObject;
import com.crud.cloud.evanliu2968.constant.admin.AdminTokenEnum;
import com.crud.cloud.evanliu2968.dto.response.admin.AdminGroupRes;
import com.crud.cloud.evanliu2968.po.admin.AdminGroupRefPO;
import com.crud.cloud.evanliu2968.po.admin.AdminMainPO;
import com.crud.cloud.evanliu2968.service.admin.AdminGroupService;
import com.crud.cloud.evanliu2968.util.session.ServiceHeader;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/** 获取当前用户信息工具类
 * @author xinhua.zhou
 * @date
 */
@Component
public class UserCommonUtil {

    private static AdminGroupService adminGroupService;
    @Autowired
    public void setAdminGroupService(AdminGroupService adminGroupService) {
        UserCommonUtil.adminGroupService = adminGroupService;
    }

    /**
     * 根据token获取用户id
     * @return 返回用户id
     */
    public static Integer getUserId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader(AdminTokenEnum.ACCESS_TOKEN.getValue());
        Integer userId = 0;
        if (!StringUtils.isEmpty(token)) {
            String serviceHeaderString = (String)request.getSession().getAttribute(AdminTokenEnum.TOKEN_KEY.getValue()+token);
            JSONObject jsStr = JSONObject.parseObject(serviceHeaderString);
            ServiceHeader serviceHeader = JSONObject.toJavaObject(jsStr,ServiceHeader.class);
            if (serviceHeader != null) {
                userId=serviceHeader.getAdminId();
            }
        }
        return userId;
    }

    /**
     * 根据token获取当前用户分组id
     * @return 返回分组id
     */
    public static Integer getGroupId() {
        Integer userId = getUserId();
        Integer groupId=null ;
        AdminGroupRefPO tAdminGroupRef = new AdminGroupRefPO();
        tAdminGroupRef.setAdminId(userId);
        List<AdminGroupRes> dAdminGroupResponses = adminGroupService.listGroupByAdminId(tAdminGroupRef);
        if (!StringUtils.isEmpty(dAdminGroupResponses)) {
            groupId=dAdminGroupResponses.get(0).getId();

        }
        return groupId;
    }

    /**
     * 根据token获取当前用户
     * @return 返回当前用户
     */
    public static AdminMainPO getCurrentUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader(AdminTokenEnum.ACCESS_TOKEN.getValue());
        AdminMainPO tAdminMain=new  AdminMainPO();
        ServiceHeader serviceHeader;
        if (!StringUtils.isEmpty(token)) {
            String serviceHeaderString = (String)request.getSession().getAttribute(AdminTokenEnum.TOKEN_KEY.getValue()+token);
            JSONObject jsStr = JSONObject.parseObject(serviceHeaderString);
            serviceHeader = JSONObject.toJavaObject(jsStr,ServiceHeader.class);
            BeanUtils.copyProperties(serviceHeader,tAdminMain);
        }
        return tAdminMain;
    }
}