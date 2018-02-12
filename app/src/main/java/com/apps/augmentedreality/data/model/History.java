package com.apps.augmentedreality.data.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@DatabaseTable(tableName = "history")
public class History {
    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField
    private Date date;
    @DatabaseField
    private Double longitude;
    @DatabaseField
    private Double latitude;

    public History() {
    }

    public History(Long id, Date date, Double longitude, Double latitude) {
        this.id = id;
        this.date = date;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id=").append(id);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss", Locale.US);
        sb.append(", ").append("date=").append(dateFormatter.format(date));
        sb.append(", ").append("longitude=").append(longitude.toString());
        sb.append(", ").append("latitude=").append(latitude.toString());
        return sb.toString();
    }
}
