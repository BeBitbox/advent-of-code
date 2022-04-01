package main

import (
	"bufio"
	"fmt"
	"os"
)

func main() {
	input, err := os.Open("src/main/go/2015/day03/input.txt")
	if err != nil {
		panic(err)
	}
	defer input.Close()
	scanner := bufio.NewScanner(input)
	scanner.Scan()

	input0 := "^v"
	fmt.Printf("input: %s is %d and with robot %d\n", input0, walkAround(input0), walkAroundWithRobotSanta(input0))
	input1 := ">>"
	fmt.Printf("input: %s is %d and with robot %d\n", input1, walkAround(input1), walkAroundWithRobotSanta(input1))
	input2 := "^>v<"
	fmt.Printf("input: %s is %d and with robot %d\n", input2, walkAround(input2), walkAroundWithRobotSanta(input2))
	input3 := "^v^v^v^v^v"
	fmt.Printf("input: %s is %d and with robot %d\n", input3, walkAround(input3), walkAroundWithRobotSanta(input3))
	fmt.Printf("input part 1:%d and with robot %d\n", walkAround(scanner.Text()), walkAroundWithRobotSanta(scanner.Text()))
}

func walkAround(input string) int {
	x := 0
	y := 0
	visited := map[string]int{}
	input = "0" + input

	for _, char := range input {
		x, y = determineDirection(char, x, y)
		house := fmt.Sprintf("%dx%d", x, y)
		visited[house] = visited[house] + 1
	}

	return len(visited)
}

func walkAroundWithRobotSanta(input string) int {
	x := 0
	y := 0
	robotX := 0
	robotY := 0
	visited := map[string]int{}
	input = "00" + input

	for i := 0; i < len(input); i += 2 {
		x, y = determineDirection(int32(input[i]), x, y)
		house := fmt.Sprintf("%dx%d", x, y)
		visited[house] = visited[house] + 1

		robotX, robotY = determineDirection(int32(input[i+1]), robotX, robotY)
		house = fmt.Sprintf("%dx%d", robotX, robotY)
		visited[house] = visited[house] + 1
	}

	return len(visited)
}

func determineDirection(char int32, x int, y int) (int, int) {
	if char == '>' {
		x++
	} else if char == '<' {
		x--
	} else if char == 'v' {
		y++
	} else {
		y--
	}
	return x, y
}
