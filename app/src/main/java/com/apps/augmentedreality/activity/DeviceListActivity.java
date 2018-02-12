package com.apps.augmentedreality.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.apps.augmentedreality.R;
import com.apps.augmentedreality.data.model.Device;
import com.apps.augmentedreality.http.ARHttpClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DeviceListActivity extends AppCompatActivity {
    private static final String TAG = "DeviceListActivity";

    private Double mLongitude;
    private Double mLatitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_list);

        Bundle b = getIntent().getExtras();
        //mLongitude = 43.969863;
       // mLatitude = 56.237461;
        if(b != null) {
            mLongitude = b.getDouble("longitude");
            mLatitude = b.getDouble("latitude");
        }

        final ListView listView = (ListView) findViewById(R.id.devlistview);
        final ArrayList<String> list = new ArrayList<String>();
        List<Device> devices = getDevices();
        int i = 1;
        for (Device device : devices) {
            list.add("Device #" + i + ":" + device.toString());
            i += 1;
        }
        final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "itemClick: position = " + position + ", id = " + id);
                Intent intent = new Intent(DeviceListActivity.this, DeviceInfoActivity.class);
                Bundle b = new Bundle();
                b.putLong("id", id);
                b.putDouble("longitude", mLongitude);
                b.putDouble("latitude", mLatitude);
                intent.putExtras(b);
                startActivity(intent);
                finish();
            }
        });
    }

    private List<Device> getDevices() {

       // ARHttpClient.getDevices(mLongitude, mLatitude);

        List<Device> devices = new ArrayList<>();
        Device dev1 = new Device();
        dev1.setId(1);
        dev1.setName("Sony Xperia E4");
        dev1.setLongitude(mLongitude);
        dev1.setLatitude(mLatitude);
        //devices.add(dev1);

        Device dev5 = new Device();
        dev5.setId(2);
        dev5.setName("Wileyfox Swift 2 x");
        dev5.setLongitude(mLongitude);
        dev5.setLatitude(mLatitude);
        devices.add(dev5);

        Device dev2 = new Device();
        dev2.setId(3);
        dev2.setName("Samsung NP350U2B-A06RU");
        dev2.setLongitude(mLongitude);
        dev2.setLatitude(mLatitude);
        //devices.add(dev2);

        Device dev3 = new Device();
        dev3.setId(4);
        dev3.setName("Vlad-PK");
        dev3.setLongitude(mLongitude);
        dev3.setLatitude(mLatitude);
        //базу devices.add(dev3);

        Device dev4 = new Device();
        dev4.setId(5);
        dev4.setName("Huawei Nexus 6P");
        dev4.setLongitude(mLongitude);
        dev4.setLatitude(mLatitude);
        devices.add(dev4);
        return devices;
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }

}
