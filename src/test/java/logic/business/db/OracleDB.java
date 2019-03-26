package logic.business.db;

import framework.config.Config;
import framework.utils.Db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OracleDB {
    private String url;
    private String username;
    private String password;

    private Connection conn;

    protected OracleDB() {
        this.url = Config.getProp("dbUrl");
        this.username = Config.getProp("dbUsername");
        this.password = Config.getProp("dbPassword");
        try {
            conn = Db.createConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected List executeQuery(String query) {
        try {
            return Db.executeQuery(conn, query, 100);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected int executeUpdate(String query) {
        try {
            allowUpdating();
            return Db.executeUpdate(conn, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void allowUpdating() {
        try {

            Db.executeQuery(conn, "SELECT pkg_audit.SetInfo('Auto',2) FROM dual", 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
