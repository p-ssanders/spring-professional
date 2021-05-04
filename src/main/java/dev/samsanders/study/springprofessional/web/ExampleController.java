package dev.samsanders.study.springprofessional.web;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class ExampleController {

	private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

	public ExampleController(ThreadPoolTaskExecutor threadPoolTaskExecutor) {
		this.threadPoolTaskExecutor = threadPoolTaskExecutor;
	}

	@PutMapping("/example-resources")
	public void voidControllerMethod() {

	}

	@GetMapping("/example-resources")
	public ResponseEntity<Map<String, String>> mapToJson() {
		Map<String, String> responseBody = Collections.singletonMap("key", "value");

		return ResponseEntity.ok(responseBody);
	}

	@GetMapping("/example-resources/bad-resource")
	public ResponseEntity<String> badEntity() {
		throw new RuntimeException("boom");
	}

	@GetMapping(value = "/server-sent-events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public SseEmitter sse() {
		SseEmitter sseEmitter = new SseEmitter();

		threadPoolTaskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				IntStream.range(0,3).forEach(x -> {
					try {
						sseEmitter.send("test " + x);
						Thread.sleep(2000);
					}
					catch (IOException | InterruptedException e) {
						e.printStackTrace();
					}
				});
				sseEmitter.complete();
			}
		});

		return sseEmitter;
	}

}
