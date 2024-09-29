package repository.custom.impl;

import db.DBConnection;
import entity.CustomerEntity;
import repository.custom.CustomerDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(CustomerEntity customer) {
        try {
            String SQL = "INSERT INTO Customer values(?,?,?,?,?,?,?,?,?)";
            Connection connection = DBConnection.getInstance().getConnection();

            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1, customer.getId());
            psTm.setObject(2, "m");
            psTm.setObject(3, customer.getName());
            psTm.setObject(4, customer.getDob());
            psTm.setObject(5, customer.getSalary());
            psTm.setObject(6, customer.getAddress());
            psTm.setObject(7, customer.getCity());
            psTm.setObject(8, customer.getProvince());
            psTm.setObject(9, customer.getPostalCode());

            return psTm.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(CustomerEntity entity) {
        return false;
    }

    @Override
    public List<CustomerEntity> findAll() {
        return null;
    }
}
