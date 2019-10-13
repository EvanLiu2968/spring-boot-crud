package com.crud.cloud.evanliu2968.util;

import lombok.extern.log4j.Log4j2;
import org.apache.sanselan.ImageInfo;
import org.apache.sanselan.Sanselan;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Sanselan图形库的使用工具
 *
 * @author jinpeng.bu
 */
@Log4j2
public class SanselanUtil {

    /**
     * 判断图片是不是300dpi以上
     *
     * @param file
     * @return
     */
    public static boolean isUp300Dpi(File file) {
        boolean result = false;
        try {
            ImageInfo imageInfo = Sanselan.getImageInfo(file);
            int dpi = imageInfo.getPhysicalHeightDpi();
            if (dpi >= 300) {
                result = true;
            }
        } catch (Exception e) {
            log.error(("判断图片是不是300dpi方法异常"), e);
        }
        return result;
    }

    /**
     * 判断图片是不是300dpi以上
     *
     * @param multipartFile
     * @return
     */
    public static boolean isUp300Dpi(MultipartFile multipartFile) {
        boolean result = false;
        try {
            ImageInfo imageInfo = Sanselan.getImageInfo(multipartFile.getInputStream(), multipartFile.getOriginalFilename());
            int dpi = imageInfo.getPhysicalHeightDpi();
            if (dpi >= 300) {
                result = true;
            }
        } catch (Exception e) {
            log.error(("判断图片是不是300dpi方法异常"), e);
        }
        return result;
    }
}
