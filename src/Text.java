import java.awt.*;

public class Text
{
    private static Font _font = new Font("Verdana", Font.BOLD, 32);
    private static Font _smallerFont = new Font("Verdana", Font.BOLD, 20);
    private static Font _largerFont = new Font("Verdana", Font.BOLD, 50);
    private static String _waitingString = "Waiting for another player";
    private static String _unableToCommunicateWithOpponentString = "Unable to communicate with opponent";
    private static String _wonString = "You won!";
    private static String _opponentWonString = "Opponent won!";

    private static String _tieString = "Its a tie!";

    public static Font selectedFont(FontType type)
    {
        switch (type)
        {
            case Small:
                return _smallerFont;
            case Large:
                return _largerFont;
            case Normal:
            default:
                return _font;
        }
    }

    public static String selectedLog(LogType type)
    {
        switch (type)
        {

            case Waiting ->
            {
                return _waitingString;
            }
            case UnableToCommunicate ->
            {
                return _unableToCommunicateWithOpponentString;
            }
            case Won ->
            {
                return _wonString;
            }
            case Lost ->
            {
                return _opponentWonString;
            }
            case Tie ->
            {
                return _tieString;
            }
            default ->
            {
                return "Log type is missing";
            }

        }
    }


    public enum FontType
    {
        Normal,
        Small,
        Large
    }

    public enum LogType
    {
        Waiting,
        UnableToCommunicate,
        Won,
        Lost,
        Tie
    }
}


