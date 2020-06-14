
import java.net.*; 
import java.io.*; 
import java.util.LinkedList;

public class MyClient 
{ 
	// initialize socket and input output streams 
	private Socket socket		 = null; 
	private DataInputStream input = null; 
	private DataOutputStream out	 = null; 

	// constructor to put ip address and port 
	public MyClient(String address, int port) 
	{ 
		// establish a connection 
		try
		{ 
			socket = new Socket(address, port); 
			System.out.println("Connected"); 

			// takes input from terminal 
			input = new DataInputStream(System.in); 
                        
                       // LinkedList<String> words = toWords(input.readUTF());
                        //System.out.println(words);
			// sends output to the socket 
			out = new DataOutputStream(socket.getOutputStream()); 
                        //out.writeUTF("");
		} 
		catch(UnknownHostException u) 
		{ 
			System.out.println(u); 
		} 
		catch(IOException i) 
		{ 
			System.out.println(i); 
		} 

		// string to read message from input 
		String line = ""; 

		// keep reading until "Over" is input 
		while (!line.equals("Over")) 
		{ 
			try
			{ 
				line = input.readLine(); 
				out.writeUTF(line); 
			} 
			catch(IOException i) 
			{ 
				System.out.println(i); 
			} 
		} 

		// close the connection 
		try
		{ 
			input.close(); 
			out.close(); 
			socket.close(); 
		} 
		catch(IOException i) 
		{ 
			System.out.println(i); 
		} 
	} 

    private MyClient() {
    }
       
	public static void main(String args[]) 
	{ 
		MyClient client = new MyClient("127.0.0.1", 5000); 
	} 
} 
