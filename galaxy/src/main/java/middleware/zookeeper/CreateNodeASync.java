package middleware.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.KeeperState;

public class CreateNodeASync implements Watcher {
	
	private static ZooKeeper zooKeeper;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		zooKeeper = new ZooKeeper("192.168.11.130:2181", 50000, new CreateNodeASync());
		System.out.println("state: " + zooKeeper.getState());
		Thread.sleep(Integer.MAX_VALUE);
	}

	private void doSomething() {
		zooKeeper.create("/galaxy_2", "galaxy".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, new IStringCallback(), "create");
	}

	public void process(WatchedEvent event) {
		System.out.println("Event: " + event);
		if (event.getState() == KeeperState.SyncConnected) {
			doSomething();
		}
	}
	
	static class IStringCallback implements AsyncCallback.StringCallback {

		public void processResult(int rc, String path, Object ctx, String name) {
			System.out.println("rc: " + rc);
			System.out.println("path: " + path);
			System.out.println("ctx: " + ctx);
			System.out.println("name: " + name);
		}
		
	}

}