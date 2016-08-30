package client_server_I_O.server;

import client_server_I_O.classes.Avatar;
import client_server_I_O.classes.Snake;
import client_server_I_O.classes.SnakePlayer;
import client_server_I_O.classes.User;
import user_interface.account.content.intelligence.card_elements.Card;

import java.io.ObjectInputStream;
import java.net.Socket;

public class Receiver {

    public static void receive(Socket socket) {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            byte key = inputStream.readByte();
            long userId = inputStream.readLong();

            switch (key) {
                case 1: Manager.saveUser(userId, (User) inputStream.readObject()); break;
                case 2: Manager.saveCards(userId, (Card[][]) inputStream.readObject()); break;
                case 3: Manager.saveSnakePlayer(userId, (SnakePlayer) inputStream.readObject()); break;
                case 4: Manager.saveAvatar(userId, (Avatar) inputStream.readObject()); break;
                case 5: Manager.saveName(userId, (String) inputStream.readObject()); break;
                case 6: Manager.saveColor(userId, (String) inputStream.readObject()); break;
                case 7: Manager.saveAbout(userId, (String) inputStream.readObject()); break;
                case 8: Manager.saveSnake(userId, (Snake) inputStream.readObject()); break;
                case 10: Manager.registration((User) inputStream.readObject(), socket); break;
                case 11: Manager.authorization((User) inputStream.readObject(), socket); break;
            }

            Sender.successfulOperation(socket);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
