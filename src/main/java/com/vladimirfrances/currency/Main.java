package com.vladimirfrances.currency;

public class Main {
    public static void main(String[] args) {
        ConsoleUI ui = new ConsoleUI(new CurrencyConverter(new ExchangeRateClient()));
        ui.run();
    }
}
