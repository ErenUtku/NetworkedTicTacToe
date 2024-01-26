import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Graphics
{
    private JFrame _frame;
    private BufferedImage _board;
    private BufferedImage _redX;
    private BufferedImage _blueX;
    private BufferedImage _redCircle;
    private BufferedImage _blueCircle;

    private Painter _painter;

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
}
