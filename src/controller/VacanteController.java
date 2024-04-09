package controller;
import entity.Empresa;
import entity.Vacante;
import model.VacanteModel;
import javax.swing.*;
import java.util.List;


public class VacanteController {
    public static void getAll() {
        VacanteModel objVacanteModel = new VacanteModel();
        String ListVacante = "Lista de Vacantes\n";
        for (Object iterador : objVacanteModel.findAll()) {
            Vacante objVacante = (Vacante) iterador;
            ListVacante += objVacante.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, ListVacante);
    }
    public static void insert(){
        String ListaEmpresas = EmpresaController.getAllString();
        int id_empresa = Integer.parseInt(JOptionPane.showInputDialog(ListaEmpresas + "\n Ingrese el ID de la empresa:"));
        try {
            String titulo = JOptionPane.showInputDialog("Ingrese el titulo de la vacante:");
            String descripcion = JOptionPane.showInputDialog("Ingrese la descripcion de la vacante:");
            String duracion = JOptionPane.showInputDialog("Ingrese la duracion de la vacante: TIEMPO COMPLETO O TIEMPO PARCIAL");
            String estado = JOptionPane.showInputDialog("Ingrese el estado de la vacante: ACTIVA O INACTIVA" );
            String tecnologia = JOptionPane.showInputDialog("Ingrese la tecnologia de la vacante:");

            Empresa objEmpresa = new Empresa();
            objEmpresa.setId(id_empresa);
            Vacante objVacante = new Vacante();
            objVacante.setId_empresa(objEmpresa);
            objVacante.setTitulo(titulo);
            objVacante.setDescripcion(descripcion);
            objVacante.setDuracion(duracion);
            objVacante.setEstado(estado);
            objVacante.setTecnologia(tecnologia);
            VacanteModel objVacanteModel = new VacanteModel();
            objVacanteModel.insert(objVacante);


        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Error: Ingrese un ID de empresa válido.");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al ingresar la vacante: "+e.getMessage());
        }

    }
    public static String getAllString() {
        VacanteModel objVacanteModel = new VacanteModel();
        String ListVacante = "Lista de Vacantes\n";
        for (Object iterador : objVacanteModel.findAll()) {
            Vacante objVacante = (Vacante) iterador;
            ListVacante += objVacante.toString() + "\n";
        }
        return ListVacante;
    }
    public static void update(){
        VacanteModel objVacanteModel = new VacanteModel();
        String ListVacante = getAllString();
        String ListEmpresas = EmpresaController.getAllString();
        int id = Integer.parseInt(JOptionPane.showInputDialog(ListVacante + "Ingrese el id de la vacante a modificar:"));
        Vacante objVacante = objVacanteModel.findById(id);
        if(objVacante==null){
            JOptionPane.showMessageDialog(null,"Vacante no encontrada");
        }else{
            int id_empresa = Integer.parseInt(JOptionPane.showInputDialog(ListEmpresas+ "Ingrese el id de la empresa:",objVacante.getId_empresa().getId()));
            String titulo = JOptionPane.showInputDialog("Ingrese el titulo de la vacante:",objVacante.getTitulo());
            String descripcion = JOptionPane.showInputDialog("Ingrese la descripcion de la vacante:",objVacante.getDescripcion());
            String duracion = JOptionPane.showInputDialog("Ingrese la duracion de la vacante: (TIEMPÓ PARCIAL O TIEMPO COMPLETO)",objVacante.getDuracion());
            String estado = JOptionPane.showInputDialog("Ingrese el estado de la vacante:",objVacante.getEstado());
            String tecnologia = JOptionPane.showInputDialog("Ingrese la tecnologia de la vacante:",objVacante.getTecnologia());

            Empresa objEmpresa = new Empresa();
            objEmpresa.setId(id_empresa);


            objVacante.setId_empresa(objEmpresa);
            objVacante.setTitulo(titulo);
            objVacante.setDescripcion(descripcion);
            objVacante.setDuracion(duracion);
            objVacante.setEstado(estado);
            objVacante.setTecnologia(tecnologia);
            objVacanteModel.update(objVacante);
        }
    }
    public static void delete(){
        VacanteModel objVacanteModel = new VacanteModel();
        String ListVacante = getAllString();
        int id = Integer.parseInt(JOptionPane.showInputDialog(ListVacante + "Ingrese el id de la vacante a eliminar:"));
        Vacante objVacante = objVacanteModel.findById(id);
        if(objVacante==null){
            JOptionPane.showMessageDialog(null,"Vacante no encontrada");
        }else{
            objVacanteModel.delete(objVacante);
        }
    }
    public static void searchTecnologia(){
        VacanteModel objVacanteModel=new VacanteModel();
        String tecnologia=JOptionPane.showInputDialog("Ingrese la tecnologia para ver las vacantes:");
        List<Vacante> listVacante=objVacanteModel.BuscarporTecnologia(tecnologia);
        if(listVacante.isEmpty()){
            JOptionPane.showMessageDialog(null,"No se encontraron vacantes con la tecnologia: "+tecnologia);
        }else{
            StringBuilder ListVacante=new StringBuilder("Lista de vacantes\n");
            for(Vacante objVacante:listVacante){
                ListVacante.append(objVacante.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null,ListVacante.toString());
        }
    }
    public static void searchPorEmpresa(){
        VacanteModel objVacanteModel=new VacanteModel();
        String ListEmpresas=EmpresaController.getAllString();
        int id_empresa=Integer.parseInt(JOptionPane.showInputDialog(ListEmpresas+"Ingrese el id de la empresa para ver las vacantes:"));
        List<Vacante> listVacante=objVacanteModel.findByEmpresa(id_empresa);
        if(listVacante.isEmpty()){
            JOptionPane.showMessageDialog(null,"No se encontraron vacantes con la empresa: "+id_empresa);
        }else{
            StringBuilder ListVacante=new StringBuilder("Lista de vacantes\n");
            for(Vacante objVacante:listVacante){
                ListVacante.append(objVacante.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null,ListVacante.toString());
        }
    }

}
