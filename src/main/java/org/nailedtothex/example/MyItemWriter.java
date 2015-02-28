package org.nailedtothex.example;

import javax.batch.api.chunk.AbstractItemWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyItemWriter extends AbstractItemWriter {

    private static final Logger log = Logger.getLogger(MyItemWriter.class.getName());

    @Override
    public void writeItems(List<Object> items) throws Exception {
        try (Connection cn = MyDatabaseUtil.getConnection()) {
            cn.setAutoCommit(false);
            try (PreparedStatement ps = cn.prepareStatement("insert into dest (data) values (?)")) {
                for (Object o : items) {
                    ps.setInt(1, (Integer) o);
                    ps.executeUpdate();
                    log.log(Level.INFO, "item={0}", o);
                }
                cn.commit();
                log.info("commit done");
            } catch (Exception e) {
                cn.rollback();
                log.info("rollback done");
                throw e;
            }
        }
    }
}
