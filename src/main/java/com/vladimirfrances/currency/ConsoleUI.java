package com.vladimirfrances.currency;

import java.util.*;
import java.io.IOException;

public class ConsoleUI {

    private final CurrencyConverter converter;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleUI(CurrencyConverter converter) {
        this.converter = converter;
    }

    public void run() {
        System.out.println("=== Conversor de Monedas (by Vladimir Frances) ===");
        System.out.println("API: exchangerate.host (sin API key)\n");

        while (true) {
            try {
                System.out.print("Moneda base (ej: USD, EUR, DOP) o 'salir': ");
                String base = scanner.nextLine().trim().toUpperCase();
                if (base.equals("SALIR")) break;

                System.out.print("Monto a convertir: ");
                double amount = Double.parseDouble(scanner.nextLine().trim());

                System.out.print("Monedas destino separadas por coma (ej: DOP,EUR,MXN) o vacío para sugeridas: ");
                String line = scanner.nextLine().trim();
                List<String> targets;
                if (line.isEmpty()) {
                    targets = Arrays.asList("USD","EUR","DOP","MXN","COP","ARS","BRL");
                } else {
                    targets = new ArrayList<>();
                    for (String t : line.split(",")) {
                        if (!t.isBlank()) targets.add(t.trim().toUpperCase());
                    }
                }

                Map<String, Double> results = converter.convertToMany(base, amount, targets);
                System.out.println("\nResultados:");
                results.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey())
                        .forEach(e -> System.out.printf("  %s: %.4f\n", e.getKey(), e.getValue()));
                System.out.println();
            } catch (NumberFormatException e) {
                System.out.println("Monto inválido. Intenta de nuevo.\n");
            } catch (IOException e) {
                System.out.println("Error de red/IO: " + e.getMessage() + "\n");
            } catch (InterruptedException e) {
                System.out.println("Operación interrumpida.");
                Thread.currentThread().interrupt();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Entrada inválida: " + e.getMessage() + "\n");
            }
        }

        System.out.println("Gracias por usar el conversor. ¡Hasta pronto!");
    }
}
