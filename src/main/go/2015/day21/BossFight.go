package main

import (
	"fmt"
	"math"
)

func main() {
	result := playExample()
	fmt.Printf("Player wins? %v\n", result)

	weapons := [5]Item{
		{
			name:   "Dagger",
			cost:   8,
			damage: 4,
			armor:  0,
		},
		{
			name:   "Shortsword",
			cost:   10,
			damage: 5,
			armor:  0,
		},
		{
			name:   "Warhammer",
			cost:   25,
			damage: 6,
			armor:  0,
		},
		{
			name:   "Longsword",
			cost:   40,
			damage: 7,
			armor:  0,
		},
		{
			name:   "Greataxe",
			cost:   74,
			damage: 8,
			armor:  0,
		},
	}
	armors := [6]Item{
		{
			name:   "Naked",
			cost:   0,
			damage: 0,
			armor:  0,
		},
		{
			name:   "Leather",
			cost:   13,
			damage: 0,
			armor:  1,
		},
		{
			name:   "Chainmail",
			cost:   31,
			damage: 0,
			armor:  2,
		},
		{
			name:   "Splintmail",
			cost:   53,
			damage: 0,
			armor:  3,
		},
		{
			name:   "Bandedmail",
			cost:   75,
			damage: 0,
			armor:  4,
		},
		{
			name:   "Platemail",
			cost:   102,
			damage: 0,
			armor:  5,
		},
	}
	rings := [7]Item{
		{
			name:   "None",
			cost:   0,
			damage: 0,
			armor:  0,
		},
		{
			name:   "Dam+1",
			cost:   25,
			damage: 1,
			armor:  0,
		},
		{
			name:   "Dam+2",
			cost:   50,
			damage: 2,
			armor:  0,
		},
		{
			name:   "Dam+3",
			cost:   100,
			damage: 3,
			armor:  0,
		},
		{
			name:   "Def+1",
			cost:   20,
			damage: 0,
			armor:  1,
		},
		{
			name:   "Def+2",
			cost:   40,
			damage: 0,
			armor:  2,
		},
		{
			name:   "Def+3",
			cost:   80,
			damage: 0,
			armor:  3,
		},
	}

	boss := Actor{
		name:      "Boss",
		hitPoints: 103,
		damage:    9,
		armor:     2,
	}

	amountNeeded := math.MaxInt
	maxAmountToLose := 0
	for weapon := 0; weapon < len(weapons); weapon++ {
		for armor := 0; armor < len(armors); armor++ {
			for ring1 := 0; ring1 < len(rings); ring1++ {
				for ring2 := ring1; ring2 < len(rings); ring2++ {
					if ring1 != ring2 || (ring1 == 0 && ring2 == 0) {
						player := Actor{
							name:      "Player",
							hitPoints: 100,
							damage:    weapons[weapon].damage + armors[armor].damage + rings[ring1].damage + rings[ring2].damage,
							armor:     weapons[weapon].armor + armors[armor].armor + rings[ring1].armor + rings[ring2].armor,
							coins:     weapons[weapon].cost + armors[armor].cost + rings[ring1].cost + rings[ring2].cost,
						}
						result := fight(player, boss, false)
						if result && amountNeeded > player.coins {
							amountNeeded = player.coins
							fmt.Printf("Player wins with %s/%s/%s/%s for cost %d\n", weapons[weapon].name, armors[armor].name, rings[ring1].name, rings[ring2].name, player.coins)
						}
						if !result && maxAmountToLose < player.coins {
							maxAmountToLose = player.coins
							fmt.Printf("Player Looses with %s/%s/%s/%s for insane cost %d\n", weapons[weapon].name, armors[armor].name, rings[ring1].name, rings[ring2].name, player.coins)
						}
					}
				}
			}
		}
	}
}

func playExample() bool {
	player := Actor{
		name:      "Player",
		hitPoints: 8,
		damage:    5,
		armor:     5,
	}
	boss := Actor{
		name:      "Boss",
		hitPoints: 12,
		damage:    7,
		armor:     2,
	}
	result := fight(player, boss, true)
	return result
}

func fight(player Actor, boss Actor, verbose bool) bool {
	for true {
		damageDone := player.damage - boss.armor
		if damageDone < 1 {
			damageDone = 1
		}
		boss.hitPoints -= damageDone
		if verbose {
			fmt.Printf("Player hits boss with %d damage, new hitpoints boss %d\n", damageDone, boss.hitPoints)
		}
		if boss.hitPoints < 1 {
			return true
		}

		damageDone = boss.damage - player.armor
		if damageDone < 1 {
			damageDone = 1
		}
		player.hitPoints -= damageDone
		if verbose {
			fmt.Printf("Boss hits player with %d damage, new hitpoints boss %d\n", damageDone, player.hitPoints)
		}
		if player.hitPoints < 1 {
			return false
		}
	}
	panic("oww no")
}

type Actor struct {
	name      string
	hitPoints int
	damage    int
	armor     int
	coins     int
}
type Item struct {
	name   string
	cost   int
	damage int
	armor  int
}
