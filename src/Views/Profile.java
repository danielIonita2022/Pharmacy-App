import javax.swing.*;
import java.awt.*;

public class Profile {

    private Customer customer;
    public Profile(){

    }

    public JPanel ProfilePanel(Customer loggedCustomer){

        customer = loggedCustomer;

        JPanel firstPanel = new JPanel(new FlowLayout());
        JLabel welcomeText = new JLabel("Profilul tau");
        welcomeText.setFont(new Font("Arial", Font.BOLD, 20));
        firstPanel.add(welcomeText);

        JPanel secondPanel = new JPanel();
        secondPanel.setLayout(new GridLayout(5,2));
        JLabel firstName = new JLabel("Prenume:  ",SwingConstants.RIGHT);
        JLabel firstNamePrinted = new JLabel(customer.getfirstName());

        JLabel lastName = new JLabel("Nume: ", SwingConstants.RIGHT);
        JLabel lastNamePrinted = new JLabel(customer.getlastName());

        JLabel userName = new JLabel("Nume de utilizator: ", SwingConstants.RIGHT);
        JLabel userNamePrinted = new JLabel(customer.getUsername());

        JLabel age = new JLabel("Varsta: ", SwingConstants.RIGHT);
        JLabel agePrinted = new JLabel(String.valueOf(customer.getAge()));

        JLabel email = new JLabel("Email: ", SwingConstants.RIGHT);
        JLabel emailPrinted = new JLabel(customer.getEmail());

        secondPanel.add(firstName);
        secondPanel.add(firstNamePrinted);
        secondPanel.add(lastName);
        secondPanel.add(lastNamePrinted);
        secondPanel.add(userName);
        secondPanel.add(userNamePrinted);
        secondPanel.add(age);
        secondPanel.add(agePrinted);
        secondPanel.add(email);
        secondPanel.add(emailPrinted);

        JPanel thirdPanel = new JPanel(new FlowLayout());

        JPanel mainPanel = new JPanel(new GridLayout(3,1));
        mainPanel.add(firstPanel);
        mainPanel.add(secondPanel);
        mainPanel.add(thirdPanel);

        return mainPanel;
    }
}
