package com.demo.upimesh.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class MeshPacket {

    private String packetId;
    private String senderVpa;
    private String receiverVpa;
    private double amount;
    private int ttl;
    private LocalDateTime createdAt;

    public MeshPacket() {
        this.packetId = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.ttl = 5;
    }

    public String getPacketId() {
        return packetId;
    }

    public void setPacketId(String packetId) {
        this.packetId = packetId;
    }

    public String getSenderVpa() {
        return senderVpa;
    }

    public void setSenderVpa(String senderVpa) {
        this.senderVpa = senderVpa;
    }

    public String getReceiverVpa() {
        return receiverVpa;
    }

    public void setReceiverVpa(String receiverVpa) {
        this.receiverVpa = receiverVpa;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}