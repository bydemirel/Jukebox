import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuyMembership extends JFrame{
    private JButton homeButton;
    private JPanel panelMain;
    private JComboBox comboBox1;
    private JTextField password;
    private JLabel paymentType;
    private JButton OKButton;
    private JTextField username;
    private JTextField password2;
    DbService db = new DbService();
    boolean isCreditCard = true;
    boolean otherPaymentMethods = false;

    public BuyMembership(Jukebox jukebox, Customer customer) {

        super("Buy Membership");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setContentPane(this.panelMain);
        paymentType.setVisible(true);
        paymentType.setText("Enter your credit card number!");
        password.setVisible(true);
        OKButton.setVisible(true);


        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Kiwify(jukebox,customer).setVisible(true);
                dispose();
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBox1.getSelectedItem() == "Credit Card")
                {
                    paymentType.setVisible(true);
                    paymentType.setText("Enter your credit card number!");
                    password.setVisible(true);
                    isCreditCard = true;
                    OKButton.setVisible(true);
                    otherPaymentMethods = false;
                }
                if(comboBox1.getSelectedItem() == "Gift Card")
                {
                    paymentType.setVisible(true);
                    paymentType.setText("Enter your gift card number!");
                    password.setVisible(true);
                    isCreditCard = false;
                    OKButton.setVisible(true);
                    otherPaymentMethods = true;
                }
                if(comboBox1.getSelectedItem() == "Coupon")
                {
                    paymentType.setVisible(true);
                    paymentType.setText("Enter your coupon number!");
                    password.setVisible(true);
                    isCreditCard = false;
                    OKButton.setVisible(true);
                    otherPaymentMethods = true;
                }
            }
        });
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(db.membershipValidity(customer,username.getText(),password2.getText()))
                {
                    String warnings = "This username and password is taken. Please find another username/password!";
                    new ErrorMessage(warnings).setVisible(true);
                }
                else if(password.getText().isEmpty() || password2.getText().isEmpty() || username.getText().isEmpty())
                {
                    String warnings = "Please enter a valid input!";
                    new ErrorMessage(warnings).setVisible(true);
                }
                else if(!(username.getText().length() <= 16 && username.getText().length() >= 4))
                {
                    new ErrorMessage("Username Error!(Username should be 4-16 character)").setVisible(true);
                }
                else if(!(password2.getText().length() <= 16 && password2.getText().length() >= 4))
                {
                    new ErrorMessage("Password Error!(Password should be 4-16 character)").setVisible(true);
                }
                else if(isCreditCard)
                {
                    for (int i = 0; i <password.getText().length() ; i++)
                    {
                        if(!Character.isDigit(password.getText().charAt(i)) || password.getText().length() != 16)
                        {
                            String warnings = "Please enter a valid credit card number(No Letter and 16 Character)";
                            new ErrorMessage(warnings).setVisible(true);
                            break;
                        }
                        else
                        {
                            String warnings = "You sucessfully created new account!";
                            new ErrorMessage(warnings).setVisible(true);
                            Customer new_customer = db.addMembership(username.getText(),password2.getText());
                            new_customer.setMembership(true);
                            new Kiwify(jukebox,new_customer).setVisible(true);
                            dispose();
                        }

                    }
                }
                else if(otherPaymentMethods)
                {
                    if(password.getText().length() != 6)
                    {
                        String warnings = "Your gift card code or coupon code must be include 6 character!";
                        new ErrorMessage(warnings).setVisible(true);
                    }
                    else
                    {
                        Customer new_customer = db.addMembership(username.getText(),password2.getText());
                        new_customer.setMembership(true);
                        new Kiwify(jukebox,new_customer).setVisible(true);
                        dispose();
                    }
                }
                else
                {
                    String warnings = "You sucessfully created new account!";
                    new ErrorMessage(warnings).setVisible(true);
                    Customer new_customer = db.addMembership(username.getText(),password2.getText());
                    new_customer.setMembership(true);
                    new Kiwify(jukebox,new_customer).setVisible(true);
                    dispose();
                }

            }
        });
    }


}
