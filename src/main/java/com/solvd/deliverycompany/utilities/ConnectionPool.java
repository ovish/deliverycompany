package com.solvd.deliverycompany.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {

    private final BlockingQueue<DBConnection> pool;
    private final int poolSize;
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class.getName());


    public ConnectionPool(int poolSize) {
        pool = new LinkedBlockingQueue<>(poolSize);
        this.poolSize = poolSize;

        for (int i =0; i <= poolSize; i++) {
            pool.offer(new DBConnection());
            logger.info("Connection Pool created");

        }
    }

    public DBConnection getConnection() throws InterruptedException {
        return pool.take();
    }

    public void releaseConnection(DBConnection connection) {
        pool.offer(connection);
    }
}
