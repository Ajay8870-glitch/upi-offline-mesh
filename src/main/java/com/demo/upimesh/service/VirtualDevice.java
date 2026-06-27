package com.demo.upimesh.service;

import com.demo.upimesh.model.MeshPacket;

import java.util.ArrayList;
import java.util.List;

public class VirtualDevice {

    private String deviceId;
    private boolean online;
    private List<MeshPacket> inbox = new ArrayList<>();

    public VirtualDevice(String deviceId) {
        this.deviceId = deviceId;
        this.online = true;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public List<MeshPacket> getInbox() {
        return inbox;
    }

    public void receivePacket(MeshPacket packet) {
        inbox.add(packet);
    }
}