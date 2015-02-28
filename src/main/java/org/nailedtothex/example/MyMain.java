package org.nailedtothex.example;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * This class just intended to call Derby shutdown procedure explicitly.
 */
public class MyMain {
    private static final Logger log = Logger.getLogger(MyMain.class.getName());

    public static void main(String[] args) {
        try {
            org.jberet.se.Main.main(args);
        } finally {
            try {
                DriverManager.getConnection("jdbc:derby:;shutdown=true");
            } catch (SQLException e) {
                log.info(e.getMessage());
            }
        }
    }
}
