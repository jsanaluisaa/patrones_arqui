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
public class LoadBalanceMain {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        loadBalance();
    }

    public static void loadBalance() {
        doGetServer(new RoundRobin());
        doGetServer(new RandomLoadBalance());
        doGetServer(new IpHash());
        doGetServer(new WeightRoundRobin());
        doGetServer(new WeightRandom());
    }


    public static void doGetServer(LoadBalance loadBalance) {
        doGetServer(loadBalance, 100);
    }

    private static void doGetServer(LoadBalance loadBalance, int queryTimes) {
        for (int i = 0; i < queryTimes; i++) {
            String serverId = loadBalance.getServer(String.valueOf(i));
            System.out.println(String.format("[%s] index:%s,%s", loadBalance.getClass().getSimpleName(), i, serverId));
        }
    }
}