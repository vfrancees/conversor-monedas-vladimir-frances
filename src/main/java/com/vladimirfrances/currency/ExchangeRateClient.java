package com.vladimirfrances.currency;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;

public class ExchangeRateClient {

    private static final String BASE_URL = "https://api.exchangerate-api.com/v4/latest";

    private final HttpClient http;
    private final Gson gson = new Gson();

    public ExchangeRateClient() {
        this.http = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }

    public Map<String, Double> fetchRates(String base, List<String> targets) throws IOException, InterruptedException {
        String symbols = String.join(",", sanitize(targets));
        String url = BASE_URL + "?base=" + encode(base) + (symbols.isEmpty() ? "" : "&symbols=" + encode(symbols));

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(20))
                .GET()
                .build();

        HttpResponse<String> resp = http.send(req, HttpResponse.BodyHandlers.ofString());
        if (resp.statusCode() != 200) {
            throw new IOException("HTTP " + resp.statusCode() + ": " + resp.body());
        }

        JsonObject root = gson.fromJson(resp.body(), JsonObject.class);
        if (root == null || !root.has("rates")) {
            throw new IOException("Respuesta JSON inválida: " + resp.body());
        }

        JsonObject rates = root.getAsJsonObject("rates");
        Map<String, Double> out = new LinkedHashMap<>();
        for (String key : rates.keySet()) {
            JsonElement v = rates.get(key);
            if (v != null && v.isJsonPrimitive()) {
                out.put(key, v.getAsDouble());
            }
        }
        return out;
    }

    private static String encode(String s) {
        return URLEncoder.encode(s, StandardCharsets.UTF_8);
    }

    private static List<String> sanitize(List<String> list) {
        List<String> out = new ArrayList<>();
        if (list == null) return out;
        for (String s : list) {
            if (s != null && !s.isBlank()) out.add(s.trim().toUpperCase());
        }
        return out;
    }
}
