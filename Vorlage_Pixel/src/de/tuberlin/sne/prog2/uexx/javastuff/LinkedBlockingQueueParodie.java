package de.tuberlin.sne.prog2.uexx.javastuff;

import java.util.LinkedList;

public class LinkedBlockingQueueParodie<T> extends LinkedList<T> {

	// poll
	// take
	// put
	// max größe

	int maxelem;

	public LinkedBlockingQueueParodie() {
		super();
		this.maxelem = 10;
	}

	public LinkedBlockingQueueParodie(int max) {
		super();
		this.maxelem = max;
	}

	@Override
	public T poll() {

		synchronized (this) {

			return super.pollFirst();

		}

	}

	public T take() {

		while (this.remainingCapacity() == maxelem) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		synchronized (this) {
			return super.removeFirst();
		}

	}

	public void put(T e) {

		while (this.remainingCapacity() == 0) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		
		synchronized (this) {
			super.add(e);
		}
	}

	public int remainingCapacity() {
		synchronized (this) {
			return maxelem - this.size();
		}
	}

}
