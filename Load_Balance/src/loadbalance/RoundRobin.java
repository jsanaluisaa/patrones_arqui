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
import java.util.Set;

public class RoundRobin implements LoadBalance {
    private static Integer position = 0;

    @Override
    public String getServer(String clientIp) {
        Set<String> servers = IpPool.ipMap.keySet();
        List<String> serverList = new ArrayList<>();
        serverList.addAll(servers);
        String target = null;

        synchronized (position) {
            if (position > serverList.size() - 1) {
                position = 0;
            }
            target = serverList.get(position);
            position++;
        }
        return target;
    }
}