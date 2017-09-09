# Volley_Common_Class

`Volley excels at RPC-type operations used to populate a UI, such as fetching a page of search results as structured data. It integrates easily with any protocol and comes out of the box with support for raw strings, images, and JSON. By providing built-in support for the features you need, Volley frees you from writing boilerplate code and allows you to concentrate on the logic that is specific to your app.`


For more details you should visit https://developer.android.com/training/volley/index.html

See my answer  https://stackoverflow.com/a/44470827/3395198

# NOTE

Android Volley library has a very elaborate caching mechanism. This is one of the best features of volley. When a request is made through volley first it is checked in the cache. If an appropriate response is present in cache then it is parsed and returned directly to main thread, else a network request is made. 
