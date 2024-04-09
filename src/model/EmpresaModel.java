package model;

import database.CRUD;
import database.ConfigDB;
import entity.Empresa;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EmpresaModel implements CRUD {
    @Override
    public List<Object> findAll() {
        //CREAR LISTA DE OBJETOS
        List<Object> ListEmpresa=new ArrayList<>();
        //CONEXION A LA BASE DE DATOS
        Connection objConnection= ConfigDB.OpenConnection();
        try {
            //CONSULTA SQL
            String sql = "SELECT * FROM Empresa;";
            //PREPARAR LA CONSULTA
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            //EJECUTAR LA CONSULTA
            ResultSet objResultSet = objPreparedStatement.executeQuery();
            //ITERAR EL RESULTADO DE LA CONSULTA
            while (objResultSet.next()) {
                //CREAR OBJETO DE TIPO EMPRESA
                Empresa objEmpresa = new Empresa();
                //ASIGNAR VALORES AL OBJETO
                objEmpresa.setId(objResultSet.getInt(1));
                objEmpresa.setNombre(objResultSet.getString(2));
                objEmpresa.setSector(objResultSet.getString(3));
                objEmpresa.setUbicacion(objResultSet.getString(4));
                objEmpresa.setContacto(objResultSet.getString(5));
                //AGREGAR EL OBJETO A LA LISTA
                ListEmpresa.add(objEmpresa);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.CloseConnection();


        return ListEmpresa;
    }

    @Override
    public Object insert(Object obj) {
        //CONEXION A LA BASE DE DATOS
        Connection objConnection = ConfigDB.OpenConnection();
        //CONVERTIR EL OBJETO A TIPO EMPRESA
        Empresa objEmpresa = (Empresa) obj;
        try {
            //CONSULTA SQL
            String sql = "INSERT INTO Empresa(nombre, sector, ubicacion, contacto) VALUES(?, ?, ?, ?)";
            //PREPARAR LA CONSULTA
            PreparedStatement objPrepareStatement = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //SETAR LOS VALORES A LOS PARAMETROS DE LA CONSULTA
            objPrepareStatement.setString(1, objEmpresa.getNombre());
            objPrepareStatement.setString(2, objEmpresa.getSector());
            objPrepareStatement.setString(3, objEmpresa.getUbicacion());
            objPrepareStatement.setString(4, objEmpresa.getContacto());
            //EJECUTAR LA CONSULTA
            objPrepareStatement.execute();
            //OBTENER EL RESULTADO DE LA CONSULTA
            ResultSet objResultSet = objPrepareStatement.getGeneratedKeys();
            //ITERAR EL RESULTADO DE LA CONSULTA
            while (objResultSet.next()) {
                //OBTENER EL ID GENERADO
                objEmpresa.setId(objResultSet.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Empresa insertada con exito");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public boolean update(Object obj) {
        //CONEXION A LA BASE DE DATOS
        Connection objConnection = ConfigDB.OpenConnection();
        //CONVERTIR EL OBJETO A TIPO EMPRESA
        Empresa objEmpresa = (Empresa) obj;
        boolean isUdate = false;
        try {
            //CONSULTA SQL
            String sql = "UPDATE Empresa SET nombre = ?, sector = ?, ubicacion = ?, contacto = ? WHERE id = ?";
            //PREPARAR LA CONSULTA
            PreparedStatement objPrepareStatement = objConnection.prepareStatement(sql);
            //SETAR LOS VALORES A LOS PARAMETROS DE LA CONSULTA
            objPrepareStatement.setString(1, objEmpresa.getNombre());
            objPrepareStatement.setString(2, objEmpresa.getSector());
            objPrepareStatement.setString(3, objEmpresa.getUbicacion());
            objPrepareStatement.setString(4, objEmpresa.getContacto());
            objPrepareStatement.setInt(5, objEmpresa.getId());
            //EJECUTAR LA CONSULTA
            int totalRowAffected = objPrepareStatement.executeUpdate();
            if (totalRowAffected > 0){
                JOptionPane.showMessageDialog(null, "Empresa actualizada con exito");
                isUdate = true;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

        return isUdate;
    }
    public Empresa findById(int id){
        //CONEXION A LA BASE DE DATOS
        Connection objConnection=ConfigDB.OpenConnection();
        //CREAR OBJETO DE TIPO EMPRESA
        Empresa objEmpresa=null;
        try {
            //CONSULTA SQL
            String sql="SELECT * FROM Empresa WHERE id=?;";
            //PREPARAR LA CONSULTA
            PreparedStatement objPrepareStatement=objConnection.prepareStatement(sql);
            //SETAR LOS VALORES A LOS PARAMETROS DE LA CONSULTA
            objPrepareStatement.setInt(1,id);
            //EJECUTAR LA CONSULTA
            ResultSet objResultSet=objPrepareStatement.executeQuery();
            //ITERAR EL RESULTADO DE LA CONSULTA
            while (objResultSet.next()){
                //CREAR OBJETO DE TIPO EMPRESA
                objEmpresa=new Empresa();
                //ASIGNAR VALORES AL OBJETO
                objEmpresa.setId(objResultSet.getInt(1));
                objEmpresa.setNombre(objResultSet.getString(2));
                objEmpresa.setSector(objResultSet.getString(3));
                objEmpresa.setUbicacion(objResultSet.getString(4));
                objEmpresa.setContacto(objResultSet.getString(5));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.CloseConnection();
        return objEmpresa;
    }

    @Override
    public boolean delete(Object obj) {
        //CONEXION A LA BASE DE DATOS
        Connection objConnection = ConfigDB.OpenConnection();
        //CONVERTIR EL OBJETO A TIPO EMPRESA
        Empresa objEmpresa = (Empresa) obj;
        boolean isDelete = false;
        try {
            //CONSULTA SQL
            String sql = "DELETE FROM Empresa WHERE id = ?";
            //PREPARAR LA CONSULTA
            PreparedStatement objPrepareStatement = objConnection.prepareStatement(sql);
            //SETAR LOS VALORES A LOS PARAMETROS DE LA CONSULTA
            objPrepareStatement.setInt(1, objEmpresa.getId());
            //EJECUTAR LA CONSULTA
            int result = objPrepareStatement.executeUpdate();
            if (result > 0){
                JOptionPane.showMessageDialog(null, "Empresa eliminada con exito");
                isDelete = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return isDelete;
    }
}
