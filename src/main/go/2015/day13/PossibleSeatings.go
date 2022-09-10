package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main() {
	exampleLine := "Alice would gain 54 happiness units by sitting next to Bob."
	examplePath := ToPath(exampleLine)
	fmt.Print(examplePath.toString())

	exampleLine2 := "Bob would lose 62 happiness units by sitting next to Alice."
	examplePath2 := ToPath(exampleLine2)
	fmt.Print(examplePath2.toString())

	paths := make([]Path, 0)
	paths = append(paths, examplePath)
	paths = append(paths, examplePath2)
	participants := GetAllParticipants(paths)
	fmt.Println(participants)

	first := CreateFirst(participants)
	first.Populate(paths, first.current)
	fmt.Println(first.HighestHappiness())

	Part1("example.txt", false)
	Part1("example.txt", true)
	Part1("input.txt", false)
	Part1("input.txt", true)

	/*
		exampleLines := []string{"London to Dublin = 464", "London to Belfast = 518", "Dublin to Belfast = 141"}
		calculatePaths(exampleLines)
		calculateLongestPaths(exampleLines)

		input, err := os.Open("src/main/go/2015/day09/input.txt")
		if err != nil {
			panic(err)
		}
		defer input.Close()
		scanner := bufio.NewScanner(input)

		lines := make([]string, 0)
		for scanner.Scan() {
			text := scanner.Text()
			lines = append(lines, text)
		}
		calculatePaths(lines)
		calculateLongestPaths(lines)/*/
}

func Part1(fileName string, includeSelf bool) {
	input, err := os.Open("src/main/go/2015/day13/" + fileName)
	if err != nil {
		panic(err)
	}
	defer input.Close()
	scanner := bufio.NewScanner(input)

	paths := make([]Path, 0)
	for scanner.Scan() {
		text := scanner.Text()
		paths = append(paths, ToPath(text))
	}

	participants := GetAllParticipants(paths)

	if includeSelf {
		for participant, _ := range participants {
			newFrom := Path{
				from:  "Bitbox",
				to:    participant,
				happy: 0,
			}
			paths = append(paths, newFrom)
			newTo := Path{
				from:  participant,
				to:    "Bitbox",
				happy: 0,
			}
			paths = append(paths, newTo)
		}

		participants["Bitbox"] = empty
	}

	fmt.Printf("%s participants:%v \n", fileName, participants)

	first := CreateFirst(participants)
	first.Populate(paths, first.current)
	fmt.Println(first.HighestHappiness())
}

func (node TreeNode) HighestHappiness() int {
	if len(node.children) == 0 {
		return node.distance
	}
	maximum := -1000
	for _, child := range node.children {
		childHappiness := child.HighestHappiness()
		if childHappiness > maximum {
			maximum = childHappiness
		}
	}
	return maximum
}

func CreateFirst(participants map[string]void) TreeNode {
	first := true
	current := ""
	missing := make([]string, 0)

	for participant := range participants {
		if first {
			current = participant
			first = false
		} else {
			missing = append(missing, participant)
		}
	}
	return TreeNode{
		current:  current,
		missing:  missing,
		distance: 0,
		children: make([]TreeNode, 0),
	}
}

func (node *TreeNode) Populate(paths []Path, first string) {
	newChildren := make([]TreeNode, 0)
	for _, missing := range node.missing {
		newMissing := make([]string, 0)
		for _, m := range node.missing {
			if m != missing {
				newMissing = append(newMissing, m)
			}
		}
		newDistance := node.distance
		for _, path := range paths {
			if len(newMissing) == 0 {
				if path.from == missing && path.to == first {
					newDistance += path.happy
				}
				if path.to == missing && path.from == first {
					newDistance += path.happy
				}
			}

			if path.from == node.current && path.to == missing {
				newDistance += path.happy
			}
			if path.to == node.current && path.from == missing {
				newDistance += path.happy
			}

		}

		newNode := TreeNode{
			current:  missing,
			missing:  newMissing,
			distance: newDistance,
			children: make([]TreeNode, 0),
		}
		newChildren = append(newChildren, newNode)
	}

	for _, child := range newChildren {
		child.Populate(paths, first)
		node.children = append(node.children, child)
	}
}

type TreeNode struct {
	current  string
	missing  []string
	distance int
	children []TreeNode
}

type void struct{}

var empty void

func GetAllParticipants(paths []Path) map[string]void {
	set := make(map[string]void)

	for _, path := range paths {
		set[path.from] = empty
	}
	return set
}

func (p Path) toString() string {
	return fmt.Sprintf("%s -> %s (%d)\n", p.from, p.to, p.happy)
}

func ToPath(line string) Path {
	split := strings.Split(line, " ")
	to := strings.TrimSuffix(split[len(split)-1], ".")
	happy, err := strconv.Atoi(split[3])
	if err != nil {
		panic(err)
	}
	if split[2] == "lose" {
		happy *= -1
	}
	return Path{
		from:  split[0],
		to:    to,
		happy: happy,
	}
}

type Path struct {
	from  string
	to    string
	happy int
}
