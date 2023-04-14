import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.Objects;

public class MainMenu extends JFrame {
    private static JFrame menu;
    private static JPanel panel;
    private static boolean isLoggedIn;
    private JMenuItem login;
    private JMenuItem register;
    private JMenuItem profile;
    private JMenuItem cart;
    private JMenuItem allMedicine;
    private JMenuItem search;
    public static CartPage cartPage;

    private static Customer customer;

    public MainMenu(){
        menu = new JFrame("Farmacie");
        menu.setSize(900, 600);
        menu.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JMenu userMenu = new JMenu("Utilizator");
        JMenu userDetails = new JMenu("Detalii cont");
        JMenu medicine = new JMenu("Medicamente fara reteta");

        login = new JMenuItem("Intra in cont");
        register = new JMenuItem("Inregistreaza-te");

        profile = new JMenuItem("Profilul tau");
        cart = new JMenuItem("Cos de cumparaturi");

        allMedicine = new JMenuItem("Lista tuturor produselor");
        search = new JMenuItem("Cautare");

        userMenu.setLayout(new FlowLayout());
        userMenu.add(login);
        userMenu.add(register);

        userDetails.setLayout(new FlowLayout());
        userDetails.add(profile);
        userDetails.add(cart);

        medicine.setLayout(new FlowLayout());
        medicine.add(allMedicine);
        medicine.add(search);

        menuBar.add(userMenu);
        menuBar.add(userDetails);
        menuBar.add(medicine);

        menu.setLocationRelativeTo(null);
        menu.setJMenuBar(menuBar);

        GridBagConstraints textConstraints = new GridBagConstraints();
        textConstraints.gridwidth = GridBagConstraints.REMAINDER;
        textConstraints.anchor = GridBagConstraints.CENTER;

        GridBagConstraints imageConstraints = new GridBagConstraints();
        imageConstraints.gridwidth = GridBagConstraints.REMAINDER;
        imageConstraints.anchor = GridBagConstraints.CENTER;

        panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);

        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("photo2.jpg")));
        JLabel logoLabel = new JLabel(logo);
        JLabel textLabel = new JLabel("Bine ati venit!");
        textLabel.setFont(new Font("Arial", Font.BOLD, 36));

        panel.add(textLabel, textConstraints);
        panel.add(logoLabel, imageConstraints);
        panel.setVisible(true);

        menu.add(panel);
        menu.setVisible(true);

    }
    public static void main(String[] args) throws SQLException {
        DBConnection db = new DBConnection();
        MainMenu mainMenu = new MainMenu();
        mainMenu.login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.remove(panel);
                LoginPage customerPage = new LoginPage();
                panel = customerPage.LoginPanel();
                menu.add(panel);
                menu.revalidate();
                menu.repaint();
                customer = customerPage.getCustomer();
                cartPage = new CartPage(customer);
            }
        });
        mainMenu.register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.remove(panel);
                RegisterPage registerPage = new RegisterPage();
                panel = registerPage.registerPanel();
                menu.add(panel);
                menu.revalidate();
                menu.repaint();
            }
        });
        mainMenu.allMedicine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.remove(panel);
                AllMedicinePage allMedicinePage = new AllMedicinePage(customer);
                panel = allMedicinePage.allMedicinePanel();
                menu.add(panel);
                menu.revalidate();
                menu.repaint();
            }
        });
        mainMenu.profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(customer != null) {
                    if (customer.getAge() != 0) {
                        menu.remove(panel);
                        Profile profilePage = new Profile();
                        panel = profilePage.ProfilePanel(customer);
                        menu.add(panel);
                        menu.revalidate();
                        menu.repaint();
                    }
                }
            }
        });
        mainMenu.search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.remove(panel);
                SearchPage searchPage = new SearchPage(customer);
                panel = searchPage.searchPanel();
                menu.add(panel);
                menu.revalidate();
                menu.repaint();
            }
        });
        mainMenu.cart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cartPage != null) {
                    menu.remove(panel);
                    panel = cartPage.cartPanel();
                    menu.add(panel);
                    menu.revalidate();
                    menu.repaint();
                }
            }
        });
    }
}
