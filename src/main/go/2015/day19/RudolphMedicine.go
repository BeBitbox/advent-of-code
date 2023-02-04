package main

import (
	"bufio"
	"container/heap"
	"fmt"
	"math/rand"
	"os"
	"strings"
)

func main() {
	keys, values := makeExample()
	example := part1(keys, values, "HOH")
	fmt.Printf("Result String 1: %v\n", len(example))
	example2 := part1(keys, values, "HOHOHO")
	fmt.Printf("Result String 2: %v\n", len(example2))

	keys, values = readInput()
	input := "CRnCaSiRnBSiRnFArTiBPTiTiBFArPBCaSiThSiRnTiBPBPMgArCaSiRnTiMgArCaSiThCaSiRnFArRnSiRnFArTiTiBFArCaCaSiRnSiThCaCaSiRnMgArFYSiRnFYCaFArSiThCaSiThPBPTiMgArCaPRnSiAlArPBCaCaSiRnFYSiThCaRnFArArCaCaSiRnPBSiRnFArMgYCaCaCaCaSiThCaCaSiAlArCaCaSiRnPBSiAlArBCaCaCaCaSiThCaPBSiThPBPBCaSiRnFYFArSiThCaSiRnFArBCaCaSiRnFYFArSiThCaPBSiThCaSiRnPMgArRnFArPTiBCaPRnFArCaCaCaCaSiRnCaCaSiRnFYFArFArBCaSiThFArThSiThSiRnTiRnPMgArFArCaSiThCaPBCaSiRnBFArCaCaPRnCaCaPMgArSiRnFYFArCaSiThRnPBPMgAr"

	part1 := part1(keys, values, input)
	fmt.Printf("Result part 1: %v\n", len(part1))

	keys, values = makeExamplePart2()
	fmt.Printf("Result part2: %v\n", part2(keys, values, "HOH"))
	fmt.Printf("Result part2: %v\n", part2(keys, values, "HOHOHO"))
	// EXPONENTIAL  fmt.Printf("Result part2: %v\n", part2(keys, values, input))

	fmt.Printf("Result random part2: %v\n", part2Reversed(keys, values, "HOH"))
	fmt.Printf("Result random part2: %v\n", part2Reversed(keys, values, "HOHOHO"))
	fmt.Printf("Result random part2: %v\n", part2Reversed(keys, values, input))

}

func part2(keys []string, values []string, expected string) int {
	pq := make(PriorityQueue, 0)
	alreadyDone := make(map[string]bool, 0)
	pq.Push(&Item{value: "e", priority: 0})

	for true {
		popped := heap.Pop(&pq).(*Item)

		if popped.value == expected {
			return popped.priority
		}

		_, done := alreadyDone[popped.value]
		if !done {
			mutations := part1(keys, values, popped.value)
			for keyMutation, _ := range mutations {
				if len(keyMutation) <= len(expected) {
					pq.Push(&Item{value: keyMutation, priority: popped.priority + 1})
				}
			}
			alreadyDone[popped.value] = true
			heap.Init(&pq)
		}
	}

	panic("should not happen")
}

func randomPart2(keys []string, values []string, expected string) int {
	number := 0
	temp := expected

	for i := 0; i < 800; i++ {
		one := rand.Intn(len(keys))
		two := rand.Intn(len(keys))
		placeholder := keys[one]
		keys[one] = keys[two]
		keys[two] = placeholder
		placeholder = values[one]
		values[one] = values[two]
		values[two] = placeholder
	}

	inside := 0
	for temp != "e" {
		found := false
		for i, _ := range values {
			if keys[i] != "e" || len(temp) <= len(values[i]) {
				if strings.Contains(temp, values[i]) {
					temp = strings.Replace(temp, values[i], keys[i], 1)
					number++
					found = true
				}
			}
		}
		if !found || inside > 10000 {
			return 9999
		}
		inside++
	}
	return number
}

func part2Reversed(keys []string, values []string, expected string) int {
	minimum := 9999

	for minimum > 9900 {
		i2 := randomPart2(keys, values, expected)

		if i2 < minimum {
			minimum = i2
		}
	}
	return minimum
}

func makeExample() ([]string, []string) {
	keys := make([]string, 3)
	values := make([]string, 3)
	keys[0] = "H"
	values[0] = "HO"
	keys[1] = "H"
	values[1] = "OH"
	keys[2] = "O"
	values[2] = "HH"
	return keys, values
}

func makeExamplePart2() ([]string, []string) {
	keys := make([]string, 5)
	values := make([]string, 5)
	keys[0] = "H"
	values[0] = "HO"
	keys[1] = "H"
	values[1] = "OH"
	keys[2] = "O"
	values[2] = "HH"
	keys[3] = "e"
	values[3] = "H"
	keys[4] = "e"
	values[4] = "O"

	return keys, values
}

func readInput() ([]string, []string) {
	keys := make([]string, 0)
	values := make([]string, 0)

	input, err := os.Open("src/main/go/2015/day19/input.txt")
	if err != nil {
		panic(err)
	}
	defer input.Close()

	scanner := bufio.NewScanner(input)

	for scanner.Scan() {
		text := scanner.Text()
		split := strings.Split(text, " => ")
		keys = append(keys, split[0])
		values = append(values, split[1])
	}
	return keys, values
}

func part1(keys []string, values []string, input string) map[string]int {
	result := make(map[string]int)

	for i := 0; i < len(keys); i++ {
		key := keys[i]
		indicesOf := IndicesOf(input, key)

		for j := 0; j < len(indicesOf); j++ {
			index := indicesOf[j]
			value := values[i]
			part1 := input[:index]
			part2 := input[index+len(key):]
			newStr := part1 + value + part2
			result[newStr] = 1
		}
	}

	return result
}

func IndicesOf(input string, substr string) []int {
	indices := make([]int, 0)
	for i := 0; i < len(input)-len(substr)+1; i++ {
		contains := true
		for j := 0; j < len(substr) && contains; j++ {
			if input[i+j] != substr[j] {
				contains = false
			}
		}
		if contains {
			indices = append(indices, i)
		}
	}
	return indices
}

type Item struct {
	value    string
	priority int
	index    int
}

type PriorityQueue []*Item

func (pq PriorityQueue) Len() int { return len(pq) }

func (pq PriorityQueue) Less(i, j int) bool {
	// We want Pop to give us the highest, not lowest, priority so we use greater than here.
	return pq[i].priority > pq[j].priority
}

func (pq PriorityQueue) Swap(i, j int) {
	pq[i], pq[j] = pq[j], pq[i]
	pq[i].index = i
	pq[j].index = j
}

func (pq *PriorityQueue) Push(x interface{}) {
	n := len(*pq)
	item := x.(*Item)
	item.index = n
	*pq = append(*pq, item)
}

func (pq *PriorityQueue) Pop() interface{} {
	old := *pq
	n := len(old)
	item := old[n-1]
	item.index = -1 // for safety
	*pq = old[0 : n-1]
	return item
}

func (pq *PriorityQueue) update(item *Item, value string, priority int) {
	item.value = value
	item.priority = priority
	heap.Fix(pq, item.index)
}
