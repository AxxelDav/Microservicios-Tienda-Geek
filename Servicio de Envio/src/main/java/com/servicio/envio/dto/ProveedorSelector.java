package com.servicio.envio.dto;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Component
public class ProveedorSelector {
    Map<Integer, String> proveedorMap;

    private String DEFAULT_PROVEEDOR = "US Postal Service.";

    public ProveedorSelector() {
        proveedorMap = new HashMap<>();
        proveedorMap.put(1, "UPS Inc.");
        proveedorMap.put(2, "XPO Logistics");
        proveedorMap.put(3, "FedEx Corp.");
        proveedorMap.put(4, "DHL");
        proveedorMap.put(5, "US Postal Service.");
    }

    public String selector() {
        Random rand = new Random();
        int index = rand.nextInt(proveedorMap.size()) + 1;
        return proveedorMap.getOrDefault(index, DEFAULT_PROVEEDOR);
    }

    public String generadorIdSeguimiento() {
        return UUID.randomUUID().toString();
    }
}
