import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Kiwify extends JFrame{
    private JButton BUYMEMBERSHIPButton;
    private JButton menuButton;
    private JButton searchButton;
    private JButton favoriteSongButton;
    private JButton shutdownButton;
    private JPanel panelMain;
    private JLabel membershipLabel;
    private JButton displayAllSongButton;
    private JButton deleteSongButton;

    public Kiwify(Jukebox jukebox, Customer customer) {
        super("KIWIFY");
        this.setContentPane(this.panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();


        if(!customer.isMembership())
        {
            favoriteSongButton.setVisible(false);
            deleteSongButton.setVisible(false);
            membershipLabel.setText("Welcome Guest");
        }
        else
        {
            BUYMEMBERSHIPButton.setVisible(false);
            membershipLabel.setText("Welcome "+ customer.getUserName());
        }

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    Menu menu = new Menu(jukebox,customer,null);
                    menu.setVisible(true);
                    dispose();
                }
                catch(Exception ex)
                {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE,null,ex);
                }

            }
        });

        shutdownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        BUYMEMBERSHIPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BuyMembership(jukebox,customer).setVisible(true);
                dispose();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              Search search = new Search(jukebox,customer);
              search.setVisible(true);
              dispose();
            }
        });

        favoriteSongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            FavoriteSongs fs = new FavoriteSongs(jukebox,customer);
            fs.setVisible(true);
            dispose();
            }
        });
        displayAllSongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShowAllSongs(jukebox,customer).setVisible(true);
                dispose();
            }
        });
        deleteSongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 new DeleteFavoriteSong(jukebox,customer).setVisible(true);
                 dispose();
            }
        });
    }


}
