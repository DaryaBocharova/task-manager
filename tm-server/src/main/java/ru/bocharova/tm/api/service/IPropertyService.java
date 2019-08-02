package ru.bocharova.tm.api.service;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface IPropertyService {

    String getJdbcPassword();

    String getJdbcUser();

    String getJdbcURL();

    String getJdbcDriver();

    String getDialect();

    String getShowSQL();

    String getHBM2DDL_AUTO();


    String getSalt();

    String getCycle();
}
