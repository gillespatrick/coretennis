package com.gilles.tennis.coretennis;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class DataSourceProvider {

    private static BasicDataSource singleDataSource;
    public static DataSource getDataSourceInstance(){
        if (singleDataSource == null){

            singleDataSource= new BasicDataSource();
            singleDataSource.setInitialSize(2);
            singleDataSource.setUrl("jdbc:mysql://localhost:3306/TENNIS");
            singleDataSource.setUsername("gilles");
            singleDataSource.setPassword("gillespatr9ck");
        }
        return singleDataSource;
    }
}
