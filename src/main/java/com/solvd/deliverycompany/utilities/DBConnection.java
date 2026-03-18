package com.solvd.deliverycompany.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBConnection {

    private static final Logger logger = LogManager.getLogger(DBConnection.class);


    public void execute(String query) {
        logger.info("Executing");
    }
}

