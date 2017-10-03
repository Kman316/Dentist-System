import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;


public class DentistDB {


    static Connection con = null;


    public static Connection getConnection(){



        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/dentistdb";
            con = DriverManager.getConnection(url, "root","");
            //		System.out.println("connected");

        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return con;


    }

    public static List<Dentist>  getDentist(){

        List<Dentist> list = new ArrayList<Dentist>();

        try {
            String sql = "SELECT * FROM `dentists` WHERE 1";
            Connection con = DentistDB.getConnection();
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                Dentist dentist = new Dentist();

                dentist.setPassword(resultSet.getString(1));

                list.add(dentist);

            }


            con.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return list;

    }

}
