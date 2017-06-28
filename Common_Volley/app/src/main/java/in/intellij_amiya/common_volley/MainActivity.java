package in.intellij_amiya.common_volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Map;

import in.intellij_amiya.common_volley.common.VolleyResponseListener;
import in.intellij_amiya.common_volley.common.VolleyUtils;

public class MainActivity extends AppCompatActivity {


    public ListView list_VIEW;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list_VIEW=(ListView)findViewById(R.id.list_VIEW);

        // You should add NETWORK Validation
        _loadAPI_POST();

    }

    public void _loadAPI_POST()
    {
        // Volley is an HTTP library that makes networking for Android apps easier and most importantly, faster.
        //Volley is not suitable for large download or streaming operations, since Volley holds all responses in memory during parsing.
        // Read https://developer.android.com/training/volley/index.html

        String URL="PUT_YOUR_URL";
           VolleyUtils.POST_METHOD(MainActivity.this, URL,getParams(), new VolleyResponseListener() {
            @Override
            public void onError(String message)
            {
                System.out.println("POST_ERROR" + message);

            }

            @Override
            public void onResponse(Object response) {

                System.out.println("POST_SUCCESS" + response);
                // Now PARSE JSON response

            }
        });


    }



    public Map<String,String> getParams()
    {
        Map<String, String> params = new HashMap<String, String>();
        params.put("YOUR_KEY", "YOUR_VALUE"); // Set Your_key_value
        return params;
    }

    public void _loadAPI_GET()
    {
        String URL = "PUT_YOUR_URL";
        VolleyUtils.GET_METHOD(MainActivity.this, URL, new VolleyResponseListener() {
            @Override
            public void onError(String message) {
                System.out.println("GET_ERROR" + message);
            }

            @Override
            public void onResponse(Object response) {

                System.out.println("GET_SUCCESS" + response);
                // Now PARSE JSON response
            }
        });
    }
}
