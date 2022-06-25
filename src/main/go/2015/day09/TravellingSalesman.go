package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
	"strconv"
	"strings"
)

func main() {
	exampleLine := "Tristram to Arbre = 14"
	exampleRoad := ToRoad(exampleLine)
	fmt.Printf("example %s becomes %v\n", exampleLine, exampleRoad)

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
	calculateLongestPaths(lines)
}

func calculatePaths(lines []string) {
	roads, _, paths := construct(lines)

	alreadyThreated := make(map[string]bool)
	//Brute Force
	for len(paths[0].missingCities) > 0 {
		newPaths := paths[0].WalkTheRoad(roads, alreadyThreated)
		alreadyThreated[paths[0].roads] = true
		newPaths = append(newPaths, paths[1:]...)
		sort.SliceStable(newPaths, func(i, j int) bool {
			return newPaths[i].distance < newPaths[j].distance
		})
		paths = newPaths
	}

	fmt.Printf("** Shortest: %s: %d and total paths: %d\n", paths[0].roads, paths[0].distance, len(paths))
}

func calculateLongestPaths(lines []string) {
	roads, cities, paths := construct(lines)

	alreadyThreated := make(map[string]bool)
	//Brute Force
	for len(paths[0].missingCities) > 0 {
		newPaths := paths[0].WalkTheRoad(roads, alreadyThreated)
		alreadyThreated[paths[0].roads] = true
		newPaths = append(newPaths, paths[1:]...)
		sort.SliceStable(newPaths, func(i, j int) bool {
			return newPaths[i].distance > newPaths[j].distance
		})
		paths = newPaths

		if len(paths[0].missingCities) == 0 {
			for i, path := range paths {
				if len(path.missingCities) > 0 && path.calculatePotential(cities) > paths[0].distance {
					p := paths[0]
					paths[0] = path
					paths[i] = p
					break
				}
			}
		}
	}

	fmt.Printf("** longest: %s: %d and total paths: %d\n", paths[0].roads, paths[0].distance, len(paths))
}

func construct(lines []string) ([]Road, map[string]int, []Path) {
	roads := make([]Road, 0, len(lines))
	for _, line := range lines {
		roads = append(roads, ToRoad(line))
	}

	cities := make(map[string]int)
	for _, road := range roads {
		if value, ok := cities[road.begin]; !ok || value < road.distance {
			cities[road.begin] = road.distance
		}
		if value, ok := cities[road.end]; !ok || value < road.distance {
			cities[road.end] = road.distance
		}
	}
	fmt.Printf("cities %v\n", cities)

	paths := make([]Path, 0)
	for _, road := range roads {
		paths = append(paths, InitialPaths(road, cities))
	}
	return roads, cities, paths
}

func (path Path) WalkTheRoad(roads []Road, treated map[string]bool) []Path {
	newPaths := make([]Path, 0)
	for _, road := range roads {
		if road.containsOneMissingCity(path.missingCities) && path.canAdd(road) {
			newPath := path.addRoad(road)
			if _, inside := treated[newPath.roads]; !inside {
				newPaths = append(newPaths, newPath)
			}
		}
	}
	return newPaths
}

func (path Path) calculatePotential(cities map[string]int) int {
	potential := 0
	for _, city := range path.missingCities {
		potential += cities[city]
	}
	return potential + path.distance
}

func InitialPaths(road Road, cities map[string]int) Path {
	missingCities := make([]string, 0, 0)
	for city, _ := range cities {
		if !road.contains(city) {
			missingCities = append(missingCities, city)
		}
	}

	return Path{
		missingCities: missingCities,
		roads:         road.begin + " -> " + road.end,
		distance:      road.distance,
		begin:         road.begin,
		end:           road.end,
	}
}

type Road struct {
	begin    string
	end      string
	distance int
}

type Path struct {
	missingCities []string
	roads         string
	distance      int
	begin         string
	end           string
}

func ToRoad(line string) Road {
	split := strings.Split(line, " to ")
	split2 := strings.Split(split[1], " = ")
	distance, e := strconv.Atoi(split2[1])
	if e != nil {
		panic(e)
	}

	return Road{
		begin:    split[0],
		end:      split2[0],
		distance: distance,
	}
}

func (road Road) contains(city string) bool {
	return road.end == city || road.begin == city
}

func (road Road) containsOneMissingCity(cities []string) bool {
	counter := 0
	for _, city := range cities {
		if road.contains(city) {
			counter++
		}
	}
	return counter == 1
}

func (path Path) canAdd(road Road) bool {
	if path.begin == road.begin && path.end != road.end {
		return true
	}
	if path.begin == road.end && path.end != road.begin {
		return true
	}
	if path.end == road.end && path.begin != road.begin {
		return true
	}
	if path.end == road.begin && path.begin != road.end {
		return true
	}
	return false
}

func (path Path) addRoad(road Road) Path {
	addToEnd := false
	begin := road.begin
	end := road.end

	if road.begin == path.end {
		addToEnd = true
	} else if road.end == path.end {
		addToEnd = true
		begin = road.end
		end = road.begin
	} else if road.begin == path.begin {
		begin = road.end
		end = road.begin
	}

	if addToEnd {
		return Path{
			missingCities: remove(path.missingCities, end),
			roads:         path.roads + " -> " + end,
			distance:      path.distance + road.distance,
			begin:         path.begin,
			end:           end,
		}
	} else {
		return Path{
			missingCities: remove(path.missingCities, begin),
			roads:         begin + " -> " + path.roads,
			distance:      path.distance + road.distance,
			begin:         begin,
			end:           path.end,
		}
	}
}

func remove(slice []string, toRemove string) []string {
	copied := make([]string, 0)
	for _, s := range slice {
		if s != toRemove {
			copied = append(copied, s)
		}
	}
	return copied
}
