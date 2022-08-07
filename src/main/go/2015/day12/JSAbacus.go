package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
	"unicode"
)

const TextToDelete = "\"red\""

func main() {
	CleanAndSum("[1,2,3]", 6)
	CleanAndSum("{\"a\":2,\"b\":4}", 6)
	CleanAndSum("[[[3]]]", 3)
	CleanAndSum("{\"a\":{\"b\":4},\"c\":-1}", 3)
	CleanAndSum("{\"a\":[-1,1]}", 0)
	CleanAndSum("[-1,{\"a\":1}]", 0)
	CleanAndSum("[]", 0)
	CleanAndSum("{}}", 0)
	CleanStringExamples("[1,2,3]")
	CleanStringExamples("[1,{\"c\":\"red\",\"b\":2},3]")
	CleanStringExamples("{\"d\":\"red\",\"e\":[1,2,3,4],\"f\":5}")
	CleanStringExamples("{\"d\":\"ged\",\"z\":{\"a\":1,\"c\":{\"a\":1,\"c\":\"ted\",\"a\":1},\"b\":1},\"e\":[1,\"red\",3,4],\"f\":\"red\"}")
	CleanStringExamples("[1,\"red\",5]")
	CleanAndSum("[1,\"red\",5]", 6)
	CleanAndSum("[1,{\"c\":\"red\",\"b\":2},3]", 4)
	CleanAndSum("{\"d\":\"red\",\"e\":[1,2,3,4]x,\"f\":5}", 0)

	input, err := os.Open("src/main/go/2015/day12/input.txt")
	if err != nil {
		panic(err)
	}
	defer input.Close()
	scanner := bufio.NewScanner(input)

	scanner.Scan()
	text := scanner.Text()
	calculateSum := CalculateSum(text)
	fmt.Printf("sum of part one is %d\n", calculateSum)
	text = CleanString(text)
	calculateSum = CalculateSum(text)
	fmt.Printf("sum of part two is %d\n", calculateSum)
}

func CleanAndSum(line string, expected int) {
	line = CleanString(line)
	calculateSum := CalculateSum(line)
	fmt.Printf("sum of JSON %s is %d (expected %d)\n", line, calculateSum, expected)
}

func CalculateSum(line string) int {
	sum := 0
	currentNumber := ""
	for _, c := range line {
		if len(currentNumber) > 0 && !unicode.IsDigit(c) {
			number, err := strconv.Atoi(currentNumber)
			currentNumber = ""
			if err != nil {
				panic(err)
			}
			sum += number
		} else if unicode.IsDigit(c) || c == '-' {
			currentNumber = currentNumber + string(c)
		}
	}
	return sum
}

func CleanStringExamples(line string) {
	cleanString := CleanString(line)
	fmt.Printf("Clean %s -> %s\n", line, cleanString)
}

func CleanString(line string) string {
	index := strings.Index(line, TextToDelete)
	for index >= 0 {
		start := 0
		end := 0

		if line[index-1] == ':' {
			numberOfSubClasses := 0
			for i := index; i >= 0 && start == 0; i-- {
				if line[i] == '{' {
					if numberOfSubClasses == 0 {
						start = i
					} else {
						numberOfSubClasses--
					}
				} else if line[i] == '}' {
					numberOfSubClasses++
				}
			}
			numberOfSubClasses = 0
			for i := index; i < len(line) && end == 0; i++ {
				if line[i] == '{' {
					numberOfSubClasses++
				} else if line[i] == '}' {
					if numberOfSubClasses == 0 {
						end = i + 1
					} else {
						numberOfSubClasses--
					}
				}
			}
		} else if line[index-1] == ',' {
			start = index - 1
			end = index + 5
		} else if line[index-1] == '[' {
			start = index
			end = index + 5
		} else {
			panic(line)
		}

		line = line[:start] + line[end:]
		index = strings.Index(line, TextToDelete)
	}
	return line
}
