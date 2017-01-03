package quiz.olgamrost.com.quiz;

import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import quiz.olgamrost.com.quiz.Json.Response;


/**
 * Created by olgamrost on 20/12/2016.
 */

public class ResponseRepository {

    private AssetManager assetManager;

    public ResponseRepository(AssetManager assetManager)
    {
        this.assetManager = assetManager;
    }

    public List<Response> GetResponses(String fileAddress) {


        try {
            InputStream is = assetManager.open(fileAddress);
            BufferedReader bR = new BufferedReader(new InputStreamReader(is));
            String line = "";
            StringBuilder responseStrBuilder = new StringBuilder();

            while ((line = bR.readLine()) != null) {
                responseStrBuilder.append(line);
            }
            is.close();

            String responcestr = responseStrBuilder.toString();
            Gson gson = new Gson();

            // http://stackoverflow.com/questions/9598707/gson-throwing-expected-begin-object-but-was-begin-array
            Type collectionType = new TypeToken<Collection<Response>>(){}.getType();
            Collection<Response> items = gson.fromJson(responcestr, collectionType);

            List<Response> list = (List<Response>) items;

            return list;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}