-- intended to use something like
-- $ ij < select.sql
connect 'jdbc:derby:./target/mydb';
select * from app.dest;
exit;
