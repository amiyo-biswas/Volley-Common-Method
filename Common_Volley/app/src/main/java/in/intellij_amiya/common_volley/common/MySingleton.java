package in.intellij_amiya.common_volley.common;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Force Power Intellij Amiyo  on 28-06-2017.
 * Please follow standard Java coding conventions.
 * http://source.android.com/source/code-style.html
 */


public class MySingleton
{
    private static MySingleton mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    private MySingleton(Context context)
    {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized MySingleton getInstance(Context context)
    {
        if (mInstance == null)
        {
            mInstance = new MySingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue()
    {
        if (mRequestQueue == null)
        {
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req)
    {
        getRequestQueue().add(req);
    }

}
