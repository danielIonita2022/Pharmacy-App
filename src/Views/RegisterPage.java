import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Arrays;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class RegisterPage {

    public RegisterPage(){

    }
    public JPanel registerPanel() {

        JPanel firstPanel = new JPanel(new FlowLayout());
        JLabel welcomeText = new JLabel("Bun venit pe pagina de inregistrare!");
        welcomeText.setFont(new Font("Arial", Font.BOLD, 20));
        firstPanel.add(welcomeText);

        JPanel secondPanel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        secondPanel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel firstNameLabel = new JLabel("Prenume:  ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.EAST;
        layout.setConstraints(firstNameLabel, gbc);
        secondPanel.add(firstNameLabel);

        JTextField firstNameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        firstNameField.setMinimumSize(new Dimension(150,20));
        layout.setConstraints(firstNameField, gbc);
        secondPanel.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Nume:  ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        layout.setConstraints(lastNameLabel, gbc);
        secondPanel.add(lastNameLabel);

        JTextField lastNameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        layout.setConstraints(lastNameField, gbc);
        secondPanel.add(lastNameField);

        JLabel usernameLabel = new JLabel("Utilizator:  ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        layout.setConstraints(usernameLabel, gbc);
        secondPanel.add(usernameLabel);

        JTextField usernameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        layout.setConstraints(usernameField, gbc);
        secondPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Parola:  ");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        layout.setConstraints(passwordLabel, gbc);
        secondPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        layout.setConstraints(passwordField, gbc);
        secondPanel.add(passwordField);

        JLabel emailLabel = new JLabel("Email:  ");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        layout.setConstraints(emailLabel, gbc);
        secondPanel.add(emailLabel);

        JTextField emailField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        layout.setConstraints(emailField, gbc);
        secondPanel.add(emailField);

        JLabel ageLabel = new JLabel("Varsta:  ");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        layout.setConstraints(ageLabel, gbc);
        secondPanel.add(ageLabel);

        JTextField ageField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        layout.setConstraints(ageField, gbc);
        secondPanel.add(ageField);


        JPanel thirdPanel = new JPanel(new FlowLayout());
        JButton submitButton = new JButton("Inregistrare");

        //thirdPanel.setBackground(new Color(0, 128, 0));
        thirdPanel.add(submitButton);
        thirdPanel.setPreferredSize(new Dimension(20,20));

        JPanel mainPanel = new JPanel(new GridLayout(3,1));
        mainPanel.add(firstPanel);
        mainPanel.add(secondPanel);
        mainPanel.add(thirdPanel);
        
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBConnection dbConnection;
                try {
                    dbConnection = new DBConnection();
                    Connection conn = dbConnection.getConn();
                    PreparedStatement preparedStatement;
                    preparedStatement = conn.prepareStatement("INSERT INTO \"User\"(\"FirstName\", \"LastName\", \"Age\", \"UserName\", \"Password\", \"Email\") VALUES (?, ?, ?, ?, ?, ?)");
                    preparedStatement.setString(1,firstNameField.getText());
                    preparedStatement.setString(2,lastNameField.getText());
                    preparedStatement.setInt(3,Integer.parseInt(ageField.getText()));
                    preparedStatement.setString(4, usernameField.getText());
                    preparedStatement.setString(5, Arrays.toString(passwordField.getPassword()));
                    preparedStatement.setString(6,emailField.getText());
                    preparedStatement.executeUpdate();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                JFrame registerSuccess = new JFrame("Success!");
                JPanel registerPanel = new JPanel();
                JLabel registerMsg = new JLabel("Ati fost inregistrat cu succes!");
                registerMsg.setSize(30,30);
                registerSuccess.setSize(250,250);
                registerPanel.add(registerMsg);
                registerSuccess.add(registerPanel);
                registerSuccess.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                registerSuccess.setVisible(true);
                submitButton.setEnabled(false);
            }
        });
        
        return mainPanel;
    }
}
