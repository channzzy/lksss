package com.example.chanzyhebat;

import android.content.Context;
import android.content.SharedPreferences;

public class TokenManager {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private final String PREF_NAME = ("token_pref");
    private final String KEY_TOKEN = ("access_token");

    public TokenManager(Context ctx){
        sp = ctx.getSharedPreferences(PREF_NAME,ctx.MODE_PRIVATE);
        editor = sp.edit();
    }
    public void saveToken(String token){
        editor.putString(KEY_TOKEN,"Bearer "+token);
    }
    public String getToken(){
        return  sp.getString(KEY_TOKEN,null);
    }
}
