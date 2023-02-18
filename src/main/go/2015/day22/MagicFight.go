package main

import (
	"fmt"
	"math"
)

var history = make(map[string]int)

const hardMode = true

func main() {
	playExample()
	history = make(map[string]int)
	part1()
}

func playExample() {
	player := Player{
		hitPoints: 10,
		armor:     0,
		mana:      250,
		manaSpent: 0,
		history:   "",
		shield:    0,
		poison:    0,
		recharge:  0,
	}
	boss := Boss{
		hitPoints: 13,
		damage:    8,
	}

	winner, outcome := fight(player, boss, true)
	fmt.Printf("Player wins? %v - player: %v\n", outcome, winner)
}

func part1() {

	player := Player{
		hitPoints: 50,
		armor:     0,
		mana:      500,
		manaSpent: 0,
		history:   "",
		shield:    0,
		poison:    0,
		recharge:  0,
	}
	boss := Boss{
		hitPoints: 51,
		damage:    9,
	}

	winner, outcome := fight(player, boss, true)
	fmt.Printf("Player wins? %v - player: %v\n", outcome, winner)
}

func fight(player Player, boss Boss, turnPlayer bool) (Player, bool) {
	gamestate := fmt.Sprintf("%d-%d-%d-%d-%d-%d-%v", player.hitPoints, player.mana, player.shield, player.poison, player.recharge, boss.hitPoints, turnPlayer)
	prev, found := history[gamestate]
	if found && prev < player.manaSpent {
		return player, false
	}
	if !found {
		history[gamestate] = player.manaSpent
	}

	if hardMode {
		if turnPlayer {
			player.hitPoints--
			if player.hitPoints <= 0 {
				return player, false
			}
		}
	}

	bestPlayer := Player{manaSpent: math.MaxInt}
	hasWon := false

	if player.shield > 0 {
		player.armor = 7
		player.shield--
	} else {
		player.armor = 0
	}
	if player.poison > 0 {
		boss.hitPoints -= 3
		player.poison--
		if boss.hitPoints <= 0 {
			return player, true
		}
	}
	if player.recharge > 0 {
		player.recharge--
		player.mana += 101
	}

	if turnPlayer {
		if player.mana >= 53 {
			newPlayer, newBoss := doMagicMissle(player, boss)
			if newBoss.hitPoints <= 0 {
				if newPlayer.manaSpent < bestPlayer.manaSpent {
					return newPlayer, true
				}
			}
			finishedPlayer, outcome := fight(newPlayer, newBoss, false)
			if outcome && finishedPlayer.manaSpent < bestPlayer.manaSpent {
				bestPlayer = finishedPlayer
				hasWon = true
			}
		}
		if player.mana >= 73 {
			newPlayer, newBoss := drain(player, boss)
			if newBoss.hitPoints <= 0 {
				if newPlayer.manaSpent < bestPlayer.manaSpent {
					return newPlayer, true
				}
			}
			finishedPlayer, outcome := fight(newPlayer, newBoss, false)
			if outcome && finishedPlayer.manaSpent < bestPlayer.manaSpent {
				bestPlayer = finishedPlayer
				hasWon = true
			}
		}
		if player.shield == 0 && player.mana >= 113 {
			finishedPlayer, outcome := fight(createPlayerWithShield(player), boss, false)
			if outcome && finishedPlayer.manaSpent < bestPlayer.manaSpent {
				bestPlayer = finishedPlayer
				hasWon = true
			}
		}
		if player.poison == 0 && player.mana >= 173 {
			finishedPlayer, outcome := fight(createPlayerWithPoison(player), boss, false)
			if outcome && finishedPlayer.manaSpent < bestPlayer.manaSpent {
				bestPlayer = finishedPlayer
				hasWon = true
			}
		}
		if player.recharge == 0 && player.mana >= 229 {
			finishedPlayer, outcome := fight(createPlayerWithRecharge(player), boss, false)
			if outcome && finishedPlayer.manaSpent < bestPlayer.manaSpent {
				bestPlayer = finishedPlayer
				hasWon = true
			}
		}
	} else {
		damage := boss.damage - player.armor
		if damage < 1 {
			damage = 1
		}
		player.hitPoints -= damage

		if player.hitPoints <= 0 {
			return player, false
		} else {
			return fight(player, boss, true)
		}
	}
	return bestPlayer, hasWon
}

func doMagicMissle(player Player, boss Boss) (Player, Boss) {
	player.mana -= 53
	player.manaSpent += 53
	boss.hitPoints -= 4
	player.history += "MagicMissle ->"
	return player, boss
}

func drain(player Player, boss Boss) (Player, Boss) {
	player.mana -= 73
	player.manaSpent += 73
	player.hitPoints += 2
	boss.hitPoints -= 2
	player.history += "Drain ->"
	return player, boss
}

func createPlayerWithShield(player Player) Player {
	player.shield = 6
	player.mana -= 113
	player.manaSpent += 113
	player.history += "Shield ->"
	return player
}

func createPlayerWithPoison(player Player) Player {
	player.poison = 6
	player.mana -= 173
	player.manaSpent += 173
	player.history += "Poison ->"
	return player
}

func createPlayerWithRecharge(player Player) Player {
	player.recharge = 5
	player.mana -= 229
	player.manaSpent += 229
	player.history += "Recharge ->"
	return player
}

type Boss struct {
	hitPoints int
	damage    int
}

type Player struct {
	hitPoints int
	armor     int
	mana      int
	manaSpent int
	history   string
	shield    int
	poison    int
	recharge  int
}
