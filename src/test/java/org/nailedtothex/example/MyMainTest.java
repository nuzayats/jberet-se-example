package org.nailedtothex.example;

import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MyMainTest {

    @Test
    public void myjobWorks() throws Exception {
        org.jberet.se.Main.main(new String[]{"myjob"});

        final List<Integer> results = new ArrayList<>();
        try (final Connection cn = MyDatabaseUtil.getConnection();
             final Statement st = cn.createStatement()) {
            try (final ResultSet rs = st.executeQuery("select data from dest order by data")) {
                while (rs.next()) {
                    results.add(rs.getInt(1));
                }
            }
        }
        assertThat(results.size(), is(30));
    }
}
