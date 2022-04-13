
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Menu extends JFrame {
    private JButton homeButton;
    private JButton volumeDownButton;
    private JButton playPauseButton;
    private JButton volumeUpButton;
    private JPanel panelMain;
    private JLabel currentSong;
    private JButton nextSongButton;
    private JButton previousSongButton;
    private JLabel soundField;
    private JLabel imageField;
    private JButton restartButton;
    private ImageIcon imageIcon;
    private Song song;
    static int  index;

    public Menu(Jukebox jukebox, Customer customer, Object favorite_song) throws Exception
    {
        super("Menu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setContentPane(this.panelMain);

        if(favorite_song != null)
        {
            for (int i = 0; i < jukebox.getAllSongList().size(); i++)
            {
                if(favorite_song.toString().equals(jukebox.getAllSongList().get(i).getName()) || favorite_song.toString().equals(jukebox.getAllSongList().get(i).toString()))
                {
                    index = i;
                    song = jukebox.getAllSongList().get(index);
                    currentSong.setText(song.toString());
                    soundField.setText(Integer.toString(jukebox.getVolume()));
                    jukebox.setFilePath(jukebox.musicpaths.get(index));
                    imageIcon = new ImageIcon(jukebox.imagepaths.get(index));
                    imageField.setIcon(imageIcon);
                    jukebox.setStruct();
                    break;
                }
            }
        }
        else
        {
            song = jukebox.getAllSongList().get(0);
            currentSong.setText(song.toString());
            soundField.setText(Integer.toString(jukebox.getVolume()));
            jukebox.setFilePath(jukebox.musicpaths.get(index));
            imageIcon = new ImageIcon(jukebox.imagepaths.get(index));
            imageField.setIcon(imageIcon);
            jukebox.setStruct();
        }



        volumeDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jukebox.getVolume() == 0)
                {
                    String warnings = "Volume is minimum!";
                    new ErrorMessage(warnings);
                }
                else
                {
                    jukebox.volumeDown();
                    soundField.setText(Integer.toString(jukebox.getVolume()));
                }

            }
        });
        playPauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try
                {
                    if(jukebox.isState() == true)
                    {
                        jukebox.pause();
                    }
                    else
                    {
                        jukebox.play();

                    }
                }
                catch(Exception ex)
                {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE,null,ex);
                }

            }
        });
        volumeUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jukebox.getVolume() == 10)
                {
                    String warnings = "Volume is maximum!";
                    new ErrorMessage(warnings);
                }
                else
                {
                    jukebox.volumeUp();
                    soundField.setText(Integer.toString(jukebox.getVolume()));
                }

            }
        });
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try
                {
                    new Kiwify(jukebox,customer).setVisible(true);
                    jukebox.stop();
                    dispose();

                }
                catch(Exception ex)
                {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE,null,ex);
                }

            }
        });
        previousSongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    try
                    {
                        jukebox.stop();

                        if(jukebox.getAllSongList().get(0).toString().equals(currentSong.getText()))
                        {
                            index = jukebox.musicpaths.size()-1;
                            Song pre_song = jukebox.getAllSongList().get(jukebox.getAllSongList().size()-1);
                            currentSong.setText(pre_song.toString());
                            imageIcon = new ImageIcon(jukebox.imagepaths.get(index));
                            imageField.setIcon(imageIcon);
                            song = pre_song;
                            jukebox.play();
                        }
                        else
                        {
                            index--;
                            Song pre_song = jukebox.getAllSongList().get(song.getID()-2);
                            currentSong.setText(pre_song.toString());
                            imageIcon = new ImageIcon(jukebox.imagepaths.get(index));
                            imageField.setIcon(imageIcon);
                            song = pre_song;
                            jukebox.play();
                        }
                        new Menu(jukebox,customer,null);
                    }
                    catch(Exception ex)
                    {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE,null,ex);
                    }


            }
        });
        nextSongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try
                {
                    jukebox.stop();

                    if(jukebox.getAllSongList().get(jukebox.getAllSongList().size()-1).toString().equals(currentSong.getText()))
                    {
                        index=0;
                        Song next_song = jukebox.getAllSongList().get(0);
                        currentSong.setText(next_song.toString());
                        imageIcon = new ImageIcon(jukebox.imagepaths.get(index));
                        imageField.setIcon(imageIcon);
                        song = next_song;
                        jukebox.play();
                    }
                    else
                    {
                        index++;
                        Song next_song = jukebox.getAllSongList().get(song.getID());
                        currentSong.setText(next_song.toString());
                        imageIcon = new ImageIcon(jukebox.imagepaths.get(index));
                        imageField.setIcon(imageIcon);
                        song = next_song;
                        jukebox.play();
                    }

                    new Menu(jukebox,customer,null);
                }
                catch(Exception ex)
                {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE,null,ex);
                }


            }
        });
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    jukebox.restart();
                    new Menu(jukebox,customer,favorite_song);
                }
                catch(Exception ex)
                {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE,null,ex);
                }

            }
        });
    }

}
