package ro.accesa.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AlertDTO(String productName, double targetPrice, LocalDateTime createdDate, double price,
                       String currency, LocalDate datePrice, String retailerName) {
}

