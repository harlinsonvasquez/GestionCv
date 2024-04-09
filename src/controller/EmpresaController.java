package controller;
import entity.Empresa;
import model.EmpresaModel;
import javax.swing.*;
import java.util.List;

public class EmpresaController {
    public static void getAll(){
        EmpresaModel objEmpresaModel=new EmpresaModel();
        String ListEmpresa="Lista de Empresas\n";
        for(Object iterador: objEmpresaModel.findAll()){
            Empresa objEmpresa=(Empresa) iterador;
            ListEmpresa+=objEmpresa.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,ListEmpresa);
    }
    public static void insert(){
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la empresa:");
        String sector = JOptionPane.showInputDialog("Ingrese el sector de la empresa:");
        String ubicacion = JOptionPane.showInputDialog("Ingrese la ubicacion de la empresa:");
        String contacto = JOptionPane.showInputDialog("Ingrese el contacto de la empresa:");
        // Crear un objeto de tipo Empresa para almacenar los nuevos datos
        Empresa objEmpresa = new Empresa();
        // Asignar los valores a los atributos del objeto
        objEmpresa.setNombre(nombre);
        objEmpresa.setSector(sector);
        objEmpresa.setUbicacion(ubicacion);
        objEmpresa.setContacto(contacto);
        // Crear un objeto de tipo EmpresaModel para insertar los datos
        EmpresaModel objEmpresaModel = new EmpresaModel();
        // Insertar los datos al metodo insert de EmpresaModel
        objEmpresaModel.insert(objEmpresa);
    }
    public static String getAllString() {
        EmpresaModel objEmpresaModel = new EmpresaModel();
        StringBuilder ListEmpresas = new StringBuilder("Lista de Empresas\n");
        for (Object iterador : objEmpresaModel.findAll()) {
            Empresa objEmpresa = (Empresa) iterador;
            ListEmpresas.append(objEmpresa.getId()).append(" ").append(objEmpresa.getNombre()).append("\n");
        }
        return ListEmpresas.toString();
    }
    public static void update(){
       EmpresaModel objEmpresaModel = new EmpresaModel();
         String ListEmpresas = getAllString();
         int updateId = Integer.parseInt(JOptionPane.showInputDialog(ListEmpresas + "\nIngrese el id de la empresa a actualizar:"));
         Empresa objEmpresa = objEmpresaModel.findById(updateId);
         if(objEmpresa==null){
             JOptionPane.showMessageDialog(null,"Empresa no encontrada");
         }else{
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la empresa:", objEmpresa.getNombre());
                String sector = JOptionPane.showInputDialog("Ingrese el sector de la empresa:", objEmpresa.getSector());
                String ubicacion = JOptionPane.showInputDialog("Ingrese la ubicacion de la empresa:", objEmpresa.getUbicacion());
                String contacto = JOptionPane.showInputDialog("Ingrese el contacto de la empresa:", objEmpresa.getContacto());

                objEmpresa.setNombre(nombre);
                objEmpresa.setSector(sector);
                objEmpresa.setUbicacion(ubicacion);
                objEmpresa.setContacto(contacto);
                objEmpresaModel.update(objEmpresa);
         }
    }
    public static void delete(){
        EmpresaModel objEmpresaModel = new EmpresaModel();
        String ListEmpresas = getAllString();
        int deleteId = Integer.parseInt(JOptionPane.showInputDialog(ListEmpresas + "\nIngrese el id de la empresa a eliminar:"));
        Empresa objEmpresa = objEmpresaModel.findById(deleteId);
        int comfirm=1;
        if(objEmpresa==null){
            JOptionPane.showMessageDialog(null,"Empresa no encontrada");
        }else{
            comfirm=JOptionPane.showConfirmDialog(null,"¿Estas seguro de eliminar la empresa?\n"+objEmpresa.toString());
            if(comfirm==0) objEmpresaModel.delete(objEmpresa);
        }
    }
}
