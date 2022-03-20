package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main() {
	input, err := os.Open("src/main/go/2015/day02/input.txt")
	if err != nil {
		panic(err)
	}
	defer input.Close()
	scanner := bufio.NewScanner(input)

	input1 := "2x3x4"
	expectedWrap1 := 58
	expectedRibbon1 := 34
	box1 := convertToBox(input1)
	fmt.Printf("Input 1: %s results in wrapping (expected: %d, actual %d) and ribbon (expected: %d, actual: %d)\n",
		input1, expectedWrap1, calculateWrappingSize(box1), expectedRibbon1, calculaterRibbonLength(box1))

	input2 := "1x1x10"
	expectedWrap2 := 43
	expectedRibbon2 := 14
	box2 := convertToBox(input2)
	fmt.Printf("Input 2: %s results in wrapping (expected: %d, actual %d) and ribbon (expected: %d, actual: %d)\n",
		input2, expectedWrap2, calculateWrappingSize(box2), expectedRibbon2, calculaterRibbonLength(box2))

	totalWrap := 0
	totalRibbon := 0
	for scanner.Scan() {
		box := convertToBox(scanner.Text())
		totalWrap += calculateWrappingSize(box)
		totalRibbon += calculaterRibbonLength(box)
	}
	fmt.Printf("Input File part 1: %d and part 2: %d\n", totalWrap, totalRibbon)
}

type Box struct {
	length int
	width  int
	height int
}

func convertToBox(input string) Box {
	split := strings.Split(input, "x")
	l, _ := strconv.Atoi(split[0])
	w, _ := strconv.Atoi(split[1])
	h, _ := strconv.Atoi(split[2])
	return Box{
		length: l,
		width:  w,
		height: h,
	}
}

func calculateWrappingSize(box Box) int {
	lw := box.length * box.width
	lh := box.length * box.height
	wh := box.width * box.height
	slack, _ := minOf(lw, lh, wh)
	return 2*lw + 2*lh + 2*wh + slack
}

func calculaterRibbonLength(box Box) int {
	min, min2 := minOf(box.length, box.width, box.height)
	return 2*min + 2*min2 + box.length*box.width*box.height
}

func minOf(a int, b int, c int) (int, int) {
	var min int
	var min2 int

	if b < a {
		min = b
		min2 = a
	} else {
		min = a
		min2 = b
	}
	if c < min {
		min2 = min
		min = c
	} else if c < min2 {
		min2 = c
	}

	return min, min2
}
