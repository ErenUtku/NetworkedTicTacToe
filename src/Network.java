import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Network
{
    public Network(Game game)
    {
        this._game = game;
    }

    private String _ip = "localHost";
    private int _port = 22222;
    private ServerSocket _serverSocket;
    private Socket _socket;
    private DataOutputStream _dos;
    private DataInputStream _dis;
    public static boolean unabletoCommunitaceWithOpponents = false;
    private int errors = 0;
    private static boolean accepted = false;

    private Game _game;

    public static boolean isAccepted()
    {
        return accepted;
    }

    public void tick()
    {
        //tick is 10
        if (errors >= 10) unabletoCommunitaceWithOpponents = true;

        if (!_game.checkTurnState() && unabletoCommunitaceWithOpponents)
        {
            try
            {
                int space = _dis.readInt();

                if (_game.checkCircleState())
                {
                    _game.setSpaceValue(space, "X");
                } else
                {
                    _game.setSpaceValue(space, "O");
                }

                _game.checkForEnemyWin();
                _game.setYourTurn(true);

            } catch (IOException e)
            {
                e.printStackTrace();
                errors++;
            }
        }
    }

    public void setIPAndPort(Scanner scanner)
    {
        System.out.println("Please input the IP:");
        _ip = scanner.nextLine();

        System.out.println("Please input the port:");
        _port = scanner.nextInt();

        while (_port < 1 || _port > 65535)
        {
            System.out.println("Invalid port number. Please input another port:");
            _port = scanner.nextInt();
        }
    }

    public void listenForServerRequest()
    {
        Socket socket = null;
        try
        {
            socket = _serverSocket.accept();
            _dos = new DataOutputStream(socket.getOutputStream());
            _dis = new DataInputStream(socket.getInputStream());
            accepted = true;
            System.out.println("CLIENT HAS REQUESTED TO JOIN, AND WE HAVE ACCEPTED");

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public boolean connect()
    {
        try
        {
            _socket = new Socket();
            _dos = new DataOutputStream(_socket.getOutputStream());
            _dis = new DataInputStream(_socket.getInputStream());
            accepted = true;
        } catch (IOException e)
        {
            System.out.printf("Unable to connect to the address: %d / port: %d", _ip, _port);
            return false;
        }

        System.out.println("Successfully connected to server");
        return true;
    }
}
