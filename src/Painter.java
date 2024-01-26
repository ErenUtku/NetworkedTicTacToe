import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Painter extends JPanel implements MouseListener
{
    private static final long serialVersionUID = 1L;

    public Painter()
    {
        setFocusable(true);
        requestFocus();
        setBackground(Color.WHITE);
        addMouseListener(this);
    }

    public void painterComponent(Graphics b)
    {

    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

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