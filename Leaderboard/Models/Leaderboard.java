package Leaderboard.Models;

import Interface.LeaderboardInterface;

import java.io.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Leaderboard implements LeaderboardInterface {
    private ArrayList<ArrayList<String>> leaderboard = new ArrayList<ArrayList<String>>(); //setting up Leaderboard arraylist
    private int order;

    public ArrayList<ArrayList<String>> getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(ArrayList<ArrayList<String>> leaderboard) {
        this.leaderboard = leaderboard;
    }

    public void saveToLeaderBoard(String file, String name, String value, String lvl) {
        FileWriter fileWriter = null;
        try {


            FileWriter writer = new FileWriter(new File((getClass().getResource("/text/" + file)).getFile()), true);

            writer.write(name + "," + value + "," + LocalDateTime.now() + "," + lvl + "\n");
            writer.close();
            System.out.println("Written");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void cleanFile(String file) {
        FileWriter fileWriter = null;
        try {


            FileWriter writer = new FileWriter(new File(getClass().getClassLoader().getResource("/text/" + file).getFile()));

            writer.write("Name,0,0,0");
            writer.close();
            System.out.println("erased");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  Leaderboard(){}

    public Leaderboard(String url) {

        loadLeaderboard(url);
    }

    public ArrayList<ArrayList<String>> loadLeaderboard(String url) {
        leaderboard.clear();
        File file1 = null;
        InputStream file = null;

        String elements[];
        leaderboard = new ArrayList<ArrayList<String>>();
        //setting up Leaderboard arraylist
        leaderboard.add(new ArrayList<String>());
        leaderboard.add(new ArrayList<String>());
        leaderboard.add(new ArrayList<String>());
        leaderboard.add(new ArrayList<String>());

        try {
            file1 = new File(this.getClass().getResource("/text/" + url).getFile());
            Scanner in = new Scanner(file1);
            int c;

            while (in.hasNext()) {

                elements = (in.nextLine()).split(",");
                leaderboard.get(0).add(elements[0]);
                leaderboard.get(1).add(elements[1]);
                leaderboard.get(2).add(elements[2]);
                leaderboard.get(3).add(elements[3]);
            }
            in.close();
        } catch (FileNotFoundException anError) {
            System.out.print("file not found!!!");
            saveToLeaderBoard(url, "0", "0", "simple maze");
        }
        leaderboard = sort(leaderboard);
        return leaderboard;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }


    public String toString() {
        String leaderboardS = "";

        for (int i = 0; i < leaderboard.get(0).size(); i++) {
            leaderboardS += "Name: " + leaderboard.get(0).get(i) + " Value: " + leaderboard.get(1).get(i) + "Date: " + leaderboard.get(2).get(i) + "Maze Level: " + leaderboard.get(3).get(i) + "\n";
        }
        return leaderboardS;
    }

    @Override
    public ArrayList<ArrayList<String>> sort(ArrayList<ArrayList<String>> list) {


        ArrayList<ArrayList<String>> temp = new ArrayList<ArrayList<String>>();
        temp.add(new ArrayList<String>());
        temp.add(new ArrayList<String>());
        temp.add(new ArrayList<String>());
        temp.add(new ArrayList<String>());


        temp.get(0).add("");
        temp.get(1).add("");
        temp.get(2).add("");
        temp.get(3).add("");


        if (order >= 0) {
            for (int i = 0; i < list.get(0).size(); i++) {

                for (int j = i + 1; j < list.get(0).size(); j++) {
                    if (list.get(1).get(j).compareToIgnoreCase(list.get(1).get(i)) > 0) {


                        temp.get(0).set(0, list.get(0).get(j));
                        temp.get(1).set(0, list.get(1).get(j));
                        temp.get(2).set(0, list.get(2).get(j));
                        temp.get(3).set(0, list.get(3).get(j));


                        list.get(0).set(j, list.get(0).get(i));
                        list.get(1).set(j, list.get(1).get(i));
                        list.get(2).set(j, list.get(2).get(i));
                        list.get(3).set(j, list.get(3).get(i));


                        list.get(0).set(i, temp.get(0).get(0));
                        list.get(1).set(i, temp.get(1).get(0));
                        list.get(2).set(j, list.get(2).get(i));
                        list.get(3).set(j, list.get(3).get(i));

                    }
                }
            }
        } else
            for (int i = 0; i < list.get(0).size(); i++) {

                for (int j = i + 1; j < list.get(0).size(); j++) {
                    if (list.get(1).get(j).compareToIgnoreCase(list.get(1).get(i)) < 0) {


                        temp.get(0).set(0, list.get(0).get(j));
                        temp.get(1).set(0, list.get(1).get(j));
                        temp.get(2).set(0, list.get(2).get(j));
                        temp.get(3).set(0, list.get(3).get(j));


                        list.get(0).set(j, list.get(0).get(i));
                        list.get(1).set(j, list.get(1).get(i));
                        list.get(2).set(j, list.get(2).get(i));
                        list.get(3).set(j, list.get(3).get(i));


                        list.get(0).set(i, temp.get(0).get(0));
                        list.get(1).set(i, temp.get(1).get(0));
                        list.get(2).set(j, list.get(2).get(i));
                        list.get(3).set(j, list.get(3).get(i));

                    }
                }
            }
        return list;
    }
}