public class Game
{
    private final int _width = 506;
    private final int _height = 527;
    private int lenghtofSpace = 160;
    private boolean yourturn = false;
    private boolean circle = true;
    private boolean won = false;
    private boolean enemyWon = false;
    private String[] spaces = new String[9];
    private int firstSpot = -1; //first winning square's coordinate
    private int secondSpot = -1; //last winning square's coordinate

    public boolean checkCircleState(){
        return circle;
    }

}
