package model;
import database.CRUD;
import database.ConfigDB;
import entity.Vacante;
import entity.Empresa;
import entity.Coder;
import entity.Contratacion;
import javax.swing.*;
import javax.swing.plaf.PanelUI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ContratacionModel implements CRUD {
    @Override
    public List<Object> findAll() {
        //traer los datos de la tabla contrataciones id,id_vacante,coder_id,fecha_contratacion,estado,salario  tambien los datos de coder y vacante y nombre de la empresa que contrata
        List<Object> ListContratacion=new ArrayList<>();
        Connection objConnection=ConfigDB.OpenConnection();
        try{
            String sql="SELECT Contratacion.id,Contratacion.vacante_id,Contratacion.coder_id,Contratacion.fecha_contratacion,Contratacion.estado,Contratacion.salario,Vacante.titulo AS nombre_vacante,Empresa.nombre AS nombre_empresa,Coder.nombre AS " +
                    "nombre_coder,Coder.apellidos,Empresa.id AS apellido_coder FROM Contratacion INNER JOIN Vacante ON Contratacion.vacante_id=Vacante.id INNER JOIN Empresa ON Vacante.empresa_id=Empresa.id INNER JOIN Coder ON Contratacion.coder_id=Coder.id";
            PreparedStatement objPreparedStatement=objConnection.prepareStatement(sql);
            ResultSet objResultSet=objPreparedStatement.executeQuery();
            while(objResultSet.next()){

                Contratacion objContratacion=new Contratacion();
                objContratacion.setId(objResultSet.getInt(1));
                Vacante objVacante=new Vacante();
                objVacante.setId(objResultSet.getInt(2));
                objVacante.setTitulo(objResultSet.getString(7));
                objContratacion.setVacante(objVacante);
                Empresa objEmpresa=new Empresa();
                objEmpresa.setNombre(objResultSet.getString(8));
                objEmpresa.setId(objResultSet.getInt(11));
                objVacante.setId_empresa(objEmpresa);
                objContratacion.setVacante(objVacante);
                objContratacion.setEmpresa(objEmpresa);
                Coder objCoder=new Coder();
                objCoder.setId(objResultSet.getInt(3));
                objCoder.setNombre(objResultSet.getString(9));
                objCoder.setApellido(objResultSet.getString(10));
                objContratacion.setCoder(objCoder);
                objContratacion.setFecha_contratacion(objResultSet.getDate(4).toLocalDate());
                objContratacion.setEstado(objResultSet.getString(5));
                objContratacion.setSalario(objResultSet.getDouble(6));
                ListContratacion.add(objContratacion);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return ListContratacion;
    }

    @Override
    public Object insert(Object obj) {
        //solo agregar una contratacion si alguna palabra de cv en la tabla coder coincide con alguna palabra de la tecnolicia en la tabla vacante
        Connection objConnection = ConfigDB.OpenConnection();
        Contratacion objContratacion = (Contratacion) obj;
        //solo agregar una contratacion si alguna palabra de cv en la tabla coder coincide con alguna palabra de la tecnolicia en la tabla vacante
        try {
            String sql = "INSERT INTO Contratacion(vacante_id,coder_id,fecha_contratacion,estado,salario) VALUES(?, ?, ?, ?,?)";
            PreparedStatement objPrepareStatement = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepareStatement.setInt(1, objContratacion.getVacante().getId());
            objPrepareStatement.setInt(2, objContratacion.getCoder().getId());
            objPrepareStatement.setDate(3, java.sql.Date.valueOf(objContratacion.getFecha_contratacion()));
            objPrepareStatement.setString(4, objContratacion.getEstado());
            objPrepareStatement.setDouble(5, objContratacion.getSalario());
            objPrepareStatement.execute();
            ResultSet objResultSet = objPrepareStatement.getGeneratedKeys();
            while (objResultSet.next()) {
                objContratacion.setId(objResultSet.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Contratacion insertado con exito");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        ConfigDB.CloseConnection();

        return objContratacion;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.OpenConnection();
        Contratacion objContratacion = (Contratacion) obj;
        boolean isUpdated = false;
        try {
            String sql = "UPDATE Contratacion SET vacante_id=?, coder_id=?, fecha_contratacion=?, estado=?, salario=? WHERE id=?";
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setInt(1, objContratacion.getVacante().getId());
            objPreparedStatement.setInt(2, objContratacion.getCoder().getId());
            objPreparedStatement.setDate(3, java.sql.Date.valueOf(objContratacion.getFecha_contratacion()));
            objPreparedStatement.setString(4, objContratacion.getEstado());
            objPreparedStatement.setDouble(5, objContratacion.getSalario());
            objPreparedStatement.setInt(6, objContratacion.getId());
            int rowsAffected = objPreparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Contratacion actualizado con éxito");
            } else {
                JOptionPane.showMessageDialog(null, "No se actualizó ninguna fila para la contratacion");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } finally {
            ConfigDB.CloseConnection();
        }
        return isUpdated;
    }
    public Contratacion findById(int id){
        Connection objConnection = ConfigDB.OpenConnection();
        Contratacion objContratacion = new Contratacion();
        try {
            String sql = "SELECT * FROM Contratacion WHERE id=?";
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setInt(1, id);
            ResultSet objResultSet = objPreparedStatement.executeQuery();
            while (objResultSet.next()) {
                objContratacion.setId(objResultSet.getInt(1));
                Vacante objVacante = new Vacante();
                objVacante.setId(objResultSet.getInt(2));
                objContratacion.setVacante(objVacante);
                Coder objCoder = new Coder();
                objCoder.setId(objResultSet.getInt(3));
                objContratacion.setCoder(objCoder);
                objContratacion.setFecha_contratacion(objResultSet.getDate(4).toLocalDate());
                objContratacion.setEstado(objResultSet.getString(5));
                objContratacion.setSalario(objResultSet.getDouble(6));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } finally {
            ConfigDB.CloseConnection();
        }
        return objContratacion;
    }
    //metodo para cambiar estado de las contrataciones solo por id
    public boolean cambiarEstado(int id,String estado){
        Connection objConnection = ConfigDB.OpenConnection();
        boolean isUpdated = false;
        try {
            String sql = "UPDATE Contratacion SET estado=? WHERE id=?";
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, estado);
            objPreparedStatement.setInt(2, id);
            int rowsAffected = objPreparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Contratacion actualizado con éxito");
            } else {
                JOptionPane.showMessageDialog(null, "No se actualizó ninguna fila para la contratacion");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } finally {
            ConfigDB.CloseConnection();
        }
        return isUpdated;
    }


    @Override
    public boolean delete(Object obj) {
        return false;
    }


}

