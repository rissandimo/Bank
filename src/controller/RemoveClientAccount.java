package controller;

import model.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RemoveClientAccount
{
    private DatabaseConnection bankConnection;
    private static int ACCOUNT_NUMBER;


    public static void main(String[] args)
    {
        new RemoveClientAccount();
    }

    public RemoveClientAccount()
    {
        bankConnection = new DatabaseConnection();
        new Frame();
    }



    private void removeClient(int ACCOUNT_NUMBER)
    {
        Connection connection = bankConnection.createConnectionToDatabase();

        String removeClientCK_WITHDRAWAL = "DELETE FROM checking_withdrawl where account_number = ?";
        String removeClientCK_DEPOSIT = "DELETE FROM checking_deposit where account_number = ?";
        String removeClientCK_ACCT = "DELETE FROM checking_account where account_number = ?";
        String removeClient = "DELETE FROM clients where account_number = ?";

        try
        {
                            //CHECKING WITHDRAWAL
        PreparedStatement preparedStatement = connection.prepareStatement(removeClientCK_WITHDRAWAL);

        preparedStatement.setInt(1, ACCOUNT_NUMBER);

        preparedStatement.execute();

                            //CHECKING DEPOSIT
        preparedStatement = connection.prepareStatement(removeClientCK_DEPOSIT);

        preparedStatement.setInt(1, ACCOUNT_NUMBER);

        preparedStatement.execute();

        System.out.println("client removed from checking_deposit");

                            //CHECKING ACCOUNT
        preparedStatement = connection.prepareStatement(removeClientCK_ACCT);

        preparedStatement.setInt(1, ACCOUNT_NUMBER);

        preparedStatement.execute();

        System.out.println("client removed from checking_account");

                            //CLIENT
        preparedStatement = connection.prepareStatement(removeClient);

        preparedStatement.setInt(1, ACCOUNT_NUMBER);

        preparedStatement.execute();

        System.out.println("client removed from clients");

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }


    }

    class Frame extends JFrame
    {
        public JTextField input;
        private JTextArea results;

        public Frame()
        {
            createView();
            displayClients();
        }

        public void createView()
        {
            setTitle("Remove Client");


            //CENTER PANEL - Text Area
            results = new JTextArea(50,50);
            JPanel panelCenter = new JPanel();
            panelCenter.add(results);

            add(panelCenter, BorderLayout.CENTER);

            //BOTTOM PANEL - Submit Buttons & Text Input
            input = new JTextField(15);
            JButton submit = new JButton("Submit");
            submit.addActionListener(new RemoveClientListener());


            JPanel panelBottom = new JPanel();
            panelBottom.add(input);
            panelBottom.add(submit);
            add(panelBottom, BorderLayout.SOUTH);


            setSize(650, 600);
            setVisible(true);

            setLocationRelativeTo(null);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }

        public void displayClients()
        {
            Connection connection = bankConnection.createConnectionToDatabase();

            try
            {
                Statement statement = connection.createStatement();

                String clientStatement = "SELECT first_name, last_name, account_number FROM clients";

                ResultSet resultSet = statement.executeQuery(clientStatement);

                results.append("List of clients in bank, please select one and click submit \n \n");

                while(resultSet.next())
                {
                    results.append(resultSet.getString(1) + ", " +
                            resultSet.getString(2) + "-" +
                            "Account #: " +
                            resultSet.getString(3) + "\n");
                }

            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }

        class RemoveClientListener implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                removeClient(Integer.parseInt(input.getText()));
            }
        }
    }// close Frame

} // close RemoveClientAccount
