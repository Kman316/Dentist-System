import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;



public class DBInfo {

    public static void main(String [] args) throws Exception{
        getConnection();
    }


    public static Connection getConnection(){
        Connection con = null;


        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/dentistdb";
            con = DriverManager.getConnection(url, "root","");


        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return con;


    }


    public static int savePatient(Patient patient){

        int st = 0;

        try {
            String sql = "INSERT INTO `patients` (`id`, `name`, `address`, `phonenumber`) VALUES (?,?,?,?,?)";
            Connection con = DBInfo.getConnection();
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
            preparedStatement.setInt(1, patient.getID());
            preparedStatement.setString(2, patient.getName());
            preparedStatement.setString(3, patient.getAddress());
            preparedStatement.setString(4, patient.getPhoneNo());

            st = preparedStatement.executeUpdate();

            con.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return st;

    }

    public static int saveProcedure(Procedure proc){

        int st = 0;

        try {
            String sql = "INSERT INTO `dentistprocedures` (`id`, `pname`, `pcost`) VALUES (?,?,?)";
            Connection con = DBInfo.getConnection();
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
            preparedStatement.setInt(1, proc.getProcNo());
            preparedStatement.setString(2, proc.getProcName());
            preparedStatement.setDouble(3, proc.getProcCost());

            st = preparedStatement.executeUpdate();

            con.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return st;

    }

    public static int savePayment(Payment payment, Patient patient){

        int st = 0;

        try {
            String sql = "INSERT INTO `payments` (`paymentno`, `name`, `paymentamount`, `date_of_payment`) VALUES (?,?,?,?)";
            Connection con = DBInfo.getConnection();
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
            preparedStatement.setInt(1, payment.getPaymentNo());
            preparedStatement.setString(2, patient.getName());
            preparedStatement.setDouble(3, payment.getPaymentAmt());
            preparedStatement.setString(4, String.valueOf(payment.getPaymentDate()));

            st = preparedStatement.executeUpdate();

            con.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return st;

    }

    public static int saveInvoice(Invoice invoice, Payment payment, Procedure proc, Patient patient){

        int st = 0;

        try {
            String sql = "INSERT INTO `invoices`(`invoiceID`, `invoiceAmnt`, `invoiceLeftToPay`, `date`, `isPaid`, `patientID`) VALUES (?,?,?,?,?,?)";
            Connection con = DBInfo.getConnection();
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
            preparedStatement.setInt(1, invoice.getInvoiceNo());
            preparedStatement.setString(2, proc.getProcName());
            preparedStatement.setDouble(3, payment.getPaymentAmt());
            preparedStatement.setString(4, String.valueOf(invoice.getInvoiceDate()));
            preparedStatement.setBoolean(5, invoice.isPaid());
            preparedStatement.setInt(6, patient.getID());


            st = preparedStatement.executeUpdate();

            con.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return st;

    }

    public static int updatePatient(Patient patient){

        int st = 0;

        try {
            String sql = "UPDATE `patients` SET `id`=?, `fname`=?,`lname`=?,`address`=?,`phone`=? WHERE 1";
            Connection con = DBInfo.getConnection();
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
            preparedStatement.setString(1, patient.getName());
            preparedStatement.setString(2, patient.getAddress());
            preparedStatement.setString(3, patient.getPhoneNo());
            preparedStatement.setInt(4, patient.getID());

            st = preparedStatement.executeUpdate();

            con.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return st;

    }

    public static int updateProcedure(Procedure proc){

        int st = 0;

        try {
            String sql = "UPDATE `dentistprocedures` SET `id`=?, `pname`=?,`pcost`=? WHERE 1";
            Connection con = DBInfo.getConnection();
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
            preparedStatement.setInt(1, proc.getProcNo());
            preparedStatement.setString(2, proc.getProcName());
            preparedStatement.setDouble(3, proc.getProcCost());


            st = preparedStatement.executeUpdate();

            con.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return st;

    }

    public static int updatePayment(Payment payment, Patient patient){

        int st = 0;

        try {
            String sql = "UPDATE `payments` SET `paymentno`=?, `fname`=?,`lname`=?,`paymentamount`=?,`date_of_payment`=?,`date`=? WHERE 1";
            Connection con = DBInfo.getConnection();
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
            preparedStatement.setString(1, patient.getName());
            preparedStatement.setString(2, patient.getAddress());
            preparedStatement.setString(3, patient.getPhoneNo());
            preparedStatement.setInt(4, patient.getID());

            st = preparedStatement.executeUpdate();

            con.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return st;

    }

    public static int updateInvoice(Invoice invoice, Payment payment, Procedure proc){

        int st = 0;

        try {
            String sql = "UPDATE `invoices` SET `id`=?, `procedure`=?,`amount`=?,`date_of_invoice`=?,`date`=? WHERE 1";
            Connection con = DBInfo.getConnection();
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
            preparedStatement.setInt(1, invoice.getInvoiceNo());
            preparedStatement.setString(2, proc.getProcName());
            preparedStatement.setDouble(3, payment.getPaymentAmt());
            preparedStatement.setString(4, String.valueOf(invoice.getInvoiceDate()));
            preparedStatement.setString(5, String.valueOf(invoice.getInvoiceDate()));

            st = preparedStatement.executeUpdate();

            con.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return st;

    }

    public static int deletePatient(int id){

        int st = 0;

        try {
            String sql = "DELETE FROM `patients` WHERE 1";
            Connection con = DBInfo.getConnection();
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            st = preparedStatement.executeUpdate();

            con.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return st;

    }

    public static int deleteProcedure(int id){

        int st = 0;

        try {
            String sql = "DELETE FROM `dentistprocedures` WHERE 1";
            Connection con = DBInfo.getConnection();
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            st = preparedStatement.executeUpdate();

            con.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return st;

    }

    public static int deleteInvoice(int id){

        int st = 0;

        try {
            String sql = "DELETE FROM `invoices` WHERE 1";
            Connection con = DBInfo.getConnection();
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            st = preparedStatement.executeUpdate();

            con.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return st;

    }

    public static int deletePayment(int id){

        int st = 0;

        try {
            String sql = "DELETE FROM `payments` WHERE 1";
            Connection con = DBInfo.getConnection();
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            st = preparedStatement.executeUpdate();

            con.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return st;

    }

    public static Patient  getPatientId(int id){

        Patient patient = new Patient();

        try {
            String sql = "SELECT * FROM `patients` WHERE 1";
            Connection con = DBInfo.getConnection();
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                patient.setID(resultSet.getInt(1));
                patient.setName(resultSet.getString(2));

                patient.setPhoneNo(resultSet.getString(3));
                patient.setAddress(resultSet.getString(4));
            }


            con.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return patient;

    }

    public static Procedure  getProcedureId(int id){

        Procedure proc = new Procedure();

        try {
            String sql = "SELECT * FROM `patients` WHERE 1";
            Connection con = DBInfo.getConnection();
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                proc.setProcNo(resultSet.getInt(1));
                proc.setProcName(resultSet.getString(2));
                proc.setProcCost(resultSet.getDouble(3));
            }


            con.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return proc;

    }

    public static Payment  getPaymentNo(int id){

        Payment payment = new Payment();
        Patient patient = new Patient();

        try {
            String sql = "SELECT * FROM `payments` WHERE 1";
            Connection con = DBInfo.getConnection();
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                payment.setPaymentNo(resultSet.getInt(1));
                patient.setName(resultSet.getString(2));
                payment.setPaymentAmt(resultSet.getDouble(3));
                payment.setPaymentDate(resultSet.getString(4));
            }


            con.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return payment;

    }

    public static Invoice  getInvoiceNo(int id){

        Payment payment = new Payment();
        Invoice invoice = new Invoice();
        Procedure proc = new Procedure();

        try {
            String sql = "SELECT * FROM `invoices` WHERE 1";
            Connection con = DBInfo.getConnection();
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                invoice.setInvoiceNo(resultSet.getInt(1));
                proc.setProcName(resultSet.getString(2));
                payment.setPaymentAmt(resultSet.getDouble(3));
                invoice.setInvoiceDate(resultSet.getString(4));
            }


            con.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return invoice;

    }

    public static List<Patient>  getPatient(){

        List<Patient> list = new ArrayList<Patient>();


        try {
            String sql = "SELECT * FROM `patients` WHERE 1";
            Connection con = DBInfo.getConnection();
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                Patient patient = new Patient();

                patient.setID(resultSet.getInt(1));
                patient.setName(resultSet.getString(2));
                patient.setPhoneNo(resultSet.getString(3));
                patient.setAddress(resultSet.getString(4));
                list.add(patient);

            }


            con.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return list;

    }

    public static List<Procedure>  getProcedure(){

        List<Procedure> proclist = new ArrayList<Procedure>();


        try {
            String sql = "SELECT * FROM `dentistprocedures` WHERE 1";
            Connection con = DBInfo.getConnection();
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                Procedure proc = new Procedure();

                proc.setProcNo(resultSet.getInt(1));
                proc.setProcName(resultSet.getString(2));
                proc.setProcCost(resultSet.getDouble(3));
                proclist.add(proc);

            }


            con.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return proclist;

    }

    public static List<Payment>  getPayment(){

        List<Payment> list = new ArrayList<Payment>();


        try {
            String sql = "SELECT * FROM `payments` WHERE 1";
            Connection con = DBInfo.getConnection();
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                Patient patient = new Patient();
                Payment payment = new Payment();

                payment.setPaymentNo(resultSet.getInt(1));
                patient.setName(resultSet.getString(2));
                payment.setPaymentAmt(resultSet.getDouble(3));
                payment.setPaymentDate(resultSet.getString(4));
                list.add(payment);

            }


            con.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return list;

    }

    public static List<Invoice>  getInvoice(){

        List<Invoice> list = new ArrayList<Invoice>();


        try {
            String sql = "SELECT * FROM `invoices` WHERE 1";
            Connection con = DBInfo.getConnection();
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                Invoice invoice = new Invoice();
                Payment payment = new Payment();
                Procedure proc = new Procedure();

                invoice.setInvoiceNo(resultSet.getInt(1));
                proc.setProcName(resultSet.getString(2));
                payment.setPaymentAmt(resultSet.getDouble(3));
                invoice.setInvoiceDate(resultSet.getString(4));
                list.add(invoice);

            }


            con.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return list;

    }

}
