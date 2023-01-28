package main

import (
	"bufio"
	"fmt"
	"os"
)

func main() {
	example := fillExampleMatrix()
	on := 0
	for i := 0; i < 5; i++ {
		example, on = tickExample(example)
		fmt.Printf("Example %v -> %d\n", example, on)
	}

	matrix := fillMatrix()

	for i := 0; i < 100; i++ {
		matrix, on = tick(matrix)
		fmt.Printf("Lights on %d\n", on)
	}
}

func tick(matrix [100][100]int) ([100][100]int, int) {
	result := [100][100]int{}
	numberOn := 0

	for x, row := range matrix {
		for y, element := range row {
			if (x == 0 && y == 0) || (x == 99 && y == 0) || (x == 99 && y == 99) || (x == 0 && y == 99) {
				result[x][y] = 1
				numberOn++
			} else {
				lightsOn := lightsOn(matrix, x, y)
				if element == 1 {
					if lightsOn == 2 || lightsOn == 3 {
						result[x][y] = 1
						numberOn++
					} else {
						result[x][y] = 0
					}
				} else {
					if lightsOn == 3 {
						result[x][y] = 1
						numberOn++
					} else {
						result[x][y] = 0
					}
				}
			}
		}
	}
	return result, numberOn
}

func tickExample(matrix [6][6]int) ([6][6]int, int) {
	result := [6][6]int{}
	numberOn := 0

	for x, row := range matrix {
		for y, element := range row {
			if (x == 0 && y == 0) || (x == 5 && y == 0) || (x == 5 && y == 5) || (x == 0 && y == 5) {
				result[x][y] = 1
				numberOn++
			} else {
				lightsOn := lightsOnExample(matrix, x, y)
				if element == 1 {
					if lightsOn == 2 || lightsOn == 3 {
						result[x][y] = 1
						numberOn++
					} else {
						result[x][y] = 0
					}
				} else {
					if lightsOn == 3 {
						result[x][y] = 1
						numberOn++
					} else {
						result[x][y] = 0
					}
				}
			}
		}
	}
	return result, numberOn
}

func fillMatrix() [100][100]int {
	matrix := [100][100]int{}
	input, err := os.Open("src/main/go/2015/day18/input.txt")
	if err != nil {
		panic(err)
	}
	defer input.Close()

	scanner := bufio.NewScanner(input)
	i := 0
	for scanner.Scan() {
		text := scanner.Text()
		for pos, char := range text {
			if char == '#' {
				matrix[i][pos] = 1
			} else {
				matrix[i][pos] = 0
			}
		}
		i++
	}

	return matrix
}

func fillExampleMatrix() [6][6]int {
	return [6][6]int{
		{1, 1, 0, 1, 0, 1},
		{0, 0, 0, 1, 1, 0},
		{1, 0, 0, 0, 0, 1},
		{0, 0, 1, 0, 0, 0},
		{1, 0, 1, 0, 0, 1},
		{1, 1, 1, 1, 0, 1},
	}
}

func lightsOn(matrix [100][100]int, x int, y int) int {
	lightsOn := 0
	if x > 0 {
		lightsOn += matrix[x-1][y]
		if y > 0 {
			lightsOn += matrix[x-1][y-1]
		}
		if y < 99 {
			lightsOn += matrix[x-1][y+1]
		}
	}
	if y > 0 {
		lightsOn += matrix[x][y-1]
	}
	if y < 99 {
		lightsOn += matrix[x][y+1]
	}
	if x < 99 {
		lightsOn += matrix[x+1][y]
		if y > 0 {
			lightsOn += matrix[x+1][y-1]
		}
		if y < 99 {
			lightsOn += matrix[x+1][y+1]
		}
	}
	return lightsOn
}

func lightsOnExample(matrix [6][6]int, x int, y int) int {
	lightsOn := 0
	if x > 0 {
		lightsOn += matrix[x-1][y]
		if y > 0 {
			lightsOn += matrix[x-1][y-1]
		}
		if y < 5 {
			lightsOn += matrix[x-1][y+1]
		}
	}
	if y > 0 {
		lightsOn += matrix[x][y-1]
	}
	if y < 5 {
		lightsOn += matrix[x][y+1]
	}
	if x < 5 {
		lightsOn += matrix[x+1][y]
		if y > 0 {
			lightsOn += matrix[x+1][y-1]
		}
		if y < 5 {
			lightsOn += matrix[x+1][y+1]
		}
	}
	return lightsOn
}
