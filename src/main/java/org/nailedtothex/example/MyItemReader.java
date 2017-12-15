package org.nailedtothex.example;

import org.apache.commons.dbutils.DbUtils;

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
    public Object readItem() throws Exception {
        if (rs.next()) {
            return rs.getInt(1);
        }
        return null;
    }

    @Override
    public void close() throws Exception {
        DbUtils.closeQuietly(cn, st, rs);
    }
}
