package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main() {
	example := "turn on 0,0 through 999,999"
	example2 := "toggle 0,0 through 999,0"
	example3 := "turn off 499,499 through 500,500"
	instruction := ToInstruction(example)
	fmt.Printf("Example %s in instruction %v\n", example, instruction)

	matrix := [1000][1000]int{}
	fmt.Printf("Number of lights in empty matrix %d\n", NumberOfLightsOn(matrix))
	matrix = instruction.part1(matrix)
	fmt.Printf("Number of lights in turned on matrix %d\n", NumberOfLightsOn(matrix))
	matrix = ToInstruction(example2).part1(matrix)
	fmt.Printf("Number of lights in toggled matrix first row %d\n", NumberOfLightsOn(matrix))
	matrix = ToInstruction(example3).part1(matrix)
	fmt.Printf("Number of lights in centered turned off matrix %d\n", NumberOfLightsOn(matrix))

	input, err := os.Open("src/main/go/2015/day06/input.txt")
	if err != nil {
		panic(err)
	}
	defer input.Close()
	scanner := bufio.NewScanner(input)

	grid := [1000][1000]int{}
	grid2 := [1000][1000]int{}
	for scanner.Scan() {
		text := scanner.Text()
		instruction := ToInstruction(text)
		grid = instruction.part1(grid)
		grid2 = instruction.part2(grid2)
	}
	fmt.Printf("Number of lights in centered turned off matrix %d and brightness %d\n", NumberOfLightsOn(grid), NumberOfLightsOn(grid2))
}

func (i Instruction) part1(matrix [1000][1000]int) [1000][1000]int {
	for x, row := range matrix {
		for y, element := range row {
			if x >= i.x1 && x <= i.x2 && y >= i.y1 && y <= i.y2 {
				switch i.action {
				case '+':
					matrix[x][y] = 1
					break
				case '-':
					matrix[x][y] = 0
					break
				case '!':
					matrix[x][y] = (element + 1) % 2
					break
				default:
					panic("unknown action")
				}
			}
		}
	}
	return matrix
}

func (i Instruction) part2(matrix [1000][1000]int) [1000][1000]int {
	for x, row := range matrix {
		for y, _ := range row {
			if x >= i.x1 && x <= i.x2 && y >= i.y1 && y <= i.y2 {
				switch i.action {
				case '+':
					matrix[x][y] += 1
					break
				case '-':
					matrix[x][y] -= 1
					if matrix[x][y] < 0 {
						matrix[x][y] = 0
					}
					break
				case '!':
					matrix[x][y] += 2
					break
				default:
					panic("unknown action")
				}
			}
		}
	}
	return matrix
}

type Instruction struct {
	x1, x2, y1, y2 int
	action         rune
}

func ToInstruction(line string) Instruction {
	fields := strings.Fields(line)
	var x1 = -1
	var x2, y1, y2 int
	for _, field := range fields {
		if strings.Contains(field, ",") {
			if x1 == -1 {
				x1, y1 = Split(field)
			} else {
				x2, y2 = Split(field)
			}
		}
	}
	return Instruction{x1: x1, y1: y1, x2: x2, y2: y2, action: ToAction(line)}
}

func Split(line string) (int, int) {
	splitted := strings.FieldsFunc(line, func(r rune) bool {
		return r == ','
	})
	a, _ := strconv.Atoi(splitted[0])
	b, _ := strconv.Atoi(splitted[1])
	return a, b
}

func ToAction(line string) rune {
	if strings.HasPrefix(line, "toggle") {
		return '!'
	} else if strings.HasPrefix(line, "turn on") {
		return '+'
	} else if strings.HasPrefix(line, "turn off") {
		return '-'
	} else {
		panic("AAAAAH " + line)
	}
}

func NumberOfLightsOn(matrix [1000][1000]int) int {
	counter := 0
	for _, row := range matrix {
		for _, element := range row {
			counter += element
		}
	}
	return counter
}
