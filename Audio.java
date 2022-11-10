package jukebox1;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
public class Audio {
    Clip clip;
    AudioInputStream audioInputStream;
    public  String status="";
    public String filePath;
    public String musicLocation;

    Scanner sc=new Scanner(System.in);
     /*public void playMusic(String musicLocation, List<Playlist> list1) throws UnsupportedAudioFileException,IOException,LineUnavailableException,SQLException{
        for(Playlist s:list1)
        {
            if(musicLocation.equalsIgnoreCase(s.getTrackName())){
                Audio aud = new Audio();
                aud.PlayTrack(musicLocation);
            }
        }
    }*/
    public void PlayTrack(String name) throws UnsupportedAudioFileException, IOException, LineUnavailableException, SQLException {
        //String playtrack = "select "
        filePath="C:\\Users\\DELL\\Downloads\\"+name+".wav";
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        play();
        System.out.println("\t\t\t\t---------------------Track is start playing---------------------------\n");
        boolean start=false;
        while(!start) {
            System.out.println("1. pause");
            System.out.println("2. resume");
            System.out.println("3. restart");
            System.out.println("4. stop");
            System.out.println("5. forward");
           // System.out.println("6. reverse");
            System.out.println("6. stop operations");

            System.out.println("Enter your choice");
            int c = sc.nextInt();
            switch (c)
            {
                case 1:
                    pause();
                    break;
                case 2:
                    resumeAudio();
                    break;
                case 3:
                    restart();
                    break;
                case 4:
                    stop();
                    break;
                case 5:
                    System.out.println("Set time for forward the clip (in sec)");
                    int f=sc.nextInt();
                    forward(f);
                    break;
                case 6:
                    start=true;
                    clip.stop();
                    System.out.println("bye");
                    break;


            }
        }
    }
    public void play()
    {

        clip.start();

        status = "play";
    }


    public void pause()
    {
        if (status.equals("paused"))
        {
            System.out.println("audio is already paused");
            return;
        }

        clip.stop();
        status = "paused";
    }

    public void resumeAudio() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException
    {
        if (status.equals("play"))
        {
            System.out.println("Audio is already "+
                    "being played");
            return;
        }
        clip.close();
        resetAudioStream();


        play();
    }


    public void restart() throws IOException, LineUnavailableException,
            UnsupportedAudioFileException
    {
        clip.stop();
        clip.close();
        resetAudioStream();
        play();
    }
    public void stop() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException
    {

        clip.stop();
        clip.close();
    }


    public void forward(int f)
    {
        clip.setMicrosecondPosition(clip.getMicrosecondPosition()+(f*1000000));
    }
    public void resetAudioStream() throws UnsupportedAudioFileException, IOException,
            LineUnavailableException
    {
        audioInputStream = AudioSystem.getAudioInputStream(
                new File(filePath).getAbsoluteFile());
        clip.open(audioInputStream);
    }


   // public void filePath(String name3, List<Playlist> list) {
    //}
}