package db;

import model.Customer;

import java.util.ArrayList;
import java.util.List;

public class DBConnection {
    private  static DBConnection instance;
    private List<Customer> connection;
    private DBConnection(){
            connection=new ArrayList<>();
    }
    public static DBConnection getInstance(){return null==instance ? instance=new DBConnection(): instance;}
    public List<Customer> getConnection(){return connection;
    }
}
