package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {

    static  Connection ObjConnection=null;

    public static Connection OpenConnection(){
        try{
            //cargar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //establecer la conexion
            String url="jdbc:mysql://bcux5m1hhdq3rgq1b6rw-mysql.services.clever-cloud.com:3306/bcux5m1hhdq3rgq1b6rw";
            String user="ucqbsd6m1ursurg1";
            String password="khP6OkMmdhnUlvDKav2J";

            ObjConnection = DriverManager.getConnection(url, user, password);//esta linea utiliza el metodo get y los parametros pasados para establecer la conexion
            System.out.println("Conexion establecida");

        }catch (ClassNotFoundException error){
            System.out.println("DRIVER NO INSTALADO: "+ error.getMessage());
        }catch (SQLException error){
            System.out.println("Error al conectar a la base de datos: "+ error.getMessage());
        }
        return ObjConnection;
    }
    public static void CloseConnection(){
        try{
            if(ObjConnection != null){
                ObjConnection.close();
                System.out.println("Conexion cerrada");
            }
            System.out.println("No hay conexion que cerrar");
        }catch (SQLException error){
            System.out.println("Error al cerrar la conexion: "+ error.getMessage());
        }
    }


}


