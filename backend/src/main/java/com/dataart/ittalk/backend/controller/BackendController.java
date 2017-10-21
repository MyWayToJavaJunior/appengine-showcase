package com.dataart.ittalk.backend.controller;

import com.dataart.ittalk.backend.service.CurrencyProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackendController {

    private final CurrencyProviderService currencyProviderService;

    @Autowired
    public BackendController(CurrencyProviderService currencyProviderService) {
        this.currencyProviderService = currencyProviderService;
    }

    @RequestMapping("/update-currencies")
    public void updateCurrencies() {
        currencyProviderService.updateCurrencies();
    }

}
