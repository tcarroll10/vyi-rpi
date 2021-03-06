package com.tcarroll10.vyi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcarroll10.vyi.domain.Game;
import com.tcarroll10.vyi.service.VyiService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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

	@GetMapping("/games")
	public ResponseEntity<?> getGames() {
		LOG.info("getGames called");
		return new ResponseEntity<>(vyiService.getGames(), HttpStatus.OK);
	}

	@GetMapping("/teams/{division}")
	public ResponseEntity<?> getTeamsByDivision(@PathVariable String division) {
		LOG.info("getTeamsByDivision for {} called", division);
		return new ResponseEntity<>(vyiService.getTeamsByDivision(division), HttpStatus.OK);
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

	@GetMapping("/justRpi")
	public ResponseEntity<?> justRpi() {
		LOG.info("get Results called");
		return new ResponseEntity<>(vyiService.calcRpiOnly(), HttpStatus.OK);
	}

	@GetMapping("/results")
	public ResponseEntity<?> getRslts() {
		LOG.info("get Results called");
		return new ResponseEntity<>(vyiService.calcRslts(), HttpStatus.OK);
	}

	@GetMapping("/results/{division}")
	public ResponseEntity<?> getRsltsByDivision(@PathVariable String division) {
		LOG.info("get Results called for {} Division", division);
		return new ResponseEntity<>(vyiService.calcRsltsForDivision(division), HttpStatus.OK);
	}

	@PostMapping("/addGame")
	public void addGameRsult(@RequestBody Game game) {
		LOG.info("Game for {} entered", game.getGameDt());
		vyiService.addGameRslt(game);
	}

	@GetMapping("/game/{id}")
	public ResponseEntity<?> getGame(@PathVariable Integer id) {
		LOG.info("getGame for {} called", id);
		return new ResponseEntity<>(vyiService.getGame(id), HttpStatus.OK);

	}

	@DeleteMapping(value = "/deleteGame/{id}")
	public void deleteGame(@PathVariable Integer id) {
		LOG.info("deleteGame called for game {}", id);
		vyiService.deleteGame(id);
	}

	@PutMapping(value = "/updateGame")
	public void updateGame(@RequestBody Game game) {
		LOG.info("Game {} updated", game.getId());
		vyiService.updateGame(game);

	}

}
