package com.vladimirfrances.currency;

import java.io.IOException;
import java.util.*;

public class CurrencyConverter {

    private final ExchangeRateClient client;

    public CurrencyConverter(ExchangeRateClient client) {
        this.client = client;
    }

    public Map<String, Double> convertToMany(String base, double amount, List<String> targets) throws IOException, InterruptedException {
        if (base == null || base.isBlank()) throw new IllegalArgumentException("La moneda base no puede estar vacía.");
        if (amount < 0) throw new IllegalArgumentException("El monto no puede ser negativo.");
        if (targets == null || targets.isEmpty()) throw new IllegalArgumentException("Debes indicar al menos una moneda destino.");

        Map<String, Double> rates = client.fetchRates(base.toUpperCase(), targets);
        Map<String, Double> results = new LinkedHashMap<>();
        for (String t : targets) {
            Double rate = rates.get(t.toUpperCase());
            if (rate != null) {
                results.put(t.toUpperCase(), amount * rate);
            }
        }
        return results;
    }
}
