package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main() {
	example := "457 -> aab"
	instruction := ToInstruction(example)
	fmt.Printf("Example %s, Instruction %v\n", example, instruction)

	exampleInput := append(make([]string, 0),
		"123 -> x",
		"x AND y -> d",
		"x OR y -> e",
		"x LSHIFT 2 -> f",
		"456 -> y",
		"y RSHIFT 2 -> g",
		"NOT x -> h",
		"NOT y -> i",
		"i -> prot")
	mapping := CreateMappingTable(exampleInput)
	fmt.Printf("solved example %v\n", mapping)

	exampleInput = append(make([]string, 0),
		"65535 AND y -> and",
		"17 -> y",
	)
	mapping = CreateMappingTable(exampleInput)
	fmt.Printf("solved example %v\n", mapping)

	input, err := os.Open("src/main/go/2015/day07/input.txt")
	if err != nil {
		panic(err)
	}
	defer input.Close()
	scanner := bufio.NewScanner(input)

	inputLines := make([]string, 0)
	for scanner.Scan() {
		text := scanner.Text()
		inputLines = append(inputLines, text)
	}
	mapping = CreateMappingTable(inputLines)
	fmt.Printf("solved a: %d part1\n %v\n", mapping["a"], mapping)

	inputLines2 := make([]string, 0)
	for _, line := range inputLines {
		if strings.HasSuffix(line, "-> b") {
			inputLines2 = append(inputLines2, fmt.Sprintf("%d -> b", mapping["a"]))
		} else {
			inputLines2 = append(inputLines2, line)
		}
	}
	mapping = CreateMappingTable(inputLines2)
	fmt.Printf("solved a: %d\n part2 %v\n", mapping["a"], mapping)
}

func CreateMappingTable(exampleInput []string) map[string]uint16 {
	exampleInstructions := make([]Instruction, len(exampleInput))
	for index, input := range exampleInput {
		exampleInstructions[index] = ToInstruction(input)
	}
	mapping := map[string]uint16{}
	for len(exampleInstructions) != 0 {
		needsToBeSolved := make([]Instruction, 0)

		for _, instr := range exampleInstructions {
			resolved1, value1 := ContainsValue(mapping, instr.variable1)

			resolved2 := false
			var value2 uint16 = 0
			if instr.variable2 == "" {
				resolved2 = true
			} else {
				resolved2, value2 = ContainsValue(mapping, instr.variable2)
			}

			if resolved1 && resolved2 {
				uint16Result := instr.perform(value1, value2)
				mapping[instr.gate] = uint16Result
			} else {
				needsToBeSolved = append(needsToBeSolved, instr)
			}
		}
		exampleInstructions = needsToBeSolved
	}
	return mapping
}

func ContainsValue(mapping map[string]uint16, variable string) (bool, uint16) {
	value, inside := mapping[variable]
	if inside {
		return true, value
	} else {
		parsed, e := strconv.Atoi(variable)
		if e == nil {
			return true, uint16(parsed)
		}
	}
	return false, 0
}

type Instruction struct {
	gate                 string
	variable1, variable2 string
	perform              func(int1 uint16, int2 uint16) uint16
}

func ToInstruction(line string) Instruction {
	fields := strings.Fields(line)

	if len(fields) == 3 {
		return Instruction{variable1: fields[0], gate: fields[2], perform: func(int1 uint16, int2 uint16) uint16 {
			return int1
		}}
	} else if len(fields) == 4 {
		return Instruction{variable1: fields[1], gate: fields[3], perform: func(int1 uint16, int2 uint16) uint16 {
			return ^int1
		}}
	} else if len(fields) == 5 {
		return Instruction{variable1: fields[0], variable2: fields[2], gate: fields[4], perform: func(i1 uint16, i2 uint16) uint16 {
			switch fields[1] {
			case "AND":
				return i1 & i2
			case "OR":
				return i1 | i2
			case "LSHIFT":
				return i1 << i2
			case "RSHIFT":
				return i1 >> i2
			default:
				panic(fields)
			}
		}}
	}
	panic(line)
}
