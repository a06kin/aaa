package lv.aaa.Hedgehogs;

import org.atmosphere.nettosphere.Config;
import org.atmosphere.nettosphere.Nettosphere;

public class Server {

    public static void main(String args[]){
        Config.Builder b = new Config.Builder();
        b.resource(HedgehogsManagedService.class)
                .resource("./webapps")
                .port(8080)
                .host("127.0.0.1")
                .build();

        Nettosphere s = new Nettosphere.Builder().config(b.build()).build();
        s.start();
    }
}
