package com.example.aplikasisqlite.model;

public class Data {
    private String idx, namex, addressd;

    public Data(){
    }

    public Data(String id, String name, String address){
        this.idx = id;
        this.namex = name;
        this.addressd = address;
    }

    public String getId(){
        return idx;
    }

    public void setId(String id){
        this.idx = id;
    }

    public String getName(){
        return namex;
    }

    public void setName(String name){
        this.namex = name;
    }

    public String getAddress(){
        return addressd;
    }

    public void setAddress(String address){
        this.addressd = address;
    }
}
