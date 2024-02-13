import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Graphics
{
    public Graphics(Game game)
    {
        this._game = game;
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

    public void loadImages()
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
            g.setColor(Color.RED);
            g.setFont(Text.selectedFont(Text.FontType.Small));
            java.awt.Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            int stringWidth = g2.getFontMetrics().stringWidth(Text.selectedLog(Text.LogType.UnableToCommunicate));
            g.drawString(Text.selectedLog(Text.LogType.UnableToCommunicate), _width / 2 - stringWidth, _height / 2);
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
                        if (_game.checkCircleState())
                        {
                            g.drawImage(_redX, xCalculation, yCalculation, null);
                        } else
                        {
                            g.drawImage(_blueX, xCalculation, yCalculation, null);
                        }
                    } else
                    {
                        if (!_game.checkCircleState())
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
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(10));
                g2.setColor(Color.BLACK);

                g.drawLine(Game.firstSpot % 3 * _lengthSpace + 10 * Game.firstSpot % 3 + _lengthSpace / 2, (Game.firstSpot / 3) * _lengthSpace + 10 * (Game.firstSpot / 3) + _lengthSpace / 2, Game.secondSpot % 3 * _lengthSpace + 10 * Game.secondSpot % 3 + _lengthSpace / 2, Game.secondSpot / 3 * _lengthSpace + 10 * (int) (Game.secondSpot / 3) + _lengthSpace / 2);

                g.setColor(Color.RED);
                g.setFont(Text.selectedFont(Text.FontType.Large));

                if (_game.getWon())
                {
                    int stringWidth = g2.getFontMetrics().stringWidth(Text.selectedLog(Text.LogType.Won));
                    g.drawString(Text.selectedLog(Text.LogType.Won), _width / 2 - stringWidth, _height / 2);
                } else if (_game.getEnemyWon())
                {
                    int stringWidth = g2.getFontMetrics().stringWidth(Text.selectedLog(Text.LogType.Lost));
                    g.drawString(Text.selectedLog(Text.LogType.Lost), _width / 2 - stringWidth, _height / 2);
                }
            }

            if (_game.checkForTie())
            {
                Graphics2D g2 = (Graphics2D) g;
                g.setColor(Color.BLACK);
                g.setFont(Text.selectedFont(Text.FontType.Large));
                int stringWidth = g2.getFontMetrics().stringWidth(Text.selectedLog(Text.LogType.Tie));
                g2.drawString(Text.selectedLog(Text.LogType.Tie), _width / 2 - stringWidth / 2, _height / 2);
            }
        } else
        {
            g.setColor(Color.RED);
            g.setFont(Text.selectedFont(Text.FontType.Normal));
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            int stringWidth = g2.getFontMetrics().stringWidth(Text.selectedLog(Text.LogType.Waiting));
            g.drawString(Text.selectedLog(Text.LogType.Waiting), _width / 2 - stringWidth, _height / 2);
        }

    }


}

