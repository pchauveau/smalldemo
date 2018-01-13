package com.smalldemo.pch.smalldemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.smalldemo.pch.smalldemo.adapter.ListViewImageAdapter;
import com.smalldemo.pch.smalldemo.model.BasicOject;
import com.smalldemo.pch.smalldemo.model.ItemInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.activity_main_lv)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ButterKnife.bind(this);

        requestPermission();

        // Test temp
        List<ItemInterface> basicOjects = new ArrayList<>();
        basicOjects.add(new BasicOject("test1"));
        basicOjects.add(new BasicOject("test2"));
        basicOjects.add(new BasicOject("test3"));
        basicOjects.add(new BasicOject("test4"));
        basicOjects.add(new BasicOject("test5"));


        ListViewImageAdapter listViewImageAdapter = new ListViewImageAdapter(this, basicOjects);
        listView.setAdapter(listViewImageAdapter);
    }

    /**
     * Used to request as many permissions as necessary
     */
    public void requestPermission() {
        List<String> strings = new ArrayList<>();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            strings.add(Manifest.permission.INTERNET);
        }

        String[] stringsTab = new String[strings.size()];
        for (int i = 0; i < strings.size(); i++) {
            stringsTab[i] = strings.get(i);
        }

        if (stringsTab.length > 0) {
            ActivityCompat.requestPermissions(this, stringsTab, 1);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    requestPermission();
                }
                return;
            }
        }
    }
}
