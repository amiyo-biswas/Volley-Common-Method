package in.intellij_amiya.common_volley.common;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Intellij Amiyo  on 28-06-2017.
 * Please follow standard Java coding conventions.
 * http://source.android.com/source/code-style.html
 */
public class VolleyUtils {

    public static void GET_METHOD(Context context, String url, final VolleyResponseListener listener)
    {

        // Initialize a new StringRequest
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listener.onResponse(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError(error.toString());

                    }
                })

        {


        };

        // Access the RequestQueue through singleton class.
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public static void POST_METHOD(Context context, String url,final Map<String,String> getParams, final VolleyResponseListener listener)
    {

        // Initialize a new StringRequest
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listener.onResponse(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError(error.toString());

                    }
                })

        {

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                getParams.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

        };

        // Access the RequestQueue through singleton class.
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
}
