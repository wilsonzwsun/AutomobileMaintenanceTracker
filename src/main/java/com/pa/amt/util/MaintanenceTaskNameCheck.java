package com.pa.amt.util;

import com.pa.amt.enums.MaintanenceEnum;

public class MaintanenceTaskNameCheck {
    public static  MaintanenceEnum checkName(String text) {
        System.out.println(text);
        for (MaintanenceEnum b : MaintanenceEnum.values()) {
            if (b.getTask().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return Enum.valueOf(MaintanenceEnum.class, text.trim().toLowerCase());
    }

//
//    public static <T extends Enum<T>> T getEnumFromString(Class<T> c, String string) throws IllegalArgumentException {
//        if( c != null && string != null ) {
//            try {
//                return Enum.valueOf(c, string.trim().toLowerCase());
//            } catch(IllegalArgumentException ex) {
//                throw ex;
//            }
//        }
//        return null;
//    }
}
