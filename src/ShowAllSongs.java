import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowAllSongs extends JFrame{
    private JList allSongList;
    private JPanel panelMain;
    private JLabel titleLabel;
    private JButton homeButton;
    private DefaultListModel listSongModel;

    public ShowAllSongs(Jukebox jukebox,Customer customer)
    {
        super("Show All Songs");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setContentPane(this.panelMain);

        listSongModel = new DefaultListModel();
        allSongList.setModel(listSongModel);
        listSongModel.removeAllElements();

        for (int i = 0; i <jukebox.getAllSongList().size() ; i++)
        {
          listSongModel.addElement(jukebox.getAllSongList().get(i).getName());
        }


        allSongList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getClickCount() == 2)
                {
                    try
                    {
                        Menu m = new Menu(jukebox,customer,allSongList.getSelectedValue());
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
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Kiwify(jukebox,customer).setVisible(true);
                dispose();
            }
        });
    }
}
