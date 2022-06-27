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
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class WeightRoundRobin implements LoadBalance {

    private static Integer position = 0;

    @Override
    public String getServer(String clientIp) {
        Set<String> servers = IpPool.ipMap.keySet();
        List<String> serverList = new ArrayList<>();

        Iterator<String> iterator = servers.iterator();
        while (iterator.hasNext()) {
            String serverItem = iterator.next();
            Integer weight = IpPool.ipMap.get(serverItem);
            if (weight > 0) {
                for (int i = 0; i < weight; i++) {
                    serverList.add(serverItem);
                }
            }

        }

        synchronized (position) {
            if (position > serverList.size()) {
                position = 0;
            }

            String target = serverList.get(position);
            position++;
            return target;
        }
    }
}