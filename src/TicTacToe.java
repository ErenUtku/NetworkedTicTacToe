import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class TicTacToe implements Runnable
{
    private Scanner _scanner = new Scanner(System.in);
    private Network _network;
    private Graphics _graphics;
    private Game _game;
    private Painter _painter;

    public TicTacToe()
    {
        //Class Initiation
        _game = new Game();
        _graphics = new Graphics(_game);
        _network = new Network(_game);

        ///summary
        ///Set ip&port via  Network
        ///Set graphics via Graphic

        _network.setIPAndPort(_scanner);

        _graphics.loadImages();

        _painter = new Painter(_graphics,_network, _game);

        _painter.setPreferredSize(new Dimension(_graphics.getWidth(), _graphics.getHeight()));

        if (!_network.connect())
        {
            _network.initializeServer(_game);
        }

        //Initialize Frame and Thread

        InitializeFrame();
        InitializeThread();
    }

    @Override
    public void run()
    {
        while (true)
        {
            _network.tick();
            _painter.repaint();

            if (!_game.checkCircleState() && !_network.isAccepted())
            {
                _network.listenForServerRequest();
            }
        }

    }

    @SuppressWarnings("unused")
    public static void main(String[] args)
    {
        TicTacToe ticTacToe = new TicTacToe();
    }

    private void InitializeFrame()
    {
        var frame = new JFrame();
        frame.setTitle("Tic-Tac-Toe");
        frame.setContentPane(_painter);
        frame.setSize(_graphics.getWidth(), _graphics.getHeight());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void InitializeThread()
    {
        var thread = new Thread(this, "TicTacToe");
        thread.start();
    }

}