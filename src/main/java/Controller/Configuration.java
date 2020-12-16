package Controller;

import Json.JSONReader;
import Json.JSONWriter;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class Configuration {
    private final JSONObject object;
    private final File file;

    public Configuration(String path) throws IOException {
        this.file = new File(path);
        if(file.exists())
            this.object = new JSONReader(file).toJSONObject();
        else
            this.object = new JSONObject();
    }

    public String getString(String key,String defaultValue){
        if(!this.object.has(key))
            object.put(key,defaultValue);
            this.save();
        return object.getString(key);
    }

    public void save(){
        try(JSONWriter writer = new JSONWriter(file)){
            writer.write(this.object);
            writer.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
