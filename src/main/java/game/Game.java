package game;

import gui.LanternaGUI;
import model.game.arena.ArenaBuilder;
import model.menu.MainMenu;
import states.MainMenuState;
import states.State;

import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class Game {
    private static ArrayList<String> stats = new ArrayList<>();
    private State state;
    private final LanternaGUI gui;

    public Game() throws IOException, URISyntaxException, FontFormatException {
        this.gui = new LanternaGUI(100, 50);
        this.state = new MainMenuState(new MainMenu());
        Game.readSavefile(); // initial save file read
    }

    public void run() throws IOException {
        // fps
        int FPS = 60;
        // 1 second / FPS
        int frameTime = 1000 / FPS;

        while(this.state != null) {
            // get start time before stepping
            long startTime = System.currentTimeMillis();

            state.step(this , gui, startTime);

            // calculate the difference in time after stepping
            long elapsedTime = System.currentTimeMillis() - startTime;

            // calculate the time to sleep to achieve the desired FPS
            long sleepTime = frameTime - elapsedTime;

            // sleep for the remaining time
            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException ignored) { }
        }
        gui.close();
    }
    public static void readSavefile() throws IOException {
        // here we need to use this way of opening the file so that we can upate it at runtine
        FileReader fileReader = new FileReader("src/main/resources/savefile.txt");
        BufferedReader reader = new BufferedReader(fileReader);

        // if stats is empty, add the lines
        if (stats.isEmpty()) {
            addStats(reader);
        }
        // otherwise replace the lines
        else {
            replaceStats(reader);
        }
        reader.close();
    }

    private static void addStats(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            stats.add(line);
        }
    }

    private static void replaceStats(BufferedReader reader) throws IOException {
        String line;
        for (int i = 0; i < stats.size(); i++) {
            line = reader.readLine();
            stats.set(i, line);
        }
    }

    public static void writeSavefile() {
        FileWriter fileWriter = getSavefileWriter();
        try (BufferedWriter writer = new BufferedWriter(fileWriter)) {
            writeStats(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static FileWriter getSavefileWriter() {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("src/main/resources/savefile.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileWriter;
    }

    private static void writeStats(BufferedWriter writer) throws IOException {
        for (String line : stats) {
            writer.write(line);
            writer.newLine();
        }
    }

    public static void setHighscore(int level, int score) {
        stats.set(level-1, "level " + level + ": " + score);
    }

    public static int getHighscore(int level) {
        // the level is found in the file savefile.txt in the resources folder
        // the highscore is found in the line corresponding to the level after the :
        // for exampmle level 1: 100
        // for this we use the stats list
        String[] split = stats.get(level - 1).split(": ");
        return Integer.parseInt(split[1]);
    }

    public static char getSkin(int skin) throws IOException {
        // the skin is found in the skins folder in the resources
        // each file is named skin.sym where inside is the char for the skin
        // for example 1.sym contains the symbol X
        URL resource = ArenaBuilder.class.getResource("/skins/" + skin + ".sym");
        assert resource != null;
        BufferedReader reader = getSkinReader(resource);
        return reader.readLine().charAt(0);
    }

    private static BufferedReader getSkinReader(URL resource) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(resource.getFile()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return reader;
    }

    public void setState(State state) { this.state = state; }
}

