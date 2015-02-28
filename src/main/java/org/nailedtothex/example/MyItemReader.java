package org.nailedtothex.example;

import javax.batch.api.chunk.AbstractItemReader;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MyItemReader extends AbstractItemReader {
    private Connection cn;
    private Statement st;
    private ResultSet rs;

    @Override
    public void open(Serializable checkpoint) throws Exception {
        cn = MyDatabaseUtil.getConnection();
        st = cn.createStatement();
        rs = st.executeQuery("select data from src order by data");
    }

    @Override
    public void close() throws Exception {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                // nop
            }
        }
        if (st != null) {
            try {
                st.close();
            } catch (Exception e) {
                // nop
            }
        }
        if (cn != null) {
            try {
                cn.close();
            } catch (Exception e) {
                // nop
            }
        }
    }

    @Override
    public Object readItem() throws Exception {
        if (rs.next()) {
            return rs.getInt(1);
        }
        return null;
    }
}
