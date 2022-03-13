package main

import (
	"bufio"
	"fmt"
	"os"
)

func main() {
	input, err := os.Open("src/main/go/2015/day01/input.txt")
	if err != nil {
		panic(err)
	}
	defer input.Close()
	scanner := bufio.NewScanner(input)
	scanner.Scan()

	part1()
	part2()
	floor, firstBasement := walkInApartment(scanner.Text())
	fmt.Printf("Part 1: %d and part 2: %d\n", floor, firstBasement)
}

func part1() {
	finalFloor := map[string]int{
		"(())":    0,
		"()()":    0,
		"(((":     3,
		"))(((((": 3,
		"())":     -1,
		")())())": -3,
	}
	for instructions, floor := range finalFloor {
		f, _ := walkInApartment(instructions)
		if floor != f {
			fmt.Printf("not ok: %s, expected %d but got %d\n", instructions, floor, f)
		}
	}
}

func part2() {
	firstBasement := map[string]int{
		"(())":    -1,
		"()()":    -1,
		"(((":     -1,
		"))(((((": 1,
		"())":     3,
		")())())": 1,
	}

	for instructions, basement := range firstBasement {
		_, b := walkInApartment(instructions)
		if basement != b {
			fmt.Printf("not ok: %s, expected %d but got %d\n", instructions, basement, b)
		}
	}
}

func walkInApartment(instructions string) (int, int) {
	counter := 0
	firstBasement := -1
	for index, char := range instructions {
		if char == '(' {
			counter++
		} else {
			counter--
		}
		if firstBasement < 0 && counter == -1 {
			firstBasement = index + 1
		}
	}
	return counter, firstBasement
}
