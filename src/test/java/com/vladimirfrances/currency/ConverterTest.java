package com.vladimirfrances.currency;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class ConverterTest {
    @Test
    void sanitizeConversion() {
        CurrencyConverter cc = new CurrencyConverter(null);
        assertThrows(IllegalArgumentException.class, () -> cc.convertToMany("", 10, List.of("USD")));
        assertThrows(IllegalArgumentException.class, () -> cc.convertToMany("USD", -1, List.of("EUR")));
        assertThrows(IllegalArgumentException.class, () -> cc.convertToMany("USD", 1, List.of()));
    }
}
