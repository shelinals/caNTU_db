package com.root.cz3002.cantu;

/**
 * Created by brigi on 15/10/2017.
 */

public class OrderListPayDabaoData {
    private String foodName;
    private String stallName;
    private String canteenName;
    private String timestamp;

    OrderListPayDabaoData(String foodName, String stallName, String canteenName, String timestamp){
        this.foodName = foodName;
        this.stallName = stallName;
        this.canteenName = canteenName;
        this.timestamp = timestamp;
    }

    public String getFoodName(){return foodName;}
    public String getStallName(){return stallName;}
    public String getCanteenName(){return canteenName;}
    public String getTimestamp(){return timestamp;}
}


