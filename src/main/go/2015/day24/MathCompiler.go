package main

import (
	"fmt"
	"math"
	"strconv"
	"strings"
)

var input = [28]int{
	1,
	3,
	5,
	11,
	13,
	17,
	19,
	23,
	29,
	31,
	41,
	43,
	47,
	53,
	59,
	61,
	67,
	71,
	73,
	79,
	83,
	89,
	97,
	101,
	103,
	107,
	109,
	113}

func main() {
	i := int64(0)
	i2 := int64(math.Pow(2, 28))

	lowestPackages := math.MaxInt
	quatumEntanglement := math.MaxInt
	for i = 0; i < i2; i++ {
		formatInt := strconv.FormatInt(i, 2)

		count := strings.Count(formatInt, "1")
		if count == 5 {
			sum := 0
			for index, char := range formatInt {
				if char == '1' {
					sum += input[index]
				}
			}
			if sum == 387 {
				newQE := calculateQuantumEntanglement(formatInt)
				fmt.Printf("new quantum %d\n", newQE)
				if count < lowestPackages {
					lowestPackages = count
					quatumEntanglement = newQE
				} else if count == lowestPackages {
					if quatumEntanglement > newQE {
						printQuantumEntanglement(formatInt)
						quatumEntanglement = newQE
					}
				}
			}
		}
	}
}

func calculateQuantumEntanglement(line string) int {
	product := 1
	for index, char := range line {
		if char == '1' {
			product *= input[index]
		}
	}
	return product
}

func printQuantumEntanglement(line string) {
	output := "--->"
	for index, char := range line {
		if char == '1' {
			output += strconv.Itoa(input[index]) + " "
		}
	}
	fmt.Printf("Line %v\n", output)
}
