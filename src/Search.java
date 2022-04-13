import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Search extends JFrame{
    private JButton homeButton;
    private JTextField textSong;
    private JButton searchSongButton;
    private JPanel panelMain;

    public Search(Jukebox jukebox, Customer customer)
    {
        super("Search");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setContentPane(this.panelMain);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Kiwify(jukebox,customer).setVisible(true);
                dispose();
            }
        });

        searchSongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(textSong.getText().isEmpty())
                {
                    String warnings = "Please enter a Song name!";
                        new ErrorMessage(warnings).setVisible(true);
                }
                else
                {
                    try
                    {
                        boolean find = false;
                        for (int i = 0; i < jukebox.getAllSongList().size(); i++)
                        {
                            if(jukebox.getAllSongList().get(i).getName().toUpperCase().equals(textSong.getText().toUpperCase()))
                            {
                                    find = true;
                                    new SearchResult(jukebox,customer, jukebox.getAllSongList().get(i)).setVisible(true);
                                    dispose();
                                    break;
                            }
                        }
                        if(!find)
                        {
                            String warnings = "The song you are looking for is not available on kiwify!";
                            new ErrorMessage(warnings).setVisible(true);
                        }

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
