/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Ratan
 */
public class FileReadCount {
    
    public static void main(String[] args) throws IOException {
        StringBuilder builder = new StringBuilder();
        FileReadCount frCount = new FileReadCount();
        try (FileReader fileRead = new FileReader("story.txt")) {
            int index;            
            while((index = fileRead.read()) != -1){
                builder.append((char)index);                
            }
            
            List<String> list = Arrays.asList(builder.toString().split("\n"));
            Map<String, Set<Integer>> map = new HashMap<>();            
            int lineNumber = 1;
            for(String sentence : list){                
                List<String> sentenceList = Arrays.asList(sentence.split(" "));
                for(String word : sentenceList){
                    word = word.trim();                    
                    word = frCount.checkWord(word);
                    Set<Integer> containedSet = map.get(word);
                    if(containedSet == null){
                        containedSet = new HashSet<>();
                    }
                    containedSet.add(lineNumber);
                    map.put(word, containedSet);
                
            }
                lineNumber++;
            }
            for(Map.Entry m:map.entrySet()){  
                
                    System.out.println(m.getKey()+" - "+m.getValue());  
                
                
            }
        }
    }
    
    public String checkWord(String word){ 
        char firstChar = word.charAt(0);
        char lastChar = word.charAt(word.length() - 1);
        if(checkSpecialChars(firstChar)){
            word = word.substring(1);
        }
        if(checkSpecialChars(lastChar)){
            word = word.substring(0, word.length() - 1);
        }      
        return word;
    }
    
    public Boolean checkSpecialChars(char ch){         
        Pattern p = Pattern.compile("[.,'\"]");
        Matcher m = p.matcher(String.valueOf(ch));
        return m.find();
    }
}