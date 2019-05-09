/*
 *Pseudocodi del Programa
String missatge = “”
Repetir fins que missatge = “FI”
     Rebre(missatge)
     Si missatge != “FI”
           Mostrar(missatge)
           Teclat(missatge)
           Enviar(missatge)
     FiSi
FiRepetir
Mostrar(“Final del programa”)
 */
package Servidor;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author paugonzalezmarti
 */
public class ServidorXatSenzill {

    private static final int PORT = 5000;
	
	public static void main(String[] args) {
		try {
			ServerSocket sServer = new ServerSocket(PORT);
			System.out.println("Servidor connectat");
			
			
                            //busquem i capturem els clients
                            System.out.println("Esperant clients...");
                            Socket sClient = sServer.accept();

                            String ipClient = 
                                            sClient.getInetAddress().getHostAddress();
                            String nomClient = 
                                            sClient.getInetAddress().getHostName();
                            System.out.println("Client " + ipClient + " (" + 
                                            nomClient + ") connectat");
                            String msg="";
                            while(!msg.equals("FI")){
                                msg = rebre(sClient);
                                if(!msg.equals("FI")){
                                    System.out.println("Client: " + msg);
                                    System.out.println("Contesta el seu missatge: ");
                                    msg = new Scanner(System.in).nextLine();
                                    enviar(sClient,msg);
                                }
                            }
                            System.out.println("Fi del programa");
                            sClient.close();
			
			sServer.close();
		System.out.println("Connexió tancada amb el(s) client(s)");
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	private static String rebre(Socket socket) throws Exception
	{
		String msg;
		try
		{
			InputStream is = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			msg = (String) ois.readObject();
		}
		catch(Exception e)
		{
			throw e;
		}
		return msg;
	}

	private static void enviar(Socket socket, String msg) throws Exception
	{
		try
		{
			OutputStream os = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(msg);
		}
		catch(Exception e)
		{
			throw e;
		}
	}
    
}
