package com.dataart.ittalk.common.dao;

import com.dataart.ittalk.common.model.Currency;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DatastoreCurrencyDao {

    private static final String CURRENCY_KIND = "Currency";
    private DatastoreService datastore;

    public DatastoreCurrencyDao() {
        datastore = DatastoreServiceFactory.getDatastoreService();
    }

    public List<Currency> getCurrencies() {
        Query query = new Query(CURRENCY_KIND);
        PreparedQuery preparedQuery = datastore.prepare(query);
        List<Entity> entities = preparedQuery.asList(FetchOptions.Builder.withDefaults());
        return entities.stream().map(this::entityToCurrency).collect(Collectors.toList());
    }

    private Currency entityToCurrency(Entity entity) {
        return new Currency.Builder()
                .currencyCode((String) entity.getProperty(Currency.CURRENCY_CODE))
                .value((String) entity.getProperty(Currency.VALUE))
                .uploadTime((Date) entity.getProperty(Currency.UPLOAD_TIME))
                .build();
    }

    public void save(Currency currency) {
        Entity currencyEntity = new Entity(CURRENCY_KIND, currency.getCurrencyCode());
        currencyEntity.setProperty(Currency.CURRENCY_CODE, currency.getCurrencyCode());
        currencyEntity.setProperty(Currency.VALUE, currency.getValue());
        currencyEntity.setProperty(Currency.UPLOAD_TIME, new Date());
        datastore.put(currencyEntity);
    }

}
