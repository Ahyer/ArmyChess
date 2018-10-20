package com.example.armychess;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;

public class basedPanel extends AppCompatActivity {
    private static final String TAG="basedPanel";
    private ListView listView;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private String receive="miss";
    private int Ch=-1;
    List<String > whatchoice=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List <distribution> distri=DataSupport.findAll(distribution.class);
        for (distribution dis:distri)
        {
             whatchoice.add(dis.getName());
        }
        showDialog();
        Intent intent=getIntent();
        receive=intent.getStringExtra("address");
        Log.d(TAG, "onCreate: "+receive);

        setContentView(R.layout.activity_based_panel);



    }
    public void showDialog()
    {
        Context context=basedPanel.this;
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(basedPanel.this,android.R.layout.simple_list_item_1,whatchoice);
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        final View layout=inflater.inflate(R.layout.choicelistview,null);
        listView= (ListView) layout.findViewById(R.id.choice);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              //  Toast.makeText(getApplicationContext(),"你点击了"+position,Toast.LENGTH_SHORT).show();
                if (alertDialog != null) {
                    Ch=position;
                    layout.setVisibility(view.GONE);
                    alertDialog.dismiss();
                }
            }
        });
        builder = new AlertDialog.Builder(context);
        builder.setView(layout);
        alertDialog = builder.create();
        alertDialog.show();
    }
    /*
    * 这个方法是为了给view传送蓝牙连接的信息
    * */
    public String initBusiness(String a)
    {
        a=this.receive;
        return a;
    }
    public int choice(int a)
    {
        a=this.Ch;
        return a;
    }
}