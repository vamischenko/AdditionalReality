package com.apps.augmentedreality.http;

import android.os.Build;

import com.apps.augmentedreality.data.model.Device;
import com.apps.augmentedreality.data.model.DeviceList;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ARHttpClient {

    static HttpClient client = null;
    private static String restServiceUrl = "http://localhost:8089/";
    private static ObjectMapper mapper = new ObjectMapper();
    private static Long id = 101L;

    public static List<Device> getDevices(Double currLongitude, Double currLatitude) {
        try {
            if (client == null) {
                client = new DefaultHttpClient();
            }
            HttpGet request = new HttpGet(restServiceUrl +
                    "/ar/device?" +
                    "longitude=" + currLongitude.toString() +
                    "&latitude=" + currLatitude.toString()
            );
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();
            String content = read(is);
            DeviceList deviceList = mapper.readValue(content, DeviceList.class);
            return deviceList.getDevices();
        } catch (IOException ex) {
            return null;
        }
    }

    public static Device getDeviceInfo(Double currLongitude, Double currLatitude, Long deviceId) {
        try {
            if (client == null) {
                client = new DefaultHttpClient();
            }
            HttpGet request = new HttpGet(restServiceUrl + "/ar/device/" + deviceId.toString());
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();
            String content = read(is);
            return mapper.readValue(content, Device.class);
        } catch (IOException ex) {
            return null;
        }
    }

    public static void sendInfoAboutSelf(Double currLongitude, Double currLatitude) {
        try {
            if (client == null) {
                client = new DefaultHttpClient();
            }
            HttpPost request = new HttpPost(restServiceUrl + "/ar/device");
            request.setHeader("Content-type", "application/json");
            Device self = getSelfInfo(currLongitude, currLatitude);
            StringEntity entity = new StringEntity(mapper.writeValueAsString(self));
            request.setEntity(entity);
            HttpResponse response = client.execute(request);
        } catch (IOException ex) {

        }
    }

    private static String read(InputStream instream) {
        StringBuilder sb = null;
        try {
            sb = new StringBuilder();
            BufferedReader r = new BufferedReader(new InputStreamReader(instream));
            for (String line = r.readLine(); line != null; line = r.readLine()) {
                sb.append(line);
            }

            instream.close();

        } catch (IOException e) {
        }
        return sb.toString();
    }

    private static Device getSelfInfo(Double currLongitude, Double currLatitude) {
        Device device = new Device();
        device.setId(Integer.parseInt(Build.ID));
        device.setName(Build.DEVICE);
        device.setMemory(getTotalRAM());
        device.setModel(Build.MODEL);
        device.setCharge(0.75);
        device.setLatitude(currLatitude);
        device.setLongitude(currLongitude);
        return device;
    }

    public static Double getTotalRAM() {
        RandomAccessFile reader = null;
        String load = null;
        DecimalFormat twoDecimalForm = new DecimalFormat("#.##");
        double totRam = 0;
        String lastValue = "";
        Double mb = 0.0;
        try {
            reader = new RandomAccessFile("/proc/meminfo", "r");
            load = reader.readLine();

            // Get the Number value from the string
            Pattern p = Pattern.compile("(\\d+)");
            Matcher m = p.matcher(load);
            String value = "";
            while (m.find()) {
                value = m.group(1);
                // System.out.println("Ram : " + value);
            }
            reader.close();

            totRam = Double.parseDouble(value);
            // totRam = totRam / 1024;

            mb = totRam / 1024.0;

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            // Streams.close(reader);
        }

        return mb;
    }

}
