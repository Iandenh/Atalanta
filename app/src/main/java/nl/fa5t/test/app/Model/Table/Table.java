package nl.fa5t.test.app.Model.Table;

import android.content.res.Resources;

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
    protected String plural = this.getClass().getSimpleName().replace("Table", "").toLowerCase();
    protected String singular;

    public Table() {
        client = new Client();

    }

    public ArrayList<T> getAll(Class<T> model) {
        String result = client.get(plural + ".json");

        if(result == null){
            throw new Resources.NotFoundException("Not Found");
        }
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

    public T get(int id, Class<T> model) {
        String result = client.get(plural + "/view/" + id + ".json");
        try {
            JSONObject o = new JSONObject(result);
            JSONObject a = o.getJSONObject(singular);
            Gson gson = new Gson();
            T obj = gson.fromJson(a.toString(), model);
            return obj;
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }
}
