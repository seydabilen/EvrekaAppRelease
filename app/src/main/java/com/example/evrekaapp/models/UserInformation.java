package com.example.evrekaapp.models;

public class UserInformation {
    public String containerID,sensorID,temperature,fullnessRate;
    public double latitude;
    public double longitude;

    public UserInformation(){

    }

    public UserInformation(String containerID,String sensorID,String temperature,String fullnessRate,double latitude,double longitude){
        this.containerID=containerID;
        this.sensorID=sensorID;
        this.latitude=latitude;
        this.longitude=longitude;
        this.temperature=temperature;
        this.fullnessRate=fullnessRate;
    }


    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getFullnessRate() {
        return fullnessRate;
    }

    public void setFullnessRate(String fullnessRate) {
        this.fullnessRate = fullnessRate;
    }

    public String getContainerID() {
        return containerID;
    }

    public void setContainerID(String containerID) {
        this.containerID = containerID;
    }

    public String getSensorID() {
        return sensorID;
    }

    public void setSensorID(String sensorID) {
        this.sensorID = sensorID;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
