package com.crud.cloud.evanliu2968.schedule;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.crud.cloud.evanliu2968.dto.response.admin.DeptRes;
import com.crud.cloud.evanliu2968.po.PositionInfoPO;
import com.crud.cloud.evanliu2968.service.DeptService;
import com.crud.cloud.evanliu2968.service.PositionInfoService;
import com.crud.cloud.evanliu2968.service.impl.PositionInfoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 旧数据升级相关的定时任务（替代用存储过程维护旧数据的升级逻辑）。
 * 旧数据的升级，涉及复杂业务的，不要使用存储过程维护。
 *
 * @author guohao.yang
 * @version v2.1
 * @since 2019/9/20 16:48
 */
@Component
public class DataUpgradeSchedule {
    private static Logger logger = LoggerFactory.getLogger(DataUpgradeSchedule.class);

    /**
     * v2.1 任职信息 升级标志。
     * 一次性执行。
     * 以后的版本，该标志可以直接置为true。
     */
    private static boolean positionInfoUpgraded = false;

    @Autowired
    PositionInfoService positionInfoService;

    @Autowired
    DeptService deptService;

    /**
     * 说明：
     * 旧的任职信息，只有一个deptId，现要改成5个id维护。
     * <p>
     * 1. 遍历  position_info
     * 2. 根据  position_info 中的  deptId，找到  四个  parentId     重新维护进position_info
     *
     * <p>
     * 说明：第一次延迟1秒后执行，不用任何repeat规则。
     */
    @Async
    @Scheduled(initialDelay = 1000, fixedRate = Long.MAX_VALUE)
    public void upgradePositionInfo() {
        if (positionInfoUpgraded) {
            return;
        }

        //找这个时间之前的数据，即为旧数据，需要升级
        String updateTime = "2019-10-10 00:00:00";

        EntityWrapper<PositionInfoPO> entityWrapper = new EntityWrapper<>();
        entityWrapper.le("update_time", updateTime);
        List<PositionInfoPO> positionInfoPOS = this.positionInfoService.selectList(entityWrapper);

        if (null != positionInfoPOS && positionInfoPOS.size() > 0) {
            Set<Integer> deptIdSet = positionInfoPOS.stream().map(PositionInfoPO::getDeptId).collect(Collectors.toSet());

            if (deptIdSet.size() > 0) {
                Map<Integer, Map<String, DeptRes>> deptMap = this.deptService.getMultiLevelParentPOByDeptId(new ArrayList<>(deptIdSet));

                positionInfoPOS.forEach(e -> {
                    if (null != e.getDeptId()) {
                        Map<String, DeptRes> deptDetailMap = deptMap.get(e.getDeptId());
                        PositionInfoServiceImpl.addMultiLevelDept(e, deptDetailMap);
                    }
                });

                this.positionInfoService.updateBatchById(positionInfoPOS);

            }

        }

        positionInfoUpgraded = true;
    }

}
