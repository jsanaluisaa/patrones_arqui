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
public interface LoadBalance {
    String getServer(String clientIp);
}
