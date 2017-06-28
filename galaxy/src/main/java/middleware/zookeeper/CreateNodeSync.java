package middleware.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.KeeperState;

public class CreateNodeSync implements Watcher {
	
	private static ZooKeeper zooKeeper;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		zooKeeper = new ZooKeeper("192.168.11.130:2181", 50000, new CreateNodeSync());
		System.out.println("state: " + zooKeeper.getState());
		Thread.sleep(Integer.MAX_VALUE);
	}

	private void doSomething() {
		try {
			String path = zooKeeper.create("/galaxy_1", "galaxy".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			System.out.println("path: " + path);
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void process(WatchedEvent event) {
		System.out.println("Event: " + event);
		if (event.getState() == KeeperState.SyncConnected) {
			doSomething();
		}
	}

}