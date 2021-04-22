package dev.samsanders.study.springprofessional.app;

import java.util.concurrent.CountDownLatch;

public class ExampleLazyInitializedBean {
	private final CountDownLatch countDownLatch;

	public ExampleLazyInitializedBean(CountDownLatch countDownLatch) {

		this.countDownLatch = countDownLatch;
	}

	void init() {
		if (null != countDownLatch)
			countDownLatch.countDown();
	}
}
