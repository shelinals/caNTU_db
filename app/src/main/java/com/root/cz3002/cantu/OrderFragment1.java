package com.root.cz3002.cantu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by brigi on 12/10/2017.
 */

public class OrderFragment1 extends Fragment {
    ToPayAdapter toPayAdapter;
    boolean checkpoint;

    public OrderFragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ArrayList<OrderPayData> orderPayRequests = new ArrayList<OrderPayData>();
        orderPayRequests.add(new OrderPayData(true, 2.80, "steamed chicken rice", "chicken rice","CAN A",2));
        orderPayRequests.add(new OrderPayData(true, 4.50, "fish bread crumb", "italian","CAN A",2));
        orderPayRequests.add(new OrderPayData(true, 2.80, "roasted chicken rice", "chicken rice","CAN A",1));
        orderPayRequests.add(new OrderPayData(true, 3.00, "steamed chicken rice", "chicken rice","CAN A",2));
        orderPayRequests.add(new OrderPayData(true, 3.00, "steamed chicken rice", "chicken rice","CAN A",2));
        orderPayRequests.add(new OrderPayData(true, 3.00, "steamed chicken rice", "chicken rice","CAN A",2));
        orderPayRequests.add(new OrderPayData(true, 3.00, "steamed chicken rice", "chicken rice","CAN A",2));
        orderPayRequests.add(new OrderPayData(true, 3.00, "steamed chicken rice", "chicken rice","CAN A",2));
        orderPayRequests.add(new OrderPayData(true, 3.00, "steamed chicken rice", "chicken rice","CAN A",2));
        orderPayRequests.add(new OrderPayData(true, 3.00, "steamed chicken rice", "chicken rice","CAN A",2));

        //TODO: orderPayRequests is an ArrayList<OrderPayData> that will get the data from database
        toPayAdapter = new ToPayAdapter(getActivity(), orderPayRequests);

        View rootView = inflater.inflate(R.layout.order_fragment1, container, false);

        final CheckBox selectAll = (CheckBox) rootView.findViewById(R.id.select_all);
        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectAll.isChecked()) {
                    for (int i = 0; i < toPayAdapter.getCount(); i++) {
                        if (!toPayAdapter.getItem(i).getIsChecked()) {
                            toPayAdapter.getItem(i).setIsChecked(true);
                        }
                    }
                    toPayAdapter.notifyDataSetChanged();
                }
                else{
                    for (int i = 0; i < toPayAdapter.getCount(); i++) {
                        if (toPayAdapter.getItem(i).getIsChecked()) {
                            toPayAdapter.getItem(i).setIsChecked(false);
                        }
                    }
                    toPayAdapter.notifyDataSetChanged();
                }
            }
        });


        ImageView delete = (ImageView) rootView.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkpoint = false;
                for(int i=0; i<toPayAdapter.getCount(); i++){
                    if(toPayAdapter.getItem(i).getIsChecked()){
                        //TODO: delete from database
                        checkpoint = true;
                    }
                }
                if(checkpoint) {
                    Toast.makeText(getContext(), "The order has been deleted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getContext(), "There is nothing to delete", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button pay = (Button) rootView.findViewById(R.id.pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Payment Confirmation")
                        .setMessage("Do you really want to buy all this order?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                //TODO:send order to database
                                Toast.makeText(getContext(), "Payment Confirmation Successfull", Toast.LENGTH_SHORT).show();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });

        Button payDabao = (Button) rootView.findViewById(R.id.pay_dabao);
        payDabao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OrderListPayDabaoActivity.class);
                startActivity(intent);
            }
        });

        ListView listView = (ListView) rootView.findViewById(R.id.listview_from_button);
        listView.setAdapter(toPayAdapter);

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(toPayAdapter.getItem(position).getIsChecked()){
                    toPayAdapter.getItem(position).setIsChecked(false);
                }
                else{
                    toPayAdapter.getItem(position).setIsChecked(true);
                }
                toPayAdapter.notifyDataSetChanged();

            }
        });
        */

        return rootView;
    }
}
