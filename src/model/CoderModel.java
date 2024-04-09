package model;

import database.CRUD;
import database.ConfigDB;
import entity.Coder;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CoderModel implements CRUD{
    @Override
    public List<Object> findAll() {
        List<Object> ListCoder=new ArrayList<>();
        Connection objConnection= ConfigDB.OpenConnection();
        try{
            String sql = "SELECT * FROM Coder;";
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            ResultSet objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()){
                Coder objCoder = new Coder();
                objCoder.setId(objResultSet.getInt(1));
                objCoder.setNombre(objResultSet.getString(2));
                objCoder.setApellido(objResultSet.getString(3));
                objCoder.setDocumento(objResultSet.getString(4));
                objCoder.setCohorte(objResultSet.getInt(5));
                objCoder.setCv(objResultSet.getString(6));
                objCoder.setClan(objResultSet.getString(7));

                ListCoder.add(objCoder);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return ListCoder;
    }

    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.OpenConnection();
        Coder objCoder = (Coder) obj;
        try {
            String sql = "INSERT INTO Coder(nombre, apellidos, documento, cohorte, cv, clan) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objPreparedStatement.setString(1, objCoder.getNombre());
            objPreparedStatement.setString(2, objCoder.getApellido());
            objPreparedStatement.setString(3, objCoder.getDocumento());
            objPreparedStatement.setInt(4, objCoder.getCohorte());
            objPreparedStatement.setString(5, objCoder.getCv());
            objPreparedStatement.setString(6, objCoder.getClan());
            objPreparedStatement.execute();
            ResultSet objResultSet = objPreparedStatement.getGeneratedKeys();
            while (objResultSet.next()) {
                objCoder.setId(objResultSet.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Coder insertado con exito");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
        }
        ConfigDB.CloseConnection();

        return objCoder;
    }

    @Override
    public boolean update(Object obj) {
        //abrir la conexion
        Connection objConnection = ConfigDB.OpenConnection();
        //convertir el objeto a tipo Coder
        Coder objCoder = (Coder) obj;
        boolean isUpdated = false;
        try {
            //crear la consulta
            String sql = "UPDATE Coder SET nombre = ?, apellidos = ?, documento = ?, cohorte = ?, cv = ?, clan = ? WHERE id = ?";
            //preparar la consulta
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            //setear los valores a los parametros de la consulta
            objPreparedStatement.setString(1, objCoder.getNombre());
            objPreparedStatement.setString(2, objCoder.getApellido());
            objPreparedStatement.setString(3, objCoder.getDocumento());
            objPreparedStatement.setInt(4, objCoder.getCohorte());
            objPreparedStatement.setString(5, objCoder.getCv());
            objPreparedStatement.setString(6, objCoder.getClan());
            objPreparedStatement.setInt(7, objCoder.getId());
            //ejecutar la consulta
            int totalRowAffected = objPreparedStatement.executeUpdate();
            if(totalRowAffected > 0){
                JOptionPane.showMessageDialog(null, "Coder actualizado con exito");
                isUpdated = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
        }
        ConfigDB.CloseConnection();
        return isUpdated;
    }
    public Coder findById(int id){
        Connection objConnection = ConfigDB.OpenConnection();
        Coder objCoder = null;
        try {
            String sql = "SELECT * FROM Coder WHERE id = ?";
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setInt(1, id);
            ResultSet objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()){
                objCoder = new Coder();
                objCoder.setId(objResultSet.getInt(1));
                objCoder.setNombre(objResultSet.getString(2));
                objCoder.setApellido(objResultSet.getString(3));
                objCoder.setDocumento(objResultSet.getString(4));
                objCoder.setCohorte(objResultSet.getInt(5));
                objCoder.setCv(objResultSet.getString(6));
                objCoder.setClan(objResultSet.getString(7));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
        }
        ConfigDB.CloseConnection();
        return objCoder;
    }
    public List<Coder> findByClan(String clan){
        List<Coder> ListCoder = new ArrayList<>();
        Connection objConnection = ConfigDB.OpenConnection();
        try {
            String sql = "SELECT * FROM Coder WHERE clan = ?";
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, clan);
            ResultSet objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()){
                Coder objCoder = new Coder();
                objCoder.setId(objResultSet.getInt(1));
                objCoder.setNombre(objResultSet.getString(2));
                objCoder.setApellido(objResultSet.getString(3));
                objCoder.setDocumento(objResultSet.getString(4));
                objCoder.setCohorte(objResultSet.getInt(5));
                objCoder.setCv(objResultSet.getString(6));
                objCoder.setClan(objResultSet.getString(7));
                ListCoder.add(objCoder);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
        }
        ConfigDB.CloseConnection();
        return ListCoder;
    }
    public List<Coder>findByTegnologia(String cv){
        List<Coder> ListCoder = new ArrayList<>();
        Connection objConnection = ConfigDB.OpenConnection();
        try {
            String sql = "SELECT * FROM Coder WHERE cv LIKE ?";
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1,"%"+cv+"%");
            ResultSet objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()){
                Coder objCoder = new Coder();
                objCoder.setId(objResultSet.getInt(1));
                objCoder.setNombre(objResultSet.getString(2));
                objCoder.setApellido(objResultSet.getString(3));
                objCoder.setDocumento(objResultSet.getString(4));
                objCoder.setCohorte(objResultSet.getInt(5));
                objCoder.setCv(objResultSet.getString(6));
                objCoder.setClan(objResultSet.getString(7));
                ListCoder.add(objCoder);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
        }
        ConfigDB.CloseConnection();
        return ListCoder;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.OpenConnection();
        Coder objCoder = (Coder) obj;
        boolean isDelete = false;
        try {
            String sql = "DELETE FROM Coder WHERE id = ?";
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setInt(1, objCoder.getId());
            int totalRowAffected = objPreparedStatement.executeUpdate();
            if(totalRowAffected > 0){
                JOptionPane.showMessageDialog(null, "Coder eliminado con exito");
                isDelete = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
        }
        return isDelete;
    }

}
