package com.example.scb.service;

import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class StringService {
    Pattern p = Pattern.compile("^[ A-Z]+$");

    public String decodeBase64String(String stringToDecode) {
        byte[] byteArray = org.apache.tomcat.util.codec.binary.Base64.decodeBase64(stringToDecode.getBytes());
        return new String(byteArray);
    }

    public Boolean isValidateString(String stringToValidate) {
        Matcher m = p.matcher(stringToValidate.trim());
        return m.matches();
    }

    public StringBuilder resultTheString(String stringToResult) {
        String decodedStrLower = stringToResult.toLowerCase();
        String[] arrOfStr = decodedStrLower.split("\\s+");
        String[] arrOfWhitespace = decodedStrLower.split("\\w+");

        StringBuilder returnStr = new StringBuilder();

        int iterator = 0;
        for (String a : arrOfStr) {
            if (arrOfStr.length - 1 > iterator) {
                if (arrOfWhitespace[0].length() > 0) {
                    returnStr.append(a).append(arrOfWhitespace[iterator].length());
                } else {
                    returnStr.append(a).append(arrOfWhitespace[iterator+1].length());
                }
                iterator++;
            } else {
                if (arrOfStr.length == arrOfWhitespace.length && arrOfWhitespace[0].length() > 0) {
                    returnStr.append(a).append(arrOfWhitespace[iterator].length());
                } else if (arrOfStr.length < arrOfWhitespace.length) {
                    if (arrOfWhitespace[iterator].length() > 0) {
                        returnStr.append(a).append(arrOfWhitespace[arrOfWhitespace.length-1].length());
                    }
                } else {
                    returnStr.append(a);
                }
            }
        }
        return returnStr;
    }

    public String reverseString(String stringToReverse) {
        StringBuilder returnStrReverse = new StringBuilder();
        returnStrReverse.append(stringToReverse);
        return String.valueOf(returnStrReverse.reverse());
    }

    public String encodeBase64String(String stringToEncode) {
        return Base64.getEncoder().encodeToString(stringToEncode.getBytes());
    }
}
