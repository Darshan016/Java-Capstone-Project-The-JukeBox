package jukebox1;

import com.mysql.cj.Query;
import com.mysql.cj.protocol.x.ResultMessageListener;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.plaf.nimbus.State;
import javax.xml.transform.Result;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.channels.ScatteringByteChannel;
import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Features {
    JukeBoxImpl jk = new JukeBoxImpl();
    static final String Query = "select * from playlist;";
    static Connection con;
    static{
try{
    Database db = new Database();
    con=db.getConnection();
}
catch(ClassNotFoundException | SQLException cnfe)
{
    System.out.println(cnfe);
}
    }
    static String user(String name) throws SQLException{
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select name from users");
        while(rs.next())
        {
       //     System.out.println(rs.getString(1));
           if(rs.getString(1).equals(name))
           {
           //    System.out.println(rs.getString(1));
               return rs.getString(1);
           }
        }
        return null;
    }
    static String artistdetailss(String name) throws SQLException{
        try {


            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from songs where artist='" + name + "';");
            while (rs.next()) {
                System.out.format("%1s %15s %15s %15s %15s %15s\n", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getTime(5), rs.getInt(6));
            }
        }catch (Exception c)
        {
            System.out.println(c);
        }
        return null;
    }

    static String password(String password) throws SQLException{
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select password from users");
        while(rs.next())
        {
         //   System.out.println(rs.getString(1));
            if (rs.getString(1).equals(password))
            {
              //  System.out.println(rs.getString(1));
                return rs.getString(1);
            }
        }
        return null;
    }
   /* public void login() throws SQLException{
        Statement st = con.createStatement();
        String query = "select name,password from users;
        ResultSet rs = st.executeQuery(query);

    }*/
    static void getGenre() throws SQLException,ClassNotFoundException{
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select title_of_song,genre from songs");
      //  System.out.format("%1s %15s","sogid","songname");
        while(rs.next())
        {
            System.out.format("%1s %35s \n",rs.getString(1),rs.getString(2));
        }
    }
    static void getArtist() throws SQLException,ClassNotFoundException{
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select artist,songid,title_of_song,genre from songs");
        //System.out.format("%1s %15s %15s %15s\n");
        while(rs.next())
        {
            System.out.format("%1s %35s %35s %35s\n",rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4));
        }
    }
    static void getartist1() throws SQLException{
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select artist from songs;");
            while (rs.next()) {
                System.out.format("%1s\n", rs.getString(1));
            }
        }catch (Exception ex)
        {
            System.out.println(ex);
        }

    }
    static void getArtists() throws SQLException,ClassNotFoundException{
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select artist from songs");
        while(rs.next())
        {
            System.out.format("%1s\n",rs.getString(1));
        }
    }
    static void getAlbum() throws SQLException,ClassNotFoundException{
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select *from albums;");
        while(rs.next())
        {
            System.out.format("%1s %40s\n",rs.getInt(1),rs.getString(2));
        }
    }
    static void SearchAlbum(String name) throws SQLException{
        PreparedStatement ps=con.prepareStatement("select s.songid, s.title_of_song, s.duration, a.album_name, s.genre, s.artist from songs s inner join albums a on s.albumid = a.albumid where album_name like '"+name+"%'");
        ResultSet rs=ps.executeQuery();
        while(rs.next()) {
            System.out.format("%1s %15s %15s %15s %15s %15s\n",rs.getInt(1),rs.getString(2),rs.getTime(3),rs.getString(4),rs.getString(5),rs.getString(6));
            System.out.println();
        }
    }
    static void displaySongs() throws SQLException,ClassNotFoundException{
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from songs");
        System.out.format("%1s %15s %25s %25s %25s %25s\n","songid","songname","artist","genre","duration","albumid");
        while(rs.next())
        {

            System.out.format("%1s %15s %25s %25s %25s %25s\n",rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getTime(5),rs.getInt(6));
        }

    }

    static boolean register(int id,String name,String password) throws SQLException{

        Statement st = con.createStatement();
        String query = "insert into users values("+id+",'"+name+"','"+password+"'";
       // System.out.println(query);
      st.executeUpdate(query);
       return true;

    }
    static boolean addsongsinp(String pname,String sname) throws SQLException{
        Statement st = con.createStatement();
        String query = "Insert into PLAYLIST(PLAYLIST_NAME,TRACK_TYPE,TRACK_NAME,SID)values('"+pname+"','SONG','"+sname+"',sid);";
   //     System.out.println(query);
        st.executeUpdate(query);
        return true;
    }
    static boolean addPodcastinp(String pname,String podname) throws SQLException{
        Statement st = con.createStatement();
        String query = "insert into playlist(playlist_name,track_type,track_name,pid)values('"+pname+"','podcast','"+podname+"',pid);";
        st.executeUpdate(query);
        return true;
    }


    static void displayPlaylist() throws SQLException {
        Statement smt=con.createStatement();
        ResultSet rs=smt.executeQuery("Select * from PLAYLIST");
        System.out.format("%1s %15s %15s %15s","PLALIST_ID","PLAYLIST_NAME","TRACK_TYPE","TRACK_NAME");
        System.out.println();
        while(rs.next()) {
            System.out.format("%1s %18s %20s %18s",rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
            System.out.println();
        }
    }
    static void displaydplaylist() throws SQLException,ClassNotFoundException{
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select distinct playlist_name from playlist;");
        while (rs.next())
        {
            System.out.format("%1s",rs.getString(1));
        }
    }
    static void displayPodcast() throws SQLException{
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from podcast");
    //    System.out.println("podcastid","podcastname","celebrityname","descriptions","duration","datereleased");
        System.out.println();
        while(rs.next())
        {
            System.out.format("%1s %15s %20s %20s %25s %25s\n",rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getTime(5),rs.getDate(6));
        }
    }
    static void deleteTrackFromPlaylist(String name) throws SQLException,ClassNotFoundException{
        Statement st = con.createStatement();
        String query = "delete from playlist where track_name='"+name+"'";
        st.executeUpdate(query);
        ResultSet rs = st.executeQuery(Query);

        while(rs.next())
        {
            System.out.format("%2s %20s %20s %20s %20s %20s\n",rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getTime(5),rs.getInt(6));
        }
        rs.close();
    }


    static void searchCelebrity() throws SQLException,ClassNotFoundException,Exception{
        ResultSet rs=null;

            Statement st = con.createStatement();

             rs = st.executeQuery("select * from podcast");

            while (rs.next()) {
                System.out.format("%1s %18s %20s %15s %15s %15s\n", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getTime(5), rs.getDate(6));
            }

    }

   static void searchCelebritypdetails(String name) {
        try {

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from podcast where celebrity_name='" + name + "';");


            while (rs.next()) {
                System.out.format("%1s %18s %20s %15s %15s %15s\n", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getTime(5), rs.getDate(6));
            }
        }catch(Exception e)
        {
            System.out.println("enter valid celebrity name");
        }






    //}catch(Exception)
       /* {
            System.out.println("enter correct celebrity/hostname");
        }*/
    }
    static void searchCelebritydetails() throws SQLException,ClassNotFoundException,Exception{
        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery("select celebrity_name from podcast");
        while(rs.next())
        {
            System.out.format("%1s \n",rs.getString(1));
        }
    }
   /* static void playMusic(String musicLocation) throws SQLException,ClassNotFoundException{
        try{
          //  String fp = "C:\\Users\\DELL\\Downloads\\"+name+".wav"
            File fp = new File(musicLocation);
            if(fp.exists())
            {
                AudioInputStream ais = AudioSystem.getAudioInputStream(fp);
                Clip clip = AudioSystem.getClip();
                clip.open(ais);
                clip.start();
            }
            else{
                System.out.println("cant find file");
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }*/


    static void playSong(String name) throws SQLException, FileNotFoundException,Exception{

    }
   /* static void addSongsInPlaylist(String sname,String pname) throws SQLException,FileNotFoundException,ClassNotFoundException,Exception{
        PreparedStatement pst = con.createStatement();
        ResultSet rs = pst.executeQuery("Insert into PLAYLIST(PLAYLIST_NAME,TRACK_TYPE,TRACK_NAME,SID)values(?,'SONG',?,?);");
        while(rs.next())
        {
            System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4));
        }
    }*/
   static void signUp() throws SQLException
   {
       try
       {
           Scanner scan=new Scanner(System.in);


           System.out.println("Enter userName :");
          // String userName = scan.nextLine();
           String choice303=scan.next();
           PreparedStatement pst1 = con.prepareStatement("SELECT Name FROM users;");
     //      pst1.setString(1,userName);
           ResultSet resultSet = pst1.executeQuery();
           while (resultSet.next())
           {

               if (resultSet.getString(1).equalsIgnoreCase(choice303))
               {
                   //throw new Exception("User Already Exists, Enter new name");
                   System.out.println("alreay exists, enter new username");
                   System.out.println("enter username again, but different");
                    choice303=scan.next();

               }
           }

           System.out.println("Enter a password");
           String password = scan.next();
           PreparedStatement pst = con.prepareStatement("insert into users(Name,password) values(?,?);");
           pst.setString(1, choice303);
           pst.setString(2, password);
           int i = pst.executeUpdate();
           if (i > 0)
           {
               System.out.println("Account Created Sucessfully");
           }
       }
       catch (Exception e)
       {
           e.printStackTrace();
           e.getMessage();
           System.out.println("\nUser Already Exists With Given UserName, Please Change Your UserName");
       }

   }
   /*static void createPlaylist() throws SQLException{
       System.out.println("enter playlist name u wann acreate");
       int id=0;
       String pname=null;
       PreparedStatement pst = con.prepareStatement("insert into playlist(playlist_id,playlist_name) values (playlist_id,?);");
       pst.setInt(1,id);
       pst.setString(2,pname);
       pst.execute();
   }*/
    static void artistDetails(String name) throws SQLException{
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from songs where artist='"+name+"';");
        while(rs.next())
        {
            System.out.format("%1s %15s %15s %15s %15s %15s",rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getTime(5),rs.getInt(6));
        }

    }
}
