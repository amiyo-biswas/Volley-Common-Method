package in.intellij_amiya.common_volley.common;

import android.content.Context;
import android.util.Base64;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by iit_amiyo on 10/10/17.
 */

public class VolleyApiCAll
{
    Context context;

    public VolleyApiCAll(Context context_) {
        context=context_;
    }

    public interface VolleyCallback
    {
        void onSuccessResponse(String result);
    }
    public void Volley_GET(String url, final VolleyCallback callback)
    {

        StringRequest strREQ = new StringRequest(Request.Method.GET, url, new Response.Listener < String > ()
        {
            @Override
            public void onResponse(String Response)
            {
                callback.onSuccessResponse(Response);
            }

        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError e)
            {
                callback.onSuccessResponse("VOLLEY_NETWORK_ERROR");
            }
        })
        {
            /**
             * Passing some request headers
             * */
/*            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                return createBasicAuthHeader("enter_username", "enter_password");
            }*/

            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Username", "enter_username");
                params.put("Password", "enter_password");
                params.put("grant_type", "password");

                return params;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(strREQ);
    }
    private HashMap<String, String> createBasicAuthHeader(String username, String password)
    {
        HashMap<String, String> headerMap = new HashMap<String, String>();

        String credentials = username + ":" + password;
        String base64EncodedCredentials =
                Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        headerMap.put("Authorization", "Basic " + base64EncodedCredentials);

        return headerMap;
    }
    String BOUNDARY = "INTELLIJ_AMIYA";

    public void Volley_POST(final Map<String, String> params, String url, final VolleyCallback callback)
    {
        try
        {

            RequestQueue requestQueue = Volley.newRequestQueue(context);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
            {
                @Override
                public void onResponse(String response)
                {
                    callback.onSuccessResponse(response);

                }
            }, new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    callback.onSuccessResponse("POST_ERROR");

                }
            })
            {
                /**
                 * Passing some request headers
                 * */
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();

                    params.put("Content-Type", "multipart/form-data; boundary=" + BOUNDARY+"; charset=utf-8");
                    return params;
                }

                @Override
                public String getBodyContentType() {
                    return "multipart/form-data; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError
                {
                    try
                    {
                        String postBody = createPostBody(params);

                        return postBody == null ? null : postBody.getBytes("utf-8");
                    }
                    catch (NegativeArraySizeException n)
                    {
                        n.printStackTrace();
                        return null;
                    }
                    catch (UnsupportedEncodingException uee)
                    {

                        uee.printStackTrace();
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response)
                {

                    String responseString = "post_error";
                    if (response != null)
                    {
                        try
                        {
                            responseString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue.add(stringRequest);
        }
        catch (NegativeArraySizeException n)
        {
            n.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private String createPostBody(Map<String, String> params) {
        StringBuilder sbPost = new StringBuilder();
        for (String key : params.keySet()) {
            if (params.get(key) != null) {
                sbPost.append("\r\n" + "--" + BOUNDARY + "\r\n");
                sbPost.append("Content-Disposition: form-data; name=\"" + key + "\"" + "\r\n\r\n");
                sbPost.append(params.get(key));
            }
        }

        return sbPost.toString();
    }
}