package org.wlgzs.recruit.util;

public class CheckImage {

    public static boolean verifyImages(String[] fileNames){
        String reg="(?i).+?\\.(jpg|gif|bmp|png|jpeg)";
        for (String fileName : fileNames) {
            if(fileName==null) return true;
            if (!fileName.matches(reg)) {
                return false;
            }
        }
        return true;
    }
}
