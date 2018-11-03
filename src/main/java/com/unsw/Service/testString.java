package com.unsw.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class testString {
//    String keyWords = "[I like you hate you]";

    public static void printkeyWords(String keyWords){
        List<String> bullyingList = new ArrayList();
        bullyingList.add("hate");
        bullyingList.add("kill");
        bullyingList.add("die");
        System.out.println("keyWords="+keyWords);
        Pattern pattern = Pattern.compile("[,].");
        System.out.println(keyWords.contains("["));

        keyWords = keyWords.substring(1,keyWords.length()-1).toLowerCase();
        System.out.println("keyWords2="+keyWords);
        String[] tmplist = keyWords.split(" ");
        List<String> keyWordsList = java.util.Arrays.asList(tmplist);
        for (String str: keyWordsList){
//            System.out.println("str="+str);
            if(bullyingList.contains(str)){
                System.out.println("B str="+str);
            }
        }



    }

    public static void main(String[] args) {
        String keyWords = "[I like you. Hate you]";

        printkeyWords(keyWords);
    }
}
