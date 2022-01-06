package com.tcarroll10.vyi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcarroll10.vyi.service.VyiService;

@RestController
public class VyiController {

	private static Logger LOG = (Logger) LoggerFactory.getLogger(VyiController.class);

	@Autowired
	private VyiService vyiService;

	@GetMapping("/hello")
	public String sayHello() {
		LOG.info("hello request made");
		return "hello";
	}

	@GetMapping("/teams")
	public ResponseEntity<?> getTeam() {
		LOG.info("getTeam called");
		return new ResponseEntity<>(vyiService.getTeam(), HttpStatus.OK);
	}

	@PostMapping("/team")
	public ResponseEntity<?> getSpecificTeam(@RequestBody Integer input) {
		LOG.info("Team ID Entered {}", input);
		return new ResponseEntity<>(vyiService.getSpecificTeam(input), HttpStatus.OK);
	}

	@PostMapping("/gamesPlayed")
	public ResponseEntity<?> gamesPlayed(@RequestBody Integer input) {
		LOG.info("Team ID Entered {}", input);
		return new ResponseEntity<>(vyiService.getGamesPlayed(input), HttpStatus.OK);
	}

	@PostMapping("/gamesWon")
	public ResponseEntity<?> gamesWon(@RequestBody Integer input) {
		LOG.info("Team ID Entered {}", input);
		return new ResponseEntity<>(vyiService.getGamesWon(input), HttpStatus.OK);
	}

	@PostMapping("/getWinRate")
	public ResponseEntity<?> winRate(@RequestBody Integer input) {
		LOG.info("Team ID Entered {}", input);
		return new ResponseEntity<>(vyiService.getWinRate(input), HttpStatus.OK);
	}

	@PostMapping("/getOpponents")
	public ResponseEntity<?> getOpponents(@RequestBody Integer input) {
		LOG.info("Team ID Entered {}", input);
		return new ResponseEntity<>(vyiService.getOpponents(input), HttpStatus.OK);
	}

	@PostMapping("/getOpponentsAvgWinRate")
	public ResponseEntity<?> getOpponentsAvgWinRate(@RequestBody Integer input) {
		LOG.info("Team ID Entered {}", input);
		return new ResponseEntity<>(vyiService.getOpponentsAvgWinRate(input), HttpStatus.OK);
	}

	@PostMapping("/getOpponentsOfOpponents")
	public ResponseEntity<?> getOpponentsOfOpponents(@RequestBody Integer input) {
		LOG.info("get Opponents of Opponents called");
		return new ResponseEntity<>(vyiService.getOpponentsOfOpponents(input), HttpStatus.OK);
	}

	@PostMapping("/getOpponentsOfOpponentsAvgWinRate")
	public ResponseEntity<?> getOpponentsOfOpponentAvgWinRate(@RequestBody Integer input) {
		LOG.info("get Opponents of Opponents called");
		return new ResponseEntity<>(vyiService.getOpponentsOfOpponentsAvgWinRate(input), HttpStatus.OK);
	}

	@PostMapping("/rpi")
	public ResponseEntity<?> calcRpi(@RequestBody Integer input) {
		LOG.info("rpi calc called for team {}", input);
		return new ResponseEntity<>(vyiService.getRpi(input), HttpStatus.OK);
	}

	@GetMapping("/results")
	public ResponseEntity<?> getRslts() {
		LOG.info("get Results called");
		return new ResponseEntity<>(vyiService.calcRslts(), HttpStatus.OK);
	}

	@GetMapping("/justRpi")
	public ResponseEntity<?> justRpi() {
		LOG.info("get Results called");
		return new ResponseEntity<>(vyiService.calcRpiOnly(), HttpStatus.OK);
	}

}
