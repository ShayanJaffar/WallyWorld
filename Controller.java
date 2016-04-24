import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.*;

public class Controller {
	//Where the database is stored
    private static final String DEFUALT_FILE_PATH = "./data/data.json";
    private static String filePath;
    private Database database;
    private UIController ui;
    private static final String Type = "AES";
    private static final byte[] keyValue = new byte[] { 'a', 'k', 'c', 'B', 'Z', 's', 'g','S', 'e', 'X', 'r','6', 'e', 't', 'e', 'y' };
	
    public static void main(String[] args) {
        Controller instance = new Controller();
        filePath = DEFUALT_FILE_PATH;
        instance.database = load();
		//give control over
        instance.database.initialize();
        instance.ui = new UIController(instance.database);
        instance.ui.run();
		//end
		save(instance.database);
        System.exit(0);
    }
    
    //Set up json format
    private static Gson gsonFactory() {
        return new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    }
    /**
     * Loads objects into the program from the json file
     * @return Database object created from the file
     */
    private static Database load() {
        Database dB = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
        	String enInfo = "", line;
        	StringBuilder builder = new StringBuilder();
        	while((line = reader.readLine()) != null)
        		builder.append(line);
        	enInfo = builder.toString();
        	dB = gsonFactory().fromJson(decrypt(enInfo), Database.class);
            //dB = gsonFactory().fromJson(enInfo, Database.class);
            dB.initialize();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Error in loading the gson file: " + filePath);
            return dB;
        }
        return dB;
    }
    /**
     * Saves database to json file
     * @param dB Database to be saved
     */
    public static void save(Database dB) {
        try (FileOutputStream fileStream = new FileOutputStream(filePath); Writer writer = new OutputStreamWriter(fileStream)) {
        	
        	String unInfo = gsonFactory().toJson(dB);
        	writer.write(encrypt(unInfo));
        	
            //gsonFactory().toJson(dB, writer);
        } catch (Exception ex) {
            System.out.println("error in saving the gson file: " + filePath);
        }
        
    }
    /**
     * Ends the program
     * @param database Database reference(for saving)
     */
    public static void endAll(Database database) {
    	save(database);
    	CMDLine.input.close();
    	System.exit(0);
    }
    public static String encrypt(String Data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(Type);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }

    public static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(Type);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, Type);
        return key;
    }

}