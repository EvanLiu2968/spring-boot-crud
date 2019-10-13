package com.crud.cloud.evanliu2968.util;


import com.crud.cloud.evanliu2968.util.session.ServiceHeader;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * Created by evanliu2968 on 2019-10-14.
 */
public class ColorUtil {

    public static String getColor() {
        String[] Color={"#FFB6C1","#FF1493","#D8BFD8","#7B68EE","#E6E6FA","#6495ED","#87CEFA","#7FFFAA","#F0E68C","#FFA500","#FA8072"};
        int index = (int) (Math.random() * Color.length);
        return Color[index];
    }

}
