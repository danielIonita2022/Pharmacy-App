import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class AllMedicinePage {

    private Customer customer;
    AllMedicinePage(Customer customer){
        this.customer = customer;
    }
    public JPanel allMedicinePanel(){
        DBConnection dbConnection;
        ResultSet resultSet;
        ArrayList<Medicine> arrayMedicine = new ArrayList<>();
        try{
            dbConnection = new DBConnection();
            Connection conn = dbConnection.getConn();
            PreparedStatement preparedStatement;
            preparedStatement = conn.prepareStatement("SELECT * FROM \"Medicine\"");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Medicine medicine = new Medicine();
                medicine.setName(resultSet.getString("Name"));
                medicine.setQuantity(Integer.parseInt(resultSet.getString("Quantity")));
                medicine.setType(resultSet.getString("Type"));
                medicine.setPrice(resultSet.getString("Price"));
                medicine.setBrand(resultSet.getString("Brand"));
                medicine.setCategory(resultSet.getString("Category"));
                medicine.setSupply(resultSet.getInt("Supply"));
                medicine.setMeasureUnit(resultSet.getString("MeasureUnit"));
                arrayMedicine.add(medicine);
            }
        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        JPanel mainPanel = new JPanel(new GridLayout(arrayMedicine.size(),7));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel newMainPanel = new JPanel();
        newMainPanel.setLayout(new BoxLayout(newMainPanel, BoxLayout.Y_AXIS));
        for(Medicine medicine : arrayMedicine){
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
            mainPanel.add(medicinePanel);
        };
        mainPanel.setVisible(true);
        return mainPanel;
    }
}
