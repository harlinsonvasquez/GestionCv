package controller;
import model.CoderModel;
import javax.swing.*;
import java.util.List;
import entity.Coder;

public class CoderController {
    public static void getAll() {
        CoderModel objCoderModel = new CoderModel();
        String ListCoder = "Lista de Coders\n";
        for (Object iterador : objCoderModel.findAll()) {
            Coder objCoder = (Coder) iterador;
            ListCoder += objCoder.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, ListCoder);
    }

    public static void insert() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del coder:");
        String apellido = JOptionPane.showInputDialog("Ingrese el apellido del coder:");
        String documento = JOptionPane.showInputDialog("Ingrese el documento del coder:");
        int cohorte = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cohorte del coder:"));
        String cv = JOptionPane.showInputDialog("Ingrese el cv del coder:");
        String clan = JOptionPane.showInputDialog("Ingrese el clan del coder:");
        Coder objCoder = new Coder();
        objCoder.setNombre(nombre);
        objCoder.setApellido(apellido);
        objCoder.setDocumento(documento);
        objCoder.setCohorte(cohorte);
        objCoder.setCv(cv);
        objCoder.setClan(clan);
        CoderModel objCoderModel = new CoderModel();
        objCoderModel.insert(objCoder);
    }
    public static String getAllString() {
        CoderModel objCoderModel = new CoderModel();
        String ListCoder = "Lista de Coders\n";
        for (Object iterador : objCoderModel.findAll()) {
            Coder objCoder = (Coder) iterador;
            ListCoder += objCoder.toString() + "\n";
        }
        return ListCoder;
    }
    public static void update(){
        CoderModel objCoderModel = new CoderModel();
        String ListCoder = getAllString();
        int id = Integer.parseInt(JOptionPane.showInputDialog(ListCoder + "Ingrese el id del coder a modificar:"));
        Coder objCoder = objCoderModel.findById(id);
        if(objCoder==null){
            JOptionPane.showMessageDialog(null,"Coders no encontrado");
        }else{
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del coder:",objCoder.getNombre());
            String apellido = JOptionPane.showInputDialog("Ingrese el apellido del coder:",objCoder.getApellido());
            String documento = JOptionPane.showInputDialog("Ingrese el documento del coder:",objCoder.getDocumento());
            int cohorte = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cohorte del coder:",objCoder.getCohorte()));
            String cv = JOptionPane.showInputDialog("Ingrese el cv del coder:",objCoder.getCv());
            String clan = JOptionPane.showInputDialog("Ingrese el clan del coder:",objCoder.getClan());
            objCoder.setNombre(nombre);
            objCoder.setApellido(apellido);
            objCoder.setDocumento(documento);
            objCoder.setCohorte(cohorte);
            objCoder.setCv(cv);
            objCoder.setClan(clan);
            objCoderModel.update(objCoder);
        }
    }
    public static void search(){
        CoderModel objCoderModel = new CoderModel();
        String SearchTecnologia = JOptionPane.showInputDialog("Ingrese la tecnologia para ver el listado de coder que la manejan:");
        List<Coder> listaCoders = objCoderModel.findByTegnologia(SearchTecnologia);
        if(listaCoders.isEmpty()){
            JOptionPane.showMessageDialog(null,"Tecnologia no encontrada");
        }else{
            StringBuilder ListCoders = new StringBuilder("Lista de Coders\n");
            for(Coder objCoder: listaCoders){
                ListCoders.append(objCoder.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null,ListCoders.toString());
        }
    }
    public static void searchClan(){
        CoderModel objCoderModel = new CoderModel();
        String SearchClan = JOptionPane.showInputDialog("Ingrese el clan para ver el listado de coder que pertenecen a el:");
        List<Coder> listaCoders = objCoderModel.findByClan(SearchClan);
        if(listaCoders.isEmpty()){
            JOptionPane.showMessageDialog(null,"Clan no encontrado");
        }else{
            StringBuilder ListCoders = new StringBuilder("Lista de Coders\n");
            for(Coder objCoder: listaCoders){
                ListCoders.append(objCoder.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null,ListCoders.toString());
        }
    }
    public static void delete(){
        CoderModel objCoderModel = new CoderModel();
        String ListCoder = getAllString();
        int id = Integer.parseInt(JOptionPane.showInputDialog(ListCoder + "Ingrese el id del coder a eliminar:"));
        Coder objCoder = objCoderModel.findById(id);
        if(objCoder==null){
            JOptionPane.showMessageDialog(null,"Coders no encontrado");
        }else{
            objCoderModel.delete(objCoder);
        }
    }
}
