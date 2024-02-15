public class Game
{
    private boolean yourTurn = false;
    private boolean isFirstPlayer = true;
    private boolean won = false;
    private boolean enemyWon = false;

    //region Constants

    private final int[][] _winningLines = new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    //endregion

    //region Static Fields
    private static String[] spaces = new String[9];
    public static int firstSpot = -1; //first winning square's coordinate
    public static int secondSpot = -1; //last winning square's coordinate

    //endregion

    //region Capsulation

    public boolean getWon()
    {
        return won;
    }

    public boolean getEnemyWon()
    {
        return enemyWon;
    }

    public boolean checkFirstPlayer()
    {
        return isFirstPlayer;
    }

    public boolean checkTurnState()
    {
        return yourTurn;
    }

    public static String[] getSpace()
    {
        return spaces;
    }

    //endregion

    //region Setters

    public void setYourTurn(boolean value)
    {
        yourTurn = value;
    }

    public void setFirstPlayer(boolean value)
    {
        isFirstPlayer = value;
    }

    public static void setSpaceValue(int iValue, String sValue)
    {
        spaces[iValue] = sValue;
    }

    //endregion

    public boolean checkWinState(UserType userType)
    {
        for (int i = 0; i < _winningLines.length; i++)
        {
            if (spaces[_winningLines[i][0]] != null && spaces[_winningLines[i][1]] != null && spaces[_winningLines[i][2]] != null)
            {
                if (spaces[_winningLines[i][0]].equals(spaces[_winningLines[i][1]]) && spaces[_winningLines[i][1]].equals(spaces[_winningLines[i][2]]))
                {
                    firstSpot = _winningLines[i][0];
                    secondSpot = _winningLines[i][2];

                    if (userType == UserType.Player)
                    {
                        won = true;
                    } else
                    {
                        enemyWon = true;
                    }
                }
            }
        }
        return userType == UserType.Player ? won : enemyWon;
    }

    public boolean checkForTie()
    {
        for (int i = 0; i < spaces.length; i++)
        {
            if (spaces[i] == null)
            {
                return false;
            }
        }
        return true;
    }

    public enum UserType
    {
        Player,
        Opponent
    }

}

