package com.demo.upimesh.controller;

import com.demo.upimesh.dto.TransferRequest;
import com.demo.upimesh.model.Account;
import com.demo.upimesh.model.AccountRepository;
import com.demo.upimesh.model.MeshPacket;
import com.demo.upimesh.model.Transaction;
import com.demo.upimesh.model.TransactionRepository;
import com.demo.upimesh.service.MeshSimulatorService;
import com.demo.upimesh.service.TransactionService;
import com.demo.upimesh.service.VirtualDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private MeshSimulatorService meshSimulatorService;

    @GetMapping("/accounts")
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @GetMapping("/transactions")
    public List<Transaction> getTransactions() {
        return transactionRepository.findTop20ByOrderByIdDesc();
    }

    @PostMapping("/transfer")
    public Transaction transfer(@RequestBody TransferRequest request) {
        return transactionService.transfer(
                request.getSenderVpa(),
                request.getReceiverVpa(),
                request.getAmount()
        );
    }

    @PostMapping("/mesh/send")
    public MeshPacket sendToMesh(@RequestBody MeshPacket packet) {
        return meshSimulatorService.createPacket(packet);
    }

    @PostMapping("/mesh/gossip")
    public String gossip() {
        meshSimulatorService.gossip();
        return "Gossip completed";
    }

    @GetMapping("/mesh/devices")
    public List<VirtualDevice> getDevices() {
        return meshSimulatorService.getDevices();
    }

    @GetMapping("/mesh/summary")
    public Map<String, Object> getMeshSummary() {
        return meshSimulatorService.getMeshSummary();
    }

    @PostMapping("/mesh/settle")
    public ResponseEntity<?> settleMeshPayment() {
        MeshPacket packet = meshSimulatorService.getBridgePacket();

        if (packet == null) {
            return ResponseEntity.badRequest().body(
                    Map.of("status", "FAILED", "reason", "No packet available at bridge")
            );
        }

        if (meshSimulatorService.isPacketSettled(packet.getPacketId())) {
            return ResponseEntity.badRequest().body(
                    Map.of("status", "FAILED", "reason", "Duplicate packet rejected")
            );
        }

        Transaction transaction = transactionService.transfer(
                packet.getSenderVpa(),
                packet.getReceiverVpa(),
                BigDecimal.valueOf(packet.getAmount())
        );

        meshSimulatorService.markPacketSettled(packet.getPacketId());

        return ResponseEntity.ok(transaction);
    }

    @PostMapping("/mesh/reset")
    public String resetMesh() {
        meshSimulatorService.resetMesh();
        return "Mesh reset completed";
    }

    @PostMapping("/device/offline/{deviceId}")
    public String makeOffline(@PathVariable String deviceId) {
        meshSimulatorService.setDeviceOnlineStatus(deviceId, false);
        return deviceId + " is now OFFLINE";
    }

    @PostMapping("/device/online/{deviceId}")
    public String makeOnline(@PathVariable String deviceId) {
        meshSimulatorService.setDeviceOnlineStatus(deviceId, true);
        return deviceId + " is now ONLINE";
    }
}