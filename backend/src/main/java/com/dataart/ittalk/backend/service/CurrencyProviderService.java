package com.dataart.ittalk.backend.service;

import com.dataart.ittalk.backend.util.CcyAPIResponseWrapper;
import com.dataart.ittalk.common.dao.DatastoreCurrencyDao;
import com.dataart.ittalk.common.model.Currency;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyProviderService {

    private static final Log LOG = LogFactory.getLog(CurrencyProviderService.class);

    private final DatastoreCurrencyDao datastoreCurrencyDao;
    private final RestTemplate restTemplate;

    @Value("${api.key}")
    private String apiKey;

    @Value("${api.url}")
    private String apiUrl;

    @Autowired
    public CurrencyProviderService(DatastoreCurrencyDao datastoreCurrencyDao, RestTemplate restTemplate) {
        this.datastoreCurrencyDao = datastoreCurrencyDao;
        this.restTemplate = restTemplate;
    }

    @Async
    public void updateCurrencies() {
        LOG.info("Updating currencies.");

        CcyAPIResponseWrapper apiWrapper = restTemplate.getForObject(apiUrl, CcyAPIResponseWrapper.class, apiKey);

        LOG.info("Api response parsed successfully.");

        List<Currency> currencies = apiWrapper.getRates()
                .entrySet()
                .stream()
                .map(entry -> new Currency.Builder().currencyCode(entry.getKey()).value(entry.getValue().toString()).build())
                .collect(Collectors.toList());

        currencies.forEach(datastoreCurrencyDao::save);

        LOG.info("Currency definitions successfully updated.");
    }

}
