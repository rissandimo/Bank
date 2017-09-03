package view;

import model.Client;
import model.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class AccessAccount extends JFrame // works
{
    private DatabaseConnection connection; // made private

    private JButton checkAcctInfo;
    private JButton deposit;
    private JButton logOut;
    private JButton submit;
    private JButton withdrawal;

    private JPanel panelBottom;
    private JPanel panelCenter;
    private JPanel panelTop;

    private JTextArea results;

    private String firstName, lastName, social;

    private ArrayList<Integer> clients = new ArrayList<>();

    //Testing

/*
    public static void main(String[] args)
    {
        new AccessAccount("Omid","Nassir","123456789");
    }
*/




    //made access package-private
     AccessAccount(String clientFirstName, String clientLastName, String clientSocial)
    {
        System.out.println("\"Access account\"");
        System.out.println(clientFirstName + clientLastName + clientSocial);
        firstName = clientFirstName;
        lastName = clientLastName;
        social = clientSocial;

        //TOP PANEL
        deposit = new JButton("Deposit");
        withdrawal = new JButton("Withdrawal");
        checkAcctInfo = new JButton("Account Info");
        logOut = new JButton("Log Out");

        panelTop = new JPanel();
        panelTop.add(deposit);
        panelTop.add(withdrawal);
        panelTop.add(checkAcctInfo);
        panelTop.add(logOut);

        add(panelTop, BorderLayout.NORTH);

        //CENTER PANEL

        results = new JTextArea(50,50);
        panelCenter = new JPanel();
        panelCenter.add(results);

        add(panelCenter, BorderLayout.CENTER);


        //BOTTOM PANEL
        submit = new JButton("Submit");
        panelBottom = new JPanel();
        panelBottom.add(submit);
        add(panelBottom, BorderLayout.SOUTH);


        setSize(650, 600);
        setVisible(true);
        setTitle("Access account");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        connection = new DatabaseConnection();
        int ssn = Integer.parseInt(social);
        connectToDatabase(ssn);
    }

    private void connectToDatabase(int ssn)
    {
        System.out.println("Attempt to connect to database");

        Connection bankConnection = connection.createConnectionToDatabase();
        System.out.println("connection successful");

        try
        {

        String selectCustomers = "SELECT first_name, last_name, ssn FROM clients where ssn = ?";

        PreparedStatement preparedStatement = bankConnection.prepareStatement(selectCustomers);

        preparedStatement.setInt(1, ssn);


        ResultSet resultSet = preparedStatement.executeQuery();



       while(resultSet.next())
       {
           results.append("Client info: ");
           results.append(resultSet.getString(1) + " " + resultSet.getString(2) + "\n" +
           "Account number: " + resultSet.getString(3) + "\n");
       }


        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

  /*      //list social security numbers
        for(Integer client: clients)
        {
            System.out.println(client);
        }
*/

    }


}
