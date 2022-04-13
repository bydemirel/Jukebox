import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame {
    private JPanel panelMain;
    private JTextField userNameText;
    private JTextField passText;
    private JButton loginButton;
    private JLabel idField;
    private JLabel passField;
    private JButton loginWithAGuestButton;
    DbService db = new DbService();

    public LoginScreen(Jukebox jukebox, Customer customer)
    {
        super("Login Screen");
        this.setContentPane(this.panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

              boolean validity = db.membershipValidity(customer,userNameText.getText(),passText.getText());

                if(userNameText.getText().isEmpty() || passText.getText().isEmpty() || !validity )
                {
                    String warnings = "You don't have a account in these username and password!";
                    new ErrorMessage(warnings).setVisible(true);
                }
                else
                {
                        db.createFavoriteSongs(customer);
                        customer.setMembership(true);
                        customer.setUserName(userNameText.getText());
                        new Kiwify(jukebox, customer).setVisible(true);
                        dispose();

                }

            }
        });
        loginWithAGuestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customer.setMembership(false);
                new Kiwify(jukebox,customer).setVisible(true);
                dispose();
            }
        });
    }

    public JPanel getPanelMain() {
        return panelMain;
    }

    public JTextField getUserNameText() {
        return userNameText;
    }

    public JTextField getPassText() {
        return passText;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JLabel getIdField() {
        return idField;
    }

    public JLabel getPassField() {
        return passField;
    }

    public JButton getLoginWithAGuestButton() {
        return loginWithAGuestButton;
    }

    public static void main(String[] args) {

        DbService db = new DbService();
        Jukebox jb = new Jukebox(123,100,true,true);

        db.getAllSong(jb);
        db.getAllArtist(jb);
        db.getAllPath(jb);


        Customer customer = new Customer(123,"Guest");

        LoginScreen ls = new LoginScreen(jb,customer);
        ls.setVisible(true);

    }
}
