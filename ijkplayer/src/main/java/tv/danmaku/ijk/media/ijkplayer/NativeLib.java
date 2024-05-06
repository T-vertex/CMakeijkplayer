package tv.danmaku.ijk.media.ijkplayer;

public class NativeLib {

    // Used to load the 'ijkplayer' library on application startup.
    static {
        System.loadLibrary("ijkplayer");
    }

    /**
     * A native method that is implemented by the 'ijkplayer' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}