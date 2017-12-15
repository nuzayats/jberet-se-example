package org.nailedtothex.example;

import org.apache.commons.dbutils.QueryRunner;

import javax.batch.api.AbstractBatchlet;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class MyBatchlet extends AbstractBatchlet {
    @Override
    public String process() throws Exception {
        try (Connection cn = MyDatabaseUtil.getConnection()) {
            final QueryRunner queryRunner = new QueryRunner();
            try {
                queryRunner.update(cn, "drop table src");
            } catch (Exception e) {
                // nop
            }
            try {
                queryRunner.update(cn, "drop table dest");
            } catch (Exception e) {
                // nop
            }
            queryRunner.update(cn, "create table src (data int)");
            queryRunner.update(cn, "create table dest (data int)");

            cn.setAutoCommit(false);
            try (PreparedStatement ps = cn.prepareStatement("insert into src (data) values (?)")) {
                for (int i = 1; i <= 30; i++) {
                    ps.setInt(1, i);
                    ps.executeUpdate();
                }
                cn.commit();
            }
        }
        return null;
    }
}
