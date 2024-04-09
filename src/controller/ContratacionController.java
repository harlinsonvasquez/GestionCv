package controller;
import entity.Contratacion;
import entity.Coder;
import entity.Empresa;
import entity.Vacante;
import java.time.LocalDate;
import model.ContratacionModel;
import model.CoderModel;
import model.EmpresaModel;
import model.VacanteModel;
import javax.swing.JOptionPane;
import java.util.List;


public class ContratacionController {
    public static void getAll() {
        ContratacionModel objContratacionModel = new ContratacionModel();
        String ListContratacion = "Lista de Contrataciones\n";
        for (Object iterador : objContratacionModel.findAll()) {
            Contratacion objContratacion = (Contratacion) iterador;
            ListContratacion += objContratacion.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, ListContratacion);
    }
    public static void insert(){
        String ListVacantes = VacanteController.getAllString();
        int id_vacante = Integer.parseInt(JOptionPane.showInputDialog(ListVacantes + "\n Ingrese el ID de la vacante:"));
        String ListCoders = CoderController.getAllString();
        int id_coder = Integer.parseInt(JOptionPane.showInputDialog(ListCoders + "\n Ingrese el ID del coder:"));
        //comprobar con el metodo contains si alguna palabra de la columna cv de la tabla coder coincide con alguna palabra de la columna tecnologia de la tabla vacante

        try{
            String estado = JOptionPane.showInputDialog("Ingrese el estado de la contratacion: ACTIVA O PENDIENTE");
            double salario = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el salario de la contratacion:"));

            Contratacion objContratacion = new Contratacion();


            Coder objCoder = (Coder) new CoderModel().findById(id_coder);
            Vacante objVacante = (Vacante) new VacanteModel().findById(id_vacante);

            objContratacion.setVacante(objVacante);
            objContratacion.setCoder(objCoder);
            objContratacion.setEstado(estado);
            objContratacion.setSalario(salario);
            objContratacion.setFecha_contratacion(LocalDate.now());
            ContratacionModel objContratacionModel = new ContratacionModel();


            if (objCoder.getCv().contains(objVacante.getTecnologia())){
                JOptionPane.showMessageDialog(null,"El coder cumple con los requisitos de la vacante");
                objContratacionModel.insert(objContratacion);
            }else {
                JOptionPane.showMessageDialog(null,"El coder no cumple con los requisitos de la vacante");
            }

        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Error: Ingrese un ID de vacante o coder válido.");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al ingresar la contratacion: "+e.getMessage());
        }

    }
    public static String getAllString(){
        ContratacionModel objContratacionModel = new ContratacionModel();
        StringBuilder ListContrataciones = new StringBuilder("Lista de Contrataciones\n");
        for(Object iterador: objContratacionModel.findAll()){
            Contratacion objContratacion = (Contratacion) iterador;
            ListContrataciones.append(objContratacion.getId()).append(" ").append(objContratacion.getVacante().getTitulo()).append(" ").append(objContratacion.getCoder().getNombre()).append(" ").append(objContratacion.getCoder().getApellido()).append(" ").append(objContratacion.getFecha_contratacion()).append(" ").append(objContratacion.getEstado()).append(" ").append(objContratacion.getSalario()).append("\n");
        }
        return ListContrataciones.toString();
    }
    public static void update(){
        ContratacionModel objContratacionModel = new ContratacionModel();
        String ListContrataciones = getAllString();
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(ListContrataciones + "\n Ingrese el ID de la contratacion a actualizar:"));
        Contratacion objContratacion = objContratacionModel.findById(idUpdate);
        if(objContratacion==null){
            JOptionPane.showMessageDialog(null,"Contratacion no encontrada");
        }else{
            String estado = JOptionPane.showInputDialog("Ingrese el estado de la contratacion:", objContratacion.getEstado());
            double salario = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el salario de la contratacion:", objContratacion.getSalario()));

            objContratacion.setEstado(estado);
            objContratacion.setSalario(salario);
            objContratacionModel.update(objContratacion);
        }
    }
    //metodo cambiar estado con el metodo cambiarestado de contratacionmodel
    public static void cambiarEstado() {
        ContratacionModel objContratacionModel = new ContratacionModel();
        String listContrataciones = getAllString();
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listContrataciones + "\nIngrese el ID de la contratación a cambiar de estado:"));

        // Verificar si la contratación existe antes de intentar cambiar su estado
        Contratacion objContratacion = objContratacionModel.findById(idUpdate);
        if (objContratacion == null) {
            JOptionPane.showMessageDialog(null, "Contratación no encontrada");
        } else {
            String estado = JOptionPane.showInputDialog("Ingrese el nuevo estado de la contratación para ID " + objContratacion.getId() + ":", objContratacion.getEstado());
            // Verificar si el estado no está vacío antes de intentar cambiarlo
            if (estado != null && !estado.trim().isEmpty()) {
                if (objContratacionModel.cambiarEstado(idUpdate, estado)) {
                    JOptionPane.showMessageDialog(null, "Estado de la contratación actualizado con éxito");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo actualizar el estado de la contratación");
                }
            } else {
                JOptionPane.showMessageDialog(null, "El estado no puede estar vacío");
            }
        }
    }

}
