import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SearchPage {

    private int searchedItem;
    private Medicine medicine = null;
    private Customer customer;
    SearchPage(Customer customer){
        this.customer = customer;
    }

    private void performSearch(String searchQuery){
        DBConnection dbConnection;
        try{
            dbConnection = new DBConnection();
            Connection conn = dbConnection.getConn();
            PreparedStatement cs = conn.prepareStatement("SELECT * FROM \"Medicine\" WHERE \"Name\" = ?");
            cs.setString(1, searchQuery);
            ResultSet rs = cs.executeQuery();
            Medicine newMedicine;
            while(rs.next()){
                String name = rs.getString("Name");
                String quantity = rs.getString("Quantity");
                String type = rs.getString("Type");
                String price = rs.getString("Price");
                String brand = rs.getString("Brand");
                String category = rs.getString("Category");
                String supply = rs.getString("Supply");
                String measureUnit = rs.getString("MeasureUnit");
                newMedicine = new Medicine(name, Integer.parseInt(quantity), type, price, brand, category, Integer.parseInt(supply), measureUnit);
                medicine = newMedicine;
            }
        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public JPanel searchPanel(){
        JPanel mainPanel = new JPanel(new FlowLayout());
        JTextField searchField = new JTextField(30);
        JButton searchButton = new JButton("Cauta");
        mainPanel.add(searchField);
        mainPanel.add(searchButton);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchQuery = searchField.getText();
                medicine = null;
                performSearch(searchQuery);
                if(medicine != null) {
                    JFrame medicineFrame = new JFrame("Medicine");
                    medicineFrame.setSize(900,200);
                    JPanel medicinePanel;
                    if(customer != null) {
                        medicinePanel = new JPanel(new GridLayout(1, 8));
                        ImageIcon icon = new ImageIcon("C:\\Users\\Dani\\Desktop\\ANUL2\\MIP\\Pharmacy\\src\\cart.png");
                        JButton buyButton = new JButton(icon);
                        JPanel buttonPanel = new JPanel(new GridBagLayout());
                        GridBagConstraints c = new GridBagConstraints();
                        c.gridx = GridBagConstraints.RELATIVE;
                        c.gridy = GridBagConstraints.RELATIVE;
                        c.anchor = GridBagConstraints.CENTER;
                        buttonPanel.add(buyButton, c);
                        buttonPanel.setVisible(true);
                        medicinePanel.add(buttonPanel);
                        buyButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                MainMenu.cartPage.addToCart(medicine);
                            }
                        });
                    }
                    else {
                        medicinePanel = new JPanel(new GridLayout(1, 7));
                    }
                        JLabel name = new JLabel(medicine.getName());
                        JLabel quantity = new JLabel(String.valueOf(medicine.getQuantity()));
                        JLabel type = new JLabel(medicine.getType());
                        JLabel price = new JLabel(medicine.getPrice().substring(1) + " RON");
                        JLabel brand = new JLabel(medicine.getBrand());
                        JLabel category = new JLabel(medicine.getCategory());
                        JLabel measureUnit = new JLabel(medicine.getMeasureUnit());
                        medicinePanel.add(name);
                        medicinePanel.add(quantity);
                        medicinePanel.add(type);
                        medicinePanel.add(price);
                        medicinePanel.add(brand);
                        medicinePanel.add(category);
                        medicinePanel.add(measureUnit);

                    medicinePanel.setVisible(true);
                    medicineFrame.add(medicinePanel);
                    medicineFrame.setVisible(true);
                }
                else {
                    JFrame errorFrame = new JFrame("Error");
                    errorFrame.setSize(400,200);
                    JPanel errorPanel = new JPanel();
                    JLabel errorMsg = new JLabel("No medicine found!");
                    errorPanel.add(errorMsg);
                    errorFrame.add(errorPanel);
                    errorFrame.setVisible(true);
                }
            }
        });
        return mainPanel;
    }
}
