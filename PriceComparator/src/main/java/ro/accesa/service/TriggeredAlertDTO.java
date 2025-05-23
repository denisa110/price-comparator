package ro.accesa.service;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public record TriggeredAlertDTO(String productName, double targetPrice, LocalDateTime createdDate, double price,
                                String currency, LocalDate datePrice, String retailerName) {
}

