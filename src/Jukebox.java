import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;
import java.util.ArrayList;

public class Jukebox {
    private long ID;
    private double price;
    private int volume;
    private boolean state;
    private boolean jukeboxState;
    long currentFrame;
    private File file;
    Clip clip;
    float decibels;
    ArrayList<String> musicpaths = new ArrayList<>();
    ArrayList<String> imagepaths = new ArrayList<>();
    private String name,filePath;
    AudioInputStream audioInputStream;
    private ArrayList<Song> allSongList = new ArrayList<>();
    private ArrayList<Artist> allArtistList = new ArrayList<>();

    public Jukebox(long ID, double price, boolean state,boolean jukeboxState) {
        this.ID = ID;
        this.price = price;
        this.volume = 5;
        this.state = state;
        this.jukeboxState = jukeboxState;
        this.decibels = 0f;

    }
    public void setStruct() throws  Exception
    {
        file = new File(filePath);
        name = getFile().getName();

        audioInputStream = AudioSystem.getAudioInputStream(file.getAbsoluteFile());

        clip = AudioSystem.getClip();

        clip.open(audioInputStream);

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public  void restart() throws Exception
    {
        clip.stop();
        clip.close();
        currentFrame =0L;
        clip.setMicrosecondPosition(0);
        this.play();
    }

    public void stop () throws Exception
    {
        currentFrame =0L;
        clip.stop();
        clip.close();
    }

    public void play() throws Exception
    {
        setState(true);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(decibels);
        clip.start();
    }

    public void pause() throws Exception
    {
        setState(false);
        this.currentFrame = this.clip.getMicrosecondPosition();
        clip.stop();
    }

    public void open()
    {
        setJukeboxState(true);
    }

    public void shutDown()
    {
        setJukeboxState(false);
    }

    public void volumeUp()
    {
        if(getVolume() == 10)
        {
            System.out.println("Volume is max!");
        }
        else
        {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            if(decibels < 0)
            {
                decibels = decibels + (16f);
                gainControl.setValue(decibels);
            }
            else
            {
                decibels = decibels + (1f);
                gainControl.setValue(decibels);
            }

            setVolume(getVolume()+1);
            System.out.println(decibels);
        }

    }

    public void volumeDown()
    {
        if(getVolume()==0)
        {
            System.out.println("Volume is 0!");
        }
        else
        {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            if(decibels > 0)
            {
                decibels = decibels - (1f);
                gainControl.setValue(decibels);
            }
            else
            {
                decibels  = decibels - (16f);
                gainControl.setValue(decibels);
            }
            setVolume(getVolume()-1);
            System.out.println(decibels);
        }

    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public boolean isState() {
        return state;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean isJukeboxState() {
        return jukeboxState;
    }

    public void setJukeboxState(boolean jukeboxState) {
        this.jukeboxState = jukeboxState;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public ArrayList<Song> getAllSongList() {
        return allSongList;
    }

    public File getFile() {
        return file;
    }

    public void setAllSongList(ArrayList<Song> allSongList) {
        this.allSongList = allSongList;
    }

    public ArrayList<Artist> getAllArtistList() {
        return allArtistList;
    }

    public void setAllArtistList(ArrayList<Artist> allArtistList) {
        this.allArtistList = allArtistList;
    }
}
