package main

import "fmt"

func main() {
	x := 1
	y := 1
	value := 20151125

	for true {
		if y == 2978 && x == 3083 {
			fmt.Printf("(%d, %d) -> %d \n", x, y, value)
			break
		}

		value = (value * 252533) % 33554393
		y--
		x++
		if y < 1 {
			y = x
			x = 1
		}
	}
}
