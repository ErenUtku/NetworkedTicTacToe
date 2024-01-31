public class Game
{
    private boolean yourTurn = false;
    private boolean circle = true;
    private boolean won = false;
    private boolean enemyWon = false;
    private boolean tie = false;
    private static String[] spaces = new String[9];
    public static int firstSpot = -1; //first winning square's coordinate
    public static int secondSpot = -1; //last winning square's coordinate

    private final int[][] _winningLines = new int[][]{ //Those are the win condition's board index numbers
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public boolean checkCircleState()
    {
        return circle;
    }

    public boolean checkTurnState()
    {
        return yourTurn;
    }

    public boolean checkForWin()
    {
        for (int i = 0; i < _winningLines.length; i++)
        {
            if (circle)
            {
                if (spaces[_winningLines[i][0]].equals("O") && spaces[_winningLines[i][1]].equals("O") && spaces[_winningLines[i][2]].equals("O"))
                {
                    firstSpot = _winningLines[i][0];
                    secondSpot = _winningLines[i][2];
                    won = true;
                }
            } else
            {
                if (spaces[_winningLines[i][0]].equals("X") && spaces[_winningLines[i][1]].equals("X") && spaces[_winningLines[i][2]].equals("X"))
                {
                    firstSpot = _winningLines[i][0];
                    secondSpot = _winningLines[i][2];
                    won = true;
                }

            }
        }
        return won;
    }

    public boolean checkForEnemyWin()
    {
        for (int i = 0; i < _winningLines.length; i++)
        {
            if (circle)
            {
                if (spaces[_winningLines[i][0]].equals("X") && spaces[_winningLines[i][1]].equals("X") && spaces[_winningLines[i][2]].equals("X"))
                {
                    firstSpot = _winningLines[i][0];
                    secondSpot = _winningLines[i][2];
                    enemyWon = true;
                }
            } else
            {
                if (spaces[_winningLines[i][0]].equals("O") && spaces[_winningLines[i][1]].equals("O") && spaces[_winningLines[i][2]].equals("O"))
                {
                    firstSpot = _winningLines[i][0];
                    secondSpot = _winningLines[i][2];
                    enemyWon = true;
                }

            }
        }
        return enemyWon;
    }

    public boolean checkForTie()
    {
        for (int i = 0; i < spaces.length; i++)
        {
            if (spaces[i] == null)
            {
                return tie = false;
            }
        }
        return tie = true;
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

    public int[][] getWinningLines()
    {
        return _winningLines;
    }
}
