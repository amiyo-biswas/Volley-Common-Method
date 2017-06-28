package in.intellij_amiya.common_volley.common;

/**
 * Created by Intellij Amiyo  on 28-06-2017.
 * Please follow standard Java coding conventions.
 * http://source.android.com/source/code-style.html
 */
public interface VolleyResponseListener {

    void onError(String message);

    void onResponse(Object response);
}
