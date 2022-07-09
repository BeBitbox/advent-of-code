package main

import (
	"bytes"
	"fmt"
	"strconv"
)

func main() {
	exampleLine := "1"
	for i := 0; i < 5; i++ {
		result := usingStringConcatenation(exampleLine)
		fmt.Printf("example %s becomes %s\n", exampleLine, result)
		exampleLine = result
	}

	part1 := "1321131112"

	for i := 0; i < 50; i++ {
		result := lookAndSay(part1)
		part1 = result
	}
	fmt.Printf("Completed size %d\n", len(part1))
}

//Very slow because of inefficient way of string concatenation
func usingStringConcatenation(line string) string {
	current := ' '
	counter := 0
	output := ""
	for index, c := range line {
		if index == 0 {
			counter = 1
			current = c
		} else {
			if current == c {
				counter++
			} else {
				output += strconv.Itoa(counter) + string(current)
				current = c
				counter = 1
			}
		}
	}
	output += strconv.Itoa(counter) + string(current)
	return output
}

//using bytes.Buffer... I didn't know that my solution was bad just because of inefficient string concatenations
//credit to https://www.reddit.com/r/adventofcode/comments/3w6h3m/day_10_solutions/cxu8bl1/
func lookAndSay(s string) string {
	var current string
	var final bytes.Buffer
	var count int
	for i, _ := range s {
		switch {
		case count == 0:
			current = string(s[i])
			count++
		case i > 0 && s[i] == s[i-1]:
			count++
		default:
			final.WriteString(strconv.Itoa(count))
			final.WriteString(current)
			current = string(s[i])
			count = 1
		}
	}
	final.WriteString(strconv.Itoa(count))
	final.WriteString(current)
	return final.String()
}
