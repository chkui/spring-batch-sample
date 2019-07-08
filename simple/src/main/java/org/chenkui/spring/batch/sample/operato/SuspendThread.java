package org.chenkui.spring.batch.sample.operato;

public class SuspendThread {
	public void run() {
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						System.err.println("掛載線程異常");
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
