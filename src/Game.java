public class Game
{
    private boolean yourTurn = false;
    private boolean circle = true;
    private boolean won = false;
    private boolean enemyWon = false;
    private static String[] spaces = new String[9];
    private int firstSpot = -1; //first winning square's coordinate
    private int secondSpot = -1; //last winning square's coordinate

    public boolean checkCircleState()
    {
        return circle;
    }

    public boolean checkTurnState()
    {
        return yourTurn;
    }

    public boolean checkForEnemyWin()
    {
        return enemyWon;
    }

    public void setYourTurn(boolean value)
    {
        yourTurn = value;
    }

    public static void setSpaceValue(int iValue, String sValue)
    {
        spaces[iValue] = sValue;
    }

    public static String[] getSpace()
    {
        return spaces;
    }

}
