/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loadbalance;

/**
 *
 * @author PXNDX
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomLoadBalance implements LoadBalance {

    @Override
    public String getServer(String clientIp) {
        Set<String> servers = IpPool.ipMap.keySet();
        List<String> serverList = new ArrayList<>();
        serverList.addAll(servers);
        int randomIndex = new Random().nextInt(serverList.size());
        String target = serverList.get(randomIndex);

        return target;
    }
}
