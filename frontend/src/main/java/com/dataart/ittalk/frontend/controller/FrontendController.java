package com.dataart.ittalk.frontend.controller;

import com.dataart.ittalk.common.dao.DatastoreCurrencyDao;
import com.dataart.ittalk.common.model.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FrontendController {

    private final DatastoreCurrencyDao datastoreCurrencyDao;

    @Autowired
    public FrontendController(DatastoreCurrencyDao datastoreCurrencyDao) {
        this.datastoreCurrencyDao = datastoreCurrencyDao;
    }

    @RequestMapping("/currencies")
    public List<Currency> getCurrencies() {
        return datastoreCurrencyDao.getCurrencies();
    }

}
