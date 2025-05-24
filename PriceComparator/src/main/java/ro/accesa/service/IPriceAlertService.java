package ro.accesa.service;

public interface IPriceAlertService {
    void checkTriggeredAlerts(String productName);

    void create(String productName, Double targetPrice);
}
