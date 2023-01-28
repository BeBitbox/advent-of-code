package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
)

func main() {
	input, err := os.Open("src/main/go/2015/day17/input.txt")
	if err != nil {
		panic(err)
	}
	defer input.Close()
	scanner := bufio.NewScanner(input)

	containers := make(map[int]int)

	counter := 0
	for scanner.Scan() {
		text := scanner.Text()
		containers[counter], _ = strconv.Atoi(text)
		counter++
	}
	fmt.Printf("input: %v \n", containers)

	totalCombinations := 0
	minimalCombinationNumber := 20
	totalCombinationsMin := 0

	bignumber := int64(math.Pow(2, 20))
	for i := int64(0); i < bignumber; i++ {
		formatInt := fmt.Sprintf("%020b", i)

		sum := 0
		result := "res "
		numberAdded := 0
		for j := 0; j < 20; j++ {
			if formatInt[j] == '1' {
				sum += containers[j]
				result += strconv.Itoa(containers[j]) + " "
				numberAdded++
			}
		}
		if sum == 150 {
			fmt.Printf("%s \n", result)
			totalCombinations++

			if numberAdded < minimalCombinationNumber {
				minimalCombinationNumber = numberAdded
				totalCombinationsMin = 1
			} else if numberAdded == minimalCombinationNumber {
				totalCombinationsMin++
			}
		}
	}
	fmt.Printf("part 1: total combinations %v \n", totalCombinations)
	fmt.Printf("part 2: total combinations %d of minimal length %d\n", totalCombinationsMin, minimalCombinationNumber)
}
