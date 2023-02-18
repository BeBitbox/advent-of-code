package main

import (
	"fmt"
)

func main() {
	a := 4591
	b := 0

	a, b = calculate(a, b)
	fmt.Printf("part 1? %v\n", b)

	a = 113383
	b = 0
	a, b = calculate(a, b)
	fmt.Printf("part 2? %v\n", b)

}

func calculate(a int, b int) (int, int) {
	for a != 1 {
		b++
		if a%2 == 0 {
			a /= 2
		} else {
			a *= 3
			a++
		}
	}
	return a, b
}
