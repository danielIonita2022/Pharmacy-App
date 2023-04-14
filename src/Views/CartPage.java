import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class CartPage {

    private Customer customer;
    private ArrayList<Medicine> shoppingCart;
    private Double totalPrice;

    private void truncatePrice(){
        String sValue = (String) String.format("%.2f", totalPrice);
        totalPrice = Double.parseDouble(sValue);
    }

    CartPage(Customer customer){
        this.customer = customer;
        shoppingCart = new ArrayList<>();
        totalPrice = 0.0;
    }

    void addToCart(Medicine medicine){
        shoppingCart.add(medicine);
    }

    public JPanel cartPanel(){
        JPanel mainPanel = new JPanel(new GridLayout(3,1));
        JPanel cartPanel = new JPanel(new GridLayout(shoppingCart.size(), 2));
        JPanel pricePanel = new JPanel(new GridBagLayout());
        JPanel buttonPanel = new JPanel(new GridBagLayout());

        for(Medicine medicine : shoppingCart){
            JLabel name = new JLabel(medicine.getName());
            JLabel price = new JLabel(medicine.getPrice().substring(1) + " RON");
            StringBuilder realPrice = new StringBuilder();
            for(int i = 1; i < medicine.getPrice().length(); ++i)
                realPrice.append(medicine.getPrice().charAt(i));
            totalPrice += Double.parseDouble(String.valueOf(realPrice));
            cartPanel.add(name);
            cartPanel.add(price);
        }

        truncatePrice();
        JLabel totalPriceLabel = new JLabel("TOTAL: " + totalPrice.toString() + " RON");
        pricePanel.add(totalPriceLabel);

        JButton button = new JButton("Plasati comanda");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Order order = new Order("19/01/2023");
                try {
                    DBConnection dbcon = new DBConnection();
                    Connection con = dbcon.getConn();
                    PreparedStatement ps = con.prepareStatement("INSERT INTO \"Order\" VALUES (?, ?, ?)");

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                JFrame orderPlaced = new JFrame("Succes");
                JPanel orderPanel = new JPanel(new FlowLayout());
                JLabel orderMsg = new JLabel("Comanda plasata cu succes!");
                orderPanel.add(orderMsg);
                orderPanel.setVisible(true);
                orderPlaced.add(orderPanel);
                orderPlaced.setVisible(true);
                orderPlaced.setSize(300,300);
            }

        });
        buttonPanel.add(button);

        cartPanel.setVisible(true);
        buttonPanel.setVisible(true);
        pricePanel.setVisible(true);
        mainPanel.add(cartPanel);
        mainPanel.add(pricePanel);
        mainPanel.add(buttonPanel);
        mainPanel.setVisible(true);
        return mainPanel;
    }
}
