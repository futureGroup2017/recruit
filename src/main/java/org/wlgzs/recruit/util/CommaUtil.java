package org.wlgzs.recruit.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * </p>
 * 描述：
 * </p>
 *
 * @author AlgerFan
 * @since 2018/9/22
 */
public class CommaUtil {
    public List<String> comma(String[] scores1,String[] scoreItems){
        List<String> list = new ArrayList<>(Arrays.asList(scores1));
        if (list.size() > scoreItems.length) {
            List<String> stringList = new ArrayList<>();
            for (int j = list.size(); j >= scoreItems.length; j--) {
                stringList.add(list.get(list.size()-1));
                list.remove(list.size()-1);
            }
            StringBuilder note = new StringBuilder();
            note.append(stringList.get(stringList.size()-1));
            for (int j = stringList.size()-2; j >= 0; j--) {
                note.append("，").append(stringList.get(j));
            }
            list.add(String.valueOf(note));
            System.out.println("使用了英文逗号");
        }
        return list;
    }
}
