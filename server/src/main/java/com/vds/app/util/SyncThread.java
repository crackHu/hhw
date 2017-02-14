package com.vds.app.util;

public class SyncThread extends Thread {



	@Override
	public synchronized void run() {
		running();
	}
	
	public synchronized void running(){
		
	}

}
