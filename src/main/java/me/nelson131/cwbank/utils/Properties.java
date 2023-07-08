package me.nelson131.cwbank.utils;

import static me.nelson131.cwbank.CWBank.config;

public class Properties {

    public static String getCFG(String key){
        return config.get(key).toString();
    }
}

