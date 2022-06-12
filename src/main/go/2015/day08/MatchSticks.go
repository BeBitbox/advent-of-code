package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strings"
)

func main() {
	testExample(`"en\\m\"kxpq\"wpb\\\""`)
	testExample(`""`)
	testExample(`"abc"`)
	testExample(`"aaa\"aaa"`)
	testExample(`"\x27"`)
	testExample(`"a\x27b"`)

	input, err := os.Open("src/main/go/2015/day08/input.txt")
	if err != nil {
		panic(err)
	}
	defer input.Close()
	scanner := bufio.NewScanner(input)

	totalCode := 0
	totalMemory := 0
	totalEscaped := 0
	for scanner.Scan() {
		text := scanner.Text()
		code, memory, escaped := getSizes(text)
		totalCode += code
		totalMemory += memory
		totalEscaped += escaped
	}
	fmt.Printf("Difference code-memory:%v  and difference code-escaped:%v\n", totalCode-totalMemory, totalEscaped-totalCode)
}

func testExample(example string) {
	code, memory, escaped := getSizes(example)
	fmt.Printf("solved example %v (%d, %d, %d)\n", example, code, memory, escaped)
}

func getSizes(input string) (int, int, int) {
	trimmed := strings.Trim(input, "\"")
	lengthMemory := 0
	for i := 0; i < len(trimmed); i++ {
		if trimmed[i] == '\\' {
			if i+1 < len(trimmed) {
				switch trimmed[i+1] {
				case '"', '\'', '\\':
					i += 1
				case 'x':
					i += 3
				default:
					log.Panicf("invalid escape sequence \\%c", trimmed[i+1])
				}
			}
		}
		lengthMemory++
	}

	lengthEscaped := 2
	for i := 0; i < len(input); i++ {
		if input[i] == '\\' || input[i] == '"' {
			lengthEscaped++
		}
		lengthEscaped++
	}
	return len(input), lengthMemory, lengthEscaped
}
