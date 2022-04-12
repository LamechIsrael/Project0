package dev.israel.data;

import dev.israel.entities.Banker;
import dev.israel.utilities.ConnectionUtil;

import java.sql.*;

public class BankerDAOPostgresImpl implements BankerDAO{

    @Override
    public Banker createBanker(Banker banker) {
            // insert into banker values(default, 'LIsrael' , 'lusterling' , 'Lamech' , 'Israel', 200000000);
        try {

            Connection conn = ConnectionUtil.createConnection();
            String sql = "insert into banker values (default, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, banker.getUsername());
            ps.setString(2, banker.getPassword());
            ps.setString(3, banker.getFirstName());
            ps.setString(4, banker.getLastName());
            ps.setDouble(5, banker.getBalance());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int generatedID = rs.getInt("banker_id");
            banker.setId(generatedID);

            return banker;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Banker loginBanker(String username, String password) {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "select * from banker where (username = ? AND bank_password = ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            rs.next();
            Banker banker = new Banker();
            banker.setId(rs.getInt("banker_id"));
            banker.setUsername(rs.getString("username"));
            banker.setFirstName(rs.getString("first_name"));
            banker.setLastName(rs.getString("last_name"));
            banker.setBalance(rs.getDouble("balance"));

            return banker;
        } catch (SQLException e) {
            System.out.println("Invalid Input");
            System.out.println("");
            //e.printStackTrace();
            return null;
        }
    }

    @Override
    public Banker updateFunds(Banker banker) {
        try {
            //Connect to table
            Connection conn = ConnectionUtil.createConnection();
            String sql = "update banker set first_name = ?, last_name = ?,balance = ? where banker_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            //Get current funds from and deposit funds into table
            ps.setString(1, banker.getFirstName());
            ps.setString(2, banker.getLastName());
            ps.setDouble(3, banker.getBalance());
            ps.setInt(4, banker.getId());
            ps.executeUpdate();

            //Set funds as the new value in table
            return banker;
        } catch (SQLException e) {
            e.printStackTrace();

            return null;
        }
    }
}
