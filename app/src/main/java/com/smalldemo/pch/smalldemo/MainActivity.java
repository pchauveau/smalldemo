package com.smalldemo.pch.smalldemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.smalldemo.pch.smalldemo.adapter.ListViewImageAdapter;
import com.smalldemo.pch.smalldemo.event.WebserviceGetListEvent;
import com.smalldemo.pch.smalldemo.model.BasicOject;
import com.smalldemo.pch.smalldemo.model.BasicOjectDao;
import com.smalldemo.pch.smalldemo.model.ItemInterface;
import com.smalldemo.pch.smalldemo.utils.App;
import com.smalldemo.pch.smalldemo.utils.Utils;
import com.smalldemo.pch.smalldemo.webservices.GetList;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(WebserviceGetListEvent event) {
        if (event.isOk()) {
            BasicOjectDao basicOjectDao = ((App) getApplication()).getDaoSession().getBasicOjectDao();
            basicOjectDao.insertOrReplaceInTx(event.getBasicOjects());

            updateList();
        } else {
            Toast.makeText(this, R.string.webserviceevent_error, Toast.LENGTH_LONG).show();
            Toast.makeText(this, event.getError(), Toast.LENGTH_LONG).show();
        }
    }

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

        if(Utils.isActiveNetwork(this))
        {
            GetList getList = new GetList();
            getList.getList();
        } else {
            updateList();
        }
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

    /**
     * Used to update the ListView with data from the DB.
     */
    private void updateList() {
        BasicOjectDao basicOjectDao = ((App) getApplication()).getDaoSession().getBasicOjectDao();
        List<ItemInterface> basicOjects = new ArrayList<>();
        basicOjects.addAll(basicOjectDao.loadAll());

        ListViewImageAdapter listViewImageAdapter = new ListViewImageAdapter(this, basicOjects);
        listView.setAdapter(listViewImageAdapter);
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

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
