import controller.ContratacionController;
import controller.EmpresaController;
import controller.CoderController;
import controller.VacanteController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String opcionMenuPrincipal;
        do {
            opcionMenuPrincipal = JOptionPane.showInputDialog("Menú Principal\n" +
                    "1. Empresas\n" +
                    "2. Vacantes\n" +
                    "3. Contrataciones\n" +
                    "4. Coders\n" +
                    "5. Salir\n" +
                    "Elija una opción:");

            switch (opcionMenuPrincipal) {
                case "1":
                    menuEmpresas();
                    break;
                case "2":
                    menuVacantes();
                    break;
                case "3":
                    menuContratacion();
                    break;
                case "4":
                    menuCoder();
                    break;
                case "5":
                    JOptionPane.showMessageDialog(null, "Saliendo del programa");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida");
                    break;
            }
        } while (!opcionMenuPrincipal.equals("5"));
    }

    private static void menuEmpresas() {
        String opcionMenuEmpresas;
        do {
            opcionMenuEmpresas = JOptionPane.showInputDialog("Menú Empresas\n" +
                    "1. Listar Empresas\n" +
                    "2. Agregar Empresas\n" +
                    "3. Editar Empresas\n" +
                    "4. Eliminar Empresas\n" +
                    "5. Volver al Menú Principal\n" +
                    "Elija una opción:");

            switch (opcionMenuEmpresas) {
                case "1":
                    EmpresaController.getAll();
                    break;
                case "2":

                   EmpresaController.insert();
                    break;
                case "3":

                    EmpresaController.update();
                    break;
                case "4":

                    EmpresaController.delete();
                    break;
                case "5":
                    JOptionPane.showMessageDialog(null, "Volviendo al Menú Principal");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida");
                    break;
            }
        } while (!opcionMenuEmpresas.equals("5"));
    }

    private static void menuVacantes() {
        String opcionMenuVacantes;
        do {
            opcionMenuVacantes = JOptionPane.showInputDialog("Menú Vacantes\n" +
                    "1. Listar Vacantes\n" +
                    "2. Agregar Vacantes\n" +
                    "3. Editar Vacantes\n" +
                    "4. Buscar Vacantes por tecnologias\n" +
                    "5. Buscar Vacantes por empresas\n" +
                    "6. Eliminar Vacantes\n" +
                    "7. Volver al Menú Principal\n" +
                    "Elija una opción:");

            switch (opcionMenuVacantes) {
                case "1":
                    VacanteController.getAll();
                    break;
                case "2":
                    VacanteController.insert();
                    break;
                case "3":
                    VacanteController.update();
                    break;
                case "4":
                    VacanteController.searchTecnologia();
                    break;
                case "5":
                    VacanteController.searchPorEmpresa();
                    break;
                case "6":
                    VacanteController.delete();
                    break;
                case "7":
                    JOptionPane.showMessageDialog(null, "Volviendo al Menú Principal");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida");
                    break;
            }
        } while (!opcionMenuVacantes.equals("7"));
    }

    private static void menuContratacion() {
        String opcionMenuContratacion;
        do {
            opcionMenuContratacion = JOptionPane.showInputDialog("Menú Contrataciones\n" +
                    "1. Listar Contrataciones\n" +
                    "2. Agregar Contrataciones\n" +
                    "3. Editar Contrataciones\n" +
                    "4. Cambiar estado  Contrataciones\n" +
                    "5. Eliminar Contrataciones\n" +
                    "6. Volver al Menú Principal\n" +
                    "Elija una opción:");

            switch (opcionMenuContratacion) {
                case "1":
                  ContratacionController.getAll();
                    break;
                case "2":
                    ContratacionController.insert();
                    break;
                case "3":
                    ContratacionController.update();
                    break;
                case "4":
                    ContratacionController.cambiarEstado();
                    break;
                case "5":
                    //PacienteController.delete();
                    break;
                case "6":
                    JOptionPane.showMessageDialog(null, "Volviendo al Menú Principal");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida");
                    break;
            }
        } while (!opcionMenuContratacion.equals("6"));
    }

    private static void menuCoder() {
        String opcionMenuCoder;
        do {
            opcionMenuCoder = JOptionPane.showInputDialog("Menú Coder\n" +
                    "1. Listar Coder\n" +
                    "2. Agregar Coder\n" +
                    "3. Editar Coder\n" +
                    "4. buscar Coder por tecnologia\n" +
                    "5. buscar Coder por clan\n" +
                    "6. Eliminar Coder\n" +
                    "7. Volver al Menú Principal\n" +
                    "Elija una opción:");

            switch ( opcionMenuCoder) {
                case "1":
                    CoderController.getAll();
                    break;
                case "2":
                    CoderController.insert();
                    break;
                case "3":
                    CoderController.update();
                    break;
                case "4":
                    CoderController.search();
                    break;
                case "5":
                    CoderController.searchClan();
                    break;
                case "6":
                    CoderController.delete();
                    break;

                case "7":
                    JOptionPane.showMessageDialog(null, "Volviendo al Menú Principal");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida");
                    break;
            }
        } while (! opcionMenuCoder.equals("7"));


    }
}