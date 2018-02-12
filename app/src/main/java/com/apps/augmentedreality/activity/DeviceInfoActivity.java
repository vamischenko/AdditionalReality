package com.apps.augmentedreality.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.augmentedreality.R;
import com.apps.augmentedreality.data.model.Device;
import com.apps.augmentedreality.http.ARHttpClient;

import java.util.ArrayList;
import java.util.List;

public class DeviceInfoActivity extends AppCompatActivity {
    private static final String TAG = "DeviceInfoActivity";

    private Double mLongitude;
    private Double mLatitude;
    private Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //mLongitude = 43.969863;
        //mLatitude = 56.237461;

        Bundle b = getIntent().getExtras();
        if(b != null) {
            id = b.getLong("id");
            mLongitude = b.getDouble("longitude");
            mLatitude = b.getDouble("latitude");
        }
        //Toast.makeText(this, "id = " + id, Toast.LENGTH_SHORT).show();

        //List<Device> devices = getDevices();
        //int i = Integer.parseInt(id.toString());
        Device device = ARHttpClient.getDeviceInfo(mLongitude, mLatitude, id);

        final TextView name = (TextView) findViewById(R.id.textView10);
        name.setText(device.getName());

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar.setProgress((int)(device.getCharge() * 100));

        setContentView(R.layout.activity_device_info);
    }

    private List<Device> getDevices() {
        List<Device> devices = new ArrayList<>();
        Device dev1 = new Device();
        dev1.setId(1);
        dev1.setName("Sony Xperia E4");
        dev1.setLongitude(mLongitude);
        dev1.setLatitude(mLatitude);
        //devices.add(dev1);

        Device dev5 = new Device();
        dev5.setId(1);
        dev5.setName("Wileyfox Swift 2 x");
        dev5.setLongitude(mLongitude);
        dev5.setLatitude(mLatitude);
        devices.add(dev5);

        Device dev2 = new Device();
        dev2.setId(2);
        dev2.setName("Samsung NP350U2B-A06RU");
        dev2.setLongitude(mLongitude);
        dev2.setLatitude(mLatitude);
        devices.add(dev2);

        Device dev3 = new Device();
        dev3.setId(2);
        dev3.setName("Vlad-PK");
        dev3.setLongitude(mLongitude);
        dev3.setLatitude(mLatitude);
        //devices.add(dev3);

        Device dev4 = new Device();
        dev4.setId(3);
        dev4.setName("Huawei Nexus 6P");
        dev4.setLongitude(mLongitude);
        dev4.setLatitude(mLatitude);
        devices.add(dev4);
        return devices;
    }

}
