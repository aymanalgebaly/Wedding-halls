package com.nuller.developer.hall.main_recycle;

import android.os.Parcel;
import android.os.Parcelable;

public class MainModel implements Parcelable  {

    String name , img ,img1,img2,img3,img4, salary , location , capacity , phone ,details , city ,map ,rate ,descound;

    public MainModel(){}


    public MainModel(String name, String img, String img1, String img2, String img3, String img4,
                     String salary, String location, String capacity, String phone, String details , String city
                        ,String map , String rate ,String descound ) {
        this.name = name;
        this.img = img;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.img4 = img4;
        this.salary = salary;
        this.location = location;
        this.capacity = capacity;
        this.phone = phone;
        this.details = details;
        this.city = city;
        this.map = map;
        this.rate = rate;
        this.descound = descound;
    }


    protected MainModel(Parcel in) {
        name = in.readString();
        img = in.readString();
        img1 = in.readString();
        img2 = in.readString();
        img3 = in.readString();
        img4 = in.readString();
        salary = in.readString();
        location = in.readString();
        capacity = in.readString();
        phone = in.readString();
        details = in.readString();
        city = in.readString();
        map = in.readString();
        rate = in.readString();
        descound = in.readString();
    }

    public static final Creator<MainModel> CREATOR = new Creator<MainModel>() {
        @Override
        public MainModel createFromParcel(Parcel in) {
            return new MainModel(in);
        }

        @Override
        public MainModel[] newArray(int size) {
            return new MainModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getImg4() {
        return img4;
    }

    public void setImg4(String img4) {
        this.img4 = img4;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDescound() {
        return descound;
    }

    public void setDescound(String descound) {
        this.descound = descound;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(img);
        parcel.writeString(img1);
        parcel.writeString(img2);
        parcel.writeString(img3);
        parcel.writeString(img4);
        parcel.writeString(salary);
        parcel.writeString(location);
        parcel.writeString(capacity);
        parcel.writeString(phone);
        parcel.writeString(details);
        parcel.writeString(city);
        parcel.writeString(map);
        parcel.writeString(rate);
        parcel.writeString(descound);
    }
}
