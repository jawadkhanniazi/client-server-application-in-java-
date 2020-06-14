 
import java.net.*; 
import java.io.*; 
import java.util.LinkedList;

public class MyServer 
{ 
	//initialize socket and input stream 
	private Socket		 socket = null; 
	private ServerSocket server = null; 
	private DataInputStream in	 = null; 

	// constructor with port 
	public MyServer(int port) 
	{ 
		// starts server and waits for a connection 
		try
		{ 
			server = new ServerSocket(port); 
			System.out.println("Server started"); 

			System.out.println("Waiting for a client ..."); 

			socket = server.accept(); 
			System.out.println("Client accepted"); 

			// takes input from the client socket 
			in = new DataInputStream( 
				new BufferedInputStream(socket.getInputStream())); 
                        
                        LinkedList<String> words = toWords(in.readUTF());
                        System.out.println("start\n");
                        System.out.println(words);
                        System.out.println("end");
			String line = ""; 

			// reads message from client until "Over" is sent 
			while (!line.equals("Over")) 
			{ 
				try
				{ 
					line = in.readUTF(); 
					//System.out.println(line); 

				} 
				catch(IOException i) 
				{ 
					System.out.println(i); 
				} 
			} 
			System.out.println("Closing connection"); 

			// close connection 
			socket.close(); 
			in.close(); 
		} 
		catch(IOException i) 
		{ 
			System.out.println(i); 
		} 
	} 
        
         public LinkedList<String> toWords(String str){
            LinkedList<String> words = new LinkedList<>();
            int num = 0;
            while((num = str.indexOf(" ")) != -1){
                words.add((String) str.subSequence(0, num));
                str = str.substring(num+1);
            }
            if(!str.equalsIgnoreCase(" "))
                words.add(str);
            return words;
        }
        
	public static void main(String args[]) 
	{ 
            
		MyServer server = new MyServer(5000); 
	} 
} 
