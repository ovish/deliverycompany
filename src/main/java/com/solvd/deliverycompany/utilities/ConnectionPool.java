package com.solvd.deliverycompany.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {

    private static ConnectionPool instance;
    private final BlockingQueue<DBConnection> pool;
    private static final int MAX_CONNECTIONS = 3;
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);


    public ConnectionPool() {
        pool = new LinkedBlockingQueue<>(MAX_CONNECTIONS);

        for (int i =0; i < MAX_CONNECTIONS; i++) {
            pool.add(new DBConnection());

        }
        logger.info("Connection Pool created");
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public DBConnection getConnection() {
        try {
            logger.info("Connection requested from pool");
            return pool.take();
        } catch (InterruptedException e) {
            logger.error("Error getting connection", e);
            return null;
        }
    }

    public void releaseConnection(DBConnection connection) {
        if (connection != null) {
            pool.offer(connection);
            logger.info("Connection returned to pool");
        }
    }

    public int getAvailableConnections() {
        return pool.size();
    }
}
