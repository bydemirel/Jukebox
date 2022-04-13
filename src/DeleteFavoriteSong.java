import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteFavoriteSong extends JFrame {

    private JPanel panelMain;
    private JList favoriteSongList;
    private JLabel titleLabel;
    private JButton homeButton;
    private DefaultListModel listSongModel;
    DbService db = new DbService();

    public DeleteFavoriteSong(Jukebox jukebox, Customer customer) {

        super("Delete Favorite Song");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setContentPane(this.panelMain);

        listSongModel = new DefaultListModel();
        favoriteSongList.setModel(listSongModel);

        db.getDeleteFavoriteSong(customer,listSongModel);





        favoriteSongList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if (e.getClickCount() == 2) {
                    try {
                        db.deleteFavoriteSong(customer, favoriteSongList.getSelectedValue());
                        deleteSongFromSonglist(customer,favoriteSongList.getSelectedValue().toString());
                        new Kiwify(jukebox,customer).setVisible(true);
                        dispose();
                    } catch (Exception ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Kiwify(jukebox,customer).setVisible(true);
                dispose();
            }
        });
    }
    
    public void deleteSongFromSonglist(Customer customer,String songName)
    {
        for (int i = 0; i < customer.getSong_list().size(); i++)
        {
            if(customer.getSong_list().get(i).getName().equals(songName))
            {
                customer.removeSong(customer.getSong_list().get(i));
            }
        }
    }
}
