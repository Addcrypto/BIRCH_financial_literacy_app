package com.example.birch;

import android.content.Context;
import android.content.SharedPreferences;

public class SP_LocalStorage {
    public SharedPreferences sp;

    public SP_LocalStorage(Context ctx) {
        this.sp = ctx.getSharedPreferences("sharedPrefs", ctx.MODE_PRIVATE);
    }

    public SharedPreferences getSP() { return this.sp; }
    public SharedPreferences.Editor getEditor() { return this.sp.edit(); }

    // public int getLoggedInUserId() { return this.sp.getInt("userId", -1); }
    public boolean getIsLoggedIn() { return this.sp.getBoolean("isLoggedIn", false); }
    public String getCurrentUserEmail() { return this.sp.getString("userEmail", ""); }
    public Boolean getIsEditingBill() { return this.sp.getBoolean("isEditingBill", false); }
    public String getCurrentBillId() { return this.sp.getString("currentBillId", ""); }
    public Boolean getIsLinked() { return this.sp.getBoolean("isLinked", false); }
    public String getAccessToken() { return this.sp.getString("accessToken", ""); }

    public void clearLocalStorage(SharedPreferences.Editor editor) {
       editor.putBoolean("isLoggedIn", false);
       editor.putString("userEmail", "");
       editor.putBoolean("isEditingBill", false);
       editor.putString("currentBillId", "");
       editor.putBoolean("isLinked", false);
       editor.putString("accessToken", "");

       editor.apply();
    }
}
