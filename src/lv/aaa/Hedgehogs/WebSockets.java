package lv.aaa.Hedgehogs;

import android.app.Activity;
import android.util.Log;
import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;

public class WebSockets extends Activity {

    private static final String TAG = "WSHedgehog";

    private final WebSocketConnection mConnection = new WebSocketConnection();

    public WebSockets() {
        start();
    }

    private void start() {

        final String wsuri = "ws://127.0.0.1:8080/info";

        try {
            mConnection.connect(wsuri, new WebSocketHandler() {

                @Override
                public void onOpen() {
                    Log.e(TAG, "Status: Connected to " + wsuri);
                    mConnection.sendTextMessage("Hello, world!");
                }

                @Override
                public void onTextMessage(String payload) {
                    Log.e(TAG, "Got echo: " + payload);
                }

                @Override
                public void onClose(int code, String reason) {
                    Log.e(TAG, "Connection lost.");
                }
            });
        } catch (WebSocketException e) {

            Log.d(TAG, e.toString());
        }
    }
}
