package org.zk;

import org.utils.Constant;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ZKTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeper zk = new ZooKeeper(Constant.ZK_ADDRESS, 3000, event -> {
            System.out.println(new Date().getTime());
            if (event.getType() == Watcher.Event.EventType.NodeDataChanged) {
                System.out.println(new Date().getTime());
            }
        });
        System.out.println(zk.getState());
//        Thread.sleep(1000000);


        String zpath = "/";
        List<String> zooChildren = new ArrayList<String>();
        if (zk != null) {
            try {
                //zk.create("/cc", "adfdf".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                zooChildren = zk.getChildren(zpath, false);
                System.out.println("Znodes of '/': ");
                for (String child : zooChildren) {
                    //print the children
                    System.out.println(child);
                }
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            zk.getChildren("/",true);
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }
}
