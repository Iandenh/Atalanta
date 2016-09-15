package nl.fa5t.test.app.Model.Table;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import nl.fa5t.test.app.Http.Client;

/**
 * Created by ian on 14-9-16.
 */
abstract public class Table<T> {
    protected Client client;
    protected String url = this.getClass().getSimpleName().replace("Table", "").toLowerCase()+".json";

    public Table() {
        client = new Client();

    }

    public ArrayList<T> getAll(Class<T> model){
        String result = client.get(url);

        try {
            JSONObject o = new JSONObject(result);
            JSONArray a = o.getJSONArray(this.getClass().getSimpleName().replace("Table", "").toLowerCase());
            Gson gson = new Gson();

            ArrayList<T> list = new ArrayList<>();
            for (int i = 0; i < a.length(); i++) {
                T obj = gson.fromJson(a.getJSONObject(i).toString(), model);
                list.add(obj);
            }

            return list;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


    }
}
