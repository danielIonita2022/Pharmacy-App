import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class LoginPage {

    private Customer customer;
    public Customer getCustomer(){return customer;}
    public LoginPage(){

    }
    public JPanel LoginPanel(){
        JPanel firstPanel = new JPanel(new FlowLayout());
        JLabel welcomeText = new JLabel("Bun venit pe pagina de autentificare!");
        welcomeText.setFont(new Font("Arial", Font.BOLD, 20));
        firstPanel.add(welcomeText);

        JPanel secondPanel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        secondPanel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel usernameLabel = new JLabel("Utilizator:  ",SwingConstants.RIGHT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.EAST;
        layout.setConstraints(usernameLabel, gbc);
        secondPanel.add(usernameLabel);


        JTextField usernameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        layout.setConstraints(usernameField, gbc);
        secondPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Parola:  ",SwingConstants.RIGHT);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        layout.setConstraints(passwordLabel, gbc);
        secondPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        layout.setConstraints(passwordField, gbc);
        secondPanel.add(passwordField);

        JPanel thirdPanel = new JPanel(new FlowLayout());
        JButton loginButton = new JButton("Intra in cont");

        thirdPanel.add(loginButton);
        JPanel mainPanel = new JPanel(new GridLayout(3,1));
        mainPanel.add(firstPanel);
        mainPanel.add(secondPanel);
        mainPanel.add(thirdPanel);

        customer = new Customer();
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBConnection dbConnection;
                ResultSet resultSet = null;
                try {
                    dbConnection = new DBConnection();
                    Connection conn = dbConnection.getConn();
                    PreparedStatement preparedStatement;
                    preparedStatement = conn.prepareStatement("SELECT * FROM \"User\" WHERE \"UserName\" = ? AND \"Password\" = ?");
                    preparedStatement.setString(1, usernameField.getText());
                    preparedStatement.setString(2, Arrays.toString(passwordField.getPassword()));

                    resultSet = preparedStatement.executeQuery();
                    while(resultSet.next()){
                        customer.setfirstName(resultSet.getString("FirstName"));
                        customer.setlastName(resultSet.getString("LastName"));
                        customer.setAge(resultSet.getInt("Age"));
                        customer.setUsername(resultSet.getString("UserName"));
                        customer.setPassword(resultSet.getString("Password"));
                        customer.setEmail(resultSet.getString("Email"));
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                if(customer.getAge() != 0) {
                    JFrame loginSuccess = new JFrame("Success!");
                    JPanel loginPanel = new JPanel();
                    JLabel loginMsg = new JLabel("Ati intrat in cont cu succes!");
                    loginMsg.setSize(30, 30);
                    loginSuccess.setSize(250, 250);
                    loginPanel.add(loginMsg);
                    loginSuccess.add(loginPanel);
                    loginSuccess.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    loginSuccess.setVisible(true);
                    loginButton.setEnabled(false);
                }
                else {
                    JFrame loginFailure = new JFrame("Error!");
                    JPanel loginPanel = new JPanel();
                    JLabel loginMsg = new JLabel("Utilizator sau parola incorecta!");
                    loginMsg.setSize(30,30);
                    loginFailure.setSize(250,250);
                    loginPanel.add(loginMsg);
                    loginFailure.add(loginPanel);
                    loginFailure.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    loginFailure.setVisible(true);
                }
            }
        });
        return mainPanel;
    }
}
