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

    private JFrame _frame;
    private BufferedImage _board;
    private BufferedImage _redX;
    private BufferedImage _blueX;
    private BufferedImage _redCircle;
    private BufferedImage _blueCircle;
    private Painter _painter;

    //region board values

    private final int _width = 506;
    private final int _height = 527;
    private final int _lengthSpace = 160;

    //endregion

    private Game _game;

    public void loadImages()
    {
        try
        {
            _blueCircle = ImageIO.read(getClass().getResourceAsStream("blueCircle.png"));
            _blueX = ImageIO.read(getClass().getResourceAsStream("blueX.png"));
            _board = ImageIO.read(getClass().getResourceAsStream("board.png"));
            _redCircle = ImageIO.read(getClass().getResourceAsStream("redCircle.png"));
            _redX = ImageIO.read(getClass().getResourceAsStream("redX.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void render(java.awt.Graphics g)
    {
        g.drawImage(_board, 0, 0, null);

        if (Network.unabletoCommunitaceWithOpponents)
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

    }


}

