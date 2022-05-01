package main

import (
	"bufio"
	"fmt"
	"os"
)

func main() {
	examplesPart1 := map[string]bool{
		"ugknbfddgicrmopn": true,
		"aaa":              true,
		"jchzalrnumimnmhp": false,
		"haegwjzuvuyypxyu": false,
		"dvszwmarrgswjxmb": false,
	}

	for key, entry := range examplesPart1 {
		fmt.Printf("Expected part 1 (%s:%v) -> %v\n", key, entry, NiceString(key))
	}
	examplesPart2 := map[string]bool{
		"qjhvhtzxzqqjkmpb": true,
		"xxyxx":            true,
		"uurcxstgmygtbstg": false,
		"ieodomkazucvgmuy": false,
	}
	for key, entry := range examplesPart2 {
		fmt.Printf("Expected part 2 (%s:%v) -> %v\n", key, entry, NiceString2(key))
	}

	input, err := os.Open("src/main/go/2015/day05/input.txt")
	if err != nil {
		panic(err)
	}
	defer input.Close()
	scanner := bufio.NewScanner(input)

	sumNiceOnes := 0
	sumNiceOnesPart2 := 0
	for scanner.Scan() {
		text := scanner.Text()
		if NiceString(text) {
			sumNiceOnes++
		}
		if NiceString2(text) {
			sumNiceOnesPart2++
		}
	}
	fmt.Printf("Nice Strings found part 1: %d and for part 2: %d", sumNiceOnes, sumNiceOnesPart2)
}

func NiceString(input string) bool {
	numberOfVowels := 0
	previousChar := '_'
	doubleLettersDetected := false
	illegalStringDetected := false
	for _, char := range input {
		if char == 'a' || char == 'e' || char == 'i' || char == 'o' || char == 'u' {
			numberOfVowels++
		}
		if previousChar == char {
			doubleLettersDetected = true
		}
		if (previousChar == 'a' && char == 'b') || (previousChar == 'c' && char == 'd') || (previousChar == 'p' && char == 'q') || (previousChar == 'x' && char == 'y') {
			illegalStringDetected = true
		}
		previousChar = char
	}
	return numberOfVowels >= 3 && doubleLettersDetected && !illegalStringDetected
}

func NiceString2(input string) bool {
	mirrored := false
	pair := false

	for i := 0; i < len(input)-2; i++ {
		for j := i + 2; j < len(input); j++ {
			if i+2 == j && input[i] == input[j] {
				mirrored = true
			}
			if j+1 < len(input) && input[i] == input[j] && input[i+1] == input[j+1] {
				pair = true
			}
		}
	}

	return mirrored && pair
}
