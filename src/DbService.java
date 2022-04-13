import javax.swing.*;
import java.sql.*;


public class DbService {
    String url = "jdbc:mysql://localhost:3306/jukebox?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String user = "root";
    String password = "1234";

    int id;
    String temp_id = "0";


    private Connection connect() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            connection = null;
        }
        return connection;
    }

    public void getAllSong(Jukebox jb) {
        String id, rating;
        String name, artist_ID, writer,songType;

        SongType songType1 = SongType.Pop;

         Connection connection = connect();
        try (PreparedStatement find = connection.prepareStatement("SELECT * " + "FROM jukebox.songList")) {
            ResultSet findSong = find.executeQuery();
            while (findSong.next()) {

                id = findSong.getString(1);
                name = findSong.getString(2);
                artist_ID = findSong.getString(3);
                writer = findSong.getString(4);
                rating = findSong.getString(5);
                songType = findSong.getString(6);

                Song s = new Song(Integer.parseInt(id), name, Long.parseLong(artist_ID), writer, Integer.parseInt(rating), songType1.determine(songType));
                jb.getAllSongList().add(s);

            }
        } catch (SQLException ex) {
            System.out.println("An error has occured." + ex.getMessage());
        }
    }

    public void getAllArtist(Jukebox jb) {
        String id, name;

        Connection connection = connect();
        try (PreparedStatement find = connection.prepareStatement("SELECT * " + "FROM jukebox.artistList")) {
            ResultSet findArtist = find.executeQuery();
            while (findArtist.next()) {
                id = findArtist.getString(1);
                name = findArtist.getString(2);

                jb.getAllArtistList().add(new Artist(Integer.parseInt(id), name));
            }
        } catch (SQLException ex) {
            System.out.println("An error has occured." + ex.getMessage());
        }
    }

    public void getAllPath(Jukebox jb) {
        String musicPath, imagePath;

        Connection connection = connect();
        try (PreparedStatement find = connection.prepareStatement("SELECT * " + "FROM jukebox.filePathList")) {
            ResultSet findPath = find.executeQuery();
            while (findPath.next()) {
                musicPath = findPath.getString(1);
                imagePath = findPath.getString(2);

                jb.musicpaths.add(musicPath);
                jb.imagepaths.add(imagePath);

            }
        } catch (SQLException ex) {
            System.out.println("An error has occured." + ex.getMessage());
        }
    }

    public void getFavoriteSongs(Customer customer, DefaultListModel ratingList, DefaultListModel artistList, DefaultListModel songNameList) {
        int rating;
        String artist, songName;

        Connection connection = connect();

        try (PreparedStatement find = connection.prepareStatement("SELECT * " + "FROM jukebox.favoritesonglist WHERE user_id = ?")) {

            find.setInt(1, customer.getID());
            ResultSet findFavSong = find.executeQuery();

            while (findFavSong.next()) {
                rating = findFavSong.getInt(2);
                artist = findFavSong.getString(3);
                songName = findFavSong.getString(4);

                ratingList.addElement(rating);
                artistList.addElement(artist);
                songNameList.addElement(songName);
            }
        } catch (SQLException ex) {
            System.out.println("An error has occured." + ex.getMessage());
        }
    }

    public void createFavoriteSongs(Customer customer) {
        Connection connection = connect();
        int rating,song_id;
        String artist,artist_ID,songName,songType;
        SongType s = SongType.Pop;

        try (PreparedStatement findFavoriteSong = connection.prepareStatement("SELECT songlist.id,songlist.name,songlist.artist_ID,songlist.writer, songlist.rating,songlist.songType FROM songlist,favoritesonglist WHERE favoritesonglist.songName = songlist.name AND favoritesonglist.user_id = ? ")) {

                findFavoriteSong.setInt(1, customer.getID());
                ResultSet findFavSong = findFavoriteSong.executeQuery();

                while (findFavSong.next()) {

                    song_id = findFavSong.getInt(1);
                    songName = findFavSong.getString(2);
                    artist_ID = findFavSong.getString(3);
                    artist = findFavSong.getString(4);
                    rating = findFavSong.getInt(5);
                    songType = findFavSong.getString(6);

                    customer.addSong(new Song(song_id,songName,Long.parseLong(artist_ID),artist,rating,s.determine(songType)));
                }


        } catch (SQLException ex) {
            System.out.println("An error has occured." + ex.getMessage());
        }
    }


    public void addFavoriteSong(Customer customer, Song song)
    {
        int user_id = customer.getID();
        int rating = song.getRating();
        String artist = song.getWriter();
        String songName = song.getName();

        Connection connection = connect();

        try (PreparedStatement addFavSong = connection.prepareStatement("INSERT INTO favoritesonglist(user_id,rating,artist,songName) VALUES(?,?,?,?)",Statement.RETURN_GENERATED_KEYS))
        {
            addFavSong.setInt(1,user_id);
            addFavSong.setInt(2,rating);
            addFavSong.setString(3,artist);
            addFavSong.setString(4,songName);

            customer.addSong(song);

            addFavSong.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.out.println("An error has occured."+ex.getMessage());
        }
    }

    public void getDeleteFavoriteSong(Customer customer,DefaultListModel songNameList)
    {
        String songName;

        Connection connection = connect();

        try (PreparedStatement find = connection.prepareStatement("SELECT songName "+"FROM jukebox.favoritesonglist WHERE user_id = ?"))
        {

            find.setInt(1,customer.getID());

            ResultSet findFavSong = find.executeQuery();

            while(findFavSong.next())
            {
                songName = findFavSong.getString(1);

                songNameList.addElement(songName);
            }
        }
        catch (SQLException ex)
        {
            System.out.println("An error has occured."+ex.getMessage());
        }
    }

   public void deleteFavoriteSong(Customer customer,Object songName)
    {
        Connection connection = connect();

            try (PreparedStatement deleteFavoriteSong = connection.prepareStatement(" DELETE  FROM favoritesonglist WHERE user_id = ?"+" AND songName = ?")) {
                deleteFavoriteSong.setInt(1, customer.getID());
                deleteFavoriteSong.setString(2,songName.toString());

                deleteFavoriteSong.executeUpdate();
            }

         catch (SQLException ex) {
            System.err.println("An error has occured." + ex.getMessage());
        }

    }

    public Customer addMembership(String username,String password)
    {
        Connection connection = connect();

        try (PreparedStatement find = connection.prepareStatement("SELECT id "+"FROM jukebox.membershiplist"))
        {
            ResultSet findID = find.executeQuery();
            while(findID.next())
            {
                temp_id=findID.getString(1);
            }
        }
        catch (SQLException ex)
        {
            System.out.println("An error has occured."+ex.getMessage());
        }

        try (PreparedStatement addMember = connection.prepareStatement("INSERT INTO membershiplist(id,username,password) VALUES(?,?,?)",Statement.RETURN_GENERATED_KEYS))
        {
            id = Integer.parseInt(temp_id);
            id++;

            addMember.setInt(1,id);
            addMember.setString(2,username);
            addMember.setString(3,password);

            Customer customer = new Customer(id,username);

            addMember.executeUpdate();

            return customer;

        }
        catch (SQLException ex)
        {
               System.out.println("An error has occured."+ex.getMessage());
        }
        return null;
    }

    public String getMember(int memberID)
    {
        String kiwify_username = "";
        Connection connection = connect();

        try (PreparedStatement find = connection.prepareStatement("SELECT username "+"FROM jukebox.membershiplist WHERE id = ?"))
        {
            find.setString(1,Integer.toString(memberID));
            ResultSet findMember = find.executeQuery();
            kiwify_username = findMember.getString(1);
        }
        catch (SQLException ex)
        {
            System.out.println("An error has occured."+ex.getMessage());
        }

        return kiwify_username;
    }

    public boolean membershipValidity(Customer customer,String username, String password)
    {
        int kiwify_id;
        String kiwify_username,kiwify_password;
        Connection connection = connect();

        try (PreparedStatement find = connection.prepareStatement("SELECT * "+"FROM jukebox.membershiplist"))
        {
            ResultSet findMember = find.executeQuery();
            while(findMember.next())
            {
                kiwify_id = findMember.getInt(1);
                kiwify_username = findMember.getString(2);
                kiwify_password = findMember.getString(3);

                if(kiwify_username.equals(username) && kiwify_password.equals(password))
                {
                    customer.setID(kiwify_id);
                    return true;
                }
            }
        }
        catch (SQLException ex)
        {
            System.out.println("An error has occured."+ex.getMessage());
        }

        return false;

    }
}

