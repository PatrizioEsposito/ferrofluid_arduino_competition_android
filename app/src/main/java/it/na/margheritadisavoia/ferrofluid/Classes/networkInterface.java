package it.na.margheritadisavoia.ferrofluid.Classes;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WpsInfo;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Looper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pe-la on 20/10/2017.
 */

public class networkInterface  extends Activity{

    private String ip;
    private int port;
    private String fingerPrint;
    private Context context;
    private final IntentFilter intentFilter = new IntentFilter();
    private WifiP2pManager.Channel mChannel;
    private WifiP2pManager manager;

    public networkInterface(String ip, int port, String fingerPrint, Context context){

        this.ip = ip;
        this.port = port;
        this.fingerPrint = fingerPrint;
        this.context = context;

    }


    public void setupInterface(){

        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);


    }

    public void initializeInterface(){

        manager = (WifiP2pManager) context.getSystemService(Context.WIFI_P2P_SERVICE);
        mChannel = manager.initialize(context, Looper.getMainLooper(), null);

    }

    private void Connect(){

        manager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {

            @Override
            public void onSuccess() {

                final List<WifiP2pDevice> pDeviceList = new ArrayList<WifiP2pDevice>();

                WifiP2pManager.PeerListListener listListener = new WifiP2pManager.PeerListListener() {
                    @Override
                    public void onPeersAvailable(WifiP2pDeviceList peers) {

                        List<WifiP2pDevice> refreshedPeers = new ArrayList<WifiP2pDevice>();
                        if(!refreshedPeers.equals(pDeviceList)){

                            pDeviceList.clear();
                            pDeviceList.addAll(refreshedPeers);
                            if(refreshedPeers.size() != 0){

                                WifiP2pDevice device = refreshedPeers.get(0);
                                WifiP2pConfig config = new WifiP2pConfig();

                                config.deviceAddress = device.deviceAddress;
                                config.wps.setup = WpsInfo.PBC;

                                manager.connect(mChannel, config, new WifiP2pManager.ActionListener() {
                                    @Override
                                    public void onSuccess() {



                                    }

                                    @Override
                                    public void onFailure(int reason) {

                                        Log.d("Problemino", String.valueOf(reason));

                                    }
                                });

                            }
                        }
                        if (refreshedPeers.size() == 0){

                            Log.d("Problemino", "Non sono stati trovati peers.");
                            return;

                        }
                    }
                };


            }

            @Override
            public void onFailure(int reason) {

                Log.d("Problemino", String.valueOf(reason));

            }
        });

    }


    //Get

    public String getIp(){

        return ip;

    }

    public int getPort(){

        return port;

    }

    public String getFingerPrint(){

        return fingerPrint;

    }

    public WifiP2pManager.Channel getChannel(){

        return mChannel;

    }

    public WifiP2pManager manager(){

        return manager;

    }
}
