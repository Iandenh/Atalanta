package nl.fa5t.test.app.Repository;

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
abstract public class Repository<T> {
    protected Class<T> model;
    protected Client client;
    protected String plural = this.getClass().getSimpleName().replace("Repository", "").toLowerCase();
    protected String singular;

    public Repository() {
        client = new Client();
    }

    public ArrayList<T> getAll() {
        String result = client.get(plural + ".json");

        if(result == null){
            throw new Resources.NotFoundException("Not Found");
        }
        try {
            JSONObject o = new JSONObject(result);
            JSONArray a = o.getJSONArray(this.getClass().getSimpleName().replace("Repository", "").toLowerCase());
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

    public T get(int id) {
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
