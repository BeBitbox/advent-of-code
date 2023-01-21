package main

import "fmt"

func main() {
	reindeers := make([]Reindeer, 0)
	reindeers = append(reindeers,
		Reindeer{
			name:      "Rudolph",
			speed:     22,
			endurance: 8,
			rest:      165,
		},
		Reindeer{
			name:      "Cupid",
			speed:     8,
			endurance: 17,
			rest:      114,
		}, Reindeer{
			name:      "Prancer",
			speed:     18,
			endurance: 6,
			rest:      103,
		}, Reindeer{
			name:      "Donner",
			speed:     25,
			endurance: 6,
			rest:      145,
		}, Reindeer{
			name:      "Dasher",
			speed:     11,
			endurance: 12,
			rest:      125,
		}, Reindeer{
			name:      "Comet",
			speed:     21,
			endurance: 6,
			rest:      121,
		}, Reindeer{
			name:      "Blitzen",
			speed:     18,
			endurance: 3,
			rest:      50,
		}, Reindeer{
			name:      "Vixen",
			speed:     20,
			endurance: 4,
			rest:      75,
		}, Reindeer{
			name:      "Dancer",
			speed:     7,
			endurance: 20,
			rest:      119,
		})

	for i := 1; i <= 2503; i++ {
		for index, reindeer := range reindeers {
			reindeers[index] = reindeer.tick()
		}

		maxTravelled := 0
		for _, reindeer := range reindeers {
			if reindeer.travelled > maxTravelled {
				maxTravelled = reindeer.travelled
			}
		}

		for index := range reindeers {
			if reindeers[index].travelled == maxTravelled {
				reindeers[index].points++
			}
		}
	}

	for _, reindeer := range reindeers {
		fmt.Printf("%v \n", reindeer)
	}
}

func (reindeer Reindeer) tick() Reindeer {
	if !reindeer.isResting {
		reindeer.runningSeconds++
		reindeer.travelled += reindeer.speed
		if reindeer.runningSeconds == reindeer.endurance {
			reindeer.isResting = true
			reindeer.runningSeconds = 0
			reindeer.restingSeconds = 0
		}
	} else {
		reindeer.restingSeconds++
		if reindeer.restingSeconds == reindeer.rest {
			reindeer.isResting = false
			reindeer.runningSeconds = 0
			reindeer.restingSeconds = 0
		}
	}
	return reindeer
}

type Reindeer struct {
	name           string
	speed          int
	endurance      int
	rest           int
	runningSeconds int
	restingSeconds int
	travelled      int
	isResting      bool
	points         int
}
