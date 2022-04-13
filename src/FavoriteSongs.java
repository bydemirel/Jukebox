import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FavoriteSongs extends JFrame{
    private JButton homeButton;
    private JPanel panelMain;
    private JList rating;
    private JList artist;
    private JList song;
    private DefaultListModel listSongModel;
    private DefaultListModel listArtistModel;
    private DefaultListModel listRatingModel;
    private Song result_song;
    DbService db = new DbService();

    public FavoriteSongs(Jukebox jukebox, Customer customer)
    {
        super("Favorite Songs");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setContentPane(this.panelMain);

        listSongModel = new DefaultListModel();
        song.setModel(listSongModel);
        listRatingModel = new DefaultListModel();
        rating.setModel(listRatingModel);
        listArtistModel = new DefaultListModel();
        artist.setModel(listArtistModel);

        db.getFavoriteSongs(customer,listRatingModel,listArtistModel,listSongModel);



        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Kiwify(jukebox,customer).setVisible(true);
                dispose();
            }
        });

        song.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getClickCount() == 2)
                {
                    try
                    {
                        Menu m = new Menu(jukebox,customer,song.getSelectedValue());
                        m.setVisible(true);
                        dispose();
                    }
                    catch(Exception ex)
                    {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE,null,ex);
                    }


                }
            }
        });
    }


}
