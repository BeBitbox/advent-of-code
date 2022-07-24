package main

import (
	"fmt"
)

func main() {
	check := []rune("abcdefgh")
	checkFalse := []rune("arcrergr")
	fmt.Printf("Completed %s:%v and %s:%v\n", string(check), Check(check), string(checkFalse), Check(checkFalse))
	check = IncrementLine(check)
	fmt.Printf("Increment line %s\n", string(check))

	example1 := []rune("abcdefgh")
	example1Str := string(example1)
	for !Check(example1) {
		example1 = IncrementLine(example1)
	}
	fmt.Printf("Example 1 %s -> %s\n", example1Str, string(example1))

	example2 := []rune("ghjaaaaa")
	example2Str := string(example2)
	for !Check(example2) {
		example2 = IncrementLine(example2)
	}
	fmt.Printf("Example 2 %s -> %s\n", example2Str, string(example2))

	part1 := []rune("cqjxjnds")
	part1Str := string(part1)
	for !Check(part1) {
		part1 = IncrementLine(part1)
	}
	fmt.Printf("Part 1 %s -> %s\n", part1Str, string(part1))
	part1 = IncrementLine(part1)
	for !Check(part1) {
		part1 = IncrementLine(part1)
	}
	fmt.Printf("Part 2 %s -> %s\n", part1Str, string(part1))

}

func Check(line []rune) bool {
	checkOne := false
	pair1 := 0
	pair2 := 0
	for i := 0; i < 8; i++ {
		current := line[i]
		if i < 6 && int(line[i+1]-current) == 1 && int(line[i+2]-line[i+1]) == 1 {
			checkOne = true
		}
		if current == 'i' || current == 'o' || current == 'l' {
			return false
		}
		if i > 0 && line[i-1] == current {
			if pair1 == 0 {
				pair1 = i
			} else if pair1+1 < i && pair2 == 0 {
				pair2 = i
			}
		}
	}
	return checkOne && pair1 > 0 && pair2 > 0
}

func IncrementLine(line []rune) []rune {
	for i := 7; i >= 0; i-- {
		current := line[i]
		if current == 'z' {
			line[i] = 'a'
		} else {
			current = current + 1
			if current == 'i' {
				current = 'j'
			} else if current == 'l' {
				current = 'm'
			} else if current == 'o' {
				current = 'p'
			}
			line[i] = current
			return line
		}
	}
	panic("No more lines to add")
}
