package org.com.prevent.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.com.prevent.domain.Log;
import org.com.prevent.exception.LogNotFoundException;
import org.com.prevent.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class LogResource {

	@Autowired
	private LogRepository logRepository;

	@GetMapping("/logs")
	public List<Log> listAllLogs() {
		return logRepository.findAll();
	}

	@GetMapping("/log/{id}")
	public Resource<Log> retrieveLog(@PathVariable long id) {
		Optional<Log> log = logRepository.findById(id);

		if (!log.isPresent())
			throw new LogNotFoundException("id-" + id);

		Resource<Log> resource = new Resource<Log>(log.get());

		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listAllLogs());

		resource.add(linkTo.withRel("all-logs"));

		return resource;
	}

	@PostMapping("/log")
	public ResponseEntity<Object> createLog(@RequestBody Log log) {
		Log logSave = logRepository.save(log);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(logSave.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}

	@DeleteMapping("/log/{id}")
	public void deleteLog(@PathVariable long id) {
		logRepository.deleteById(id);
	}

	@PutMapping("/log/{id}")
	public ResponseEntity<Object> updateLog(@RequestBody Log log, @PathVariable long id) {

		Optional<Log> LogOptional = logRepository.findById(id);

		if (!LogOptional.isPresent())
			return ResponseEntity.notFound().build();

		log.setId(id);

		logRepository.save(log);

		return ResponseEntity.noContent().build();
	}
}
