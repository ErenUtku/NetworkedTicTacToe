import java.util.Scanner;

public class TicTacToe implements Runnable
{
    private Scanner _scanner = new Scanner(System.in);
    private Network _network;
    private Graphics _graphics;
    private Game _game;
    private Painter _painter;
    private Thread _thread;

    public TicTacToe()
    {
        //Class Initiation
        _graphics= new Graphics();
        _game= new Game();
        _network = new Network(_game);

        ///summary
        ///Set ip&port via  Network
        ///Set graphics via Graphic
        _network.setIPAndPort(_scanner);
        _graphics.loadImages();

        _painter = new Painter();
    }

    @Override
    public void run()
    {
        while (true)
        {
            _network.tick();
            _painter.repaint();

            if(!_game.checkCircleState() && !_network.isAccepted())
            {
                _network.listenForServerRequest();
            }
        }

    }

    public static void main(String[] args)
    {
        TicTacToe ticTacToe = new TicTacToe();
    }

}