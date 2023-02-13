package main

import (
	"fmt"
)

const input = 36_000_000

func main() {
	part1()
	part2()
}

func part1() {
	houses := [input + 1]int64{}

	for i := 1; i <= input; i++ {
		for j := i; j <= input; j += i {
			houses[j] += 10 * int64(i)
		}
	}

	fmt.Printf("First 10 houses: %v\n", houses[1:11])
	houseInput := houses[input]
	fmt.Printf("Presents at location %d : %v\n", input, houseInput)

	index := -1
	for i := 0; i < input && index < 0; i++ {
		if houses[i] >= input {
			fmt.Printf("Found index with value %d : %d\n", i, houses[i])
			index = i
		}
	}
}

func part2() {
	houses := [input + 1]int64{}

	for i := 1; i <= input; i++ {
		k := 0
		for j := i; j <= input && k < 50; j += i {
			houses[j] += 11 * int64(i)
			k++
		}
	}

	fmt.Printf("First 10 houses: %v\n", houses[1:11])
	houseInput := houses[input]
	fmt.Printf("Presents at location %d : %v\n", input, houseInput)

	index := -1
	for i := 0; i < input && index < 0; i++ {
		if houses[i] >= input {
			fmt.Printf("Found index with value %d : %d\n", i, houses[i])
			index = i
		}
	}
}
