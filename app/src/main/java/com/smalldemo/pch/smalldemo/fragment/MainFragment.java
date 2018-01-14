package com.smalldemo.pch.smalldemo.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.smalldemo.pch.smalldemo.R;
import com.smalldemo.pch.smalldemo.adapter.ListViewImageAdapter;
import com.smalldemo.pch.smalldemo.event.WebserviceGetListEvent;
import com.smalldemo.pch.smalldemo.model.BasicObjectForView;
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

public class MainFragment extends Fragment {
    ListViewImageAdapter listViewImageAdapter;

    // Catching the evenment cast by the WS response.
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(WebserviceGetListEvent event) {
        if (event.isOk()) {
            BasicOjectDao basicOjectDao = ((App) getActivity().getApplication()).getDaoSession().getBasicOjectDao();
            basicOjectDao.insertOrReplaceInTx(event.getBasicOjects());

            updateList();
        } else {
            Toast.makeText(getContext(), R.string.webserviceevent_error, Toast.LENGTH_LONG).show();
            Toast.makeText(getContext(), event.getError(), Toast.LENGTH_LONG).show();
        }
    }


    @BindView(R.id.fragment_main_lv)
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);        // for the @Binding
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        requestPermission();

        // Calling this, if we already hava data, no need to wait.
        updateList();

        if (Utils.isActiveNetwork(getContext())) {
            GetList getList = new GetList();
            getList.getList();
        }
    }

    /**
     * Used to request as many permissions as necessary
     */
    public void requestPermission() {
        List<String> strings = new ArrayList<>();

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            strings.add(Manifest.permission.INTERNET);
        }

        String[] stringsTab = new String[strings.size()];
        for (int i = 0; i < strings.size(); i++) {
            stringsTab[i] = strings.get(i);
        }

        if (stringsTab.length > 0) {
            ActivityCompat.requestPermissions(getActivity(), stringsTab, 1);
        }
    }

    /**
     * Used to update the ListView with data from the DB.
     * The data of the DB are passed into a BasicObjectForView to add a good onClick reaction.
     */
    private void updateList() {
        // Getting the DB Dao session
        BasicOjectDao basicOjectDao = ((App) getActivity().getApplication()).getDaoSession().getBasicOjectDao();

        // Creating a List of objects to show based from the DB
        List<BasicObjectForView> basicObjectForViews = new ArrayList<>();
        for (BasicOject basicOject : basicOjectDao.loadAll()) {
            basicObjectForViews.add(new BasicObjectForView(basicOject));
        }

        // If there is no data, no need to work
        if (basicObjectForViews.size() > 0) {
            List<ItemInterface> itemInterfaces = new ArrayList<>();
            itemInterfaces.addAll(basicObjectForViews); // Cast into a list of ItemInterfaces for the ListViewImageAdapter

            listViewImageAdapter = new ListViewImageAdapter(getContext(), itemInterfaces);
            listView.setAdapter(listViewImageAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    ((ItemInterface) adapterView.getItemAtPosition(i)).switchImageToShow();
                    listViewImageAdapter.notifyDataSetChanged();
                }
            });
        } else {
            Toast.makeText(getContext(), "Pas de données à afficher, veuillez vous connecter.", Toast.LENGTH_LONG).show();
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
