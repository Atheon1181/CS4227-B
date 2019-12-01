package Maze;

import java.util.ArrayList;

public class MazePreference {
    private int sizeFactor=60;
    private boolean bombedWalls=true;
    private boolean doors=true;
    private  boolean traps=false;
    private boolean extraLives=true;
    private boolean enemy=false;
    private boolean shield=false;
    private long length=30000;
    private int doorAmnt=2;
    private int trapAmnt= 5;
    private int extraLivesAmnt=3;
    private int shieldAmnt=1;
    private int trapDamage=1;
    private int enemyDamage=1;
    private int enemyAmnt=1;
    private double wallDifficulty=10.0;
    private int treasureGoal=3000;
    private boolean RMG=false;
    private ArrayList<Player> playerInGame=new ArrayList<>();

    public int getEnemySpeed() {
        return enemySpeed;
    }

    public void setTreasureGoal(int treasureGoal) {
        this.treasureGoal = treasureGoal;
    }

    private int enemySpeed=2000
            ;

    public ArrayList<Player> getPlayerInGame() {
        return playerInGame;
    }

    public int getShieldAmnt() {
        return shieldAmnt;
    }

    public void setShieldAmnt(int shieldAmnt) {
        this.shieldAmnt = shieldAmnt;
    }

    public void setPlayerInGame(ArrayList<Player> playerInGame) {
        this.playerInGame = playerInGame;
    }

    public boolean isRMG() {
        return RMG;
    }

    public void setRMG(boolean RMG) {
        this.RMG = RMG;
    }

    public boolean isShield() {
        return shield;
    }

    public void setShield(boolean shield) {
        this.shield = shield;
    }

    public boolean isTraps() {
        return traps;
    }

    public void setTraps(boolean traps) {
        this.traps = traps;
    }

    public MazePreference(int sizeFactor, boolean bombedWalls, boolean doors, long length) {
        this.sizeFactor = sizeFactor;
        this.bombedWalls = bombedWalls;
        this.doors = doors;
        this.length = length;
    }

    public MazePreference() {

    }

    public int getSizeFactor() {
        return sizeFactor;
    }

    public void setSizeFactor(int sizeFactor) {
        this.sizeFactor = sizeFactor;
    }

    public boolean isBombedWalls() {
        return bombedWalls;
    }

    public void setBombedWalls(boolean bombedWalls) {
        this.bombedWalls = bombedWalls;
    }

    public boolean isDoors() {
        return doors;
    }

    public void setDoors(boolean doors) {
        this.doors = doors;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public boolean isExtraLives() {
        return extraLives;
    }

    public void setExtraLives(boolean extraLives) {
        this.extraLives = extraLives;
    }

    public boolean isEnemy() {
        return enemy;
    }

    public void setEnemy(boolean enemy) {
        this.enemy = enemy;
    }

    public int getDoorAmnt() {
        return doorAmnt;
    }

    public void setDoorAmnt(int doorAmnt) {
        this.doorAmnt = doorAmnt;
    }

    public int getTrapAmnt() {
        return trapAmnt;
    }

    public void setTrapAmnt(int trapAmnt) {
        this.trapAmnt = trapAmnt;
    }

    public int getExtraLivesAmnt() {
        return extraLivesAmnt;
    }

    public void setExtraLivesAmnt(int extraLivesAmnt) {
        this.extraLivesAmnt = extraLivesAmnt;
    }

    public int getTrapDamage() {
        return trapDamage;
    }

    public void setTrapDamage(int trapDamage) {
        this.trapDamage = trapDamage;
    }

    public int getEnemyDamage() {
        return enemyDamage;
    }

    public void setEnemyDamage(int enemyDamage) {
        this.enemyDamage = enemyDamage;
    }

    public int getEnemyAmnt() {
        return enemyAmnt;
    }

    public void setEnemyAmnt(int enemyAmnt) {
        this.enemyAmnt = enemyAmnt;
    }

    public double getWallDifficulty() {
        return wallDifficulty;
    }

    public void setWallDifficulty(double wallDifficulty) {
        this.wallDifficulty = wallDifficulty;
    }

    public void setEnemySpeed(int i) {
        this.enemySpeed=i;
    }

    @Override
    public String toString() {
        return "MazePreference{" +
                "sizeFactor=" + sizeFactor +
                ", bombedWalls=" + bombedWalls +
                ", doors=" + doors +
                ", traps=" + traps +
                ", extraLives=" + extraLives +
                ", enemy=" + enemy +
                ", shield=" + shield +
                ", length=" + length +
                ", doorAmnt=" + doorAmnt +
                ", trapAmnt=" + trapAmnt +
                ", extraLivesAmnt=" + extraLivesAmnt +
                ", trapDamage=" + trapDamage +
                ", enemyDamage=" + enemyDamage +
                ", enemyAmnt=" + enemyAmnt +
                ", wallDifficulty=" + wallDifficulty +
                ", RMG=" + RMG +
                ", playerInGame=" + playerInGame +
                ", enemySpeed=" + enemySpeed +
                '}';
    }

    public int getTreasureGoal() {
        return this.treasureGoal;
    }
}
