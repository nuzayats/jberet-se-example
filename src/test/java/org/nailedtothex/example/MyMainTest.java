package org.nailedtothex.example;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MyMainTest {

    @Test
    public void myjobWorks() throws Exception {
        org.jberet.se.Main.main(new String[]{"myjob"});

        final List<Integer> actual = selectDataFromDest();
        assertThat(actual.size(), is(30));
    }

    private static List<Integer> selectDataFromDest() throws SQLException {
        final List<Integer> actual;
        try (final Connection cn = MyDatabaseUtil.getConnection()) {
            actual = new QueryRunner().query(cn, "select data from dest order by data",
                    rs -> {
                        final List<Integer> results = new ArrayList<>();
                        while (rs.next()) {
                            results.add(rs.getInt(1));
                        }
                        return results;
                    }
            );
        }
        return actual;
    }
}
