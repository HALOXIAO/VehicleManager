package com.vehicle.business.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author HALOXIAO
 **/
@Slf4j
public class DateSourcePool {

    private static DataSource pool;

    static {
        try {
            Properties p = new Properties();
            p.load(DateSourcePool.class.getClassLoader().getResource("DB.properties").openStream());
            pool = DruidDataSourceFactory.createDataSource(p);
        } catch (IOException e) {
            log.error("DataBase IOException", e);
        } catch (Exception e) {
            log.error("DataBase Exception", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return pool.getConnection();
    }

}
