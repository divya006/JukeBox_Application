import javax.sound.sampled.*;
import javax.swing.*;
import javax.xml.xpath.XPath;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class musicApplication {
    public Connection getconnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jukebox", "root", "Divya0606");
        return connection;
    }

    public void getUserDetails() throws SQLException, ClassNotFoundException, InputMismatchException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        Statement statement = getconnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Your choice :1. Registered User 2.New User ");
        int choice = sc.nextInt();
        boolean check = false;
        switch (choice) {
            case 1:
                System.out.println("User name:");
                String name = sc.next();
                System.out.println("password:   ");
                String pass = sc.next();
                ResultSet result = statement.executeQuery("select * from users where username='" +name+"'");
                String username = "";
                String password = "";
                while (result.next()) {
                    username = result.getString(2);
                    password = result.getNString(3);
                }
                if ((pass.equals(password)) && username.equals(name))
            {
                System.out.println("                 ** LOGIN SUCESSFULLY **          ");
                System.out.println("--------------------------------------------------------------");
                System.out.println("                  WELCOME TO THE JUKEBOX          ");
                System.out.println("----------------------------------------------------------------");
                songDetails();
                check = true;
            }


        if (check == false) {
            System.out.println("Invalid user");
            System.out.println("Please Try Again");
        }
        break;
    }

        switch (choice)
        {
            case 2:
            System.out.println("Enter the user Name :");
            String name=sc.next();
            System.out.println("Enter the password");
            String passw=sc.next();
            int i=statement.executeUpdate("insert into users(username,password) values("+"'"+name+"','"+passw+"')");
                System.out.println("");
                System.out.println("*********************    YOUR ACCOUNT IS REGISTERED SUCCESSFULLY     ***********************");
                System.out.println("******************************    LOG IN AGAIN    **************************");

        }
    }
    public void displayuserDetaile()
    {
        System.out.println("             **************************************************               ");
        System.out.println("                          WELCOME TO THE JUKEBOX                               ");
        System.out.println("             ***************************************************             ");
        System.out.println("                   1.REGISTERED USER         2.NEW USER    ");
        System.out.println("-----------------------------------------------------------------------------------------------");
    }

     public void songDetails() throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException, IOException {
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jukebox", "root", "Divya0606");
         Statement statement = connection.createStatement();

         Scanner sc = new Scanner(System.in);
         int n;
         do {
             System.out.println("");
             System.out.println("Enter 1 for songs, Enter 2 Playlist And Enter 3 for Podcast");
             System.out.println("1.SONGS");
             System.out.println("2.PLAYLIST");
             System.out.println("3.PODCAST");
             int i = sc.nextInt();
             if (i == 1) {
                 int p;
                 do {
                     System.out.println("Enter The Number If You Want to  Search By");
                     System.out.println("------------------------------------------------------------------------------------");
                     System.out.println("1.SONG LIST");
                     System.out.println("2.GENRE");
                     System.out.println("3.ARTIST");
                     System.out.println("");
                     int s = sc.nextInt();
                     if (s == 1) {
                         int l;
                         do {
                             System.out.println("");
                             System.out.println("Song ID  :Song Name    :Duartion");
                             System.out.println("");
                             ResultSet set = statement.executeQuery("select * from songs");
                             ArrayList<String> list = new ArrayList<>();
                             while (set.next()) {
                                 System.out.println(set.getInt(1) + "  " + set.getString(2) + "             "+set.getString(7));
                                 list.add(set.getString(6));
                             }
                             System.out.println("---------------------------------------------------------------");
                             System.out.println("If yoy want play all list enter 1 or play specific song enter 2");
                             System.out.println("---------------------------------------------------------------");
                             int a = sc.nextInt();
                             if (a == 1) {
                                 System.out.println("Play the music list Enter 1 ");
                                 playSongs(list);
                             } else if (a == 2) {
                                 System.out.println("Enter the song id");
                                 int id = sc.nextInt();
                                 playSpecificSong(id);
                             }
                             System.out.println("Enter 1==> Go back To list or Enter 0==>For Previouse option");
                             l=sc.nextInt();
                         }while(l==1);

                     } else if (s == 2) {
                         int e;
                         do {
                             System.out.println("");
                             System.out.println("Search By Genre");
                             System.out.println("");
                             ResultSet res = statement.executeQuery("select distinct(genre) from songs");
                             while (res.next()) {
                                 System.out.println(res.getString(1));
                             }
                             System.out.println("");
                             System.out.println("************* ENTER GENRE *********** ");
                             Scanner scc = new Scanner(System.in);
                             String genre = scc.nextLine();
                             ResultSet resultSet = statement.executeQuery("select * from songs where genre='" + genre + "'");
                             List<Integer> list=new ArrayList<>();
                             while (resultSet.next()) {
                                 System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2));
                                 list.add(resultSet.getInt(1));
                             }
                             System.out.println("-----------------------");
                             System.out.println("Enter song id");
                             System.out.println("-----------------------");
                             int songid = sc.nextInt();
                             for(int a:list)
                             {
                                 if(a==songid)
                                 {
                                     playbyGenre(songid);
                                 }else{
                                     System.out.println("Song is not available");
                                 }
                             }
                             System.out.println("Enter 1==> Go Back For Genre List or Enter 0 For Priviouse list or main menu ");
                             e=sc.nextInt();
                         }while(e==1);
                     } else if (s == 3) {
                         int f;
                         do {
                             System.out.println("");
                             System.out.println("Search By Artist");
                             System.out.println("");
                             ResultSet set = statement.executeQuery("select distinct(artistname) from songs");
                             while (set.next()) {
                                 System.out.println(set.getString(1));
                             }
                             System.out.println("");
                             System.out.println("************* ENTER ARTIST NAME *********** ");
                             Scanner scan = new Scanner(System.in);
                             String artistname = scan.nextLine();
                             ResultSet resultSet = statement.executeQuery("select * from songs where artistname='" + artistname + "'");
                             List<Integer> list1=new ArrayList<>();
                             while (resultSet.next()) {
                                 System.out.println(resultSet.getInt(1) + "  " + resultSet.getString(2));
                                 list1.add(resultSet.getInt(1));
                             }
                             System.out.println("Enter song Id");
                             int songid = sc.nextInt();
                             for(int b:list1)
                             {
                                 if(b==songid)
                                 {
                                     playbyArtist(songid);
                                 }
                                 else{
                                     System.out.println("Song is not available");
                                 }
                             }
                             System.out.println("Enter 1 ==> Go Back to Artist list or Enter 0==> Priviouse list or main menu option");
                             f=sc.nextInt();
                         }while(f==1);
                     } else {
                         System.out.println("Enter Valid Number");
                     }
                     System.out.println("Enter 1 ==> previous list Menu and Enter ==> 0 Main menu or exit option");
                     p=sc.nextInt();
                 }while(p==1);

             } else if (i == 2) {
                 System.out.println("Enter number what you want to choice");
                 System.out.println("");
                 int m;
                 do {
                     System.out.println("Enter 1 ==> Open Existing playlist ,Enter 2==>Favourate songs and Enter 3 ==> Add songs and podacst Playlist ");
                     System.out.println("1.Existing Playlist");
                     System.out.println("2.Favourate songs");
                     System.out.println("3.add songs and podcast New Playlist");
                     int a = sc.nextInt();
                     if (a == 1) {
                         int g;
                         do {
                             ResultSet set = statement.executeQuery("select * from playlist");
                             System.out.println("PlAYLIST ID :   SONG  ID   :   SONG NAME             :  PODCAST NAME    :   PODCAST ID ");
                             System.out.println("------------------------------------------------------------------------------------------------------------------------");
                             while (set.next()) {
                                 System.out.println(set.getInt(1) + "             " + set.getInt(2) + "          " + set.getString(3) +"         "   + set.getString(4) +"               "+set.getInt(5));
                             }
                             System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");
                             System.out.println("Enter 1==>Play Songs or Enter 2==> Play Podcast");
                             System.out.println("1.play songs");
                             System.out.println("2.play podcast");
                             int k = sc.nextInt();
                             if (k == 1) {
                                 System.out.println("Enter song Id");
                                 int id = sc.nextInt();
                                 playPlaylist(id);
                             } else if (k == 2) {
                                 System.out.println("Enter podcast Id");
                                 int pod = sc.nextInt();
                                 if (pod == 2221) {
                                     System.out.println("------PODCAST EPISODES------");
                                     System.out.println("");
                                     System.out.println("  Episode Id   :  Episode Name");
                                     ResultSet res1 = statement.executeQuery("select * from podcastepisode where episodeid=" + 1);
                                     while (res1.next()) {
                                         System.out.println(res1.getDouble(2) + "            " + res1.getString(3));
                                     }
                                     System.out.println("");
                                     System.out.println("Enter the episode Id");
                                     double podid = sc.nextDouble();
                                     playbypodcast(podid);
                                 } else if (pod == 2222) {
                                     System.out.println("--------PODCAST EPISODS-------");
                                     System.out.println("");
                                     System.out.println("Podcast id :   Episode Id   :  Episode Name");
                                     ResultSet res1 = statement.executeQuery("select * from podcastepisode where episodeid=" + 2);
                                     while (res1.next()) {
                                         System.out.println(res1.getInt(1) + "            " + res1.getDouble(2) + "            " + res1.getString(3));
                                     }
                                     System.out.println("");
                                     System.out.println("Enter the episode Id");
                                     double podid = sc.nextDouble();
                                     playbypodcast(podid);
                                 } else if (pod == 2223) {
                                     System.out.println("--------PODCAST EPISODS-------");
                                     System.out.println("");
                                     System.out.println("Podcast id :   Episode Id   :  Episode Name");
                                     ResultSet res1 = statement.executeQuery("select * from podcastepisode where episodeid=" + 3);
                                     while (res1.next()) {
                                         System.out.println(res1.getInt(1) + "            " + res1.getDouble(2) + "            " + res1.getString(3));
                                     }
                                     System.out.println("");
                                     System.out.println("Enter the episode Id");
                                     double podid = sc.nextDouble();
                                     playbypodcast(podid);
                                 } else if (pod == 2224) {
                                     System.out.println("--------PODCAST EPISODS-------");
                                     System.out.println("");
                                     System.out.println("Podcast id :   Episode Id   :  Episode Name");
                                     ResultSet res1 = statement.executeQuery("select * from podcastepisode where episodeid=" + 4);
                                     while (res1.next()) {
                                         System.out.println(res1.getInt(1) + "            " + res1.getDouble(2) + "            " + res1.getString(3));
                                     }
                                     System.out.println("");
                                     System.out.println("Enter the episode Id");
                                     double podid = sc.nextDouble();
                                     playbypodcast(podid);
                                 } else if (pod == 2225) {
                                     System.out.println("--------PODCAST EPISODS-------");
                                     System.out.println("");
                                     System.out.println("Podcast id :   Episode Id   :  Episode Name");
                                     ResultSet res1 = statement.executeQuery("select * from podcastepisode where episodeid=" + 5);
                                     while (res1.next()) {
                                         System.out.println(res1.getInt(1) + "            " + res1.getDouble(2) + "            " + res1.getString(3));
                                     }
                                     System.out.println("");
                                     System.out.println("Enter the episode Id");
                                     double podid = sc.nextDouble();
                                     playbypodcast(podid);
                                 } else {
                                     System.out.println("Enter valid input");
                                 }
                             }
                             else {
                                 System.out.println("Enter valid Number");
                             }
                             System.out.println("Enter 1==>Go Back To Playlist or 0 for previouse option");
                             g=sc.nextInt();
                         }while (g==1);
                     }
                     else if(a==2)
                     {
                         int h;
                         do {
                             System.out.println("");
                             System.out.println("Song ID  :Song Name");
                             System.out.println("");
                             ResultSet set = statement.executeQuery("select * from songs");
                             ArrayList<String> list = new ArrayList<>();
                             while (set.next()) {
                                 System.out.println(set.getInt(1) + "  " + set.getString(2));
                                 list.add(set.getString(6));
                             }
                             System.out.println("Enter the song id");
                             int id = sc.nextInt();
                             playSpecificSong(id);
                             System.out.println("Enter 1==> Go Back To Song list or Enter 0==> For priviouse option");
                             h=sc.nextInt();
                         }while(h==1);
                     }
                 else if (a == 3) {
                         System.out.println("Enter the playlis id");
                         int id = sc.nextInt();
                         System.out.println("Enter Song Id");
                         ResultSet resultSet= statement.executeQuery("select * from songs");
                         while (resultSet.next())
                         {
                             System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2));
                         }
                         int songid = sc.nextInt();
                         System.out.println("Enter Song Name");
                         String name = sc.next();
                         System.out.println("Enter the podcast name");
                         ResultSet re=statement.executeQuery("select * from playlist");
                         while(re.next())
                         {
                             System.out.println(re.getInt(5)+" "+re.getString(4));
                         }
                         String podname = sc.next();
                         System.out.println("Enter the podcast id");
                         int podid = sc.nextInt();
                         System.out.println("Enter user id");
                         int user=sc.nextInt();
                         int i1 = statement.executeUpdate("insert into playlist values(" + id + ",'" + songid + "','" + name + "','" + podname + "','" + podid + "','"+user+"')");
                     }
                     System.out.println("Enter 1==> Go Back To List or Enter 0==>Back option");
                     m=sc.nextInt();
                 }while(m==1);
             } else if (i == 3) {
                 int l;
                 do {
                     System.out.println("Enter The Number If You Want to  Search By");
                     System.out.println("----------------------------------------------------------------------------------");
                     System.out.println("1.PODCAST NAME ");
                     System.out.println("2.ARTIST");
                     System.out.println("3.GENRE");
                     System.out.println("");
                     int b = sc.nextInt();
                     if (b == 1) {
                         int p;
                         do {
                             System.out.println("Podcast Id    :    Podcast Name");
                             System.out.println("");
                             ResultSet set = statement.executeQuery("select * from podcast");
                             while (set.next()) {
                                 System.out.println(set.getInt(1) + "  " + set.getString(2));
                             }
                             System.out.println("-----------------------");
                             System.out.println("Enter podcast id");
                             System.out.println("-----------------------");
                             int podcastid = sc.nextInt();
                             switch (podcastid) {
                                     case 2221:
                                         System.out.println("**********PODCAST EPISODS***********");
                                         System.out.println("");
                                         System.out.println("Podcast id :   Episode Id   :  Episode Name");
                                         ResultSet res1 = statement.executeQuery("select * from podcastepisode where episodeid=" + 1);
                                         while (res1.next()) {
                                             System.out.println(res1.getInt(1) + "            " + res1.getDouble(2) + "            " + res1.getString(3));
                                         }
                                         System.out.println("");
                                         System.out.println("Enter the episode Id");
                                         double podid = sc.nextDouble();
                                         //  System.out.println("executed");
                                         playbypodcast(podid);
                                         break;
                                 case 2222:
                                     System.out.println("**********PODCAST EPISODS***********");
                                     System.out.println("");
                                     System.out.println("Podcast id :   Episode Id   :  Episode Name");
                                     ResultSet res2 = statement.executeQuery("select * from podcastepisode where episodeid=" + 2);
                                     while (res2.next()) {
                                         System.out.println(res2.getInt(1) + "              " + res2.getDouble(2) + "              " + res2.getString(3));
                                     }
                                     System.out.println("");
                                     System.out.println("Enter the episode Id");
                                     double podid1 = sc.nextDouble();
                                     playbypodcast(podid1);
                                     break;
                                 case 2223:
                                     System.out.println("**********PODCAST EPISODS***********");
                                     System.out.println("");
                                     System.out.println("Podcast id :   Episode Id   :  Episode Name");
                                     ResultSet res3 = statement.executeQuery("select * from podcastepisode where episodeid=" + 3);
                                     while (res3.next()) {
                                         System.out.println(res3.getInt(1) + "                   " + res3.getDouble(2) + "           " + res3.getString(3));
                                     }
                                     System.out.println("");
                                     System.out.println("Enter the episode Id");
                                     double podid2 = sc.nextDouble();
                                     playbypodcast(podid2);
                                     break;
                                 case 2224:
                                     System.out.println("**********PODCAST EPISODS***********");
                                     System.out.println("");
                                     System.out.println("Podcast id :    Episode Id :    Episode Name");
                                     ResultSet res4 = statement.executeQuery("select * from podcastepisode where episodeid=" + 4);
                                     while (res4.next()) {
                                         System.out.println(res4.getInt(1) + "                " + res4.getDouble(2) + "            " + res4.getString(3));
                                     }
                                     System.out.println("");
                                     System.out.println("Enter the episode Id");
                                     double podid3 = sc.nextDouble();
                                     playbypodcast(podid3);
                                     break;
                                 case 2225:
                                     System.out.println("**********PODCAST EPISODS***********");
                                     System.out.println("");
                                     System.out.println("Podcast id :    Episode Id :     Episode Name");
                                     ResultSet res5 = statement.executeQuery("select * from podcastepisode where episodeid=" + 5);
                                     while (res5.next()) {
                                         System.out.println(res5.getInt(1) + "               " + res5.getDouble(2) + "             " + res5.getString(3));
                                     }
                                     System.out.println("");
                                     System.out.println("Enter the episode Id");
                                     double podid4 = sc.nextDouble();
                                     playbypodcast(podid4);
                                     break;
                                 case 2226:
                                     System.out.println("**********PODCAST EPISODS***********");
                                     System.out.println("");
                                     System.out.println("Podcast id :    Episode Id :    Episode Name");
                                     ResultSet res6 = statement.executeQuery("select * from podcastepisode where episodeid=" + 6);
                                     while (res6.next()) {
                                         System.out.println(res6.getInt(1) + "                " + res6.getDouble(2) + "             " + res6.getString(3));
                                     }
                                     System.out.println("");
                                     System.out.println("Enter the episode Id");
                                     double podid5 = sc.nextDouble();
                                     playbypodcast(podid5);
                                     break;
                             }
                             System.out.println("Enter 1 Go Back Podacst List or Enter 0 ==>Previous Potion");
                             p=sc.nextInt();
                         }while(p==1);
                     } else if (b == 2) {
                         int c;
                         do {
                             System.out.println("*******  Search By Artist  ********");
                             System.out.println("");
                             ResultSet set = statement.executeQuery("select * from podcast");
                             while (set.next()) {
                                 System.out.println(set.getString(3));
                             }
                             System.out.println("");
                             System.out.println("************* ENTER ARTIST NAME *********** ");
                             List<Double> list3=new ArrayList<>();
                             Scanner scc = new Scanner(System.in);
                             String artist = scc.nextLine();
                             ResultSet resultSet = statement.executeQuery("select * from podcastepisode where artistName='"+ artist+"'");
                             System.out.println("Episode Id :  Episode Name");
                             while (resultSet.next()) {
                                 System.out.println(resultSet.getDouble(2) + " " + resultSet.getString(3));
                                 list3.add(resultSet.getDouble(2));
                             }
                             System.out.println("");
                             System.out.println("Enter the episode Id");
                             double podid1 = sc.nextDouble();
                             for(double i1 :list3)
                             {
                                 if(i1==podid1)
                                 {
                                     playbyArtist((int) podid1);
                                 }
                             }
                             System.out.println("Enter 1==>Back to Artist List or Enter ==>0 to Previouse Option");
                             c=sc.nextInt();
                         }while(c==1);

                     } else if (b == 3) {
                         int d;
                         do {
                             System.out.println("******* SEARCH BY GENRE ********");
                             System.out.println("");
                             ResultSet set = statement.executeQuery("select * from podcast");
                             while (set.next()) {
                                 System.out.println(set.getString(5));
                             }
                             System.out.println("");
                             System.out.println("***** ENTER THE GENRE *****");
                             List<Double> list4=new ArrayList<>();
                             Scanner scc = new Scanner(System.in);
                             String genre = scc.nextLine();
                             ResultSet resultSet = statement.executeQuery("select * from podcastepisode where genre='" + genre + "'");
                             System.out.println("Episode Id  :    Episode Name");
                             while (resultSet.next()) {
                                 System.out.println(resultSet.getDouble(2) + "                " + resultSet.getString(3));
                                 System.out.println("");
                                 list4.add(resultSet.getDouble(2));
                             }
                             System.out.println("Enter the episode Id");
                             double podid1 = sc.nextDouble();
                             for(double i3:list4)
                             {
                                 if(i3==podid1)
                                 {
                                     playbypodGenre(podid1);
                                 }
                             }
                             System.out.println("Enter 1==>Go back To Genre List or Enter 0 For Previouse Option");
                             d=sc.nextInt();
                         }while(d==1);
                     } else {
                         System.out.println("Enter valid input");
                     }

                     System.out.println("Enter 1 ==> previous Menu or Enter 0 ==> Back To main menu or exit");
                     l=sc.nextInt();
                 }while(l==1);
             }
             else {
                 System.out.println("Enter valid input");
             }
             System.out.println("Enter 1==> Back  Enter 0==> Exit");
             n=sc.nextInt();
         }while(n==1);
     }
    public void playSongs(List<String>songList) throws IOException, LineUnavailableException
    {
        long currentTime = 0;
        Scanner sca = new Scanner(System.in);
        Clip clip ;
        int a ;

        try
        {
            for(int i=0;i<songList.size();i++)
            {
                File songFile = new File(songList.get(i));
                AudioInputStream audioinputstream =AudioSystem.getAudioInputStream(songFile);
                clip = AudioSystem.getClip();
                clip.open(audioinputstream);
                currentTime = clip.getMicrosecondPosition();

                do
                {
                    System.out.println("1-play , 2-Stop ,3-Resume , 4-Loop , 5-PlayNext , 6-PlayPrevious, 7-Exit ");
                    System.out.println("Enter Choice");
                    a= sca.nextInt();
                    switch(a)
                    {
                        case 1 :

                            clip.start();
                            break ;

                        case 2 :
                            long tot=clip.getMicrosecondLength();
                            long micro=clip.getMicrosecondPosition();
                            System.out.println(clip.getMicrosecondLength()/1000000);
                            System.out.println("Played in second:"+micro/1000000);
                            System.out.println("Remaining time for this song:"+(tot-micro)/1000000);

                            clip.stop();
                            break;

                        case 3 :
                            clip.setMicrosecondPosition(currentTime);
                            clip.start();
                            break ;

                        case 4 :

                            clip.loop(clip.LOOP_CONTINUOUSLY);
                            break;

                        case 5:

                            clip.close();
                            if(i==songList.size()-1)
                            {
                                i=-1;
                            }
                            break;
                        case 6 :
                            i=i-2;
                           a=5;
                            clip.close();
                            break;
                        case 7 :
                            clip.close();
                            a=5;
                            i=songList.size();
                            break;
                    }

                }while(a!=5);

            }

        }catch (UnsupportedAudioFileException ex)
        {
            ex.printStackTrace();
        }
    }
    public void playSpecificSong(int id) throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        Statement statement=getconnection().createStatement();
        ResultSet resultSet=statement.executeQuery("Select filepath from songs where songid="+id);
        Scanner sc=new Scanner(System.in);
        long currentTime=0;
        int a;
        while(resultSet.next())
        {
            String path=resultSet.getString(1);
            AudioInputStream audio= AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
            Clip clip=AudioSystem.getClip();
            clip.open(audio);
            do
            {
                System.out.println("1-play , 2-Stop , 3-Resume , 4-Loop , 5-Privouse Option ");
                System.out.println("Enter Choice");
                a= sc.nextInt();
                switch(a)
                {
                    case 1 :

                        clip.start();
                        break ;

                    case 2 :
                        long tot=clip.getMicrosecondLength();
                        long micro=clip.getMicrosecondPosition();
                        System.out.println(clip.getMicrosecondLength()/1000000);
                        System.out.println("Played in second:"+micro/1000000);
                        System.out.println("Remaining time for this song:"+(tot-micro)/1000000);
                        clip.stop();
                        break;

                    case 3 :

                        clip.setMicrosecondPosition(currentTime);
                        clip.start();
                        break ;

                    case 4 :

                        clip.loop(clip.LOOP_CONTINUOUSLY);
                        break;

                    case 5:
                        clip.close();
                        break;

                }

            }while(a!=5);
        }
    }
    public void playbyGenre(int id) throws SQLException, ClassNotFoundException, IOException, LineUnavailableException, UnsupportedAudioFileException
    {
        Statement statement=getconnection().createStatement();
        ResultSet resultSet=statement.executeQuery("Select filepath from songs where songid="+id);
        Scanner sc=new Scanner(System.in);
        long currentTime=0;
        int a;
        while(resultSet.next())
        {
            String path=resultSet.getString(1);
            AudioInputStream audio= AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
            Clip clip=AudioSystem.getClip();
            clip.open(audio);
            do
            {
                System.out.println("1-play , 2-Stop , 3-Resume , 4-Loop , 5-Privouse option ");
                System.out.println("Enter Choice");
                a= sc.nextInt();
                switch(a)
                {
                    case 1 :

                        clip.start();
                        break ;

                    case 2 :
                        long tot=clip.getMicrosecondLength();
                        long micro=clip.getMicrosecondPosition();
                        System.out.println(clip.getMicrosecondLength()/1000000);
                        System.out.println("Played in second:"+micro/1000000);
                        System.out.println("Remaining time for this song:"+(tot-micro)/1000000);
                        clip.stop();
                        break;

                    case 3 :

                        clip.setMicrosecondPosition(currentTime);
                        clip.start();
                        break ;

                    case 4 :

                        clip.loop(clip.LOOP_CONTINUOUSLY);
                        break;

                    case 5:

                        clip.close();
                        break;

                }

            }while(a!=5);
        }
    }
    public void playbyArtist(int id) throws SQLException, ClassNotFoundException, IOException, LineUnavailableException, UnsupportedAudioFileException
    {
        Statement statement=getconnection().createStatement();
        ResultSet resultSet=statement.executeQuery("Select filepath from songs where songid="+id);
        Scanner sc=new Scanner(System.in);
        long currentTime=0;
        int a;
        while(resultSet.next())
        {
            String path=resultSet.getString(1);
            AudioInputStream audio= AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
            Clip clip=AudioSystem.getClip();
            clip.open(audio);
            do
            {
                System.out.println("1-play , 2-Stop , 3-Resume , 4-Loop , 5-Previouse option ");
                System.out.println("Enter Choice");
                a= sc.nextInt();
                switch(a)
                {
                    case 1 :

                        clip.start();
                        break ;

                    case 2 :
                        long tot=clip.getMicrosecondLength();
                        long micro=clip.getMicrosecondPosition();
                        System.out.println(clip.getMicrosecondLength()/1000000);
                        System.out.println("Played in second:"+micro/1000000);
                        System.out.println("Remaining time for this song:"+(tot-micro)/1000000);
                        clip.stop();
                        break;

                    case 3 :

                        clip.setMicrosecondPosition(currentTime);
                        clip.start();
                        break ;

                    case 4 :

                        clip.loop(clip.LOOP_CONTINUOUSLY);
                        break;

                    case 5:

                        clip.close();
                        break;

                }

            }while(a!=5);
        }
    }
    public void playPlaylist(int id) throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        Statement statement=getconnection().createStatement();
        ResultSet resultSet=statement.executeQuery("Select filepath from songs where songid="+id);
        Scanner sc=new Scanner(System.in);
        long currentTime=0;
        int a;
        while(resultSet.next())
        {
            String path=resultSet.getString(1);
            AudioInputStream audio= AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
            Clip clip=AudioSystem.getClip();
            clip.open(audio);
            do
            {
                System.out.println("1-play , 2-Stop , 3-Resume , 4-Loop , 5-Privouse option");
                System.out.println("Enter Choice");
                a= sc.nextInt();
                switch(a)
                {
                    case 1 :

                        clip.start();
                        break ;

                    case 2 :
                        long tot=clip.getMicrosecondLength();
                        long micro=clip.getMicrosecondPosition();
                        System.out.println(clip.getMicrosecondLength()/1000000);
                        System.out.println("Played in second:"+micro/1000000);
                        System.out.println("Remaining time for this song:"+(tot-micro)/1000000);
                        clip.stop();
                        break;

                    case 3 :

                        clip.setMicrosecondPosition(currentTime);
                        clip.start();
                        break ;

                    case 4 :

                        clip.loop(clip.LOOP_CONTINUOUSLY);
                        break;

                    case 5:
                        clip.close();
                        break;

                }

            }while(a!=5);
        }
    }
    public void playbypodcast(double id) throws SQLException, ClassNotFoundException, IOException, LineUnavailableException, UnsupportedAudioFileException {
        Statement statement=getconnection().createStatement();
        ResultSet resultSet=statement.executeQuery("Select * from podcastepisode where episodeno="+id);
        Scanner sc=new Scanner(System.in);
        long currentTime=0;
        int a;
        while(resultSet.next())
        {
            String path=resultSet.getString(4);
            AudioInputStream audio= AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
            Clip clip=AudioSystem.getClip();
            clip.open(audio);
            do
            {
                System.out.println("1-play , 2-Stop , 3-Resume , 4-Loop , 5-Previouse Option ");
                System.out.println("Enter Choice");
                a= sc.nextInt();
                switch(a)
                {
                    case 1 :

                        clip.start();
                        break ;

                    case 2 :
                        long tot=clip.getMicrosecondLength();
                        long micro=clip.getMicrosecondPosition();
                        System.out.println(clip.getMicrosecondLength()/1000000);
                        System.out.println("Played in second:"+micro/1000000);
                        System.out.println("Remaining time for this song:"+(tot-micro)/1000000);
                        clip.stop();
                        break;

                    case 3 :

                        clip.setMicrosecondPosition(currentTime);
                        clip.start();
                        break ;

                    case 4 :

                        clip.loop(clip.LOOP_CONTINUOUSLY);
                        break;

                    case 5:

                        clip.close();
                        break;

                }

            }while(a!=5);
        }
        }
    public void playbypodArtist(double id) throws SQLException, ClassNotFoundException, IOException, LineUnavailableException, UnsupportedAudioFileException {
        Statement statement=getconnection().createStatement();
        ResultSet resultSet=statement.executeQuery("Select * from podcastepisode where episodeno="+id);
        Scanner sc=new Scanner(System.in);
        long currentTime=0;
        int a;
        while(resultSet.next())
        {
            String path=resultSet.getString(4);
            AudioInputStream audio= AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
            Clip clip=AudioSystem.getClip();
            clip.open(audio);
            do
            {
                System.out.println("1-play , 2-Stop , 3-Resume , 4-Loop , 5-Privouse option ");
                System.out.println("Enter Choice");
                a= sc.nextInt();
                switch(a)
                {
                    case 1 :

                        clip.start();
                        break ;

                    case 2 :
                        long tot=clip.getMicrosecondLength();
                        long micro=clip.getMicrosecondPosition();
                        System.out.println(clip.getMicrosecondLength()/1000000);
                        System.out.println("Played in second:"+micro/1000000);
                        System.out.println("Remaining time for this song:"+(tot-micro)/1000000);
                        clip.stop();
                        break;

                    case 3 :

                        clip.setMicrosecondPosition(currentTime);
                        clip.start();
                        break ;

                    case 4 :

                        clip.loop(clip.LOOP_CONTINUOUSLY);
                        break;

                    case 5:
                        clip.close();
                        break;

                }

            }while(a!=5);
        }
      }
    public void playbypodGenre(double id) throws SQLException, ClassNotFoundException, IOException, LineUnavailableException, UnsupportedAudioFileException
    {
        Statement statement=getconnection().createStatement();
        ResultSet resultSet=statement.executeQuery("Select * from podcastepisode where episodeno="+id);
        Scanner sc=new Scanner(System.in);
        long currentTime=0;
        int a;
        while(resultSet.next())
        {
            String path=resultSet.getString(4);
            AudioInputStream audio= AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
            Clip clip=AudioSystem.getClip();
            clip.open(audio);
            do
            {
                System.out.println("1-play , 2-Stop , 3-Resume , 4-Loop , 5-Next Episode ");
                System.out.println("Enter Choice");
                a= sc.nextInt();
                switch(a)
                {
                    case 1 :

                        clip.start();
                        break ;

                    case 2 :
                        long tot=clip.getMicrosecondLength();
                        long micro=clip.getMicrosecondPosition();
                        System.out.println(clip.getMicrosecondLength()/1000000);
                        System.out.println("Played in second:"+micro/1000000);
                        System.out.println("Remaining time for this song:"+(tot-micro)/1000000);
                        clip.stop();
                        break;

                    case 3 :

                        clip.setMicrosecondPosition(currentTime);
                        clip.start();
                        break ;

                    case 4 :

                        clip.loop(clip.LOOP_CONTINUOUSLY);
                        break;

                    case 5:
                        clip.close();
                        break;

                }

            }while(a!=5);
        }
     }
}


