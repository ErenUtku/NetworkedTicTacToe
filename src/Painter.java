import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class Painter extends JPanel implements MouseListener
{
    private static final long serialVersionUID = 1L;

    private Graphics _graphics;
    private Network _network;

    private Game _game;

    public Painter(Graphics graphics, Network network, Game game)
    {
        setFocusable(true);
        requestFocus();
        setBackground(Color.WHITE);
        addMouseListener(this);

        this._graphics = graphics;

        this._network = network;

        this._game = game;

        this.setPreferredSize(new Dimension(_graphics.getWidth(), _graphics.getHeight()));
    }

    @Override
    public void paintComponent(java.awt.Graphics g)
    {
        super.paintComponent(g);
        _graphics.render(g);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if (Network.isAccepted())
        {
            if (_game.checkTurnState() && !Network.unableToCommunicateWithOpponents && !_game.getWon() && !_game.getEnemyWon())
            {
                int x = e.getX() / _graphics.getLengthSpace();
                int y = e.getY() / _graphics.getLengthSpace();
                y *= 3;
                int position = x + y;

                if (Game.getSpace()[position] == null)
                {
                    if (_game.checkFirstPlayer())
                    {
                        Game.setSpaceValue(position, "O");
                    }
                    else Game.setSpaceValue(position, "X");

                    _game.setYourTurn(false);
                    repaint();
                    Toolkit.getDefaultToolkit().sync();

                    try
                    {
                        _network.getDos().writeInt(position);
                        _network.getDos().flush();
                    } catch (IOException e1)
                    {
                        _network.sentError();
                        e1.printStackTrace();
                    }

                    System.out.println("DATA WAS SENT");

                    _game.checkWinState(Game.UserType.Player);
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }
}