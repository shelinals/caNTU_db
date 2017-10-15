package com.root.cz3002.cantu;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by brigi on 15/10/2017.
 */

public class OrderListPayDabaoAdapter extends ArrayAdapter<OrderListPayDabaoData> {
    private static final String LOG_TAG = OrderListPayDabaoAdapter.class.getSimpleName();

    public OrderListPayDabaoAdapter(Activity context, ArrayList<OrderListPayDabaoData> orderDabaoRequests){
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, orderDabaoRequests);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.order_list_pay_data_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        OrderListPayDabaoData currentOrderDabaoRequest = getItem(position);

        // Find the TextView in the order_list_pay_data_item.xml layout with the ID canteen_name
        TextView tv0 = (TextView) listItemView.findViewById(R.id.food_name);
        tv0.setText(currentOrderDabaoRequest.getFoodName());

        // Find the TextView in the order_list_pay_data_item.xml layout with the ID canteen_name
        TextView tv1 = (TextView) listItemView.findViewById(R.id.canteen_name);
        tv1.setText(currentOrderDabaoRequest.getCanteenName());

        // Find the TextView in the order_list_pay_data_item.xml layout with the ID stall_name
        TextView tv2 = (TextView) listItemView.findViewById(R.id.stall_name);
        tv2.setText(currentOrderDabaoRequest.getStallName());

        // Find the TextView in the order_list_pay_data_item.xml layout with the ID place_deliver
        TextView tv3 = (TextView) listItemView.findViewById(R.id.timestamp);
        tv3.setText(currentOrderDabaoRequest.getTimestamp());

        Button b1 = (Button) listItemView.findViewById(R.id.cancel);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:delete from database and refresh page.
            }
        });
        //b1.setOnClickListener(); implement to send data to database.

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
