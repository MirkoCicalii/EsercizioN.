import java.io.*;
import java.net.*;
import java.util.*;

public class ServerStr
{
    ServerSocket server = null;
    Socket client = null;
    String operazione = null;
    String numero=null;
    String stringaricevuta=null;
    String stringaModificata = null;
    int risultato=0;
    String Risultato=null;
    Vector <String> a= new Vector<String>(); // Dovrebbe essere Integer


    BufferedReader inDalClient;
    DataOutputStream outVersoClient;


public Socket attendi()
{
    try
    {
        System.out.println(" Server partito in esecuzione ...");
        server = new ServerSocket(6789);
        client = server.accept();
        server.close();
        inDalClient=new BufferedReader (new InputStreamReader(client.getInputStream()));
        outVersoClient= new DataOutputStream ( client.getOutputStream());
        outVersoClient.writeBytes("Connessione effettuata!"+'\n');
    }

    catch (Exception e)
    {
        System.out.println(e.getMessage());
        System.out.println("Errore durante l'istanza del server!");
        System.exit(1);
    }
    return client;

}

public void comunica()
{
    try
    {
        for(;;){
                  
            
             outVersoClient.writeBytes("Dammi il numero estratto (scritto) per visualizzare i numeri inseriti digitare VISUALIZZA"+'\n');
             numero = inDalClient.readLine();
             outVersoClient.writeBytes("Numeri estratti: "+ numero +'\n');
             if(numero.equals("VISUALIZZA")){
                outVersoClient.writeBytes("la lista delle note è: "+'\n');
                for(int i=0;i<a.size();i++){
                    outVersoClient.writeBytes(a.get(i)+'\n');
                }
            }
            
                else{
                    a.add(numero);
                    System.out.println("stringa dal cliente: "+ numero);
                    outVersoClient.writeBytes("numero salvato con sucesso"+'\n');
                }
             
             
             
             
             
        }

// CONTROLLO SE IL NUMERO È UNO DOPO L'ALTRO
        for(int i = 0 ; i< a.size(); i++){
            if ( a.get(i).equals(a.get(i+1)-1))
        }



    }
    catch (Exception e)
    {
        System.out.println(e.getMessage());
        System.out.println("Errore durante la comunicazione col server!");
        System.exit(1);
    }
}

public static void main(String args[]){
    ServerStr servente = new ServerStr();
    servente.attendi();
    servente.comunica();
}
}
