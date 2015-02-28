jberet-se-example
=================

To execute issue following command:

    mvn clean compile exec:exec -Dexec.args="-cp %classpath org.nailedtothex.example.MyMain myjob"

The batch creates embedded Derby database in ./target/mydb. you can easily check what data were saved as follows:

    $ ij select.sql