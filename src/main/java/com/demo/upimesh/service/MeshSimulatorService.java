package com.demo.upimesh.service;

import com.demo.upimesh.model.MeshPacket;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MeshSimulatorService {

    private List<VirtualDevice> devices = new ArrayList<>();
    private Set<String> settledPacketIds = new HashSet<>();

    public MeshSimulatorService() {
        devices.add(new VirtualDevice("phone-a"));
        devices.add(new VirtualDevice("phone-b"));
        devices.add(new VirtualDevice("phone-c"));
        devices.add(new VirtualDevice("phone-d"));
    }

    public List<VirtualDevice> getDevices() {
        return devices;
    }

    public MeshPacket createPacket(MeshPacket packet) {
        devices.get(0).receivePacket(packet);
        return packet;
    }

    public void gossip() {
        for (int i = 0; i < devices.size() - 1; i++) {

            VirtualDevice current = devices.get(i);
            VirtualDevice next = devices.get(i + 1);

            if (!current.isOnline() || !next.isOnline()) {
                continue;
            }

            for (MeshPacket packet : current.getInbox()) {
                if (packet.getTtl() > 0) {
                    packet.setTtl(packet.getTtl() - 1);
                    next.receivePacket(packet);
                }
            }
        }
    }

    public MeshPacket getBridgePacket() {
        VirtualDevice bridge = devices.get(devices.size() - 1);

        if (bridge.getInbox().isEmpty()) {
            return null;
        }

        return bridge.getInbox().get(0);
    }

    public boolean isPacketSettled(String packetId) {
        return settledPacketIds.contains(packetId);
    }

    public void markPacketSettled(String packetId) {
        settledPacketIds.add(packetId);
    }

    public void resetMesh() {
        for (VirtualDevice device : devices) {
            device.getInbox().clear();
            device.setOnline(true);
        }
        settledPacketIds.clear();
    }

    public void setDeviceOnlineStatus(String deviceId, boolean online) {
        for (VirtualDevice device : devices) {
            if (device.getDeviceId().equals(deviceId)) {
                device.setOnline(online);
                return;
            }
        }

        throw new RuntimeException("Device not found: " + deviceId);
    }

    public Map<String, Object> getMeshSummary() {
        int onlineDevices = 0;
        int offlineDevices = 0;
        int totalPackets = 0;

        for (VirtualDevice device : devices) {
            if (device.isOnline()) {
                onlineDevices++;
            } else {
                offlineDevices++;
            }

            totalPackets += device.getInbox().size();
        }

        Map<String, Object> summary = new LinkedHashMap<>();
        summary.put("totalDevices", devices.size());
        summary.put("onlineDevices", onlineDevices);
        summary.put("offlineDevices", offlineDevices);
        summary.put("totalPacketsInMesh", totalPackets);
        summary.put("settledPackets", settledPacketIds.size());

        return summary;
    }
}