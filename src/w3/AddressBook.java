//This is for Address Object
package w3;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
public class AddressBook {
public String Name=""; // Name 
public String Street=""; // Address street
public String City="";// Address city
public String State=""; //// Address state 
public String Zip=""; //// Address zip
static RandomAccessFile aFile ;
static FileChannel channel; // File channel for byte unit
public int num; // start o, index 
AddressBook(int num1) throws IOException{ // constructor
	num=num1;
	aFile = new RandomAccessFile("address.dat", "rw");
	channel  = aFile.getChannel();
	try {
		if(!load()) {
			throw  new NullPointerException("Nothing in here");
		}
	}catch(NullPointerException e) {
		System.out.println("You should make new one");
	}
	
}
public Boolean isEmpty() { // check object is empty
	if(Name.equals("")&&(Street.equals("")&&City.equals("")&&State.equals("")&&Zip.equals(""))) {
		return true;
	}else {
		return false;
	}
}
public static int size( )throws IOException { //check how many address object
	aFile = new RandomAccessFile("address.dat", "rw");
	channel  = aFile.getChannel();
	 long length = channel.size()/91;
	int howMany = (int)length;
	return howMany;
}
public Boolean load() throws IOException{ // load information and save to variable
	

	ByteBuffer check = ByteBuffer.allocate(1);
	ByteBuffer nameb  = ByteBuffer.allocate(32);
	ByteBuffer streetb = ByteBuffer.allocate(32);
	ByteBuffer cityb =  ByteBuffer.allocate(20);
	ByteBuffer stateb = ByteBuffer.allocate(2);
	ByteBuffer zipb =  ByteBuffer.allocate(5);
	channel.position(num*91);
	 int nread;
	 if(channel.read(check)==-1) {
		 return false;
	 }
	 //rewind to my position 
	 channel.position(num*91);
	 //get name
	 do {
	        nread = channel.read(nameb);
	       
	 } while (nread != -1 && nameb.hasRemaining());
	 Name =  new String(nameb.array(),"UTF-8");
	 System.out.println(Name);
	 //get street
	 do {
	        nread = channel.read(streetb);
	 } while (nread != -1 && streetb.hasRemaining());
	 Street = new String(streetb.array(),"UTF-8");
	//get city 
	 do {
	        nread = channel.read(cityb);
	 } while (nread != -1 && cityb.hasRemaining());
	 City = new String(cityb.array(),"UTF-8");
	 //state
	 do {
	        nread = channel.read(stateb);
	 } while (nread != -1 && stateb.hasRemaining());
	 State = new String(stateb.array(),"UTF-8");
	 //zip
	 do {
	        nread = channel.read(zipb);
	 } while (nread != -1 && zipb.hasRemaining());
	 Zip = new String(zipb.array(),"UTF-8");;
	return true;    
}


public void save(String name1, String street1, String city1, String state1, String zip1) throws IOException {
	// Form to file
	ByteBuffer nameb = ByteBuffer.wrap(changeByte(name1,32));
	ByteBuffer streetb = ByteBuffer.wrap(changeByte(street1,32));
	ByteBuffer cityb = ByteBuffer.wrap(changeByte(city1,20));
	ByteBuffer stateb = ByteBuffer.wrap(changeByte(state1,2));
	ByteBuffer zipb = ByteBuffer.wrap(changeByte(zip1,5));
	channel.position(num*91);
	 while (nameb.hasRemaining())
		 channel.write(nameb);
	 
	 while (streetb.hasRemaining())
		 channel.write(streetb);
	 
	 while (cityb.hasRemaining())
		 channel.write(cityb);
	 
	 while (stateb.hasRemaining())
		 channel.write(stateb);
	 
	 while (zipb.hasRemaining())
		 channel.write(zipb);
	
	
}
public byte[] changeByte(String aa, int length) { //Pad or truncate to fit size 32,32,20,2,5
	String send = aa;
	byte[] sendB = send.getBytes();
	byte[] temporal1 = Arrays.copyOf(sendB, length); // Pads or truncates.
	return temporal1;
}
public void setNum(int numm) { //set Name
	num=numm;
}
public int getNum() { // get Name
	return num;
}

}
