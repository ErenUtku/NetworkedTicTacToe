import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Graphics
{
    public Graphics(Game game)
    {
        this._game = game;

        //Load Images as BufferedImage
        loadImages();
    }

    private BufferedImage _board;
    private BufferedImage _redX;
    private BufferedImage _blueX;
    private BufferedImage _redCircle;
    private BufferedImage _blueCircle;

    //region board values

    private final int _width = 506;

    public int getWidth()
    {
        return _width;
    }

    private final int _height = 527;

    public int getHeight()
    {
        return _height;
    }

    private final int _lengthSpace = 160;

    public int getLengthSpace()
    {
        return _lengthSpace;
    }

    //endregion

    private Game _game;

    private void loadImages()
    {
        try
        {
            _blueCircle = ImageIO.read(getClass().getResourceAsStream("blueCircle.png"));
            _blueX = ImageIO.read(getClass().getResourceAsStream("/blueX.png"));
            _board = ImageIO.read(getClass().getResourceAsStream("/board.png"));
            _redCircle = ImageIO.read(getClass().getResourceAsStream("/redCircle.png"));
            _redX = ImageIO.read(getClass().getResourceAsStream("/redX.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void render(java.awt.Graphics g)
    {
        g.drawImage(_board, 0, 0, null);

        if (Network.unableToCommunicateWithOpponents)
        {
            DrawTextOnScreen(g, Color.RED, Text.FontType.Small, Text.LogType.UnableToCommunicate);
            return;
        }

        if (Network.isAccepted())
        {
            var spaces = _game.getSpace();
            for (int i = 0; i < _game.getSpace().length; i++)
            {
                if (spaces[i] != null)
                {
                    int xCalculation = (i % 3) * _lengthSpace + 10 * (i % 3);
                    int yCalculation = (i / 3) * _lengthSpace + 10 * (i / 3);

                    if (spaces[i].equals("X"))
                    {
                        if (_game.checkFirstPlayer())
                        {
                            g.drawImage(_redX, xCalculation, yCalculation, null);
                        } else
                        {
                            g.drawImage(_blueX, xCalculation, yCalculation, null);
                        }
                    } else
                    {
                        if (!_game.checkFirstPlayer())
                        {
                            g.drawImage(_redCircle, xCalculation, yCalculation, null);
                        } else
                        {
                            g.drawImage(_blueCircle, xCalculation, yCalculation, null);
                        }
                    }
                }
            }

            if (_game.getWon() || _game.getEnemyWon())
            {

                if (_game.getWon())
                {
                    //Game is Won=>
                    DrawTextOnScreen(g, Color.BLACK, Text.FontType.Large, Text.LogType.Won);

                    //Draw Stroke
                    DrawWinStroke(g, 10, Color.BLACK);
                } else if (_game.getEnemyWon())
                {
                    //Game is Lost=>
                    DrawTextOnScreen(g, Color.RED, Text.FontType.Large, Text.LogType.Lost);

                    //Draw Stroke
                    DrawWinStroke(g, 10, Color.RED);
                }
            }

            if (_game.checkForTie())
            {
                //Game is Tie =>
                DrawTextOnScreen(g, Color.BLACK, Text.FontType.Large, Text.LogType.Tie);
            }
        } else
        {
            //Waiting For Opponents..
            DrawTextOnScreen(g, Color.RED, Text.FontType.Normal, Text.LogType.Waiting);
        }

    }

    public void DrawTextOnScreen(java.awt.Graphics g, Color color, Text.FontType fontType, Text.LogType logType)
    {
        g.setColor(color);
        g.setFont(Text.selectedFont(fontType));

        java.awt.Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int stringWidth = g2.getFontMetrics().stringWidth(Text.selectedLog(logType));
        g.drawString(Text.selectedLog(logType), (_width / 2) - (stringWidth / 2), _height / 2);
    }

    public void DrawWinStroke(java.awt.Graphics g, float strokeThickness, Color strokeColor)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(strokeThickness));
        g2.setColor(strokeColor);

        g.drawLine(
                //Start Point's x and y calculation
                Game.firstSpot % 3 * _lengthSpace + 10 * Game.firstSpot % 3 + _lengthSpace / 2, (Game.firstSpot / 3) * _lengthSpace + 10 * (Game.firstSpot / 3) + _lengthSpace / 2,
                //Finish Point's x and y calculation
                Game.secondSpot % 3 * _lengthSpace + 10 * Game.secondSpot % 3 + _lengthSpace / 2, Game.secondSpot / 3 * _lengthSpace + 10 * (int) (Game.secondSpot / 3) + _lengthSpace / 2);
    }

}

