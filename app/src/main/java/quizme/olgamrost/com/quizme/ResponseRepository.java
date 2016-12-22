package quizme.olgamrost.com.quizme;

import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

/**
 * Created by olgamrost on 20/12/2016.
 */

public class ResponseRepository {

    private AssetManager assetManager;

    public ResponseRepository(AssetManager assetManager)
    {
        this.assetManager = assetManager;
    }

    public List<Response> GetResponses() {

        try {
            InputStream is = assetManager.open("twoJsons.txt");

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

//            for (Responce item : items) {
//                Log.v("--- item: ---", item.toString());
//            }

            List<Response> list = (List<Response>) items;

            for (Response item : list) {
                Log.v("---list item: ---", item.toString());
                Log.v("- list item question -", item.getQuestion());
                item.getAnswers();
                List<AnswersBean> answers = item.getAnswers();
                for (AnswersBean answer : answers) {
                    Log.v("-- list item answer --", answer.getAnswer());
                }
            }

            return list;

        } catch (IOException e) {
            e.printStackTrace();
            //} catch (JSONException e) {
            //  e.printStackTrace();
        }
        return null;
    }

    public Response GetNext(Response response)
    {
        List<Response> list = GetResponses();
        Response nextResponse = null;
        for (int j = 0; j< list.size(); j++)
        {
            Response currentResp = list.get(j);
            if (currentResp.get_id().equals(response.get_id()))
            {
                //current question index of list is in j
                nextResponse = list.get(j+1);
            }
        }
        return nextResponse;
    }
}
