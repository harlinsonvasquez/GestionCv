package model;
import database.CRUD;
import database.ConfigDB;
import entity.Vacante;
import entity.Empresa;
import javax.swing.*;
import javax.swing.plaf.PanelUI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class VacanteModel implements CRUD{
    @Override
    public List<Object> findAll() {
        List<Object> ListVacante=new ArrayList<>();
        Connection objConnection= ConfigDB.OpenConnection();
        try{
            String sql="SELECT v.id, v.empresa_id, v.titulo, v.descripcion, v.duracion, v.estado, v.tecnologia, e.nombre AS nombre_empresa " +
                    "FROM Vacante AS v " +
                    "INNER JOIN Empresa AS e ON v.empresa_id = e.id;";
            PreparedStatement objPreparedStatement=objConnection.prepareStatement(sql);
            ResultSet objResultSet=objPreparedStatement.executeQuery();
            while (objResultSet.next()){
                Vacante objVacante=new Vacante();
                objVacante.setId(objResultSet.getInt(1));
                Empresa objEmpresa=new Empresa();
                objEmpresa.setId(objResultSet.getInt(2));
                objVacante.setTitulo(objResultSet.getString(3));
                objVacante.setDescripcion(objResultSet.getString(4));
                objVacante.setDuracion(objResultSet.getString(5));
                objVacante.setEstado(objResultSet.getString(6));
                objVacante.setTecnologia(objResultSet.getString(7));
                objEmpresa.setNombre(objResultSet.getString(8));
                objVacante.setId_empresa(objEmpresa);

                ListVacante.add(objVacante);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.CloseConnection();
        return ListVacante;
    }

    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.OpenConnection();
        Vacante objVacante = (Vacante) obj;
        try {
            String sql = "INSERT INTO Vacante(empresa_id, titulo, descripcion, duracion, estado, tecnologia) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objPreparedStatement.setInt(1, objVacante.getId_empresa().getId());
            objPreparedStatement.setString(2, objVacante.getTitulo());
            objPreparedStatement.setString(3, objVacante.getDescripcion());
            objPreparedStatement.setString(4, objVacante.getDuracion());
            objPreparedStatement.setString(5, objVacante.getEstado());
            objPreparedStatement.setString(6, objVacante.getTecnologia());
            objPreparedStatement.execute();
            ResultSet objResultSet = objPreparedStatement.getGeneratedKeys();
            while (objResultSet.next()) {
                objVacante.setId(objResultSet.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Vacante insertado con exito");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
        }
        ConfigDB.CloseConnection();
        return objVacante;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.OpenConnection();
        Vacante objVacante = (Vacante) obj;
        boolean isUpdated = false;
        try {
            String sql = "UPDATE Vacante SET empresa_id = ?, titulo = ?, descripcion = ?, duracion = ?, estado = ?, tecnologia = ? WHERE id = ?";
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setInt(1, objVacante.getId_empresa().getId());
            objPreparedStatement.setString(2, objVacante.getTitulo());
            objPreparedStatement.setString(3, objVacante.getDescripcion());
            objPreparedStatement.setString(4, objVacante.getDuracion());
            objPreparedStatement.setString(5, objVacante.getEstado());
            objPreparedStatement.setString(6, objVacante.getTecnologia());
            objPreparedStatement.setInt(7, objVacante.getId());
            int rowsAffected = objPreparedStatement.executeUpdate();
            if (rowsAffected >0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Vacante actualizado con éxito");
            } else {
                JOptionPane.showMessageDialog(null, "No se actualizó ninguna fila para la vacante");
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
        Connection objConnection = ConfigDB.OpenConnection();
        Vacante objVacante = (Vacante) obj;
        boolean isDeleted = false;
        try {
            String sql = "DELETE FROM Vacante WHERE id = ?";
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setInt(1, objVacante.getId());
            int rowsAffected = objPreparedStatement.executeUpdate();
            if (rowsAffected >0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Vacante eliminado con éxito");
            } else {
                JOptionPane.showMessageDialog(null, "No se eliminó ninguna fila para la vacante");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } finally {
            ConfigDB.CloseConnection();
        }
        return false;
    }
    public Vacante findById(int id){
        Connection objConnection=ConfigDB.OpenConnection();
        Vacante objVacante=null;
        try {
            String sql="SELECT v.id, v.empresa_id, v.titulo, v.descripcion, v.duracion, v.estado, v.tecnologia, e.nombre AS nombre_empresa " +
                    "FROM Vacante AS v " +
                    "INNER JOIN Empresa AS e ON v.empresa_id = e.id " +
                    "WHERE v.id = ?";
            PreparedStatement objPrepareStatement=objConnection.prepareStatement(sql);
            objPrepareStatement.setInt(1,id);
            ResultSet objResultSet=objPrepareStatement.executeQuery();
            if(objResultSet.next()){
                objVacante=new Vacante();
                objVacante.setId(objResultSet.getInt(1));
                Empresa objEmpresa=new Empresa();
                objEmpresa.setId(objResultSet.getInt(2));
                objVacante.setTitulo(objResultSet.getString(3));
                objVacante.setDescripcion(objResultSet.getString(4));
                objVacante.setDuracion(objResultSet.getString(5));
                objVacante.setEstado(objResultSet.getString(6));
                objVacante.setTecnologia(objResultSet.getString(7));
                objEmpresa.setNombre(objResultSet.getString(8));
                objVacante.setId_empresa(objEmpresa);

            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
        }

    return objVacante;
    }
    public List<Vacante> findByEmpresa(int id){
        List<Vacante> ListVacante=new ArrayList<>();
        Connection objConnection=ConfigDB.OpenConnection();
        try {
            String sql="SELECT v.id, v.empresa_id, v.titulo, v.descripcion, v.duracion, v.estado, v.tecnologia, e.nombre AS nombre_empresa " +
                    "FROM Vacante AS v " +
                    "INNER JOIN Empresa AS e ON v.empresa_id = e.id " +
                    "WHERE v.empresa_id = ?";
            PreparedStatement objPrepareStatement=objConnection.prepareStatement(sql);
            objPrepareStatement.setInt(1,id);
            ResultSet objResultSet=objPrepareStatement.executeQuery();
            while (objResultSet.next()){
                Vacante objVacante=new Vacante();
                objVacante.setId(objResultSet.getInt(1));
                Empresa objEmpresa=new Empresa();
                objEmpresa.setId(objResultSet.getInt(2));
                objVacante.setTitulo(objResultSet.getString(3));
                objVacante.setDescripcion(objResultSet.getString(4));
                objVacante.setDuracion(objResultSet.getString(5));
                objVacante.setEstado(objResultSet.getString(6));
                objVacante.setTecnologia(objResultSet.getString(7));
                objEmpresa.setNombre(objResultSet.getString(8));
                objVacante.setId_empresa(objEmpresa);
                ListVacante.add(objVacante);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
        }
        ConfigDB.CloseConnection();
        return ListVacante;
    }
    public List<Vacante>BuscarporTecnologia(String tecnologia){
        List<Vacante> ListVacante=new ArrayList<>();
        Connection objConnection=ConfigDB.OpenConnection();
        try {
            String sql="SELECT v.id, v.empresa_id, v.titulo, v.descripcion, v.duracion, v.estado, v.tecnologia, e.nombre AS nombre_empresa " +
                    "FROM Vacante AS v " +
                    "INNER JOIN Empresa AS e ON v.empresa_id = e.id " +
                    "WHERE v.tecnologia LIKE ?";
            PreparedStatement objPrepareStatement=objConnection.prepareStatement(sql);
            objPrepareStatement.setString(1,"%"+tecnologia+"%");
            ResultSet objResultSet=objPrepareStatement.executeQuery();
            while (objResultSet.next()){
                Vacante objVacante=new Vacante();
                objVacante.setId(objResultSet.getInt(1));
                Empresa objEmpresa=new Empresa();
                objEmpresa.setId(objResultSet.getInt(2));
                objVacante.setTitulo(objResultSet.getString(3));
                objVacante.setDescripcion(objResultSet.getString(4));
                objVacante.setDuracion(objResultSet.getString(5));
                objVacante.setEstado(objResultSet.getString(6));
                objVacante.setTecnologia(objResultSet.getString(7));
                objEmpresa.setNombre(objResultSet.getString(8));
                objVacante.setId_empresa(objEmpresa);
                ListVacante.add(objVacante);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
        }
        ConfigDB.CloseConnection();
        return ListVacante;
    }
}
