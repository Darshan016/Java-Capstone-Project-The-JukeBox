package jukebox1;



import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.IllegalCharsetNameException;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class JukeBoxImpl {

    public static void displayMenu() {
        System.out.println("1 see all available artists\n" +
                "2 see all available albums\n" +
                "3 to play tracks\n" +
                "4 to add tracks in a playlist\n" +
                "5 to see the playlists available\n" +
                "6 see available celebrities on podcast\n" +
                "7 see all available songs\n" +


                "8 to add podcast into playlist\n" +
                "9 see all available genres\n" +
                "10 to delete song or podcast from a playlist\n" +
                "11 to see available podcasts\n" +
                "12 display available songs\n" +
                "13 to exit \n");
    }



    public static void main(String[] args) throws Exception {
      //  String name99="";
            boolean check=false;

      //  String musicLocation = "C:\\Users\\DELL\\Downloads\\"+name99+".wav";
        Audio a = new Audio();
        Playlist pl = new Playlist();
        Song s = new Song();
        Scanner scan = new Scanner(System.in);
        Features f = new Features();
       // List<Playlist> list = f.PlaylistintoList();
     //   List<Song> List = Features.SongsIntoList();
        //System.out.println(list.size());
        boolean ans=false;
        String username="";
        String pwd="";

        do {
            System.out.println("----------------welcome to the jukebox------------");
            System.out.println("1 for register 2 to login");
            int news = scan.nextInt();
            if (news == 1) {
                Features.signUp();
           /* System.out.println("enter new username");
            String newu=scan.next();
            System.out.println("enter password");
            String ps=scan.next();

           // Features.user();
            Features.register(id,newu,ps);*/
                System.out.println("congrats u are registered");
                displayMenu();
            } else if (news == 2) {
                System.out.println("enter yuur username");
                String name = scan.next();
                System.out.println("enter ur password");
                String password = scan.next();

                username=Features.user(name);
               pwd= Features.password(password);


                if (username.equalsIgnoreCase(name) && pwd.equalsIgnoreCase(password)) {
                    System.out.println("===============================");
                    System.out.println("welcome to your jukebox " + name);
                    System.out.println("===============================");
                    do {
                        displayMenu();
                        System.out.println("please enter what would you like to do");

                        int choice = scan.nextInt();
                        switch (choice) {
                            case 1:
                                System.out.println("see all available artists");
                                //String name1 = scan.next();
                                // System.out.println(" artistname  songid  songname");
                                System.out.println("=================================================================================================================");
                               // System.out.format("%1s %40s %35s %30s\n", "artistname", "songid", "songname", "genre");
                                Features.getartist1();
                                System.out.println("===================================================================================================================");
                                System.out.println("enter name of the artist to see details");
                                String chpoice=scan.next();
                                System.out.format("%1s %15s %15s %15s %15s %15s\n","songid","songname","artist","genre","duration","alumid");
                                Features.artistdetailss(chpoice);
                                System.out.println("do u wanna play? type(yes/no)");
                                String choice1 = scan.next();
                                try {
                                    if (choice1.equalsIgnoreCase("yes")) {
                                        System.out.println("enter song name");
                                        String choice2 = scan.next();
                                        a.PlayTrack(choice2);
                                    } else {
                                        System.out.println("got it");
                                        displayMenu();
                                    }
                                } catch (FileNotFoundException fnfe) {
                                    System.out.println("enter valid song name");
                                }
                                /*System.out.println("see all available artists");
                                System.out.format("%1sn","artist");
                                Features.getArtists();
                                System.out.println("enter name of the artist u wanna se details");
                                String lop = scan.next();
                                try{
                                    Features.artistDetails(lop);
                                }
                                catch (Exception e)
                                {
                                    System.out.println("enter valid artist name");
                                    displayMenu();
                                }

                                System.out.println("do u wanna play tracks?");
                                String pa = scan.next();
                                a.PlayTrack(pa);


                                ;*/
                                break;

                            case 2:
                                // System.out.println("enter album name to see");
                                //   String name2 = scan.next();
                                //    System.out.println("songid title_of_song genre duration artist");

                                System.out.format("%3s %40s\n", "albumid", "albumname");
                                System.out.println("-------------------------------------------");
                                Features.getAlbum();
                                System.out.println("---------------------------------------------");
                                System.out.println("enter album name to view songs of that album");
                                String chooice00 = scan.next();
                                System.out.println("=================================================================================");
                                System.out.format("%1s %15s %14s %13s %13s %13s\n","songid","songname","duration","albumnamme","genre","artist");
                                Features.SearchAlbum(chooice00);
                                System.out.println("==================================================================================");
                                System.out.println("do u wanna play? type(yes/no)");
                                String choice4 = scan.next();
                                try {
                                    if (choice4.equalsIgnoreCase("yes")) {
                                        System.out.println("enter song name");
                                        String choice5 = scan.next();
                                        a.PlayTrack(choice5);
                                    } else {
                                        System.out.println("bye");
                                        displayMenu();
                                    }
                                } catch (FileNotFoundException fn) {
                                    System.out.println("enter valid song");
                                }
                                break;
                            case 3:
                                // Audio a = new Audio();
                                System.out.println("==========================================================================================================");
                                Features.displaySongs();
                                System.out.println("==========================================================================================================");
                                System.out.println("enter the track name u wanna play");
                                try {
                                    // scan.nextLine();
                                    String name3 = scan.next();

                                    a.PlayTrack(name3);
                                } catch (FileNotFoundException fn) {
                                    System.out.println("please enter correct song name");
                                }
                                break;
                            case 4:
                                Features.displaySongs();
                                System.out.println("enter the name of existing or new playlist");
                                //scan.next();
                                String name4 = scan.next();
                                System.out.println("enter the track name u wanna add in the " + name4);
                                String name5 = scan.next();
                                // Features.addSongIntoPlaylist(name4,name5,List);
                                Features.addsongsinp(name4,name5);
                                //Features.addSongIntoPlaylist(name4, name5, List);
                                System.out.println("saved into the playlist");
                                break;
                            case 5:
                                // System.out.println("enter the name of playlist u wanna see");
                                //String name7 = scan.next();
                                System.out.println("========================================================================================================");
                                Features.displayPlaylist();
                                System.out.println("========================================================================================================");
                                System.out.println("do u wanna play songs or podcast? type(yes/no)");
                                String choice6 = scan.next();
                                try {
                                    if (choice6.equalsIgnoreCase("yes")) {
                                        System.out.println("enter trackname name u wanna play");
                                        String choice7 = scan.next();
                                        a.PlayTrack(choice7);
                                    } else {
                                        System.out.println("bye");
                                        displayMenu();
                                    }
                                } catch (FileNotFoundException fnf) {
                                    System.out.println("enter valid song/podcast name");
                                }
                                break;
                            case 6:
                                // System.out.println("enter the host/celebrity name");
                                //String name8 = scan.next();
                                System.out.println("=============================================================================================================");
                              //  System.out.println("podcastid  podcastname      host/celebrityname     description    duration    Publisheddate\n");
                             //   Features.searchCelebrity();
                                System.out.println("hostname");
                                Features.searchCelebritydetails();

                                System.out.println("=============================================================================================================");
                                System.out.println("enter celebrity name to see their podcast details");

                                    String lostcount = scan.next();

                                    System.out.format("%1s %15s %15s %15s %15s %15s\n","podcastid","podcastname","host/celebrityname","description","Duration","datepublished");
                                    Features.searchCelebritypdetails(lostcount);

                                //catch (Exception d)

                                //    System.out.println("enter correct celebrity/hostname");

                                //    System.out.println("enter correct host/celebrityname");

                                System.out.println("do u wanna play any podcast? type(yes/no)");
                                String choice8 = scan.next();
                                try {
                                    if (choice8.equalsIgnoreCase("yes")) {
                                        System.out.println("enter pdcast name");
                                        String choice9 = scan.next();
                                        a.PlayTrack(choice9);
                                    } else {
                                        System.out.println("bye");
                                        displayMenu();
                                    }
                                } catch (FileNotFoundException e) {
                                    System.out.println("enter valid file name please");
                                }
                                break;
                            case 7:
                                //System.out.println("");
                                //String name9 = scan.next();
                                //System.out.println("Id username  emailid");
                                //Features.displayUsers();

                                //System.out.println("%1s %15s %15s %15s %15s %15s");
                                System.out.println("=========================================================================================================================");
                                Features.displaySongs();
                                System.out.println("==========================================================================================================================");
                                System.out.println("do u wanna play songs? type(yes/no)");
                                String choice55 = scan.next();
                                try {
                                    if (choice55.equalsIgnoreCase("yes")) {
                                        System.out.println("enter file name");
                                        String chice44 = scan.next();
                                        a.PlayTrack(chice44);
                                    } else {
                                        System.out.println("got it");
                                    }
                                } catch (FileNotFoundException fns) {
                                    System.out.println("enter the song name correct");
                                }

                                break;
                            case 8:
                                System.out.format("%1s %15s %19s %19s %27s %19s","PID","Podcastname","celebrity/host","Description","Duration","date");
                                Features.displayPodcast();
                                System.out.println("enter the name of playlist u wanna create");
                                //scan.next();
                                String name46 = scan.next();
                                System.out.println("enter the track name u wanna add in the " + name46);
                                String name56 = scan.next();
                                // Features.addSongIntoPlaylist(name4,name5,List);
                                Features.addPodcastinp(name46,name56);
                                //Features.addSongIntoPlaylist(name4, name5, List);
                                System.out.println("saved into the playlist");
                                break;
                            case 9:
                                //  System.out.println("enter genre name");
                                //String name13 = scan.next();
                                System.out.format("%1s %35s\n", "songname", "Genres");
                                System.out.println("=======================================================================================================");

                                Features.getGenre();
                                System.out.println("=========================================================================================================");
                                System.out.println("do u wanna play? type(yes/no)");
                                String choice10 = scan.next();
                                try {
                                    if (choice10.equalsIgnoreCase("yes")) {
                                        System.out.println("enter track name to play");
                                        String choice11 = scan.next();
                                        a.PlayTrack(choice11);
                                    } else {
                                        System.out.println("bye");
                                        displayMenu();
                                    }
                                } catch (FileNotFoundException fnf) {
                                    System.out.println("enter valid file name");
                                }
                                break;
                            case 10:
                                System.out.println("========================================================================");
                                Features.displayPlaylist();
                                System.out.println("========================================================================");
                                System.out.println("enter name of the song/podcast u wanna delete");
                                String name14 = scan.next();
                                // System.out.println("remaining songs are:");
                                //System.out.println("songid  name  artist  genre  duration  album");

                                System.out.println("after deleting remaining playlists are");

                                System.out.format("%1s %15s %15s %15s %15s %15s\n","PLALIST_ID","PLAYLIST_NAME","TRACK_TYPE","TRACK_NAME","SONG_ID","POD_ID");
                                Features.deleteTrackFromPlaylist(name14);
                                //Features.displayPlaylist();
                                break;
                            case 11:
                                System.out.format("%1s %15s %20s %20s %25s %25s", "podcastid", "dpodcastname", "celebrityname", "descriptions", "duration", "datereleased");
                                //  System.out.println("podcasti  dpodcastname  celebrityname  descriptions  duration  datereleased");
                                System.out.println("================================================================================================================");
                                Features.displayPodcast();
                                System.out.println("================================================================================================================");
                                System.out.println("do u wanna play podcast? type(yes/no)");
                                String choiceidk = scan.next();
                                try {
                                    if (choiceidk.equalsIgnoreCase("yes")) {
                                        System.out.println("enter podcast name  to play");
                                        String pname = scan.next();
                                        a.PlayTrack(pname);
                                    } else {
                                        System.out.println("understood");
                                        displayMenu();
                                    }
                                } catch (FileNotFoundException ex) {
                                    System.out.println("enter valid file name");
                                }


                                break;
                            case 12:
                                System.out.println("available songs are");
                                // System.out.format("%15s %15s %15s %15s %15s %15s\n","songid","songname","artist","genre","Duration","albumid");
                                // System.out.format("");
                                System.out.println("==================================================================================================================");
                                Features.displaySongs();
                                System.out.println("==================================================================================================================");
                                System.out.println("do u wanna play any tracks? type (yes/no)");
                                String splay = scan.next();
                                try {
                                    if (splay.equalsIgnoreCase("yes")) {
                                        System.out.println("enter track name");
                                        String idk = scan.next();
                                        a.PlayTrack(idk);
                                    } else {
                                        System.out.println("ok");
                                        displayMenu();
                                    }
                                } catch (FileNotFoundException fnfe) {
                                    System.out.println("enter valid file name please");
                                }
                                break;
                            case 13:
                                System.out.println("thanks for visiting. byebye!");//
                                System.exit(0);
                               /* Features.displayPodcast();
                                System.out.println("enter the name of existing or new playlist");
                                //scan.next();
                                String name404 = scan.next();
                                System.out.println("enter the podcast name u wanna add in the " + name404);
                                String name504 = scan.next();

                                // Features.addSongIntoPlaylist(name4,name5,List);
                                Features.addPodcastinp(name404,name504);
                                //Features.addSongIntoPlaylist(name4, name5, List);
                                System.out.println("saved into the playlist");
*/
                            case 14:

                  /* System.out.format("%1s %15s %20s %20s %25s %25s","podcastid",  "dpodcastname",  "celebrityname",  "descriptions",  "duration",  "datereleased");
                  //  System.out.println("podcasti  dpodcastname  celebrityname  descriptions  duration  datereleased");
                    Features.displayPodcast();*/
                                break;
                            case 15:
                                Features.searchCelebrity();
                                System.out.println("enter host name");
                                String seleb=scan.next();
                                try{
                                    Features.searchCelebritydetails();

                                    try{
                                        System.out.println("do u wanna play");
                                        String ansi=scan.next();
                                        a.PlayTrack(ansi);

                                    }catch (Exception df)
                                    {
                                        System.out.println("enter valid song name");
                                    }
                                }
                                catch (Exception c)
                                {
                                    System.out.println("enter valid celeb namne");
                                }
                                break;




                        }
                        //sout  -->want to continue(y/n);
                        System.out.println("want to continue?(true/false)");

                        ans = scan.nextBoolean();
                        //System.out.println("thanks for visiting. ByeBye!");
                    } while (ans == true);
                } else {
                    System.out.println("please enter correct username and password");
                    check=true;
                }


            }
        }while(check);
        //else{
         //   System.out.println("enter valid credentials");
       // }




    }
}
/*
/*check with password, group all song related operations followed by podcast operations,
display list of list of song with every search operations.
 */
