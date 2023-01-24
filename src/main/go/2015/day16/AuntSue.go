package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

var auntSue = map[string]int{
	"children":    3,
	"cats":        7,
	"samoyeds":    2,
	"pomeranians": 3,
	"akitas":      0,
	"vizslas":     0,
	"goldfish":    5,
	"trees":       3,
	"cars":        2,
	"perfumes":    1,
}

func main() {
	input, err := os.Open("src/main/go/2015/day16/input.txt")
	if err != nil {
		panic(err)
	}
	defer input.Close()
	scanner := bufio.NewScanner(input)

	lines := make([]string, 0)
	for scanner.Scan() {
		text := scanner.Text()
		lines = append(lines, text)
	}

	for _, line := range lines {
		splittedSplice := strings.Split(line, " ")
		number, _ := strconv.Atoi(strings.TrimSuffix(splittedSplice[1], ":"))

		if evaluate(splittedSplice[2], splittedSplice[3]) && evaluate(splittedSplice[4], splittedSplice[5]) && evaluate(splittedSplice[6], splittedSplice[7]) {
			fmt.Printf("aunt part 1: %d \n", number)
		}

		if evaluatePart2(splittedSplice[2], splittedSplice[3]) && evaluatePart2(splittedSplice[4], splittedSplice[5]) && evaluatePart2(splittedSplice[6], splittedSplice[7]) {
			fmt.Printf("aunt part 2: %d \n", number)
		}
	}
}

func evaluate(item string, count string) bool {
	item = strings.TrimSuffix(item, ":")
	itemCount, _ := strconv.Atoi(strings.TrimSuffix(count, ","))

	itemSue, ok := auntSue[item]
	return itemSue == itemCount && ok
}

func evaluatePart2(item string, count string) bool {
	item = strings.TrimSuffix(item, ":")
	itemCount, _ := strconv.Atoi(strings.TrimSuffix(count, ","))

	itemSue, ok := auntSue[item]

	switch item {
	case "cats":
	case "trees":
		return itemSue < itemCount && ok
	case "pomeranians":
	case "goldfish":
		return itemSue > itemCount && ok
	}
	return itemSue == itemCount && ok
}
