package ro.accesa.repository;

import ro.accesa.entity.PriceAlert;

import java.util.List;

public interface IPriceAlertRepository {

    List<PriceAlert> findAllAlerts();

    void save(PriceAlert alert);
}
