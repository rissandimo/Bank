package view;

import controller.RemoveClientAccount;

import javax.swing.*;
import java.awt.*;

/**
 * Allows customer to access existing account or to create a new one
 */
public class WelcomeScreen extends JFrame
{

    public WelcomeScreen()
    {
        new AccessAccountFrame();
    }

        class AccessAccountFrame extends JFrame
        {
            public AccessAccountFrame()
            {
                setSize(500, 200);
                setLocationRelativeTo(null);
                setTitle("Nassir Bank");
                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                //BUTTONS

                JButton buttonAccessAccount = new JButton("Access Account");
                buttonAccessAccount.addActionListener(e ->
                {
                    dispose();
                    new OpenAccount();
                });

                JButton buttonCreateAccount = new JButton("Create Account");
                buttonCreateAccount.addActionListener(e ->
                {
                    dispose();
                    new CreateAccount();
                });

                JButton buttonDeleteAccount = new JButton("Delete Account");
                buttonDeleteAccount.addActionListener(e ->
                {
                    dispose();
                    new RemoveClientAccount();
                });

                //LABELS

                JLabel labelSelection = new JLabel("Please make a selection below");


                //PANELS
                JPanel buttonsPanel = new JPanel();
                JPanel labelPanel =   new JPanel();


                // ADD COMPONENTS TO FRAME

                buttonsPanel.add(buttonAccessAccount);
                buttonsPanel.add(buttonCreateAccount);
                buttonsPanel.add(buttonDeleteAccount);

                labelPanel.add(labelSelection);

                add(buttonsPanel, BorderLayout.SOUTH);
                add(labelPanel, BorderLayout.CENTER);

                setVisible(true);
            }
        }
    }
