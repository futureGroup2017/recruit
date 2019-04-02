package org.wlgzs.recruit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * </p>
 * 描述：
 * </p>
 *
 * @author AlgerFan
 * @since 2018/9/21
 */
public class test {
    public static void main(String[] args) {
        String str1 = "行为举止,言谈举止,个人素养,综合评价-7,8,4,还可以,嗯嗯,好吧,可以";//行为举止,言谈举止,个人素养,综合评价-7,8,4,还可以
        String strOne = str1;
        strOne = strOne.substring(0, strOne.indexOf("-"));
        String[] scoreItemsOne = strOne.split(",");

        str1 = str1.substring(str1.indexOf("-") + 1, str1.length());
        String[] scores1 = str1.split(",");
        /*for (String aScores1 : scores1) {
            System.out.println(aScores1);
        }*/
        List<String> list = new ArrayList<>(Arrays.asList(scores1));
        if (list.size() > scoreItemsOne.length) {
            List<String> stringList = new ArrayList<>();
            for (int i = list.size(); i >= scoreItemsOne.length; i--) {
                stringList.add(list.get(list.size()-1));
                list.remove(list.size()-1);
            }
            StringBuilder note = new StringBuilder();
            note.append(stringList.get(stringList.size()-1));
            for (int i = stringList.size()-2; i >= 0; i--) {
                note.append("，").append(stringList.get(i));
            }
            list.add(String.valueOf(note));
        }
        /*for (String aList : list) {
            System.out.println(aList);
        }*/

        /*String string = scores1[scores1.length-1];
        System.out.println(string);
        string = string.replaceAll("[,]", "，");
        System.out.println(string);*/

        /*float score = 0;
        for (int j = 0; j < scores1.length-1; j++) {
            score += Float.parseFloat(scores1[j]);
        }
        score = score/(scores1.length-1)*10;
        List<String> score1 = new ArrayList<>();
        score1.add("樊世杰");
        score1.addAll(Arrays.asList(scores1));
        score1.add((float)(Math.round(score*100))/100+"");
        for (int i = 0; i < score1.size(); i++) {
            System.out.println(score1.get(i));
        }*/
    }
}
