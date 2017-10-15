package com.root.cz3002.cantu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by brigi on 15/10/2017.
 */

public class OrderListPayDabaoActivity extends AppCompatActivity { //user order dabao list
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_from_button);

        ArrayList<OrderListPayDabaoData> orderDabaoRequests = new ArrayList<OrderListPayDabaoData>();
        orderDabaoRequests.add(new OrderListPayDabaoData("Pasta","Italian Pasta","CAN A","12 Sept 2017"));
        orderDabaoRequests.add(new OrderListPayDabaoData("Pasta","Italian Pasta","CAN A","4 Dec 2017"));
        orderDabaoRequests.add(new OrderListPayDabaoData("Pasta","Italian Pasta","CAN A","9 March 2017"));
        orderDabaoRequests.add(new OrderListPayDabaoData("Pasta","Italian Pasta","CAN A","18 Feb 2017"));
        orderDabaoRequests.add(new OrderListPayDabaoData("Pasta","Italian Pasta","CAN A","13 Oct 2017"));
        orderDabaoRequests.add(new OrderListPayDabaoData("Pasta","Italian Pasta","CAN A","30 Dec 2017"));
        orderDabaoRequests.add(new OrderListPayDabaoData("Pasta","Italian Pasta","CAN A","1 Sept 2017"));

        OrderListPayDabaoAdapter orderListPayDabaoAdapter = new OrderListPayDabaoAdapter(this, orderDabaoRequests);

        ListView listView = (ListView) findViewById(R.id.listview_from_button);
        listView.setAdapter(orderListPayDabaoAdapter);
    }
}
