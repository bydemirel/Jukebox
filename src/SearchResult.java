
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchResult extends JFrame{
    private JPanel panelMain;
    private JLabel result;
    private JButton playButton;
    private JButton homeButton;
    private JButton ADDTOFAVORITESONGButton;
    DbService db = new DbService();

    public SearchResult(Jukebox jukebox, Customer customer, Song song)
    {
        super("Search Result");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setContentPane(this.panelMain);

        if(customer == null || !customer.isMembership())
        {
            ADDTOFAVORITESONGButton.setVisible(false);
            result.setText(song.toString());
        }
        else
        {
            ADDTOFAVORITESONGButton.setVisible(true);
            result.setText("Do you want to add this song to your favorite Songs/Library \n"+song.toString());

        }


        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    new Menu(jukebox,customer,song).setVisible(true);
                    dispose();
                }
                catch(Exception ex)
                {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE,null,ex);
                }

            }
        });
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jukebox.setState(false);
                new Kiwify(jukebox,customer).setVisible(true);
                dispose();
            }
        });
        ADDTOFAVORITESONGButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!customer.getSong_list().isEmpty())
                {
                    for (int i = 0; i < customer.getSong_list().size(); i++)
                    {
                        if(song.getName().equals(customer.getSong_list().get(i).getName()))
                        {
                            new ErrorMessage("You have already add this song to your favorite song list!").setVisible(true);
                            break;
                        }
                        else if(i == customer.getSong_list().size()-1)
                        {
                            db.addFavoriteSong(customer,song);
                            FavoriteSongs fs = new FavoriteSongs(jukebox, customer);
                            fs.setVisible(true);
                            dispose();
                        }
                    }

                }
                else
                {
                    db.addFavoriteSong(customer,song);
                    FavoriteSongs fs = new FavoriteSongs(jukebox, customer);
                    fs.setVisible(true);
                    dispose();
                }
            }
        });

    }
}
