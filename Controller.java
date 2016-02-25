import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class Controller {
	//Where the database is stored
    private static final String DEFUALT_FILE_PATH = "./data/data.json";
    private static String filePath;
    private Database database;
    
    
    private UIController ui;
    
	
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
        try (Reader reader = new InputStreamReader(new FileInputStream(filePath), "UTF-8")) {
            dB = gsonFactory().fromJson(reader, Database.class);
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
            gsonFactory().toJson(dB, writer);
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

}