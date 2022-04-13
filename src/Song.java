public class Song {
    private int ID;
    private String name;
    private long artist_ID;
    private String writer;
    private int rating;
    private SongType songType;


    public Song(int ID, String name, long artist_ID, String writer, int rating,SongType songType) {
        this.ID = ID;
        this.name = name;
        this.artist_ID = artist_ID;
        this.writer = writer;
        this.rating = rating;
        this.songType = songType;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getArtist_ID() {
        return artist_ID;
    }

    public void setArtist_ID(long artist_ID) {
        this.artist_ID = artist_ID;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public SongType getSongType() {
        return songType;
    }

    public void setSongType(SongType songType) {
        this.songType = songType;
    }

    @Override
    public String toString() {
        return "Song{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", artist_ID=" + artist_ID +
                ", writer='" + writer + '\'' +
                ", rating=" + rating +
                ", songType=" + songType +
                '}';
    }
}
