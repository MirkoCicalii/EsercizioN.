package it.cicali;
import java.io.*;
import java.net.*;


public class ClientStr {
    String nomeServer = "localhost";
    int portaServer = 6789;
    Socket miosocket;
    BufferedReader tastiera;
    String stringUtente;
    String stringRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;

    public Socket connetti(){
        System.out.println("CLIENT partito in esecuzione ...");
        try
        {
            tastiera= new BufferedReader(new InputStreamReader(System.in));
            miosocket = new Socket (nomeServer, portaServer);
            outVersoServer = new DataOutputStream(miosocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(miosocket.getInputStream()));
            stringRicevutaDalServer=inDalServer.readLine();
            System.out.println("server: "+stringRicevutaDalServer);
        }
        catch (UnknownHostException a){
            System.err.println("Host sconosciuto");
        }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                System.out.println("Errore durante la connessione");
                System.exit(1);
            }
            return miosocket;
     }

     public void comunica(){
         try{
             for(;;){
             stringRicevutaDalServer=inDalServer.readLine();
             System.out.println("server1: "+ stringRicevutaDalServer);
             stringUtente = tastiera.readLine();
             System.out.println("invio la stringa al server e attendo..." +  stringUtente);
             outVersoServer.writeBytes( stringUtente+'\n');
             stringRicevutaDalServer=inDalServer.readLine();
             if (stringUtente.equals("VISUALIZZA")) {
                for (;;) {
                    stringRicevutaDalServer = inDalServer.readLine();
                        if (stringRicevutaDalServer.equals("Fine")) {
                            break;
                        }
                    System.out.println("server2: " + stringRicevutaDalServer);
                    
                }
            } else {
                stringRicevutaDalServer = inDalServer.readLine();
                System.out.println("server3: " + stringRicevutaDalServer);
            }            
             
             }
         }
         catch (Exception e)
         {
             System.out.println(e.getMessage());
             System.out.println("Errore durante la comunicazione col server!");
             System.exit(1);
         }
     }

     public static void main (String args[]){
        ClientStr cliente = new ClientStr();
        cliente.connetti();
        cliente.comunica();
    }


    }
