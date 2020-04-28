package ua.polina.model.dao.implementation;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

public class ConnectionPool {
    private static volatile DataSource dataSource;
     public static DataSource getDataSource() {
         if(dataSource==null){
             synchronized (ConnectionPool.class){
                 if(dataSource==null){
                     BasicDataSource ds = new BasicDataSource();
                     ds.setUrl("dbc:postgresql://localhost:5432/hotel-reservation");
                     ds.setUsername("postgres");
                     ds.setPassword("p12042000");
                     ds.setMinIdle(5);
                     ds.setMaxIdle(10);
                     ds.setMaxOpenPreparedStatements(100);
                     dataSource = ds;
                 }
             }
         }
         return dataSource;
     }
}
