/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xatsenzill;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Pseudo Codi del Programa
 *  String missatge = “”
    Repetir fins que missatge = “FI”
        Teclat(missatge)
        Enviar(missatge)
        Si missatge != “FI”
            Rebre(missatge)
            Si missatge != “FI”
                Mostrar(missatge)
            FiSi
        FiSi
    FiRepetir
    Mostrar(“Final del programa”)
 * @author paugonzalezmarti
 */
public class ClientXatSenzill {

    private static final int PORT = 5000;
	public static void main(String[] args)
	{
		try
		{
			System.out.print("IP del servidor: ");
			String ip = new Scanner(System.in).nextLine();
                        Socket socket = new Socket(ip, PORT);
			System.out.println("Connexió realitzada amb el servidor");
			String msg="";
                        while(!msg.equals("FI")){
                            
                            //Enviem el missatge
                            System.out.print("Missatge: ");
                            msg = new Scanner(System.in).nextLine();
                            enviar(socket, msg);
                            
                            if(!msg.equals("FI")){
                                //Rebem el missatge
                                msg = rebre(socket);
                                if(!msg.equals("FI")){
                                    
                                    System.out.println("Servidor: " + msg);
                                }
                            }
                        }
                        System.out.println("Fi del programa");
			socket.close();
			System.out.println("Connexió tancada amb el servidor");
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
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
}
